package main;

public class point {
    public int x;
    public int y;
    public point() {
        this.x = 0;
        this.y = 0;
    }
    public point(point other) {
        this.x = other.getX();
        this.y = other.getY();
    }
    public point(int other_x, int other_y) {
        this.x = other_x;
        this.y = other_y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
