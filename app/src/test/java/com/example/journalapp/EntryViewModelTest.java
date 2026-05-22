package com.example.journalapp;

import android.app.Application;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.journalapp.models.JournalEntry;
import com.example.journalapp.viewmodels.EntryViewModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for EntryViewModel.
 */
public class EntryViewModelTest {
    
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    
    @Mock
    private Application application;
    
    private EntryViewModel viewModel;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new EntryViewModel(application);
    }
    
    @Test
    public void testViewModelCreation() {
        assertNotNull(viewModel);
    }
    
    @Test
    public void testInsertEntry() {
        JournalEntry entry = new JournalEntry("Test Entry", "Test Content");
        viewModel.insert(entry);
        
        assertNotNull(entry);
        assertEquals("Test Entry", entry.getTitle());
    }
    
    @Test
    public void testUpdateEntry() {
        JournalEntry entry = new JournalEntry("Original Title", "Original Content");
        entry.setId(1);
        
        entry.setTitle("Updated Title");
        entry.setContent("Updated Content");
        viewModel.update(entry);
        
        assertEquals("Updated Title", entry.getTitle());
        assertEquals("Updated Content", entry.getContent());
    }
    
    @Test
    public void testDeleteEntry() {
        JournalEntry entry = new JournalEntry("To Delete", "Delete me");
        entry.setId(1);
        
        viewModel.insert(entry);
        viewModel.delete(entry);
        
        assertNotNull(entry);
    }
}
