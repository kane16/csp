package pl.delukesoft.csp.games.heuristic;

import pl.delukesoft.csp.games.Node;
import pl.delukesoft.csp.games.gamerules.Rules;

import java.util.List;

public class LeastConstrainingFirstHeuristic extends Heuristic{

    public LeastConstrainingFirstHeuristic(Rules rules) {
        super(rules);
    }

    @Override
    public Node getNextAvailableNode(int row, int column) {
        return null;
    }
}
