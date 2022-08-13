package game;

import java.util.Arrays;

public class RombBoard extends MnkBoard {

    public RombBoard(int n, int k, int longNextMove) {
        super(2 * n - 1, k, longNextMove, true);
        int cnt = 0;
        drawLength = n * n + (n - 1) * (n - 1);
        boolean flag = false;
        for (Cell[] row : cells) {
            Arrays.fill(row, 0, n - 1 - cnt, Cell.F);
            Arrays.fill(row, n + cnt, 2 * n - 1, Cell.F);
            if (flag) {
                cnt--;
            } else {
                cnt++;
                if (n - 1 == cnt) flag = true;
            }
        }
    }
    public RombBoard(int n, int k) {
        this(n, k, Integer.MAX_VALUE);
    }
}
