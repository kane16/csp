package pl.delukesoft.csp.games.gamerules;

import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;

public abstract class Rules {

    public int[][] board;

    public Heuristic heuristic;

    public abstract boolean isConstraintsFulfilled(Node node);

    public boolean eliminateForwardPossibilitiesAndReturnIfCanMoveForward(ArrayList<Integer>[][] possibilities){
        int currentIndex = heuristic.currentIndex;
        Node nextNode = heuristic.getNextAvailableNodeAndSetPointer();
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
                heuristic.currentIndex = currentIndex;
                return false;
            }
            nextNode = heuristic.getNextAvailableNodeAndSetPointer();
        }
        heuristic.currentIndex = currentIndex;
        return true;
    }

}
