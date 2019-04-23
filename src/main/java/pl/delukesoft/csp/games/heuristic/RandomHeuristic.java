package pl.delukesoft.csp.games.heuristic;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Item;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;
import java.util.List;

public class RandomHeuristic extends Heuristic{

    public RandomHeuristic(Item item) {
        super(item);
    }

    public void setOrderList(){
        for(int i=0 ; i<item.board.length; i++){
            for(int j=0 ; j<item.board[0].length; j++){
                if(item.board[i][j] == 0){
                    orderList.add(new Node(0, i, j));
                }
            }
        }
    }
    
}
