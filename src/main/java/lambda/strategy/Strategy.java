package lambda.strategy;

public interface Strategy {

    int doOperation(int num1, int num2);

    default void normalFun()
    {
        System.out.println("Hello");
    }

}
