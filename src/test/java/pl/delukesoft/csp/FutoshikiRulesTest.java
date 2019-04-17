package pl.delukesoft.csp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.delukesoft.csp.games.CSPGameSimulation;
import pl.delukesoft.csp.games.dataextraction.CSPDataExtractorService;
import pl.delukesoft.csp.games.gamerules.FutoshikiRules;
import pl.delukesoft.csp.games.heuristic.NoHeuristic;
import pl.delukesoft.csp.games.models.FutoshikiItem;
import pl.delukesoft.csp.games.solutionsearch.BacktrackingAlgorithm;
import pl.delukesoft.csp.games.solutionsearch.ForwardCheckingAlgorithm;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FutoshikiRulesTest {

    @Autowired
    CSPDataExtractorService dataExtractorService;

    @Test
    public void testFutoshikiForConstraints() throws FileNotFoundException {
        FutoshikiItem item = dataExtractorService.getFutoshikiItemFromFile("futoshiki_5_0");
        NoHeuristic heuristic = new NoHeuristic(item);
        FutoshikiRules rules = new FutoshikiRules(item.constraints, item.board, heuristic);
        BacktrackingAlgorithm backtrackingAlgorithm = new BacktrackingAlgorithm(rules, heuristic);
        CSPGameSimulation cspGameSimulation = new CSPGameSimulation(rules, heuristic, backtrackingAlgorithm);
        List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
        for(int[][] solution: solutions){
            System.out.println("[");
            System.out.println(Arrays.toString(solution[0]));
            System.out.println(Arrays.toString(solution[1]));
            System.out.println(Arrays.toString(solution[2]));
            System.out.println(Arrays.toString(solution[3]));
            System.out.println(Arrays.toString(solution[4]));
            System.out.println("]");
        }
    }

    @Test
    public void testFutoshikiForConstraintsForwardChecking() throws FileNotFoundException {
        FutoshikiItem item = dataExtractorService.getFutoshikiItemFromFile("futoshiki_5_0");
        NoHeuristic heuristic = new NoHeuristic(item);
        FutoshikiRules rules = new FutoshikiRules(item.constraints, item.board, heuristic);
        ForwardCheckingAlgorithm forwardCheckingAlgorithm = new ForwardCheckingAlgorithm(rules, heuristic);
        CSPGameSimulation cspGameSimulation = new CSPGameSimulation(rules, heuristic, forwardCheckingAlgorithm);
        List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
        for(int[][] solution: solutions){
            System.out.println("[");
            System.out.println(Arrays.toString(solution[0]));
            System.out.println(Arrays.toString(solution[1]));
            System.out.println(Arrays.toString(solution[2]));
            System.out.println(Arrays.toString(solution[3]));
            System.out.println(Arrays.toString(solution[4]));
            System.out.println("]");
        }
    }


}
