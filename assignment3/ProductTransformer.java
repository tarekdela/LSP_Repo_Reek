package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Handles the transformation phase of the ETL pipeline by applying business rules
 * to product data. This class implements all transformation logic including name
 * formatting, price discounts, category updates, and price range classification.
 * 
 * @author [Tarek Delavallade]
 * @version 1.0
 */
public class ProductTransformer {
    
    /**
     * Applies transformations to a list of products in the specified order:
     * 1. Convert product names to UPPERCASE
     * 2. Apply 10% discount to Electronics category items
     * 3. Recategorize high-value Electronics to Premium Electronics
     * 4. Calculate and assign price range based on final price
     * 
     * @param products the list of products to transform
     */
    public void transformProducts(List<Product> products) {
        for (Product product : products) {
            transformProduct(product);
        }
    }
    
    /**
     * Applies all transformations to a single product following the business rules.
     * Transformations are applied in a specific order to ensure correct results.
     * 
     * @param product the product to transform
     */
    private void transformProduct(Product product) {
        // Step 1: Convert name to UPPERCASE
        product.setName(product.getName().toUpperCase());
        
        // Step 2: Apply 10% discount to Electronics category
        if ("Electronics".equals(product.getCategory())) {
            BigDecimal discountedPrice = product.getPrice()
                .multiply(new BigDecimal("0.9"))
                .setScale(2, RoundingMode.HALF_UP);
            product.setPrice(discountedPrice);
        }
        
        // Step 3: Recategorize if necessary
        if ("Electronics".equals(product.getCategory()) && 
            product.getPrice().compareTo(new BigDecimal("500.00")) > 0) {
            product.setCategory("Premium Electronics");
        }
        
        // Step 4: Determine price range based on final price
        String priceRange = determinePriceRange(product.getPrice());
        product.setPriceRange(priceRange);
    }
    
    /**
     * Determines the price range classification based on the product's final price.
     * Price ranges are defined as:
     * - Low: $0.00 - $10.00
     * - Medium: $10.01 - $100.00
     * - High: $100.01 - $500.00
     * - Premium: $500.01 and above
     * 
     * @param price the final price of the product
     * @return the price range classification as a string
     */
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