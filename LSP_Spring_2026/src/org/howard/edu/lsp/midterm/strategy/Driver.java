package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class to demonstrate the Strategy Pattern for price calculation.
 *
 * @author Taylor Vander
 */
public class Driver {

    /**
     * Main method to test different pricing strategies.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        double price = 100.0;

        PriceCalculator calculator;

        // REGULAR
        calculator = new PriceCalculator(new RegularPriceStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        // MEMBER
        calculator.setStrategy(new MemberPriceStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        // VIP
        calculator.setStrategy(new VIPPriceStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        // HOLIDAY
        calculator.setStrategy(new HolidayPriceStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}