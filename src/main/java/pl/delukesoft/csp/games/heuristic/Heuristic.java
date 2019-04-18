package pl.delukesoft.csp.games.heuristic;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Item;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class Heuristic {

    public List<Node> orderList = new ArrayList<>();

    public abstract void setOrderList();

    public Item item;

    public Heuristic(Item item){
        this.item = item;
        setOrderList();
    }

    public abstract void clearAllChoices(int row, int column, List<Integer>[][] possibilities);

    public abstract Node getNextAvailableNode(int row, int column);

}
