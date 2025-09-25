package org.howard.edu.lsp.assignment3;

/**
 * Manages statistics and reporting for the ETL pipeline execution.
 * This class tracks the number of rows processed and provides summary
 * information about the pipeline's performance and results.
 * 
 * @author [Tarek Delavallade]
 * @version 1.0
 */
public class ETLPipelineStats {
    private int rowsRead;
    private int rowsTransformed;
    private int rowsSkipped;
    private String outputPath;
    
    /**
     * Constructs a new ETLPipelineStats object with initial values set to zero.
     */
    public ETLPipelineStats() {
        this.rowsRead = 0;
        this.rowsTransformed = 0;
        this.rowsSkipped = 0;
        this.outputPath = "";
    }
    
    /**
     * Increments the count of rows read from the input file.
     */
    public void incrementRowsRead() { 
        rowsRead++; 
    }
    
    /**
     * Increments the count of rows successfully transformed.
     */
    public void incrementRowsTransformed() { 
        rowsTransformed++; 
    }
    
    /**
     * Increments the count of rows that were skipped due to errors.
     */
    public void incrementRowsSkipped() { 
        rowsSkipped++; 
    }
    
    /**
     * Sets the output file path for reporting purposes.
     * 
     * @param outputPath the path where the output file was written
     */
    public void setOutputPath(String outputPath) { 
        this.outputPath = outputPath; 
    }
    
    /**
     * Gets the total number of rows read from the input file.
     * 
     * @return the number of rows read
     */
    public int getRowsRead() { 
        return rowsRead; 
    }
    
    /**
     * Gets the total number of rows successfully transformed.
     * 
     * @return the number of rows transformed
     */
    public int getRowsTransformed() { 
        return rowsTransformed; 
    }
    
    /**
     * Gets the total number of rows that were skipped due to errors.
     * 
     * @return the number of rows skipped
     */
    public int getRowsSkipped() { 
        return rowsSkipped; 
    }
    
    /**
     * Prints a detailed summary of the ETL pipeline execution to the console.
     * The summary includes counts of rows processed and the output file location.
     */
    public void printSummary() {
        System.out.println("ETL Pipeline completed successfully!");
        System.out.println("Summary:");
        System.out.println("  Rows read: " + rowsRead);
        System.out.println("  Rows transformed: " + rowsTransformed);
        System.out.println("  Rows skipped: " + rowsSkipped);
        System.out.println("  Output written to: " + outputPath);
    }
}