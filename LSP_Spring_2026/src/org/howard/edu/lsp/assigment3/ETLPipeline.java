/**
 * Name: Taylor Vander
 * @param args
 */

package org.howard.edu.lsp.assigment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Coordinates the Extract-Transform-Load (ETL) process for product data.
 * 
 * The pipeline reads product records from an input CSV file,
 * applies transformation rules, and writes the transformed
 * records to an output CSV file. It also prints a summary
 * of rows processed.
 */
public class ETLPipeline {
    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Entry point of the ETL application.
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
	}
    
    /**
     * Executes the full ETL process:
     * extraction, transformation, loading, and summary reporting.
     */
    public void run() {
        int rowsWritten = 0;

        ProductExtractor extractor = new ProductExtractor(INPUT_PATH);
        List<Product> products;

        try {
            products = extractor.extract();
        } catch (IOException e) {
            System.out.println("Error: Unable to read input file " + INPUT_PATH + ".");
            return;
        }

        Transformer<Product> transformer = new ProductTransformer();
        List<Product> transformedProducts = new ArrayList<>();

        for (Product product : products) {
            Product transformed = transformer.transform(product);
            transformedProducts.add(transformed);
            rowsWritten++;
        }

        ProductLoader loader = new ProductLoader(OUTPUT_PATH);

        try {
            loader.load(transformedProducts);
        } catch (IOException e) {
            System.out.println("Error: Unable to write output file " + OUTPUT_PATH + ".");
            return;
        }

        printSummary(extractor.getRowsRead(),
                     rowsWritten,
                     extractor.getRowsSkipped(),
                     OUTPUT_PATH);
    }

    /**
     * Prints a summary of ETL processing results.
     */
    private void printSummary(int read, int written, int skipped, String outputPath) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath);
	}

}
