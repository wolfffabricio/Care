package com.example.gocode.care.provider;

import android.content.SearchRecentSuggestionsProvider;

public class SearchableProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.example.gocode.care.provider.SearchableProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchableProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
