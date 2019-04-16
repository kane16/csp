package pl.delukesoft.csp.games.gamerules;

import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;

public abstract class Rules {

    public int[][] board;

    public abstract boolean isConstraintsFulfilled(Node node);

    public abstract boolean eliminateForwardPossibilitiesAndReturnIfCanMoveForward(int currentRow, int currentColumn,
                                                                                   ArrayList<Integer>[][] possibilities);

}
