package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class that uses a pricing strategy to calculate a final price.
 *
 * @author Taylor Vander
 */
public class PriceCalculator {

    private PriceStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to use
     */
    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets the pricing strategy to use.
     *
     * @param strategy the new pricing strategy
     */
    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original purchase price
     * @return the final calculated price
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}