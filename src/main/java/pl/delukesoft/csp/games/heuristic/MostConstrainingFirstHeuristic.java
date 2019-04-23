package pl.delukesoft.csp.games.heuristic;

import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Item;
import pl.delukesoft.csp.games.models.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MostConstrainingFirstHeuristic extends Heuristic {


    public MostConstrainingFirstHeuristic(Item item) {
        super(item);
    }

    @Override
    public void setOrderList() {
        for(int i=0 ; i<item.board.length; i++){
            for(int j=0 ; j<item.board[0].length; j++){
                if(item.board[i][j] == 0){
                    orderList.add(new Node(0, i, j));
                }
            }
        }
        this.orderList = this.orderList.stream()
                .sorted(Comparator.comparing(node -> item.constraintNodes[node.row][node.column]))
                .collect(Collectors.toList());
        Collections.reverse(orderList);
    }
}
