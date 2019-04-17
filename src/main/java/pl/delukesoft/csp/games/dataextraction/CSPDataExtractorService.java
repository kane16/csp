package pl.delukesoft.csp.games.dataextraction;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.models.FutoshikiItem;
import pl.delukesoft.csp.games.models.SkyscraperItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CSPDataExtractorService {

    public SkyscraperItem getScascraperItemFromFile(String filename) throws FileNotFoundException {
        SkyscraperItem skyscraperItem = new SkyscraperItem();
        Scanner sc = new Scanner(getDataInputFile(filename));
        skyscraperItem.size = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<4;i++){
            String[] items = sc.nextLine().split(";");
            List<Integer> boundValues = new ArrayList<>();
            for(int j=1;j<items.length;j++){
                boundValues.add(Integer.parseInt(items[j]));
            }
            switch (items[0]) {
                case "G":
                    skyscraperItem.topBound = boundValues;
                    break;
                case "D":
                    skyscraperItem.bottomBound = boundValues;
                    break;
                case "P":
                    skyscraperItem.rightBound = boundValues;
                    break;
                case "L":
                    skyscraperItem.leftBound = boundValues;
                    break;
            }
        }
        skyscraperItem.board = new int[skyscraperItem.size][skyscraperItem.size];
        return skyscraperItem;
    }

    public FutoshikiItem getFutoshikiItemFromFile(String filename) throws FileNotFoundException {
        FutoshikiItem futoshikiItem = new FutoshikiItem();
        Scanner sc = new Scanner(getDataInputFile(filename));
        futoshikiItem.size = sc.nextInt();
        sc.nextLine();
        sc.nextLine();
        int[][] futoshikiContent = new int[futoshikiItem.size][futoshikiItem.size];
        for(int i = 0; i<futoshikiItem.size; i++){
            int[] futoshikiRow = new int[futoshikiItem.size];
            String nextLine = sc.nextLine();
            String[] numbers = nextLine.split(";");
            for(int j=0 ; j<numbers.length; j++){
                futoshikiRow[j] = Integer.parseInt(numbers[j]);
            }
            futoshikiContent[i] = futoshikiRow;
        }
        futoshikiItem.board = futoshikiContent;
        sc.nextLine();
        ArrayList<String> constraints = new ArrayList<>();
        while(sc.hasNextLine()){
            String constraint = replaceLettersWithIndexes(sc.nextLine());
            constraints.add(constraint);
        }
        futoshikiItem.constraints = constraints;
        return futoshikiItem;
    }

    private String replaceLettersWithIndexes(String constraint) {
        return constraint.replace("A", "1").replace("B", "2")
                .replace("C", "3").replace("D", "4").replace("E", "5")
                .replace("F", "6").replace("G", "7").replace("H", "8")
                .replace("I", "9");
    }

    public File getDataInputFile(String filename){
        File file = null;
        try{
            file = new File(Objects.requireNonNull(CSPDataExtractorService.class.getClassLoader()
                    .getResource("testdata/" + filename + ".txt"))
                    .getFile());
            file = new File(file.getAbsolutePath().replace("%20", " "));
        }catch (NullPointerException exc){
            System.out.println("Nie znaleziono pliku");
        }
        return file;
    }

    public List<String> getAllFilesOfGameFromInputDataFolder(String gameName){
        File file = null;
        try{
            file = new File(Objects.requireNonNull(CSPDataExtractorService.class.getClassLoader()
                    .getResource("testdata"))
                    .getFile());
            file = new File(file.getAbsolutePath().replace("%20", " "));
        }catch (NullPointerException exc){
            System.out.println("Nie znaleziono folderu");
        }
        return Arrays.stream(file.listFiles())
                .filter(scenario -> scenario.getName().contains(gameName))
                .map(scenario -> scenario.getName().replace(".txt", ""))
                .collect(Collectors.toList());
    }

}
