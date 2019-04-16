package pl.delukesoft.csp.games.heuristic;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class Heuristic {

    public List<Node> orderList = new ArrayList<>();

    public abstract void setOrderList();

    Rules rules;

    public Heuristic(Rules rules){
        this.rules = rules;
        setOrderList();
    }

    public abstract Node getNextAvailableNode(int row, int column);

}
