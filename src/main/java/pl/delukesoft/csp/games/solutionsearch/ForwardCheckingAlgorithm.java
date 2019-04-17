package pl.delukesoft.csp.games.solutionsearch;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.heuristic.Heuristic;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;
import java.util.List;

public class ForwardCheckingAlgorithm extends SearchSolutionAlgorithm{

    ArrayList<Integer>[][] possibilities = (ArrayList<Integer>[][])new ArrayList[rules.board.length][rules.board[0].length];

    public ForwardCheckingAlgorithm(Rules rules, Heuristic heuristic) {
        super(rules, heuristic);
        fillPossibiliesTable();
    }

    @Override
    public List<int[][]> runAlgorithm() {

        checkSolutionsOnNode(heuristic.getNextAvailableNode(-1, -1));

        return solutions;
    }

    private void fillPossibiliesTable() {
        for(int i=0 ; i<heuristic.orderList.size() ;i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int n=1 ; n<rules.board.length+1 ; n++){
                list.add(n);
            }
            possibilities[heuristic.orderList.get(i).row][heuristic.orderList.get(i).column]=list;
        }
    }

    private void solveWithForwardChecking(Node node) {
        if(heuristic.getNextAvailableNode(node.row, node.column) == null && rules.isConstraintsFulfilled(node)){
            assignAndFindSolution(node);
        }else if(rules.isConstraintsFulfilled(node)){
            assignAndFindSolution(node);
        }else backtrackCounter++;
    }

    private void assignAndFindSolution(Node node){
        rules.board[node.row][node.column] = node.value;
        if(rules.eliminateForwardPossibilitiesAndReturnIfCanMoveForward(node.row, node.column, possibilities)){
            Node nextNode = heuristic.getNextAvailableNode(node.row, node.column);
            checkSolutionsOnNode(nextNode);
        }
        rules.board[node.row][node.column] = 0;
    }

    private void checkSolutionsOnNode(Node nextNode) {
        ArrayList<Integer> possibleList = possibilities[nextNode.row][nextNode.column];
        for(Integer i: possibleList){
            solveWithForwardChecking(new Node(i, nextNode.row, nextNode.column));
            ArrayList<Integer> cellPossibilities = new ArrayList<>();
            for(int j=1 ; j<rules.board.length+1 ; j++){
                cellPossibilities.add(j);
            }
            possibilities[nextNode.row][nextNode.column] = cellPossibilities;
        }
    }

}
