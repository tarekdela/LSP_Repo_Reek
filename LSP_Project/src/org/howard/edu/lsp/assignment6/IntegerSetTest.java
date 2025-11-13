package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for IntegerSet.
 * Tests all public methods with various scenarios.
 */
public class IntegerSetTest {
    
    private IntegerSet set1;
    private IntegerSet set2;
    
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }
    
    @Test
    public void testClear() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.clear();
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }
    
    @Test
    public void testLength() {
        assertEquals(0, set1.length());
        set1.add(1);
        assertEquals(1, set1.length());
        set1.add(2);
        set1.add(3);
        assertEquals(3, set1.length());
    }
    
    @Test
    public void testEquals() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(3);
        set2.add(2);
        set2.add(1);
        
        assertTrue(set1.equals(set2));
    }
    
    @Test
    public void testNotEquals() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(3);
        
        assertFalse(set1.equals(set2));
    }
    
    @Test
    public void testEqualsWithNull() {
        set1.add(1);
        assertFalse(set1.equals(null));
    }
    
    @Test
    public void testContains() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        assertTrue(set1.contains(2));
        assertFalse(set1.contains(5));
    }
    
    @Test
    public void testLargest() {
        set1.add(1);
        set1.add(5);
        set1.add(3);
        
        assertEquals(5, set1.largest());
    }
    
    @Test
    public void testLargestThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            set1.largest();
        });
    }
    
    @Test
    public void testSmallest() {
        set1.add(1);
        set1.add(5);
        set1.add(3);
        
        assertEquals(1, set1.smallest());
    }
    
    @Test
    public void testSmallestThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            set1.smallest();
        });
    }
    
    @Test
    public void testAdd() {
        set1.add(1);
        assertTrue(set1.contains(1));
        assertEquals(1, set1.length());
    }
    
    @Test
    public void testAddDuplicate() {
        set1.add(1);
        set1.add(1);
        assertEquals(1, set1.length());
    }
    
    @Test
    public void testRemove() {
        set1.add(1);
        set1.add(2);
        set1.remove(1);
        
        assertFalse(set1.contains(1));
        assertEquals(1, set1.length());
    }
    
    @Test
    public void testRemoveNonExistent() {
        set1.add(1);
        set1.remove(2);
        assertEquals(1, set1.length());
    }
    
    @Test
    public void testUnion() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        
        set1.union(set2);
        
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }
    
    @Test
    public void testIntersect() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
    }
    
    @Test
    public void testDiff() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        
        set1.diff(set2);
        
        assertEquals(1, set1.length());
        assertTrue(set1.contains(1));
        assertFalse(set1.contains(2));
    }
    
    @Test
    public void testComplement() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.complement(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue(set1.isEmpty());
        set1.add(1);
        assertFalse(set1.isEmpty());
    }
    
    @Test
    public void testToString() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        String result = set1.toString();
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }
}
