package com.rubicus.keymechapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rubicus.keymechapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogPageFragment extends Fragment {


    public CatalogPageFragment() {
    }


    public static CatalogPageFragment newInstance() {
        return new CatalogPageFragment();
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

        view.findViewById(R.id.catalog_item_2).setOnClickListener(v -> navigateTo(SwichItemListFragment.class));
        view.findViewById(R.id.catalog_item_4).setOnClickListener(v -> navigateTo(ProductItemListFragment.class, 0));
        view.findViewById(R.id.catalog_item_1).setOnClickListener(v -> navigateTo(ProductItemListFragment.class, 1));


    }
    private void navigateTo(Class<? extends Fragment> class_) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.main_navigation_layout ,class_, null)
                .commit();

    }

    private void navigateTo(Class<? extends Fragment> class_, Integer listType) {
        Bundle bundle = new Bundle();
        bundle.putInt("list-type", listType);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.main_navigation_layout ,class_, bundle)
                .commit();

    }
}