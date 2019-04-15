package pl.delukesoft.csp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.delukesoft.csp.games.CSPDataExtractorService;
import pl.delukesoft.csp.games.CSPGameSimulation;
import pl.delukesoft.csp.games.gamerules.FutoshikiRules;
import pl.delukesoft.csp.games.heuristic.NoHeuristic;
import pl.delukesoft.csp.games.inputmodels.FutoshikiItem;
import pl.delukesoft.csp.games.solutionsearch.BacktrackingAlgorithm;

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
        FutoshikiItem futoshikiItem = dataExtractorService.getFutoshikiItemFromFile("futoshiki_4_0");
        FutoshikiRules futoshiki = new FutoshikiRules(futoshikiItem.constraints, futoshikiItem.contentTable);
        NoHeuristic heuristic = new NoHeuristic(futoshiki);
        BacktrackingAlgorithm backtrackingAlgorithm = new BacktrackingAlgorithm(futoshiki, heuristic);
        CSPGameSimulation cspGameSimulation = new CSPGameSimulation(futoshiki, heuristic, backtrackingAlgorithm);
        List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
        for(int[][] solution: solutions){
            System.out.println(Arrays.toString(solution[3]));
        }
    }


}
