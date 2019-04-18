package pl.delukesoft.csp.games.heuristic;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Item;
import pl.delukesoft.csp.games.models.Node;

import java.util.List;

public class MostConstrainingFirstHeuristic extends Heuristic {


    public MostConstrainingFirstHeuristic(Item item) {
        super(item);
    }

    @Override
    public void clearAllChoices(int row, int column, List<Integer>[][] possibilities) {

    }

    @Override
    public void setOrderList() {

    }


    @Override
    public Node getNextAvailableNode(int row, int column) {
        return null;
    }
}
