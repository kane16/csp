package pl.delukesoft.csp.games.solutionsearch;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;

import java.util.List;

public class ForwardCheckingAlgorithm extends SearchSolutionAlgorithm{

    public ForwardCheckingAlgorithm(Rules rules, Heuristic heuristic) {
        super(rules, heuristic);
    }

    @Override
    public List<int[][]> runAlgorithm() {
        return null;
    }

}
