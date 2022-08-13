package game;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;


public class MnkBoard implements Position, Board {
    protected final int n, m, k;
    protected int drawLength;
    private final int longNextMove;
    private int countMove = 0;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.F, ' '
    );
    protected final Cell[][] cells;
    private Cell turn;

    public MnkBoard(final int n, final int m, final int k, final int longNextMove) {
        this.longNextMove = longNextMove;
        this.n = n;
        this.m = m;
        this.k = k;
        drawLength = n * m;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public MnkBoard(final int n, final int m, final int k) {
        this(n, m, k, Integer.MAX_VALUE);
    }

    public MnkBoard(final int n, final int k, final int longNextMove, boolean romb) {
        this(n, n, k, longNextMove);
    }

    private int countPoz(final Move move, int x, int y) {
        int u = move.getRow();
        int v = move.getColumn();
        Cell z = move.getValue();
        int res = 0;
        while(u < n && v < m && u >= 0  && v >= 0 && cells[u][v] == z) {
            u += x;
            v += y;
            res++;
        }
        return res;
    }

    @Override
    public Position getPosition() {
        return new ProxyPos(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public int getN() {
        return n;
    }
    @Override
    public int getM() {
        return m;
    }
    @Override
    public int getK() {
        return k;
    }

    private int countLine(final Move move) {
        return IntStream.of(
                countPoz(move, -1, -1) + countPoz(move, 1, 1) - 1,
                countPoz(move, -1, 1) + countPoz(move, 1, -1) - 1,
                countPoz(move, 1, 0) + countPoz(move, -1, 0) - 1,
                countPoz(move, 0, 1) + countPoz(move, 0, -1) - 1).max().getAsInt();
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        countMove++;
        final int mx = countLine(move);
        if (mx >= k) {
            return Result.WIN;
        } else if (countMove == drawLength) {
            return Result.DRAW;
        } else if (mx >= longNextMove) {
            return Result.REMOVE;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    private int numLength(int num) {
        int cnt = 0;
        do {
            num /= 10;
            cnt++;
        } while (num > 0);
        return cnt;
    }

    private void appendNumber(int num, int length, final StringBuilder sb) {
        int lengthNum = numLength(num);
        for (int j = 0; j < length - lengthNum; j++) sb.append(0);
        sb.append(num);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        int lengthN = numLength(n);
        int lengthM = numLength(m);
        for (int j = 1; j < lengthN; j++) {
            sb.append(" ");
        }
        for (int i = 0; i < m; i++) {
            appendNumber(i, lengthM, sb);
            sb.append(" ");
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            appendNumber(r, lengthN, sb);
            for (int c = 0; c < m; c++) {
                for (int j = 0; j < lengthM - 1; j++) sb.append(" ");
                sb.append(SYMBOLS.get(cells[r][c]));
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
