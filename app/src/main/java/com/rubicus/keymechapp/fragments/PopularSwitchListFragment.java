package com.rubicus.keymechapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.adatpers.SwichItemListRecyclerViewAdapter;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Switch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularSwitchListFragment extends Fragment {
    private ArrayList<Switch> switchArrayList = new ArrayList<>(){};

    private SwichItemListRecyclerViewAdapter swichItemListRecyclerViewAdapter;

    public PopularSwitchListFragment() {
    }

    @SuppressWarnings("unused")
    public static PopularSwitchListFragment newInstance() {
        return new PopularSwitchListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_switch_item_list, container, false);

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
        KeyMechServiceGenerator.service.getPopularSwitches().enqueue(new Callback<>() {
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
