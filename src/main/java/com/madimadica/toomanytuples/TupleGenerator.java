package com.madimadica.toomanytuples;

import java.util.ArrayList;
import java.util.List;

import static com.madimadica.toomanytuples.GeneratorUtils.*;

public class TupleGenerator {
    private final StringBuilder src = new StringBuilder();
    private final int dimension;
    private final String className;
    private final List<Integer> dimensions;

    public TupleGenerator(int dimension) {
        this.dimension = dimension;
        this.className = "Tuple" + dimension + "D";
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
        src.append("}");
        System.out.println(src);
    }

    private void addClassDeclaration() {
        src.append("public class ").append(className);
        src.append(getDimensionGenerics(dimensions));
        src.append(" {");
    }

    private void addClassAttributes() {
        for (int i = 0; i < dimension; ++i) {
            if (i > 0) {
                newLine();
            }
            src.append("\tprivate ").append(getDimensionArg(i)).append(";");
        }
    }

    private void addNoArgsConstructor() {
        src.append("\tpublic ").append(className).append("() {}");
    }

    private void addAllArgsConstructor() {
        src.append("\tpublic ").append(className).append('(').append(getDimensionArgs(dimensions)).append(") {");
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
        src.append("\tpublic void setX").append(dim).append("(").append(getDimensionArg(dim)).append(") {\n")
                .append("\t\tthis.x").append(dim).append(" = x").append(dim).append(";\n\t}");
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
