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

import com.rubicus.keymechapp.placeholder.PlaceholderContent;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ProductItemListFragment extends Fragment {

    private static final String LIST_TYPE = "list-type";
    private int listType;
    private ArrayList<Keyboard> keyboardArrayList = new ArrayList<>(){};
    private ArrayList<Keycap> keycapArrayList = new ArrayList<>(){};

    private KeycapItemListRecyclerViewAdapter keycapItemListRecyclerViewAdapter;
    private KeyboardItemListRecyclerViewAdapter keyboardItemListRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductItemListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductItemListFragment newInstance(int listType) {
        ProductItemListFragment fragment = new ProductItemListFragment();
        Bundle args = new Bundle();
        args.putInt(LIST_TYPE, listType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            listType = getArguments().getInt(LIST_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            if(listType == 0) {
                keyboardItemListRecyclerViewAdapter = new KeyboardItemListRecyclerViewAdapter(keyboardArrayList);
                recyclerView.setAdapter(keyboardItemListRecyclerViewAdapter);
            }
            else {
                keycapItemListRecyclerViewAdapter = new KeycapItemListRecyclerViewAdapter(keycapArrayList);
                recyclerView.setAdapter(keycapItemListRecyclerViewAdapter);
            }
        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (listType == 0) {
            KeyMechServiceGenerator.service.getKeyboards(1).enqueue(new Callback<ArrayList<Keyboard>>() {
                @Override
                public void onResponse(Call<ArrayList<Keyboard>> call, Response<ArrayList<Keyboard>> response) {
                    ArrayList<Keyboard> arrayList = response.body();
                    keyboardArrayList.addAll(arrayList);
                    keyboardItemListRecyclerViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Keyboard>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            KeyMechServiceGenerator.service.getKeycaps(1).enqueue(new Callback<ArrayList<Keycap>>() {
                @Override
                public void onResponse(Call<ArrayList<Keycap>> call, Response<ArrayList<Keycap>> response) {
                    ArrayList<Keycap> arrayList = response.body();
                    keycapArrayList.addAll(arrayList);
                    keycapItemListRecyclerViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Keycap>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}