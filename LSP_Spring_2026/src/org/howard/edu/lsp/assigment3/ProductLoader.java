/**
 * Name: Taylor Vander
 * @param args
 */

package org.howard.edu.lsp.assigment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * Loads transformed Product objects into a CSV output file.
 *
 * This class writes a header row followed by
 * transformed product records.
 */
public class ProductLoader {

    private final String outputPath;

    /**
     * Constructs a ProductLoader with the specified output file path.
     */
    public ProductLoader(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Writes a list of Product objects to the output CSV file.
     */
    public void load(List<Product> products) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            for (Product product : products) {
                writer.write(product.getProductId() + "," +
                             product.getName() + "," +
                             product.getPrice().toString() + "," +
                             product.getCategory() + "," +
                             product.getPriceRange());
                writer.newLine();
            }
        }
    }
}
