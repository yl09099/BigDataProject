package com.mini;

/**
 * Hello world!
 */

class Value {
    int value;

    public Value(int v2) {
        this.value = v2;
    }
}

public class App {
    public static void main(String[] args) {

        final int a = 1;

        final double bb;
        bb = 2;

        final Value value = new Value(3);

        value.value = 4;

        System.out.println("Hello World!");
    }
}
