package pl.delukesoft.csp.games.gamerules;


import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;
import java.util.List;

public class FutoshikiRules extends Rules {

    public List<String> constraints;

    public FutoshikiRules(List<String> constraints, int[][] board, Heuristic heuristic){
        this.constraints = constraints;
        this.board = board;
        this.heuristic = heuristic;
    }

    public boolean isConstraintsFulfilled(Node node){
        for(int i=0 ; i<board.length ; i++){
            if(node.value == board[node.row][i]){
                return false;
            }
            if(node.value == board[i][node.column]){
                return false;
            }
        }
        int[][] newBoard = new int[board.length][board.length];
        for(int i=0; i<board.length; i++){
            newBoard[i] = board[i].clone();
        }
        newBoard[node.row][node.column] = node.value;
        return constraints.stream().noneMatch(constraint -> !isConstraintFulfilled(constraint, newBoard));
    }


    private boolean isConstraintFulfilled(String constraint, int[][] board) {
        int value1 = board[Character.getNumericValue(constraint.charAt(0))-1]
                [Character.getNumericValue(constraint.charAt(1))-1];
        int value2 = board[Character.getNumericValue(constraint.charAt(3))-1]
                [Character.getNumericValue(constraint.charAt(4))-1];
        if(value1 == 0 || value2 ==0){
            return true;
        }
        return value1 < value2;
    }

}
