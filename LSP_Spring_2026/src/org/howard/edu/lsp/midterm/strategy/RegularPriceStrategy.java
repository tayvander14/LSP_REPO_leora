package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 *
 * @author Taylor Vander
 */
public class RegularPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a regular customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price;
    }
}