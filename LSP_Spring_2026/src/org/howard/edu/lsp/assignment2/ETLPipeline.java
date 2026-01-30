package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {

    	// Define the path of the input file in the data/ directory
        String inputPath = "data/products.csv";
        // Define the path of the output file in the data/ directory
        String outputPath = "data/transformed_products.csv";

        // Initialize variables for later use
        int rowsRead = 0;
        int rowsWritten = 0;
        int rowsSkipped = 0;

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
        	// Read a CSV file named products.csv from the data/ directory
            reader = new BufferedReader(new FileReader(inputPath));
            // Create a new CSV file to write the transformed data
            writer = new BufferedWriter(new FileWriter(outputPath));

            // Always write a header row in the correct output column order: ProductID, Name, Price, Category, PriceRange
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            // Read in the first row (header) and do not transform it
            String line = reader.readLine();
            // if there is no header row, print a summary 
            if (line == null) {
                printSummary(rowsRead, rowsWritten, rowsSkipped, outputPath);
                return;
            }

            // Reads a full line from the CSV
            while ((line = reader.readLine()) != null) {
                // Counts number of rows read (non-header lines encountered, including bad ones)
            	rowsRead++;

            	// Skips and counts any skipped that are blank
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // Counts any rows skipped that do not contain exactly four comma-separated fields
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                	// Trims whitespace around each field before processing
                    int productId = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim().toUpperCase();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    // If the category is "Electronics", apply a 10% discount to the price
                    boolean wasElectronics = category.equals("Electronics");

                    if (wasElectronics) {
                        price = price.multiply(new BigDecimal("0.9"));
                    }

                    // Prices must be rounded to exactly two decimal places using round-half-up.
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // If the final rounded price is strictly greater than $500.00 AND the original category was "Electronics", change the category to "Premium Electronics".
                    if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
                        category = "Premium Electronics";
                    }

                    // Update field PriceRange based on the final rounded price
                    String priceRange = determinePriceRange(price);

                    // Write the transformed data to data/transformed_products.csv
                    writer.write(productId + "," +
                                 name + "," +
                                 price.toString() + "," +
                                 category + "," +
                                 priceRange);
                    writer.newLine();

                    // Counts any rows transformed (written to output)
                    rowsWritten++;

                } catch (Exception e) {
                	// Counts any rows skipped that have a ProductID or Price that cannot be parsed
                    rowsSkipped++;
                }
            }

            // At the end of execution, print a summary
            printSummary(rowsRead, rowsWritten, rowsSkipped, outputPath);

        } catch (IOException e) {
        	// The file data/products.csv does not exist; your program must print a clear error and exit cleanly.
        	System.out.println("Error: Unable to read input file " + inputPath + ".");
        } finally {
        	// Ensure files are closed
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    /*
     * Determine PriceRange based on the final rounded price: 
     * -  <= $10.00 → Low
     * -  > $10.00 and <= $100.00 → Medium
     * - > $100.00 and <= $500.00 → High
     * - > $500.00 → Premium
     */    private static String determinePriceRange(BigDecimal price) {
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

    /*
     * At the end of execution, print a summary that includes: 
     * - Number of rows read (non-header lines encountered, including bad ones)
     * - Number of rows transformed (written to output)
     * - Number of rows skipped
     * - Output file path written 
     */
    
    private static void printSummary(int read, int written, int skipped, String outputPath) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath);
    }
}
