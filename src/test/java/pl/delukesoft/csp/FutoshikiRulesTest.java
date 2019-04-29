package pl.delukesoft.csp;

import org.apache.logging.log4j.message.ReusableMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.delukesoft.csp.games.CSPGameSimulation;
import pl.delukesoft.csp.games.dataextraction.CSPDataExtractorService;
import pl.delukesoft.csp.games.gamerules.FutoshikiRules;
import pl.delukesoft.csp.games.gamerules.SkyscrapperRules;
import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.heuristic.LeastConstrainingFirstHeuristic;
import pl.delukesoft.csp.games.heuristic.MostConstrainingFirstHeuristic;
import pl.delukesoft.csp.games.heuristic.RandomHeuristic;
import pl.delukesoft.csp.games.models.FutoshikiItem;
import pl.delukesoft.csp.games.models.SkyscraperItem;
import pl.delukesoft.csp.games.models.entities.Result;
import pl.delukesoft.csp.games.solutionsearch.BacktrackingAlgorithm;
import pl.delukesoft.csp.games.solutionsearch.ForwardCheckingAlgorithm;
import pl.delukesoft.csp.games.solutionsearch.SearchSolutionAlgorithm;
import pl.delukesoft.csp.repository.ResultRepository;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FutoshikiRulesTest {

    @Autowired
    CSPDataExtractorService dataExtractorService;

    @Autowired
    ResultRepository resultRepository;

    @Test
    public void testFutoshikiForConstraints() throws FileNotFoundException {
        FutoshikiItem item = dataExtractorService.getFutoshikiItemFromFile("test_futo_6_1");
        MostConstrainingFirstHeuristic heuristic = new MostConstrainingFirstHeuristic(item);
        FutoshikiRules rules = new FutoshikiRules(item.constraints, item.board, heuristic);
        BacktrackingAlgorithm backtrackingAlgorithm = new BacktrackingAlgorithm(rules, heuristic);
        CSPGameSimulation cspGameSimulation = new CSPGameSimulation(rules, heuristic, backtrackingAlgorithm);
        List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
        for(int[][] solution: solutions){
            System.out.println("[");
            for(int i=0 ; i<solution.length;i++)
                System.out.println(Arrays.toString(solution[i]));
            System.out.println("]");
        }
    }

    @Test
    public void testFutoshikiForConstraintsForwardChecking() throws FileNotFoundException {
        List<List<String>> games = List.of(dataExtractorService.getAllFilesOfGameFromInputDataFolder("futo"),
                dataExtractorService.getAllFilesOfGameFromInputDataFolder("sky"));
        List<String> heuristics = List.of("random", "most", "least");
        List<String> algorithms = List.of("back", "forward");
        for (String game : games.get(0)) {
            for(String heuristicName: heuristics){
                for(String algorithmName: algorithms){
                    if(game.contains("6") && heuristicName.equals("least"))
                        continue;
                    if(game.contains("7") && (heuristicName.equals("least") || algorithmName.equals("back")
                            || heuristicName.equals("random")) )
                        continue;
                    System.out.println(game);
                    System.out.println(heuristicName);
                    System.out.println(algorithmName);
                    FutoshikiItem item = dataExtractorService.getFutoshikiItemFromFile(game);
                    Heuristic heuristic;
                    if(heuristicName.equals("random")){
                        heuristic = new RandomHeuristic(item);
                    }else if(heuristicName.equals("least")){
                        heuristic = new LeastConstrainingFirstHeuristic(item);
                    }else {
                        heuristic = new MostConstrainingFirstHeuristic(item);
                    }
                    FutoshikiRules rules = new FutoshikiRules(item.constraints, item.board, heuristic);
                    SearchSolutionAlgorithm algorithm;
                    if(algorithmName.equals("forward")){
                        algorithm = new ForwardCheckingAlgorithm(rules, heuristic);
                    }else algorithm = new BacktrackingAlgorithm(rules, heuristic);
                    CSPGameSimulation cspGameSimulation = new CSPGameSimulation(rules, heuristic, algorithm);
                    List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
                    Result result = new Result();
                    result.setAlgorithmType(algorithmName);
                    result.setGame("Futoshiki");
                    result.setGameName(Integer.parseInt(""+game.charAt(10)));
                    result.setVariant(Integer.parseInt(""+game.charAt(12)));
                    result.setHeuristic(heuristicName);
                    result.setBacktrackNumber((long)algorithm.backtrackCounter);
                    result.setTime(algorithm.time);
                    result.setVisitedNodes((long)algorithm.nodesCounter);
                    resultRepository.save(result);

                }
            }
        }

        for (String game : games.get(1)) {
            for(String heuristicName: heuristics){
                for(String algorithmName: algorithms){
                    System.out.println(game);
                    System.out.println(heuristicName);
                    System.out.println(algorithmName);
                    SkyscraperItem item = dataExtractorService.getScascraperItemFromFile(game);
                    Heuristic heuristic;
                    if(heuristicName.equals("random")){
                        heuristic = new RandomHeuristic(item);
                    }else if(heuristicName.equals("least")){
                        heuristic = new LeastConstrainingFirstHeuristic(item);
                    }else {
                        heuristic = new MostConstrainingFirstHeuristic(item);
                    }
                    SkyscrapperRules rules = new SkyscrapperRules(item.bottomBound, item.topBound, item.leftBound,
                            item.rightBound, item.board, heuristic);
                    SearchSolutionAlgorithm algorithm;
                    if(algorithmName.equals("forward")){
                        algorithm = new ForwardCheckingAlgorithm(rules, heuristic);
                    }else algorithm = new BacktrackingAlgorithm(rules, heuristic);
                    CSPGameSimulation cspGameSimulation = new CSPGameSimulation(rules, heuristic, algorithm);
                    List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
                    Result result = new Result();
                    result.setAlgorithmType(algorithmName);
                    result.setGame("Skyscrapper");
                    result.setGameName(Integer.parseInt(""+game.charAt(9)));
                    result.setVariant(Integer.parseInt(""+game.charAt(11)));
                    result.setHeuristic(heuristicName);
                    result.setBacktrackNumber((long)algorithm.backtrackCounter);
                    result.setTime(algorithm.time);
                    result.setVisitedNodes((long)algorithm.nodesCounter);
                    resultRepository.save(result);
                    if(game.contains("6") && heuristicName.equals("least"))
                        continue;

                }
            }
        }
    }


}
