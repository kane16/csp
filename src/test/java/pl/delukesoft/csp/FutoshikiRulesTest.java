package pl.delukesoft.csp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.delukesoft.csp.games.dataextraction.CSPDataExtractorService;
import pl.delukesoft.csp.games.CSPGameSimulation;
import pl.delukesoft.csp.games.gamerules.FutoshikiRules;
import pl.delukesoft.csp.games.models.FutoshikiItem;

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
        CSPGameSimulation cspGameSimulation = new CSPGameSimulation(futoshiki);
        List<int[][]> solutions = cspGameSimulation.runGameAndFindSolutions();
        for(int[][] solution: solutions){
            System.out.println(Arrays.toString(solution[3]));
        }
    }


}
