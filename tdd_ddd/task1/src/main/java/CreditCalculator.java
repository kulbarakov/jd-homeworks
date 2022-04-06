public class CreditCalculator {
    public double calcMonthlyPayment(double paymentSum, int monthCount) {
        return paymentSum / monthCount;
    }

    public double calcPaymentSum(int creditSum, int monthCount, double annualRate) {
        return creditSum + (creditSum * ((annualRate / 12) * monthCount));
    }

    public double calcOverpayment(double paymentSum, int creditSum) {
        return paymentSum - creditSum;
    }
}
