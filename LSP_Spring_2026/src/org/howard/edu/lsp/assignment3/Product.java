/**
 * Name: Taylor Vander
 * @param args
 */

package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product record in the ETL pipeline.
 * 
 * This class encapsulates product attributes including
 * product ID, name, price, category, and price range.
 * Instances of this class are immutable once created.
 */

public class Product {

    private final int productId;
    private final String name;
    private final BigDecimal price;
    private final String category;
    private final String priceRange;

    /**
     * Constructs a Product object.
     */
    public Product(int productId, String name, BigDecimal price, String category, String priceRange) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    /**
     * Returns the product ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the product price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Returns the product category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the product price range classification.
     */
    public String getPriceRange() {
        return priceRange;
    }
}
