package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers.
 *
 * @author Taylor Vander
 */
public class HolidayPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a holiday customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}