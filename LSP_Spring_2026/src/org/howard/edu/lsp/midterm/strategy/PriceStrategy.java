package org.howard.edu.lsp.midterm.strategy;

/**
 * Represents a pricing strategy for calculating a final purchase price.
 *
 * @author Taylor Vander
 */
public abstract class PriceStrategy {

    /**
     * Calculates the final price for the given base price.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    public abstract double calculatePrice(double price);
}