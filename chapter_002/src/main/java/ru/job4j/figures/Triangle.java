package ru.job4j.figures;

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   +   ");
        pic.append("  + +  ");
        pic.append(" +   + ");
        pic.append("+++++++");
        return pic.toString();
    }
}
