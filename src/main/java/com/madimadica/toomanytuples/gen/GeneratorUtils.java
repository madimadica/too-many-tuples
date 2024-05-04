package com.madimadica.toomanytuples.gen;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneratorUtils {

    /**
     * Generate the class name for a given dimension
     * @param dimension dimension of the target tuple
     * @return String class name, such as {@code Tuple2D}, with no generic typing.
     */
    public static String getClassName(int dimension) {
        return "Tuple" + dimension + "D";
    }

    /**
     * Return a string of arguments starting from 0 such as {@code "x0, x1, x2"} for {@code getDimensionArguments(3)}. <br>
     * Returns {@code ""} for an empty list.
     * @param dimension number of dimensions to create arguments for
     * @return String formatted dimensional arguments
     */
    public static String getDimensionArguments(int dimension) {
        if (dimension == 0) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < dimension; ++i) {
            sj.add("x" + i);
        }
        return sj.toString();
    }

    /**
     * Return a string of arguments such as {@code "x1, x3, x5"}, or {@code ""} for an empty list.
     * @param dimensions dimension values to create arguments for (0 based)
     * @return String formatted dimensional arguments
     */
    public static String getDimensionArguments(List<Integer> dimensions) {
        if (dimensions.isEmpty()) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ");
        for (int i : dimensions) {
            sj.add("x" + i);
        }
        return sj.toString();
    }

    /**
     * Return a string of parameters starting from 0 such as {@code "X0 x0, X1 x1, X2 x2"} for {@code getDimensionParameters(3)}. <br>
     * Returns {@code ""} for an empty list.
     * @param dimension number of dimensions to create parameters for
     * @return String formatted dimensional parameters
     */
    public static String getDimensionParameters(int dimension) {
        if (dimension == 0) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < dimension; ++i) {
            sj.add(getDimensionParameter(i));
        }
        return sj.toString();
    }

    /**
     * Return a string of method parameters such as {@code "X1 x1, X3 x3, X5 x5"}, or {@code ""} for an empty list.
     * @param dimensions dimension values to create parameters for (0 based)
     * @return String formatted dimensional parameters
     */
    public static String getDimensionParameters(List<Integer> dimensions) {
        if (dimensions.isEmpty()) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ");
        for (Integer dim : dimensions) {
            sj.add(getDimensionParameter(dim));
        }
        return sj.toString();
    }

    /**
     * Generates a parameter for the given dimension, such as {@code "X3, x3"}
     * @param dimension to create a parameter for
     * @return String Java parameter
     */
    public static String getDimensionParameter(int dimension) {
        return "X" + dimension + " x" + dimension;
    }

    /**
     * Returns a string representation of the given dimension's generic typing, such as
     * <br>{@code Tuple2D<X0, X1>} for dimension 2.
     * @param dimension of Tuple
     * @return String
     */
    public static String getGenericType(int dimension) {
        String rawType = getClassName(dimension);
        if (dimension == 0) {
            return rawType;
        }
        return rawType + "<" + getGenerics(dimension) + ">";
    }

    /**
     * Returns a generic type with no generic arguments, such as {@code Tuple2D<>} or {@code Tuple0D}
     * @param dimension of Tuple
     * @return String of shorthand Java type
     */
    public static String getShortGenericType(int dimension) {
        String rawType = getClassName(dimension);
        if (dimension == 0) {
            return rawType;
        }
        return rawType + "<>";
    }

    /**
     * Returns a string representation of the given dimensions' generic typing, such as
     * <br>{@code Tuple2D<X0, X2>} for {@code List.of(0, 2)}
     * @param dimensions List of dimensions for Tuple generics
     * @return String
     */
    public static String getGenericType(List<Integer> dimensions) {
        String rawType = getClassName(dimensions.size());
        if (dimensions.isEmpty()) {
            return rawType;
        }
        return rawType + "<" + getGenerics(dimensions) + ">";
    }

    /**
     * Generate the generic typing for the given dimension, such as {@code "X0, X1, X2"}
     * @param dimension of Tuple
     * @return String
     */
    public static String getGenerics(int dimension) {
        return getDimensionArguments(dimension).toUpperCase();
    }

    /**
     * Generate the generic typing for the given dimensions, such as {@code "X0, X5, X9"}
     * @param dimensions List of dimensions values for Tuple
     * @return String
     */
    public static String getGenerics(List<Integer> dimensions) {
        return getDimensionArguments(dimensions).toUpperCase();
    }

    public static List<Integer> range(int startInclusive, int endExclusive) {
        return IntStream.range(startInclusive, endExclusive).boxed().collect(Collectors.toList());
    }

    public static List<Integer> rangeClosed(int startInclusive, int endInclusive) {
        return IntStream.range(startInclusive, endInclusive).boxed().collect(Collectors.toList());
    }
}
