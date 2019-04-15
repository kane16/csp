package pl.delukesoft.csp.games.heuristic;

import pl.delukesoft.csp.games.Node;
import pl.delukesoft.csp.games.gamerules.Rules;

import java.util.List;

public class MostConstrainingFirstHeuristic extends Heuristic {


    public MostConstrainingFirstHeuristic(Rules rules) {
        super(rules);
    }

    @Override
    public Node getNextAvailableNode(int row, int column) {
        return null;
    }
}
