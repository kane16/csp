package pl.delukesoft.csp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.delukesoft.csp.games.dataextraction.CSPDataExtractorService;
import pl.delukesoft.csp.games.CSPGameSimulation;
import pl.delukesoft.csp.games.gamerules.SkyscrapperRules;
import pl.delukesoft.csp.games.models.SkyscraperItem;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkyscrapperTest {

    @Autowired
    CSPDataExtractorService dataExtractorService;

    @Test
    public void testSkyscrapperForConstraints() throws FileNotFoundException {
        SkyscraperItem skyscraperItem = dataExtractorService.getScascraperItemFromFile("skyscrapper_5_3");
        SkyscrapperRules skyscrapperRules = new SkyscrapperRules(skyscraperItem.bottomBound, skyscraperItem.topBound,
                skyscraperItem.leftBound, skyscraperItem.rightBound, skyscraperItem.board);
        CSPGameSimulation cspGameSimulation = new CSPGameSimulation(skyscrapperRules);
        List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
        for(int[][] solution: solutions){
            System.out.println(Arrays.toString(solution[3]));
        }
    }

}
