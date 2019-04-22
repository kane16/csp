package pl.delukesoft.csp.games.solutionsearch;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchSolutionAlgorithm {

    Rules rules;

    Heuristic heuristic;

    int backtrackCounter;

    int nodesCounter;

    List<int[][]> solutions = new ArrayList<>();

    public SearchSolutionAlgorithm(Rules rules, Heuristic heuristic){
        this.rules = rules;
        this.heuristic = heuristic;
    }

    public abstract List<int[][]> runAlgorithm();

}
