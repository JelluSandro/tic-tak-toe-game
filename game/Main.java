package game;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        final int n = 3, m = 4, k = 5, longNextMove = 4;
        if (n <= 0 || m <= 0 || k <= 0 || longNextMove < 0) {
            throw new NoSuchElementException();
        }
        final Game game = new Game(false, new HumanPlayer(), new RandomPlayer());
        int result;
        do {
            result = game.play(new RombBoard(n, k));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
