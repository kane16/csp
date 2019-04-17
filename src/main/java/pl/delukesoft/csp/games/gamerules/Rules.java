package pl.delukesoft.csp.games.gamerules;

import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;

public abstract class Rules {

    public int[][] board;

    public Heuristic heuristic;

    public abstract boolean isConstraintsFulfilled(Node node);

    public boolean eliminateForwardPossibilitiesAndReturnIfCanMoveForward(int currentRow, int currentColumn,
                                                                                   ArrayList<Integer>[][] possibilities){
        Node nextNode = new Node(0, currentRow, currentColumn);
        while(nextNode != null){
            ArrayList<Integer> compliantValues = new ArrayList<>();
            ArrayList<Integer> possibleList = possibilities[nextNode.row][nextNode.column];
            for(Integer i : possibleList){
                Node checkedNode = new Node(i, nextNode.row, nextNode.column);
                if(isConstraintsFulfilled(checkedNode)){
                    compliantValues.add(checkedNode.value);
                }
            }
            possibilities[nextNode.row][nextNode.column] = compliantValues;
            if(compliantValues.size()==0){
                return false;
            }
            nextNode = heuristic.getNextAvailableNode(nextNode.row, nextNode.column);
        }
        return true;
    }

}
