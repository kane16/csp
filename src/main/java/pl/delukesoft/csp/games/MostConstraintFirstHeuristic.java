package pl.delukesoft.csp.games;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;

public class MostConstraintFirstHeuristic extends Heuristic {

    @Override
    public void setOrderList() {

    }

    public MostConstraintFirstHeuristic(Rules rules) {
        super(rules);
    }

    @Override
    public Node getNextAvailableNode(int row, int column) {
        return null;
    }
}
