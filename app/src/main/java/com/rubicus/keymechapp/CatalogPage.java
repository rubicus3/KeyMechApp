package com.rubicus.keymechapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogPage extends Fragment {


    public CatalogPage() {
    }


    public static CatalogPage newInstance() {
        return new CatalogPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catalog_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.catalog_item_1).setOnClickListener(v -> onKeycapCatalogClicked());



    }

    private void onKeycapCatalogClicked() {
        getParentFragmentManager().beginTransaction().replace(R.id.main_navigation_layout, SwichItemListFragment.class, null).commit();
    }
}