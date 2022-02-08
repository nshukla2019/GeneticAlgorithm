package src;

import java.util.List;

public class Pool {
    public List<Organism> generation;
    final int POPULATION_SIZE;
    public Pool(int POPULATION_SIZE, List<Organism> generation) {
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.generation = generation;
    }

}

