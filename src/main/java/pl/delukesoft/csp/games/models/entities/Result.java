package pl.delukesoft.csp.games.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String algorithmType;

    @Column
    private String game;

    @Column
    private int gameName;

    @Column
    private int variant;

    @Column
    private String heuristic;

    @Column
    private Long time;

    @Column
    private Long backtrackNumber;

    @Column
    private Long visitedNodes;


}
