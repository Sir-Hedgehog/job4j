package ru.job4j.gc;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 17.12.2019
 */

public class AnalysisOfMemory {

    private static int counter = 0;

    public static void main(String[] args) {
        checkGarbageCollector();
    }

    /**
     * Метод проверяет работу сборщика мусора в процессе запонения памяти
     */

    private static void checkGarbageCollector() {
        getMemory();
        for (int index = 0; index < 1000000; index++) {
            counter++;
            User user = new User("Иван", 18, 'М');
            System.out.println(counter + "");
        }
        System.out.println(counter + "");
        getMemory();
        //System.gc();
    }

    /**
     * Метод выводит информацию о состоянии памяти
     */

    private static void getMemory() {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("----- Memory Usage [Mb] -----");
        System.out.println("Total memory: " + runtime.totalMemory() / mb);
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free memory: " + runtime.freeMemory() / mb);
        System.out.println("Max memory: " + runtime.maxMemory() / mb);
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
