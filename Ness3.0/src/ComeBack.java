public class ComeBack {
    private int x;
    private int y;
    private int numberOfNeighbours;

    public ComeBack(int x, int y, int numberOfNeighbours) {
        this.x = x;
        this.y = y;
        this.numberOfNeighbours = numberOfNeighbours;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumberOfNeighbours() {
        return numberOfNeighbours;
    }

    public void setNumberOfNeighbours(int numberOfNeighbours) {
        this.numberOfNeighbours = numberOfNeighbours;
    }
}
