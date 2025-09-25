package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Main orchestrator class for the ETL (Extract-Transform-Load) pipeline.
 * This class coordinates the entire ETL process by managing the extraction of data
 * from CSV files, applying business transformations, and loading the results
 * back to a new CSV file. It maintains the same functionality as Assignment 2
 * while implementing improved object-oriented design principles.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class ETLPipeline {
    
    /** Input CSV file path relative to the project root */
    private static final String INPUT_FILE = "data/products.csv";
    
    /** Output CSV file path relative to the project root */
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    private DataExtractor extractor;
    private ProductTransformer transformer;
    private DataLoader loader;
    private ETLPipelineStats stats;
    
    /**
     * Constructs a new ETLPipeline with all necessary components initialized.
     * Uses composition to create instances of each component responsible for
     * different phases of the ETL process.
     */
    public ETLPipeline() {
        this.extractor = new DataExtractor();
        this.transformer = new ProductTransformer();
        this.loader = new DataLoader();
        this.stats = new ETLPipelineStats();
    }
    
    /**
     * Main method that serves as the entry point for the ETL pipeline application.
     * 
     * @param args command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.runPipeline();
    }
    
    /**
     * Executes the complete ETL pipeline process including extraction, transformation,
     * and loading phases. Handles all errors gracefully and provides detailed
     * reporting of the pipeline execution results.
     * 
     * The pipeline maintains the exact same functionality as Assignment 2:
     * - Same input/output files and paths
     * - Same transformations in the same order
     * - Same error handling for missing/empty files
     * - Same summary reporting format
     */
    public void runPipeline() {
        try {
            // Extract phase: Read products from CSV file
            List<Product> products = extractor.extractFromCsv(INPUT_FILE);
            stats.incrementRowsRead(); // Count header row
            
            // Count data rows
            for (Product product : products) {
                stats.incrementRowsRead();
                stats.incrementRowsTransformed();
            }
            
            // Transform phase: Apply business rules to products
            transformer.transformProducts(products);
            
            // Load phase: Write transformed data to output file
            loader.loadToCsv(products, OUTPUT_FILE);
            stats.setOutputPath(OUTPUT_FILE);
            
            // Report phase: Display execution summary
            stats.printSummary();
            
        } catch (Exception e) {
            System.err.println("Error in ETL pipeline: " + e.getMessage());
            e.printStackTrace();
        }
    }
}