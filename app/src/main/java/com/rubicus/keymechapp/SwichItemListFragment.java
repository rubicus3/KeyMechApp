package com.rubicus.keymechapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class SwichItemListFragment extends Fragment {
    private ArrayList<Switch> switchArrayList = new ArrayList<>(){};

    private SwichItemListRecyclerViewAdapter swichItemListRecyclerViewAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SwichItemListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SwichItemListFragment newInstance(int columnCount) {
        return new SwichItemListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_switch_item_list, container, false);

        swichItemListRecyclerViewAdapter = new SwichItemListRecyclerViewAdapter(switchArrayList);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(swichItemListRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KeyMechServiceGenerator.service.getSwitches(1).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ArrayList<Switch>> call, Response<ArrayList<Switch>> response) {
                ArrayList<Switch> arrayList = response.body();
                Log.d("TAG", "onResponse: " + arrayList);
                switchArrayList.addAll(arrayList);
                swichItemListRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Switch>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}