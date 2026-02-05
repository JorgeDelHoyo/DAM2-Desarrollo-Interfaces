package org.example;
import java.util.ArrayList;
import java.util.List;

public class Potencia {
    public static String povOf2(int exp) {
        if (exp == 0)
            return "0";
        List<Integer> digits = new ArrayList<Integer>();
        digits.add(1);
        int size = 1;
        int tmp;
        int ac = 0;
        // Primer bucle
        for (int i = 0; i < exp; i++) {
            // Segundo Bucle
            for (int c = 0; c < size; c++) {
                tmp = digits.get(c);
                tmp *= 2;
                tmp += ac;

                if (tmp > 9) {
                    tmp -= 10;
                    ac = 1;
                } else {
                    ac = 0;
                }
                digits.set(c, tmp);
            }
            // Segundo if
            if (ac == 1) {
                digits.add(ac);
                size++;
                ac = 0;
            }
        }
        String s = "";
        // Tercer bucle
        for (int i = digits.size() - 1; i >= 0; i--) {
            s += digits.get(i);
        }
        return s;
    }
}