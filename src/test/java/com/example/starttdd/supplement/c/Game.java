package com.example.starttdd.supplement.c;

public class Game {
    private final GameNumGen gen;

    public Game(GameNumGen gen) {
        this.gen = gen;
    }

    public String init(GameLevel level) {
        return gen.generate(level);
    }
}
