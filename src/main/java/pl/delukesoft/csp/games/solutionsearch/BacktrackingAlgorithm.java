package pl.delukesoft.csp.games.solutionsearch;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.models.Node;

import java.util.List;

public class BacktrackingAlgorithm extends SearchSolutionAlgorithm {

    public BacktrackingAlgorithm(Rules rules, Heuristic heuristic) {
        super(rules, heuristic);
    }

    @Override
    public List<int[][]> runAlgorithm() {

        long time = System.currentTimeMillis();
        checkSolutionsOnNode(heuristic.getNextAvailableNodeAndSetPointer());
        this.time = System.currentTimeMillis() - time;
        return solutions;

    }

    private void solveWithBacktracking(Node node) {
        if(heuristic.getNextAvailableNode()==null && rules.isConstraintsFulfilled(node)){
            assignAndCopyBoard(node);
        }else if(rules.isConstraintsFulfilled(node)){
            assignAndFindSolution(node);
        }else backtrackCounter++;
    }

    private void assignAndFindSolution(Node node) {
        rules.board[node.row][node.column] = node.value;
        int currentIndex = heuristic.currentIndex;
        Node nextNode = heuristic.getNextAvailableNodeAndSetPointer();
        checkSolutionsOnNode(nextNode);
        heuristic.currentIndex = currentIndex;
        rules.board[node.row][node.column] = 0;
    }

    private void checkSolutionsOnNode(Node nextNode) {
        if(nextNode==null){
            copyBoardAndAddToSolution();
            return;
        }
        for(int i=1 ; i<rules.board.length+1 ; i++){
            nodesCounter++;
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
