package calcmvvm.k.calculator.model;


import java.util.Arrays;

public class SupportedOperation {

    ExpressionEvaluation expressionEvaluation;
    Division division;
    Plus plus;
    Minus minus;
    int whereToEnd;
    Multiplication mulitplication;
    String fragmentedStatement;

    public String makeCalculation(String inputStatement) {
        boolean isCalculationFinished = false;
        int whereToStart = 0;
        int whereToEnd;
        String fragmenentedStatement;
        String resultFromCalculation = "";
        String[] actualInputExpression = new String[inputStatement.length()];
        while (isCalculationFinished == true) {

            actualInputExpression = convertFromStringToTabString(inputStatement);
            actualInputExpression = executionOfParenthesis(actualInputExpression);
            performEquationsWithoutParenthesis(actualInputExpression);


        }

        resultFromCalculation = actualInputExpression[0];
        return resultFromCalculation;
    }

    private String performEquationsWithoutParenthesis(String[] actualInputExpression) {
        boolean wasMultiplicationAndDivisionExecuted = false;
        for (int i = 0; i < actualInputExpression.length; i++) {
            if (!wasMultiplicationAndDivisionExecuted) {
                for (int j = 0; j < actualInputExpression.length; j++) {

                    if(j==actualInputExpression.length-1){
                        wasMultiplicationAndDivisionExecuted=true;
                    }

                    if (actualInputExpression[j].contains("*")) {
                        actualInputExpression = executeMultiplication(actualInputExpression, j);
                        j = 0;

                    }
                    if (actualInputExpression[j].contains("/")) {
                        executeDivision(actualInputExpression, j);
                        actualInputExpression = executeDivision(actualInputExpression, j);
                        j = 0;
                    }
                }
            }
            if (actualInputExpression[i].contains("+")) {
                executePlus(actualInputExpression, i);
                actualInputExpression = executePlus(actualInputExpression, i);
                i = 0;
            }

            if (actualInputExpression[i].contains("-")) {
                actualInputExpression = executeMinus(actualInputExpression, i);
                i = 0;
            }

        }
        return actualInputExpression[0].toString();
    }

    private String[] executionOfParenthesis(String[] actualInputExpression) {
        // String [] expressionAtBegin = actualInputExpression;
        int whereToEnd = 0;
        int whereToStart = 0;
        for (int i = 0; i < actualInputExpression.length; i++) {
            if (actualInputExpression[i].contains("(")) {
                whereToStart = i;
                if (actualInputExpression[i].contains(")")) {
                    whereToEnd = i;
                }
            }

            for (i = whereToStart; i != whereToEnd; i++) {
                actualInputExpression = executeMultiplication(actualInputExpression, i);
                actualInputExpression = executeDivision(actualInputExpression, i);
                actualInputExpression = executePlus(actualInputExpression, i);
                actualInputExpression = executeMinus(actualInputExpression, i);
            }


        }


        return actualInputExpression;
    }

    private String[] executeMinus(String[] actualInputExpression, int i) {
        if (actualInputExpression[i].contains("-")) {
            minus.setA(Double.parseDouble(actualInputExpression[i - 1]));
            minus.setB(Double.parseDouble(actualInputExpression[i + 1]));
            actualInputExpression[i - 1] = String.valueOf(expressionEvaluation.giveResult(minus));
            actualInputExpression[i] = "";
            actualInputExpression[i + 1] = "";
            actualInputExpression = removeNullFromArray(actualInputExpression);

        }
        return actualInputExpression;
    }

    private String[] executePlus(String[] actualInputExpression, int i) {
        if (actualInputExpression[i].contains("+")) {
            plus.setA(Double.parseDouble(actualInputExpression[i - 1]));
            plus.setB(Double.parseDouble(actualInputExpression[i + 1]));
            actualInputExpression[i - 1] = String.valueOf(expressionEvaluation.giveResult(plus));
            actualInputExpression[i] = "";
            actualInputExpression[i + 1] = "";
            actualInputExpression = removeNullFromArray(actualInputExpression);

        }
        return actualInputExpression;
    }

    private String[] executeDivision(String[] actualInputExpression, int i) {
        if (actualInputExpression[i].contains("/")) {
            division.setA(Double.parseDouble(actualInputExpression[i - 1]));
            division.setB(Double.parseDouble(actualInputExpression[i + 1]));
            actualInputExpression[i - 1] = String.valueOf(expressionEvaluation.giveResult(division));
            actualInputExpression[i] = "";
            actualInputExpression[i + 1] = "";
            actualInputExpression = removeNullFromArray(actualInputExpression);
        }
        return actualInputExpression;
    }

    private String[] executeMultiplication(String[] actualInputExpression, int i) {
        if (actualInputExpression[i].contains("*")) {
            mulitplication.setA(Double.parseDouble(actualInputExpression[i - 1]));
            mulitplication.setB(Double.parseDouble(actualInputExpression[i + 1]));
            actualInputExpression[i - 1] = String.valueOf(expressionEvaluation.giveResult(mulitplication));
            actualInputExpression[i] = "";
            actualInputExpression[i + 1] = "";
            actualInputExpression = removeNullFromArray(actualInputExpression);
        }
        return actualInputExpression;
    }

    private String[] convertFromStringToTabString(String inputStatement) {

        String[] convertedString = new String[inputStatement.length()];
        int statementIteration;
        int tableIteration = 0;

        for (int i = 0; i < inputStatement.length(); i++) {

            if (String.valueOf(inputStatement.charAt(i + 1)).contains(".")) {
                statementIteration = i;
                while (!String.valueOf(inputStatement.charAt(i + 1)).contentEquals("+") ||
                        !String.valueOf(inputStatement.charAt(i + 1)).contentEquals("-") ||
                        !String.valueOf(inputStatement.charAt(i + 1)).contentEquals("*") ||
                        !String.valueOf(inputStatement.charAt(i + 1)).contentEquals("/") ||
                        !String.valueOf(inputStatement.charAt(i + 1)).contentEquals("(") ||
                        !String.valueOf(inputStatement.charAt(i + 1)).contentEquals(")")
                        ) {
                    convertedString[tableIteration] = convertedString[tableIteration] + inputStatement.charAt(statementIteration + 1);
                    statementIteration++;
                    i++;
                }
            } else {
                convertedString[tableIteration] = String.valueOf(inputStatement.charAt(i));
                tableIteration++;
            }

        }
        return convertedString;
    }


    private String[] removeNullFromArray(String[] arrayToCheck) {
        String[] removedNull = Arrays.stream(arrayToCheck)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);
        return removedNull;
    }
}

abstract class Result {
    public abstract double operation();

}


class Plus extends Result {
    private double a, b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double operation() {
        return a * b;
    }
}

class Minus extends Result {

    private double a, b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double operation() {
        return a - b;
    }
}

class Division extends Result {

    private double a, b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double operation() {
        return a / b;
    }
}

class Multiplication extends Result {

    private double a, b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double operation() {
        return a * b;
    }
}

class ExpressionEvaluation {
    public double giveResult(Result result) {
        return result.operation();
    }
}

}
