package divide_conquer;

import org.junit.jupiter.api.Test;

public class TestClass {

    @Test
    public void test() {

        int startIndex = 0;
        int endIndex = 5;

        int numberOfElements = endIndex - startIndex;
        int medianIndex = startIndex + (numberOfElements / 2 + numberOfElements % 2 - 1);

        System.out.println(medianIndex);
    }
}
