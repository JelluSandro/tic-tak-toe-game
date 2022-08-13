package game;

import java.util.Scanner;

public class NextIntCheck {
    private final Scanner in;

    public NextIntCheck(final Scanner in) {
        this.in = in;
    }

    public NextIntCheck() {
        this(new Scanner(System.in));
    }

    public int nextInt() {
        if (in.hasNextInt()) {
            return in.nextInt();
        } else {
            in.next();
            return -1;
        }
    }
}
