package exprprser;

public class AST {

    public static abstract class Exper {

        public abstract double evaluate();
        
    }

    public static class AddExper extends Exper {

        Exper left, right;

        public AddExper(Exper left, Exper right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return left.toString() + " + " + right.toString();
        }

        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }
    }

    public static class MulExper extends Exper {

        Exper left, right;

        public MulExper(Exper left, Exper right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return left.toString() + " * " + right.toString();
        }

        @Override
        public double evaluate() {
            return left.evaluate() * right.evaluate();
        }
    }

    public static class NumExper extends Exper {

        double value;

        public NumExper(double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "";
        }

        @Override
        public double evaluate() {
            return value;
        }
    }

}
