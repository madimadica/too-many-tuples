package com.madimadica.toomanytuples.gen;

import java.util.List;
import java.util.StringJoiner;

public class GeneratorUtils {

    public static String getDimensionGenerics(List<Integer> dimensions) {
        if (dimensions.isEmpty()) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ", "<", ">");
        for (Integer dim : dimensions) {
            sj.add("X" + dim);
        }
        return sj.toString();
    }

    public static String getDimensionArgs(List<Integer> dimensions) {
        if (dimensions.isEmpty()) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ");
        for (Integer dim : dimensions) {
            sj.add("X" + dim + " x" + dim);
        }
        return sj.toString();
    }

    public static String getDimensionArg(int dim) {
        return "X" + dim + " x" + dim;
    }

    public static String className(int dim) {
        return "Tuple" + dim + "D";
    }
}
