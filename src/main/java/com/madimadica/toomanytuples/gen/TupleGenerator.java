package com.madimadica.toomanytuples.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import static com.madimadica.toomanytuples.gen.CodeGenerator.GLOBAL_MAX_DIM;
import static com.madimadica.toomanytuples.gen.GeneratorUtils.*;

public class TupleGenerator {
    private final StringBuilder src = new StringBuilder();
    private final int dimension;
    private final String className;
    private final List<Integer> dimensions;

    public TupleGenerator(int dimension) {
        this.dimension = dimension;
        this.className = getClassName(dimension);
        dimensions = new ArrayList<>();
        for (int i = 0; i < dimension; ++i) {
            dimensions.add(i);
        }
    }

    public void generate() {
        src.append("package com.madimadica.toomanytuples;");

        newLine(2);
        addClassDeclaration();
        newLine();
        addClassAttributes();
        newLine(2);
        addNoArgsConstructor();
        newLine(2);
        addAllArgsConstructor();
        newLine();
        addGetters();
        addSetters();
        addInserts();
        addRemoves();
        addConverts();
        addReplaces();
        src.append("}");
        System.out.println(src);
    }

    private void addClassDeclaration() {
        src.append("public class ").append(getGenericType(dimensions));
        src.append(" {\n");
    }

    private void addClassAttributes() {
        for (int i = 0; i < dimension; ++i) {
            src.append("\tprivate ").append(getDimensionParameter(i)).append(";");
        }
    }

    private void addNoArgsConstructor() {
        src.append("\tpublic ").append(className).append("() {}");
    }

    private void addAllArgsConstructor() {
        src.append("\tpublic ").append(className).append('(').append(getDimensionParameters(dimensions)).append(") {");
        for (int i = 0; i < dimension; ++i) {
            src.append("\n\t\tthis.x").append(i).append(" = x").append(i).append(";");
        }
        src.append("\n\t}\n");
    }

    private void addGetters() {
        for (int i = 0; i < dimension; ++i) {
            addGetter(i);
            newLine(2);
        }
    }

    private void addSetters() {
        for (int i = 0; i < dimension; ++i) {
            addSetter(i);
            newLine(2);
        }
    }

    private void addGetter(int dim) {
        src.append("\tpublic X").append(dim).append(" getX").append(dim).append("() {\n")
                .append("\t\treturn this.x").append(dim).append(";\n\t}");
    }

    private void addSetter(int dim) {
        src.append("\tpublic void setX").append(dim).append("(").append(getDimensionParameter(dim)).append(") {\n")
                .append("\t\tthis.x").append(dim).append(" = x").append(dim).append(";\n\t}");
    }

    private void addInserts() {
        // Don't generate inserts if at max dim
        if (dimension == GLOBAL_MAX_DIM) {
            return;
        }
        for (int i = 0; i <= dimension && i < GLOBAL_MAX_DIM; ++i) {
            addInsert(i);
            newLine(2);
        }

        // if dim 3, and max is 10, need to add inserts for X4, ... X10
        for (int newDim = dimension + 1; newDim <= GLOBAL_MAX_DIM; ++newDim) {
            addMultiInsert(newDim);
            newLine(2);
        }
        // add variable adds between dimension and maxGlobal
    }

    private void addInsert(int dim) {
        String newClassName = getClassName(dimension + 1);
        // Dimension 0 is not an edge case here
        StringJoiner newDimensions = new StringJoiner(", ");
        for (int i = 0; i < dimension; ++i) {
            if (i == dim) {
                newDimensions.add("T");
            }
            newDimensions.add("X" + i);
        }
        // Handle the edge case since i < dimension
        if (dimension == dim) {
            newDimensions.add("T");
        }
        String returnType = newClassName + "<" + newDimensions + ">";
        String args = newDimensions.toString().toLowerCase();
        src.append("\tpublic <T> ").append(returnType).append(" insertX").append(dim).append("(T t) {\n");
        src.append("\t\treturn new ").append(returnType).append("(").append(args).append(");\n\t}");
    }

    private void addMultiInsert(int newDimension) {
        List<Integer> newDims = range(this.dimension, newDimension);
        src.append("\tpublic <").append(getGenerics(newDims)).append("> ")
                .append(getGenericType(newDimension)).append(" insert(")
                .append(getDimensionParameters(newDims)).append(") {\n")
                .append("\t\treturn new ").append(getShortGenericType(newDimension))
                .append("(").append(getDimensionArguments(newDimension))
                .append(");\n\t}");
    }

    private void addRemoves() {
        for (int i = 0; i < dimension; ++i) {
            addRemove(i);
            newLine(2);
        }
    }

    private void addRemove(int dim) {
        List<Integer> resultingDimensions = range(0, dimension);
        resultingDimensions.remove(Integer.valueOf(dim));
        String returnType = getGenericType(resultingDimensions);
        String shortType = getShortGenericType(resultingDimensions.size());
        src.append("\tpublic ").append(returnType).append(" removeX").append(dim)
                .append("() {\n\t\t")
                .append("return new ").append(shortType)
                .append("(")
                .append(getDimensionArguments(resultingDimensions))
                .append(");\n\t}");
    }

    private void addConverts() {
        for (int i = 0; i < dimension; ++i) {
            addConvert(i);
            newLine(2);
        }
    }

    private void addConvert(int dim) {
        String returnType = getGenericType(dimension).replace("X" + dim, "T");
        src.append("\tpublic <T> ").append(returnType).append(" convertX").append(dim)
                .append("(Function<X").append(dim).append(", T> converter) {\n")
                .append("\t\tT t = converter.apply(x").append(dim).append(");\n")
                .append("\t\treturn new ").append(getShortGenericType(dimension))
                .append("(")
                .append(getDimensionArguments(dimension).replace("x" + dim, "t"))
                .append(");\n\t}");
    }

    private void addReplaces() {
        for (int i = 0; i < dimension; ++i) {
            addReplace(i);
            newLine(2);
        }
    }

    private void addReplace(int dim) {
        String returnType = getGenericType(dimension).replace("X" + dim, "T");
        src.append("\tpublic <T> ").append(returnType).append(" replaceX").append(dim)
                .append("(T t) {\n")
                .append("\t\treturn new ").append(getShortGenericType(dimension))
                .append("(")
                .append(getDimensionArguments(dimension).replace("x" + dim, "t"))
                .append(");\n\t}");
    }

    private void newLine() {
        src.append('\n');
    }

    private void newLine(int amount) {
        for (int i = 0; i < amount; ++i) {
            newLine();
        }
    }



}
