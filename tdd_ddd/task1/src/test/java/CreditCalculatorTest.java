import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class CreditCalculatorTest {
    Random random = new Random();

    @Test
    public void testCalcMonthlyPayment() {
        CreditCalculator calculator = new CreditCalculator();
        double var1 = random.nextDouble();
        int var2 = random.nextInt();
        double result = calculator.calcMonthlyPayment(var1, var2);
        Assertions.assertEquals(var1 / var2, result, "Ежемесячный платеж за кредит на сумму " + var1 + " на " + var2 +
                " месяцев.");
    }

    @Test
    public void testCalcPaymentSum() {
        CreditCalculator calculator = new CreditCalculator();
        int var1 = random.nextInt();
        int var2 = random.nextInt();
        double var3 = random.nextFloat();
        double result = calculator.calcPaymentSum(var1, var2, var3);
        double expected = var1 + (var1 * ((var3 / 12) * var2));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCalcOverpayment() {
        CreditCalculator calculator = new CreditCalculator();
        double var1 = random.nextDouble();
        int var2 = random.nextInt();
        double result = calculator.calcOverpayment(var1, var2);
        double expected = var1 - var2;
        Assertions.assertEquals(expected, result);
    }
}
