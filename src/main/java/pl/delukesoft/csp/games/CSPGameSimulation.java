package pl.delukesoft.csp.games;


import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.solutionsearch.SearchSolutionAlgorithm;

import java.util.ArrayList;
import java.util.List;


public class CSPGameSimulation {

    Rules rules;

    Heuristic heuristic;

    SearchSolutionAlgorithm algorithm;

    List<int[][]> solutions = new ArrayList<>();

    int backtrackCounter;

    public CSPGameSimulation(Rules rules, Heuristic heuristic, SearchSolutionAlgorithm algorithm){
        this.rules = rules;
        this.algorithm = algorithm;
        this.heuristic = heuristic;
    }

    public List<int[][]> runGameAndFindSolutions(){

        solutions = algorithm.runAlgorithm();

        return solutions;
    }

}
