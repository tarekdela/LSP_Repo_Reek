package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {
    
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    public static void main(String[] args) {
        ETLPipeline etl = new ETLPipeline();
        etl.runPipeline();
    }
    
    public void runPipeline() {
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;
        
        try {
            // Check if input file exists
            File inputFile = new File(INPUT_FILE);
            System.out.println("Looking for input file at: " + inputFile.getAbsolutePath());
            if (!inputFile.exists()) {
                System.err.println("Error: Input file '" + INPUT_FILE + "' not found.");
                System.err.println("Please ensure the file exists in the data/ directory relative to the project root.");
                System.err.println("Current working directory: " + System.getProperty("user.dir"));
                return;
            }
            
            // Show where output will be written
            File outputFile = new File(OUTPUT_FILE);
            System.out.println("Output file will be written to: " + outputFile.getAbsolutePath());
            
            // Read input file and write output file
            try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
                
                String line;
                boolean isFirstLine = true;
                
                while ((line = reader.readLine()) != null) {
                    rowsRead++;
                    
                    if (isFirstLine) {
                        // Write header to output file
                        writer.write("ProductID,Name,Price,Category,PriceRange");
                        writer.newLine();
                        isFirstLine = false;
                        continue;
                    }
                    
                    try {
                        String transformedLine = transformRow(line);
                        if (transformedLine != null) {
                            writer.write(transformedLine);
                            writer.newLine();
                            rowsTransformed++;
                        } else {
                            rowsSkipped++;
                        }
                    } catch (Exception e) {
                        System.err.println("Warning: Skipping malformed row: " + line);
                        System.err.println("Error: " + e.getMessage());
                        rowsSkipped++;
                    }
                }
            }
            
            // Check if output file was actually created
            File checkOutput = new File(OUTPUT_FILE);
            if (checkOutput.exists()) {
                System.out.println("File successfully created at: " + checkOutput.getAbsolutePath());
                System.out.println("File size: " + checkOutput.length() + " bytes");
            } else {
                System.out.println("ERROR: File was NOT created at: " + checkOutput.getAbsolutePath());
            }
            
            // Print summary
            System.out.println("ETL Pipeline completed successfully!");
            System.out.println("Summary:");
            System.out.println("  Rows read: " + rowsRead);
            System.out.println("  Rows transformed: " + rowsTransformed);
            System.out.println("  Rows skipped: " + rowsSkipped);
            System.out.println("  Output written to: " + OUTPUT_FILE);
            
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private String transformRow(String csvRow) {
        if (csvRow == null || csvRow.trim().isEmpty()) {
            return null;
        }
        
        // Parse CSV row
        String[] fields = csvRow.split(",");
        if (fields.length != 4) {
            throw new IllegalArgumentException("Expected 4 fields, found " + fields.length);
        }
        
        try {
            // Extract fields
            String productIdStr = fields[0].trim();
            String name = fields[1].trim();
            String priceStr = fields[2].trim();
            String category = fields[3].trim();
            
            // Validate ProductID
            int productId = Integer.parseInt(productIdStr);
            
            // Parse price
            BigDecimal price = new BigDecimal(priceStr);
            
            // Transform order: (1) uppercase name → (2) discount → (3) recategorization → (4) price range
            
            // Step 1: Convert name to UPPERCASE
            String transformedName = name.toUpperCase();
            
            // Step 2: Apply 10% discount to Electronics category
            BigDecimal transformedPrice = price;
            if ("Electronics".equals(category)) {
                transformedPrice = price.multiply(new BigDecimal("0.9"));
                transformedPrice = transformedPrice.setScale(2, RoundingMode.HALF_UP);
            }
            
            // Step 3: Recategorize if necessary
            String transformedCategory = category;
            if ("Electronics".equals(category) && transformedPrice.compareTo(new BigDecimal("500.00")) > 0) {
                transformedCategory = "Premium Electronics";
            }
            
            // Step 4: Determine price range based on final price
            String priceRange = determinePriceRange(transformedPrice);
            
            // Format output
            return String.format("%d,%s,%.2f,%s,%s", 
                productId, 
                transformedName, 
                transformedPrice.doubleValue(), 
                transformedCategory, 
                priceRange);
                
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in row: " + csvRow, e);
        }
    }
    
    private String determinePriceRange(BigDecimal price) {
        BigDecimal ten = new BigDecimal("10.00");
        BigDecimal hundred = new BigDecimal("100.00");
        BigDecimal fiveHundred = new BigDecimal("500.00");
        
        if (price.compareTo(ten) <= 0) {
            return "Low";
        } else if (price.compareTo(hundred) <= 0) {
            return "Medium";
        } else if (price.compareTo(fiveHundred) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}