package pl.delukesoft.csp.games;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.models.Item;
import pl.delukesoft.csp.games.models.Node;

public class MostConstraintFirstHeuristic extends Heuristic {

    public MostConstraintFirstHeuristic(Item item) {
        super(item);
    }

    @Override
    public void setOrderList() {

    }

    @Override
    public Node getNextAvailableNode(int row, int column) {
        return null;
    }
}
