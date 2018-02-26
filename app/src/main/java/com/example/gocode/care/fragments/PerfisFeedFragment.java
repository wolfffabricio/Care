package com.example.gocode.care.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gocode.care.R;
import com.example.gocode.care.activities.FeedActivity;
import com.example.gocode.care.activities.UsuarioActivity;
import com.example.gocode.care.adapters.UserAdapter;
import com.example.gocode.care.extras.UtilCare;
import com.example.gocode.care.interfaces.RecyclerViewOnClickListnerHack;
import com.example.gocode.care.models.Usuario;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.util.ArrayList;


public class PerfisFeedFragment extends Fragment implements RecyclerViewOnClickListnerHack {

    private RecyclerView mRecyclerView;
    private ArrayList<Usuario> mList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public PerfisFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_perfis_feed, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = ((FeedActivity) getActivity()).getSetUserList(11);
        UserAdapter adapter = new UserAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListnerHack(this);
        mRecyclerView.setAdapter(adapter);

        //SWIPE REFRESH LAYOUT
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srlSwipe);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (UtilCare.verifyConnection(getActivity())) {
                    UserAdapter adapter = (UserAdapter) mRecyclerView.getAdapter();
                    ArrayList<Usuario> listAux = ((FeedActivity) getActivity()).getSetUserList(11);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), 0);
                        mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(2000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                }
                            });
                        }
                    }).start();
                } else {
                    mSwipeRefreshLayout.setRefreshing(false);

                    SnackbarManager.show(
                            Snackbar.with(getActivity())
                                    .text("Sem conexão com a rede. Por favor, verifique seu WIFI ou 4G.")
                                    .color(getActivity().getResources().getColor(android.R.color.black))
                                    .textColor(getActivity().getResources().getColor(android.R.color.white))
                                    .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                                    .type(SnackbarType.MULTI_LINE)
                                    .actionLabel("Conectar")
                                    .actionColor(getActivity().getResources().getColor(R.color.colorPrimary))
                                    .actionListener(new ActionClickListener() {
                                        @Override
                                        public void onActionClicked(Snackbar snackbar) {
                                            Intent it = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                            startActivity(it);
                                        }
                                    }), (ViewGroup) view
                    );
                }
            }
        });

        return view;
    }

    @Override
    public void OnClickListner(View view, int position) {
        Intent i = new Intent(getActivity(), UsuarioActivity.class);
        i.putExtra("user", mList.get(position));
        getActivity().startActivity(i);
    }
}
