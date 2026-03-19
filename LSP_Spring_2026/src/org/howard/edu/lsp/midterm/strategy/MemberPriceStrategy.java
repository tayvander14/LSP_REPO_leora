package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 *
 * @author Taylor Vander
 */
public class MemberPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a member customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}