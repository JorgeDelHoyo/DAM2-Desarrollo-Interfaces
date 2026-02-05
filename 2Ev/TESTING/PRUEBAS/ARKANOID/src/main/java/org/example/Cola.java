package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cola<T> {

    List<T> almacen;

    public Cola() {
        almacen = new ArrayList<T>();
    }

    public T desencolar() throws ColaVacia {
        try {
            // LIFO
            // return almacen.remove(almacen.size()-1);

            // FIFO
            return almacen.remove(0);

        } catch (Exception e) {
            throw new ColaVacia();
        }
    }

    public void encolar(T t) {
        almacen.add(t);
    }

    public int numElementos() {
        return almacen.size();
    }
}
