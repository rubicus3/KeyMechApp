package com.rubicus.keymechapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubicus.keymechapp.CartManager;
import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.helper.KeyMechServiceGenerator;
import com.rubicus.keymechapp.schemas.Keyboard;
import com.rubicus.keymechapp.schemas.Keycap;
import com.rubicus.keymechapp.schemas.Product;
import com.rubicus.keymechapp.schemas.ProductType;
import com.rubicus.keymechapp.schemas.Switch;
import com.squareup.picasso.Picasso;

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

    private Integer amount;
    private Product product;
    ImageView productImage;
    TextView productKeywords;
    TextView productTitle;
    TextView productPrice;
    TextView textAmount;
    LinearLayout characteristicsLayout;

    public ProductPageFragment() {
        // Required empty public constructor
    }



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
        productImage = view.findViewById(R.id.image_product);
        productKeywords = view.findViewById(R.id.text_product_keywords);
        productTitle = view.findViewById(R.id.text_product_title);
        productPrice = view.findViewById(R.id.text_product_pice);
        characteristicsLayout = view.findViewById(R.id.product_characteristics_list);
        textAmount = view.findViewById(R.id.text_box_amount_);

        view.findViewById(R.id.button_amount_add).setOnClickListener(v -> {
            changeAmount(1);
        });

        view.findViewById(R.id.button_amount_subtract).setOnClickListener(v -> {
            changeAmount(-1);
        });

        view.findViewById(R.id.button_amount_add).setOnClickListener(v -> {
            CartManager.getInstance().addToCart(product);
        });

        if (getArguments() != null) {
            getProduct(view);
        }
    }

    private void getProduct(View view) {
        int productId = getArguments().getInt(PRODUCT_ID);
        ProductType productType = (ProductType) getArguments().getSerializable(PRODUCT_TYPE);

        if(productType == ProductType.Keyboard) {
            KeyMechServiceGenerator.service.getKeyboard(productId).enqueue(new Callback<Keyboard>() {
                @Override
                public void onResponse(Call<Keyboard> call, Response<Keyboard> response) {
                    Product product = response.body();
                    setProduct(product);
                    fillProduct(view);
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
                    Product product = response.body();
                    setProduct(product);
                    fillProduct(view);
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
                    Product product = response.body();
                    setProduct(product);
                    fillProduct(view);
                }

                @Override
                public void onFailure(Call<Keycap> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
    private void setProduct(Product product) {
        Log.d("TAG", "setProduct: " + product.title);
        this.product = product;
    }
    private void fillProduct(View view) {
        TextView productDescription= view.findViewById(R.id.text_product_description);
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
    private void fillCharacteristics(View view, Map<String, String> characteristics) {
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

    private void changeAmount(int amount) {
        product.setAmount(product.amount + amount);


        textAmount.setText(product.amount.toString());
    }

}