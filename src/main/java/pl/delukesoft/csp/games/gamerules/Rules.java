package pl.delukesoft.csp.games.gamerules;

import pl.delukesoft.csp.games.models.Node;

public abstract class Rules {

    public int[][] board;

    public abstract boolean isConstraintsFulfilled(Node node);

}
