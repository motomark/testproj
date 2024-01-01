package lambda.strategy;

import lambda.strategy.Context;
import lambda.strategy.Strategy;

public class StrategyExample {

    /**
     * Concrete implementations of the strategy interface
   static class AddOperation implements Strategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }

    static class SubtractOperation implements Strategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    static class MultiplyOperation implements Strategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2;
        }
    }
     *
     */

    static class DefaultOperation implements Strategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2;
        }
    }


        public static void main(String[] args) {
            // Using lambda expressions for strategies
            Strategy addOperation = (num1, num2) -> num1 + num2;
            Strategy subtractOperation = (num1, num2) -> num1 - num2;
            Strategy multiplyOperation = (num1, num2) -> num1 * num2;
            Strategy defaultOperation = null;

            // Using the strategies
            Context context1 = new Context(addOperation);
            System.out.println("10 + 5 = " + context1.executeStrategy(10, 5));

            Context context2 = new Context(subtractOperation);
            System.out.println("10 - 5 = " + context2.executeStrategy(10, 5));

            Context context3 = new Context(multiplyOperation);
            System.out.println("10 * 5 = " + context3.executeStrategy(10, 5));

            Context context4 = new Context(new DefaultOperation());
            context4.executeStrategy();

        }


}
