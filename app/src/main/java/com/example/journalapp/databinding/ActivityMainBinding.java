package com.example.journalapp.databinding;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;
import android.widget.FrameLayout;

public class ActivityMainBinding {
    public final Toolbar toolbar;
    public final RecyclerView rvEntries;
    public final TextView tvNoEntries;
    public final FloatingActionButton fabAdd;
    private final FrameLayout root;
    
    public ActivityMainBinding(View root, Toolbar toolbar, RecyclerView rvEntries,
                               TextView tvNoEntries, FloatingActionButton fabAdd) {
        this.root = (FrameLayout) root;
        this.toolbar = toolbar;
        this.rvEntries = rvEntries;
        this.tvNoEntries = tvNoEntries;
        this.fabAdd = fabAdd;
    }
    
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater) {
        View root = inflater.inflate(com.example.journalapp.R.layout.activity_main, null);
        return new ActivityMainBinding(
            root,
            root.findViewById(com.example.journalapp.R.id.toolbar),
            root.findViewById(com.example.journalapp.R.id.rv_entries),
            root.findViewById(com.example.journalapp.R.id.tv_no_entries),
            root.findViewById(com.example.journalapp.R.id.fab_add)
        );
    }
    
    public View getRoot() {
        return root;
    }
}
