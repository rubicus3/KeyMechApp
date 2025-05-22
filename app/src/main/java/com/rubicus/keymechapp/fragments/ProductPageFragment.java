package com.rubicus.keymechapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.helper.KeyMechService;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.helper.Keyboard;
import com.rubicus.keymechapp.helper.Keycap;
import com.rubicus.keymechapp.helper.Product;
import com.rubicus.keymechapp.helper.ProductType;
import com.rubicus.keymechapp.helper.Switch;
import com.squareup.picasso.Picasso;

import java.util.Dictionary;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String PRODUCT_ID = "product-id";
    public static final String PRODUCT_TYPE = "product-type";


    // TODO: Rename and change types of parameters
    private Integer productId;
    private ProductType productType;


    public ProductPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductPageFragment newInstance(String param1, ProductType param2) {
        ProductPageFragment fragment = new ProductPageFragment();
        Bundle args = new Bundle();
        args.putString(PRODUCT_ID, param1);
        args.putSerializable(PRODUCT_TYPE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_page, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView productImage = view.findViewById(R.id.image_product);
        TextView productKeywords = view.findViewById(R.id.text_product_keywords);
        TextView productTitle = view.findViewById(R.id.text_product_title);
        TextView productPrice = view.findViewById(R.id.text_product_pice);

        TextView productDescription= view.findViewById(R.id.text_product_description);


        if (getArguments() != null) {
            int productId = getArguments().getInt(PRODUCT_ID);
            ProductType productType = (ProductType) getArguments().getSerializable(PRODUCT_TYPE);

            if(productType == ProductType.Keyboard) {
                KeyMechServiceGenerator.service.getKeyboard(productId).enqueue(new Callback<Keyboard>() {
                    @Override
                    public void onResponse(Call<Keyboard> call, Response<Keyboard> response) {
                        Keyboard product = response.body();
                        productKeywords.setText(product.getKeywords());
                        productTitle.setText(product.title);
                        productPrice.setText(product.getPrice());
                        productDescription.setText(product.description);
                        fillCharacteristics(view, product.characteristics);

                        Picasso.get()
                                .load("http://10.0.2.2:8080/image/" + product.image_name)
                                .placeholder(R.drawable.ic_placeholder)
                                .into(productImage);
                    }

                    @Override
                    public void onFailure(Call<Keyboard> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            } else if (productType == ProductType.Switch) {
                KeyMechServiceGenerator.service.getSwitch(productId).enqueue(new Callback<Switch>() {
                    @Override
                    public void onResponse(Call<Switch> call, Response<Switch> response) {
                        Switch product = response.body();
                        productKeywords.setText(product.getKeywords());
                        productTitle.setText(product.title);
                        productPrice.setText(product.getPrice());
                        productDescription.setText(product.description);
                        fillCharacteristics(view, product.characteristics);

                        Picasso.get()
                                .load("http://10.0.2.2:8080/image/" + product.image_name)
                                .placeholder(R.drawable.ic_placeholder)
                                .into(productImage);
                    }

                    @Override
                    public void onFailure(Call<Switch> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            else if (productType == ProductType.Keycap) {
                KeyMechServiceGenerator.service.getKeycap(productId).enqueue(new Callback<Keycap>() {
                    @Override
                    public void onResponse(Call<Keycap> call, Response<Keycap> response) {
                        Keycap product = response.body();
                        productKeywords.setText(product.getKeywords());
                        productTitle.setText(product.title);
                        productPrice.setText(product.getPrice());
                        productDescription.setText(product.description);
                        fillCharacteristics(view, product.characteristics);

                        Picasso.get()
                                .load("http://10.0.2.2:8080/image/" + product.image_name)
                                .placeholder(R.drawable.ic_placeholder)
                                .into(productImage);
                    }

                    @Override
                    public void onFailure(Call<Keycap> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }

    }

    private void fillCharacteristics(View view, Map<String, String> characteristics) {
        LinearLayout characteristicsLayout = view.findViewById(R.id.product_characteristics_list);

        for (String i : characteristics.keySet()) {
            LayoutInflater inflater1 = LayoutInflater.from(requireContext());
            View characteristicView = inflater1.inflate(R.layout.characteristics_item, characteristicsLayout, false);

            TextView textKey = characteristicView.findViewById(R.id.text_characteristics_key);
            TextView textValue = characteristicView.findViewById(R.id.text_characteristics_value);

            textKey.setText(i);
            textValue.setText(characteristics.get(i));

            characteristicsLayout.addView(characteristicView);
        }
    }
}