package ru.job4j.ood.ocp.first.wrong;

public class AreaCalculator {

    public Double calculateRectangleArea(Rectangle rectangle) {
        return rectangle.getLength() * rectangle.getWidth();
    }
    public Double calculateCircleArea(Circle circle) {
        return circle.getRadius() * circle.getRadius() * Math.PI;
    }
}
