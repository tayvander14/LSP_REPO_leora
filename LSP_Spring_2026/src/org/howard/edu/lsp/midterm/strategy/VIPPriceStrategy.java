package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 *
 * @author Taylor Vander
 */
public class VIPPriceStrategy extends PriceStrategy {

    /**
     * Calculates the final price for a VIP customer.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}