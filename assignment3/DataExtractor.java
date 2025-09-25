package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the extraction phase of the ETL pipeline by reading CSV files and converting
 * them into Product objects. This class is responsible for file validation, CSV parsing,
 * and error handling during the data extraction process.
 * 
 * @author [Tarek Delavallade]
 * @version 1.0
 */
public class DataExtractor {
    
    /**
     * Extracts product data from a CSV file and returns a list of Product objects.
     * The first row is treated as a header and is skipped during processing.
     * 
     * @param filePath the relative path to the CSV file to be read
     * @return a list of Product objects parsed from the CSV file
     * @throws IOException if the file cannot be found or read
     * @throws IllegalArgumentException if the CSV data is malformed
     */
    public List<Product> extractFromCsv(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        
        File file = new File(filePath);
        System.out.println("Looking for input file at: " + file.getAbsolutePath());
        
        if (!file.exists()) {
            throw new FileNotFoundException("Input file '" + filePath + "' not found. " +
                "Please ensure the file exists in the data/ directory relative to the project root. " +
                "Current working directory: " + System.getProperty("user.dir"));
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header
                }
                
                try {
                    Product product = parseProductFromCsvLine(line);
                    products.add(product);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                        "Error parsing line " + lineNumber + ": " + line + 
                        ". Reason: " + e.getMessage(), e);
                }
            }
        }
        
        return products;
    }
    
    /**
     * Parses a single CSV line into a Product object.
     * Expects the format: ProductID,Name,Price,Category
     * 
     * @param csvLine the CSV line to parse
     * @return a Product object created from the CSV data
     * @throws IllegalArgumentException if the line format is invalid or data cannot be parsed
     */
    private Product parseProductFromCsvLine(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty line");
        }
        
        String[] fields = csvLine.split(",");
        if (fields.length != 4) {
            throw new IllegalArgumentException("Expected 4 fields, found " + fields.length);
        }
        
        try {
            int productId = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            BigDecimal price = new BigDecimal(fields[2].trim());
            String category = fields[3].trim();
            
            return new Product(productId, name, price, category);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format", e);
        }
    }
}