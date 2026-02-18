/**
 * Name: Taylor Vander
 * @param args
 */

package org.howard.edu.lsp.assigment3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Extracts Product objects from a CSV input file.
 *
 * This class reads product data from the specified file,
 * validates rows, and converts valid rows into Product objects.
 * It also tracks the number of rows read and skipped.
 */
public class ProductExtractor {
    private final String inputPath;
    private int rowsRead = 0;
    private int rowsSkipped = 0;

    /**
     * Constructs a ProductExtractor with the given input file path.
     */
    public ProductExtractor(String inputPath) {
        this.inputPath = inputPath;
    }

    
    /**
     * Reads the input file and converts valid rows into Product objects.
     *
     * Rows that are blank, malformed, or contain invalid data
     * are skipped and counted.
     */ 
    public List<Product> extract() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {

            String line = reader.readLine();
            if (line == null) {
                return products;
            }

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int productId = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    products.add(new Product(productId, name, price, category, null));

                } catch (Exception e) {
                    rowsSkipped++;
                }
            }
        }

        return products;
    }

    /**
     * Returns the number of rows read from the input file
     * (excluding the header).
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Returns the number of rows skipped due to errors or invalid format.
     */
    public int getRowsSkipped() {
        return rowsSkipped;
    }
}
