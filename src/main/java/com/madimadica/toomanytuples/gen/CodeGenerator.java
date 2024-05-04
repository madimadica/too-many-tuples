package com.madimadica.toomanytuples.gen;

public class CodeGenerator {
    public static int GLOBAL_MAX_DIM = 10;

    public static void main(String[] args) {
        for (int i = 0; i <= GLOBAL_MAX_DIM; ++i) {
            TupleGenerator gen = new TupleGenerator(i);
            gen.generate();
        }
    }
}
