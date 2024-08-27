package com.example.starttdd.supplement.c;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;

class GameTest {
    @Test
    void init() {
        final GameNumGen genMock = mock(GameNumGen.class);
        final Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock)
                .should()
                .generate(GameLevel.EASY);
    }

    @Test
    void init2() {
        final GameNumGen genMock = mock(GameNumGen.class);
        final Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock)
                .should(only())
                .generate(any());
    }
}
