package com.david;

import java.io.IOException;

public class Suma {
    public static void main(String[] args) throws IOException {
        try {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            System.exit(num1 + num2);
        } catch (Throwable e) {
            System.exit(0);
        }
    }
}
