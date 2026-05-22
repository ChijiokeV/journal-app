package com.example.journalapp.databinding;

import android.view.View;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;

public class ItemEntryBinding {
    public final TextView tvTitle;
    public final TextView tvContent;
    public final TextView tvDate;
    public final TextView tvMood;
    private final MaterialCardView root;
    
    public ItemEntryBinding(View root, TextView tvTitle, TextView tvContent,
                           TextView tvDate, TextView tvMood) {
        this.root = (MaterialCardView) root;
        this.tvTitle = tvTitle;
        this.tvContent = tvContent;
        this.tvDate = tvDate;
        this.tvMood = tvMood;
    }
    
    public static ItemEntryBinding inflate(android.view.LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(com.example.journalapp.R.layout.item_entry, parent, attachToParent);
        return new ItemEntryBinding(
            root,
            root.findViewById(com.example.journalapp.R.id.tv_title),
            root.findViewById(com.example.journalapp.R.id.tv_content),
            root.findViewById(com.example.journalapp.R.id.tv_date),
            root.findViewById(com.example.journalapp.R.id.tv_mood)
        );
    }
    
    public View getRoot() {
        return root;
    }
}
