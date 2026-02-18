/**
 * Name: Taylor Vander
 * @param args
 */

package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies business transformation rules to Product objects.
 *
 * Transformation rules include:
 * - Converting product names to uppercase
 * - Applying a 10% discount to Electronics
 * - Rounding prices to two decimal places
 * - Updating category to "Premium Electronics" when applicable
 * - Determining the appropriate price range classification
 */

public class ProductTransformer implements Transformer<Product> {
    @Override
    
    /**
     * Applies transformation rules to a Product and returns a new transformed Product.
     */
    public Product transform(Product input) {

        int productId = input.getProductId();
        String name = input.getName().toUpperCase();
        BigDecimal price = input.getPrice();
        String category = input.getCategory();

        boolean wasElectronics = category.equals("Electronics");

        if (wasElectronics) {
            price = price.multiply(new BigDecimal("0.9"));
        }

        price = price.setScale(2, RoundingMode.HALF_UP);

        if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
            category = "Premium Electronics";
        }

        String priceRange = determinePriceRange(price);

        return new Product(productId, name, price, category, priceRange);
    }
    
    /**
     * Determines the price range classification based on price.
     */
    private String determinePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}
