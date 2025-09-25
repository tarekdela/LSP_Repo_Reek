package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product with its properties including ID, name, price, category, and price range.
 * This class serves as the data model for the ETL pipeline, encapsulating all product-related
 * information and providing methods to access and modify product data.
 * 
 * @author [Tarek Delavallade]
 * @version 1.0
 */
public class Product {
    private int productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;
    
    /**
     * Constructs a new Product with the specified details.
     * The price range is initially set to null and will be calculated during transformation.
     * 
     * @param productId the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product as a BigDecimal
     * @param category the category the product belongs to
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = null; // Will be set during transformation
    }
    
    /**
     * Gets the product ID.
     * 
     * @return the unique identifier of the product
     */
    public int getProductId() { 
        return productId; 
    }
    
    /**
     * Gets the product name.
     * 
     * @return the name of the product
     */
    public String getName() { 
        return name; 
    }
    
    /**
     * Gets the product price.
     * 
     * @return the price of the product as a BigDecimal
     */
    public BigDecimal getPrice() { 
        return price; 
    }
    
    /**
     * Gets the product category.
     * 
     * @return the category of the product
     */
    public String getCategory() { 
        return category; 
    }
    
    /**
     * Gets the price range classification of the product.
     * 
     * @return the price range (Low, Medium, High, or Premium), or null if not set
     */
    public String getPriceRange() { 
        return priceRange; 
    }
    
    /**
     * Sets the product name.
     * 
     * @param name the new name for the product
     */
    public void setName(String name) { 
        this.name = name; 
    }
    
    /**
     * Sets the product price.
     * 
     * @param price the new price for the product
     */
    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }
    
    /**
     * Sets the product category.
     * 
     * @param category the new category for the product
     */
    public void setCategory(String category) { 
        this.category = category; 
    }
    
    /**
     * Sets the price range classification for the product.
     * 
     * @param priceRange the price range classification (Low, Medium, High, or Premium)
     */
    public void setPriceRange(String priceRange) { 
        this.priceRange = priceRange; 
    }
    
    /**
     * Returns a CSV-formatted string representation of the product.
     * The format follows: ProductID,Name,Price,Category,PriceRange
     * 
     * @return a comma-separated string representation of the product
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%.2f,%s,%s", 
            productId, name, price.doubleValue(), category, 
            priceRange != null ? priceRange : "");
    }
}