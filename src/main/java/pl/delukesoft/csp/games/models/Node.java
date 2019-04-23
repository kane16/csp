package pl.delukesoft.csp.games.models;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Node {

    public int value;
    public int row;
    public int column;

    @Override
    public boolean equals(Object obj) {
        return this.column==((Node)obj).column && this.row==((Node)obj).row;
    }
}
