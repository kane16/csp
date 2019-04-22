package pl.delukesoft.csp.games.heuristic;

import org.springframework.stereotype.Service;
import pl.delukesoft.csp.games.gamerules.Rules;
import pl.delukesoft.csp.games.models.Item;
import pl.delukesoft.csp.games.models.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class Heuristic {

    public List<Node> orderList = new ArrayList<>();

    public abstract void setOrderList();

    public Item item;

    public Heuristic(Item item){
        this.item = item;
        setOrderList();
    }

    public void clearAllChoices(int row, int column, List<Integer>[][] possibilities) {
        Node currentNode = new Node(0, row, column);
        while(currentNode != null){
            ArrayList<Integer> cellPossibilities = new ArrayList<>();
            for(int j=1 ; j<item.size+1 ; j++){
                cellPossibilities.add(j);
            }
            possibilities[currentNode.row][currentNode.column] = cellPossibilities;
            currentNode = getNextAvailableNode(currentNode.row, currentNode.column);
        }
    }

    public abstract Node getNextAvailableNode(int row, int column);

}
