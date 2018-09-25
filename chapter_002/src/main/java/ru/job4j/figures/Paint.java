package ru.job4j.figures;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
