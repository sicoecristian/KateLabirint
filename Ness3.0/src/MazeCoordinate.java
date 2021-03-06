import java.util.Objects;

public class MazeCoordinate {
    private int x;
    private int y;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeCoordinate that = (MazeCoordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public String toString() {
        return "MazeCoordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public MazeCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
