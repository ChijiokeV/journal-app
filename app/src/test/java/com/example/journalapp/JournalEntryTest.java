package com.example.journalapp;

import com.example.journalapp.models.JournalEntry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for JournalEntry model class.
 */
public class JournalEntryTest {
    
    private JournalEntry entry;
    
    @Before
    public void setUp() {
        entry = new JournalEntry("Test Title", "Test Content");
    }
    
    @Test
    public void testEntryCreation() {
        assertNotNull(entry);
        assertEquals("Test Title", entry.getTitle());
        assertEquals("Test Content", entry.getContent());
    }
    
    @Test
    public void testEntrySettersAndGetters() {
        entry.setId(1);
        entry.setTitle("Updated Title");
        entry.setContent("Updated Content");
        entry.setMood("😊 Happy");
        entry.setTags("journal, reflection");
        
        assertEquals(1, entry.getId());
        assertEquals("Updated Title", entry.getTitle());
        assertEquals("Updated Content", entry.getContent());
        assertEquals("😊 Happy", entry.getMood());
        assertEquals("journal, reflection", entry.getTags());
    }
    
    @Test
    public void testEntryTimestamps() {
        long createdTime = entry.getCreatedAt();
        long updatedTime = entry.getUpdatedAt();
        
        assertTrue(createdTime > 0);
        assertTrue(updatedTime > 0);
        assertEquals(createdTime, updatedTime);
    }
    
    @Test
    public void testEntryUpdateTimestamp() {
        long originalTime = entry.getUpdatedAt();
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long newTime = System.currentTimeMillis();
        entry.setUpdatedAt(newTime);
        
        assertTrue(entry.getUpdatedAt() > originalTime);
    }
    
    @Test
    public void testEntryToString() {
        entry.setId(1);
        String result = entry.toString();
        
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("Test Title"));
        assertNotNull(result);
    }
    
    @Test
    public void testEntryWithAllParameters() {
        JournalEntry fullEntry = new JournalEntry("Full Title", "Full Content", "tag1, tag2", "😊 Happy");
        
        assertEquals("Full Title", fullEntry.getTitle());
        assertEquals("Full Content", fullEntry.getContent());
        assertEquals("tag1, tag2", fullEntry.getTags());
        assertEquals("😊 Happy", fullEntry.getMood());
    }
}
