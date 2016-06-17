package com.cjgaliana.xamarinvideos.Fragments;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjgaliana.xamarinvideos.Adapters.PlaylistAdapter;
import com.cjgaliana.xamarinvideos.Models.VideoCollection;
import com.cjgaliana.xamarinvideos.Services.DataService;

import java.util.List;

public class PlaylistsFragment extends ListFragment {

    RecyclerView recyclerView;
    private PlaylistAdapter adapter;


    public PlaylistsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(false);
        setRetainInstance(true);


        // Use getActivity().getApplicationContext() instead of just getActivity() because this
        // object lives in a fragment and needs to be kept separate from the Activity lifecycle.
        //
        // We could get a LayoutInflater from the ApplicationContext but it messes with the
        // default theme, so generate it from getActivity() and pass it in separately.
        adapter = new PlaylistAdapter(getActivity().getApplicationContext(),
                LayoutInflater.from(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);

        setListAdapter(adapter);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getListView().setDivider(null);
        getListView().setDividerHeight(0);

        setEmptyText("No Playlists found. Refresh to try again");

        loadData();
    }

    private void loadData() {
        List<VideoCollection> items = DataService.getInstance().getVideoCollections(getActivity().getBaseContext());

        this.adapter.setData(items);

        adapter.notifyDataSetChanged();
    }



}
