package ru.job4j.condition;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * @param ab расстояние между точками a и b
     * @param ac расстояние между точками a и c
     * @param bc расстояние между точками b и c
     * @return Периметр.
     */

    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод вычисляет площадь треугольника.
     *
     * @return прощадь, если треугольник существует, или -1, если треугольника нет.
     */

    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Метод проверяет, можно ли построить треугольник с такими длинами сторон.
     *
     * @param ab Длина от точки a до b.
     * @param ac Длина от точки a до c.
     * @param bc Длина от точки b до c.
     * @return true, если сумма двух любых сторон треугольника не меньше третьей стороны, иначе - false.
     */
    private boolean exist(double ab, double ac, double bc) {
        return (ab + ac > bc && ab + bc > ac && ac + bc > ab);
    }
}
