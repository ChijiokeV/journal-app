package com.example.journalapp.databinding;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import android.widget.ScrollView;

public class ActivityAddEditEntryBinding {
    public final Toolbar toolbar;
    public final TextInputEditText etTitle;
    public final TextInputEditText etContent;
    public final TextInputEditText etTags;
    public final MaterialAutoCompleteTextView spinnerMood;
    private final ScrollView root;
    
    public ActivityAddEditEntryBinding(View root, Toolbar toolbar, TextInputEditText etTitle,
                                      TextInputEditText etContent, TextInputEditText etTags,
                                      MaterialAutoCompleteTextView spinnerMood) {
        this.root = (ScrollView) root;
        this.toolbar = toolbar;
        this.etTitle = etTitle;
        this.etContent = etContent;
        this.etTags = etTags;
        this.spinnerMood = spinnerMood;
    }
    
    public static ActivityAddEditEntryBinding inflate(android.view.LayoutInflater inflater) {
        View root = inflater.inflate(com.example.journalapp.R.layout.activity_add_edit_entry, null);
        return new ActivityAddEditEntryBinding(
            root,
            root.findViewById(com.example.journalapp.R.id.toolbar),
            root.findViewById(com.example.journalapp.R.id.et_title),
            root.findViewById(com.example.journalapp.R.id.et_content),
            root.findViewById(com.example.journalapp.R.id.et_tags),
            root.findViewById(com.example.journalapp.R.id.spinner_mood)
        );
    }
    
    public View getRoot() {
        return root;
    }
}
