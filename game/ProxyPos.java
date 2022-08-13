package game;

public class ProxyPos implements Position {
    private Position position;

    public ProxyPos(Position board) {
        this.position = board;
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    @Override
    public int getN() {
        return position.getN();
    }

    @Override
    public int getM() {
        return position.getM();
    }

    @Override
    public int getK() {
        return position.getK();
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
