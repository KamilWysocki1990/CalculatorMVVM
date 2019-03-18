package calcmvvm.k.calculator.model;

public class SupportedOperation {

    public abstract class Result {
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

    class Mulitiplication extends Result {

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
