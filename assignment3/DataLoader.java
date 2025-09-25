package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;

/**
 * Handles the loading phase of the ETL pipeline by writing transformed product data
 * to CSV files. This class manages file creation, CSV formatting, and output verification.
 * 
 * @author [Tarek Delavallade]
 * @version 1.0
 */
public class DataLoader {
    
    /**
     * Writes a list of products to a CSV file with proper headers and formatting.
     * The output format follows: ProductID,Name,Price,Category,PriceRange
     * 
     * @param products the list of products to write to the CSV file
     * @param filePath the relative path where the CSV file will be created
     * @throws IOException if the file cannot be created or written to
     */
    public void loadToCsv(List<Product> products, String filePath) throws IOException {
        File outputFile = new File(filePath);
        System.out.println("Output file will be written to: " + outputFile.getAbsolutePath());
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();
            
            // Write data rows
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
        
        // Verify file was created and report status
        if (outputFile.exists()) {
            System.out.println("File successfully created at: " + outputFile.getAbsolutePath());
            System.out.println("File size: " + outputFile.length() + " bytes");
        } else {
            System.out.println("ERROR: File was NOT created at: " + outputFile.getAbsolutePath());
        }
    }
}