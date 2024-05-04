package com.madimadica.toomanytuples.gen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneratorUtilsTests {

    private List<Integer> intList(Integer... ints) {
        return new ArrayList<>(Arrays.asList(ints));
    }

    @Test
    public void testGetClassName() {
        assertEquals(GeneratorUtils.getClassName(0), "Tuple0D");
        assertEquals(GeneratorUtils.getClassName(1), "Tuple1D");
        assertEquals(GeneratorUtils.getClassName(2), "Tuple2D");
        assertEquals(GeneratorUtils.getClassName(15), "Tuple15D");
    }

    @Test
    public void testGetDimensionArgumentsInt() {
        assertEquals(GeneratorUtils.getDimensionArguments(0), "");
        assertEquals(GeneratorUtils.getDimensionArguments(1), "x0");
        assertEquals(GeneratorUtils.getDimensionArguments(2), "x0, x1");
        assertEquals(GeneratorUtils.getDimensionArguments(3), "x0, x1, x2");
        assertEquals(GeneratorUtils.getDimensionArguments(4), "x0, x1, x2, x3");
        assertEquals(GeneratorUtils.getDimensionArguments(5), "x0, x1, x2, x3, x4");
    }

    @Test
    public void testGetDimensionArgumentsList() {
        assertEquals(GeneratorUtils.getDimensionArguments(intList()), "");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(0)), "x0");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(0, 1)), "x0, x1");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(0, 1, 2)), "x0, x1, x2");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(0, 1, 2, 3)), "x0, x1, x2, x3");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(1)), "x1");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(2)), "x2");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(2, 4)), "x2, x4");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(4, 2)), "x4, x2");
        assertEquals(GeneratorUtils.getDimensionArguments(intList(4, 2, 7, 12)), "x4, x2, x7, x12");
    }

    @Test
    public void testGetDimensionParametersInt() {
        assertEquals(GeneratorUtils.getDimensionParameters(0), "");
        assertEquals(GeneratorUtils.getDimensionParameters(1), "X0 x0");
        assertEquals(GeneratorUtils.getDimensionParameters(2), "X0 x0, X1 x1");
        assertEquals(GeneratorUtils.getDimensionParameters(3), "X0 x0, X1 x1, X2 x2");
        assertEquals(GeneratorUtils.getDimensionParameters(4), "X0 x0, X1 x1, X2 x2, X3 x3");
        assertEquals(GeneratorUtils.getDimensionParameters(5), "X0 x0, X1 x1, X2 x2, X3 x3, X4 x4");
    }

    @Test
    public void testGetDimensionParametersList() {
        assertEquals(GeneratorUtils.getDimensionParameters(intList()), "");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(0)), "X0 x0");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(0, 1)), "X0 x0, X1 x1");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(0, 1, 2)), "X0 x0, X1 x1, X2 x2");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(0, 1, 2, 3)), "X0 x0, X1 x1, X2 x2, X3 x3");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(1)), "X1 x1");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(2)), "X2 x2");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(2, 4)), "X2 x2, X4 x4");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(4, 2)), "X4 x4, X2 x2");
        assertEquals(GeneratorUtils.getDimensionParameters(intList(4, 2, 7, 12)), "X4 x4, X2 x2, X7 x7, X12 x12");
    }

    @Test
    public void testGetGenericTypeInt() {
        assertEquals(GeneratorUtils.getGenericType(0), "Tuple0D");
        assertEquals(GeneratorUtils.getGenericType(1), "Tuple1D<X0>");
        assertEquals(GeneratorUtils.getGenericType(2), "Tuple2D<X0, X1>");
        assertEquals(GeneratorUtils.getGenericType(3), "Tuple3D<X0, X1, X2>");
    }

    @Test
    public void testGetGenericTypeList() {
        assertEquals(GeneratorUtils.getGenericType(intList()), "Tuple0D");
        assertEquals(GeneratorUtils.getGenericType(intList(0)), "Tuple1D<X0>");
        assertEquals(GeneratorUtils.getGenericType(intList(1)), "Tuple1D<X1>");
        assertEquals(GeneratorUtils.getGenericType(intList(0, 1)), "Tuple2D<X0, X1>");
        assertEquals(GeneratorUtils.getGenericType(intList(0, 1, 2)), "Tuple3D<X0, X1, X2>");
        assertEquals(GeneratorUtils.getGenericType(intList(0, 1, 3)), "Tuple3D<X0, X1, X3>");
        assertEquals(GeneratorUtils.getGenericType(intList(5, 6, 1, 2)), "Tuple4D<X5, X6, X1, X2>");
    }

    @Test
    public void testGetGenericsInt() {
        assertEquals(GeneratorUtils.getGenerics(0), "");
        assertEquals(GeneratorUtils.getGenerics(1), "X0");
        assertEquals(GeneratorUtils.getGenerics(2), "X0, X1");
        assertEquals(GeneratorUtils.getGenerics(3), "X0, X1, X2");
    }

    @Test
    public void testGetGenericsList() {
        assertEquals(GeneratorUtils.getGenerics(intList()), "");
        assertEquals(GeneratorUtils.getGenerics(intList(0)), "X0");
        assertEquals(GeneratorUtils.getGenerics(intList(1)), "X1");
        assertEquals(GeneratorUtils.getGenerics(intList(0, 1)), "X0, X1");
        assertEquals(GeneratorUtils.getGenerics(intList(0, 1, 2)), "X0, X1, X2");
        assertEquals(GeneratorUtils.getGenerics(intList(0, 1, 3)), "X0, X1, X3");
        assertEquals(GeneratorUtils.getGenerics(intList(5, 6, 1, 2)), "X5, X6, X1, X2");
    }
}
