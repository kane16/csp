package pl.delukesoft.csp.games.heuristic;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Node;

import java.util.List;

public class LeastConstrainingFirstHeuristic extends Heuristic{

    @Override
    public void setOrderList() {

    }

    public LeastConstrainingFirstHeuristic(Rules rules) {
        super(rules);
    }

    @Override
    public Node getNextAvailableNode(int row, int column) {
        return null;
    }
}
