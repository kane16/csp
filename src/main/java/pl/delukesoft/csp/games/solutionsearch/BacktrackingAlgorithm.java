package pl.delukesoft.csp.games.solutionsearch;

import pl.delukesoft.csp.games.Node;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;

import java.util.List;

public class BacktrackingAlgorithm extends SearchSolutionAlgorithm {

    public BacktrackingAlgorithm(Rules rules, Heuristic heuristic) {
        super(rules, heuristic);
    }

    @Override
    public List<int[][]> runAlgorithm() {

        checkSolutionsOnNode(heuristic.getNextAvailableNode(-1,-1));

        return solutions;

    }

    private void solveWithBacktracking(Node node) {
        if(heuristic.getNextAvailableNode(node.column, node.row)==null && rules.isConstraintsFulfilled(node)){
            assignAndCopyBoard(node);
        }else if(rules.isConstraintsFulfilled(node)){
            assignAndFindSolution(node);
        }else backtrackCounter++;
    }

    private void assignAndFindSolution(Node node) {
        rules.board[node.row][node.column] = node.value;
        Node nextNode = heuristic.getNextAvailableNode(node.row, node.column);
        checkSolutionsOnNode(nextNode);
        rules.board[node.row][node.column] = 0;
    }

    private void checkSolutionsOnNode(Node nextNode) {
        for(int i=1 ; i<rules.board.length+1 ; i++){
            solveWithBacktracking(new Node(i, nextNode.row, nextNode.column));
        }
    }

    private void assignAndCopyBoard(Node node) {
        rules.board[node.row][node.column] = node.value;
        copyBoardAndAddToSolution();
        rules.board[node.row][node.column] = 0;
    }

    private void copyBoardAndAddToSolution() {
        int[][] solutionBoard = new int[rules.board.length][rules.board.length];
        for (int i = 0; i < rules.board.length; i++) {
            solutionBoard[i] = rules.board[i].clone();
        }
        solutions.add(solutionBoard);
    }

}
