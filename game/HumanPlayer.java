package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private NextIntCheck in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = new NextIntCheck(in);
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int u, v;
            while (true) {
                u = in.nextInt();
                v = in.nextInt();
                if (u == -1 || v == -1) {
                    System.out.println("Invalid input, not number. Please try again");
                } else break;
            }
            final Move move = new Move(u, v, cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }
}
