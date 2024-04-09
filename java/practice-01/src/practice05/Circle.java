package practice05;

public class Circle {
    private int x, y;
    private int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ") 반지름" + this.radius ;
    }

    @Override
    public boolean equals(Object obj) {
        Circle circle = (Circle) obj;
        return this.x == circle.x && this.y == circle.y;
    }

}