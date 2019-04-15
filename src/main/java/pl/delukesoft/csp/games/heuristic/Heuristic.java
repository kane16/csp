package pl.delukesoft.csp.games.heuristic;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.Node;
import pl.delukesoft.csp.games.gamerules.Rules;

import java.util.List;

public abstract class Heuristic {

    Rules rules;

    public Heuristic(Rules rules){
        this.rules = rules;
    }

    public abstract Node getNextAvailableNode(int row, int column);

}
