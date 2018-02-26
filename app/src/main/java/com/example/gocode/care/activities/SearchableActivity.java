package com.example.gocode.care.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocode.care.R;
import com.example.gocode.care.adapters.UserAdapter;
import com.example.gocode.care.interfaces.RecyclerViewOnClickListnerHack;
import com.example.gocode.care.models.Usuario;
import com.example.gocode.care.provider.SearchableProvider;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity implements RecyclerViewOnClickListnerHack {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<Usuario> mList;
    private ArrayList<Usuario> mListAux;
    private UserAdapter adapter;
    private CoordinatorLayout cl_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        mToolbar = (Toolbar) findViewById(R.id.tbSearchable);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mList = savedInstanceState.getParcelableArrayList("mList");
            mListAux = savedInstanceState.getParcelableArrayList("mListAux");
        } else {
            mList = (new FeedActivity()).getSetUserList(6);
            mListAux = new ArrayList<>();
        }

        cl_container = (CoordinatorLayout) findViewById(R.id.cl_container);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvSearchable);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        adapter = new UserAdapter(this, mListAux);
        adapter.setRecyclerViewOnClickListnerHack(this);
        mRecyclerView.setAdapter(adapter);

        handleSearch(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch(intent);
    }

    public void handleSearch(Intent intent) {
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())) {
            String q = intent.getStringExtra(SearchManager.QUERY);
            mToolbar.setTitle(q);
            filterUsers(q);

            SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this, SearchableProvider.AUTHORITY,
                    SearchableProvider.MODE);
            searchRecentSuggestions.saveRecentQuery(q, null);
        }
    }

    public void filterUsers(String q) {
        mListAux.clear();
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getNome().toLowerCase().startsWith(q.toLowerCase())) {
                mListAux.add(mList.get(i));
            }
        }
        for (int i = 0; i < mList.size(); i++) {
            if (!mListAux.contains(mList.get(i))
                    && mList.get(i).getCidade().toLowerCase().startsWith(q.toLowerCase())) {
                mListAux.add(mList.get(i));
            }
        }

        mRecyclerView.setVisibility(mListAux.isEmpty() ? View.GONE : View.VISIBLE);
        if (mListAux.isEmpty()) {
            TextView tv = new TextView(this);
            tv.setText("Nenhum usuário encontrado.");
            tv.setTextColor(getResources().getColor(android.R.color.black));
            tv.setId(1);
            tv.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);

            cl_container.addView(tv);
        } else if (cl_container.findViewById(1) != null) {
            cl_container.removeView(cl_container.findViewById(1));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("mList", mList);
        outState.putParcelableArrayList("mListAux", mListAux);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchable_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getText(R.string.search_hint));

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_delete) {
            SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this, SearchableProvider.AUTHORITY,
                    SearchableProvider.MODE);
            searchRecentSuggestions.clearHistory();
            Toast.makeText(this, "Cookies removidos", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    // LISTENERS
    @Override
    public void OnClickListner(View view, int position) {
        Intent intent = new Intent(this, UsuarioActivity.class);
        intent.putExtra("user", mListAux.get(position));
        startActivity(intent);
    }
}
