package com.example.journalapp.databinding;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import android.widget.ScrollView;

public class ActivityEntryDetailBinding {
    public final Toolbar toolbar;
    public final TextView tvTitle;
    public final TextView tvContent;
    public final TextView tvCreatedDate;
    public final TextView tvUpdatedDate;
    public final TextView tvMood;
    public final TextView tvTags;
    private final ScrollView root;
    
    public ActivityEntryDetailBinding(View root, Toolbar toolbar, TextView tvTitle,
                                     TextView tvContent, TextView tvCreatedDate,
                                     TextView tvUpdatedDate, TextView tvMood, TextView tvTags) {
        this.root = (ScrollView) root;
        this.toolbar = toolbar;
        this.tvTitle = tvTitle;
        this.tvContent = tvContent;
        this.tvCreatedDate = tvCreatedDate;
        this.tvUpdatedDate = tvUpdatedDate;
        this.tvMood = tvMood;
        this.tvTags = tvTags;
    }
    
    public static ActivityEntryDetailBinding inflate(android.view.LayoutInflater inflater) {
        View root = inflater.inflate(com.example.journalapp.R.layout.activity_entry_detail, null);
        return new ActivityEntryDetailBinding(
            root,
            root.findViewById(com.example.journalapp.R.id.toolbar),
            root.findViewById(com.example.journalapp.R.id.tv_title),
            root.findViewById(com.example.journalapp.R.id.tv_content),
            root.findViewById(com.example.journalapp.R.id.tv_created_date),
            root.findViewById(com.example.journalapp.R.id.tv_updated_date),
            root.findViewById(com.example.journalapp.R.id.tv_mood),
            root.findViewById(com.example.journalapp.R.id.tv_tags)
        );
    }
    
    public View getRoot() {
        return root;
    }
}
