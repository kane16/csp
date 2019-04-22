package pl.delukesoft.csp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.delukesoft.csp.games.CSPGameSimulation;
import pl.delukesoft.csp.games.dataextraction.CSPDataExtractorService;
import pl.delukesoft.csp.games.gamerules.SkyscrapperRules;
import pl.delukesoft.csp.games.heuristic.RandomHeuristic;
import pl.delukesoft.csp.games.models.SkyscraperItem;
import pl.delukesoft.csp.games.solutionsearch.BacktrackingAlgorithm;

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
        SkyscraperItem item = dataExtractorService.getScascraperItemFromFile("test_sky_5_2");
        SkyscrapperRules rules = new SkyscrapperRules(item.bottomBound, item.topBound, item.leftBound, item.rightBound,
                item.board);
        RandomHeuristic heuristic = new RandomHeuristic(item);
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

}
