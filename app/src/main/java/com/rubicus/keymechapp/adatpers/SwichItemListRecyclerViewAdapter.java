package com.rubicus.keymechapp.adatpers;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.fragments.ProductPageFragment;
import com.rubicus.keymechapp.schemas.ProductType;
import com.rubicus.keymechapp.schemas.Switch;
import com.rubicus.keymechapp.databinding.FragmentSwitchItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Switch}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SwichItemListRecyclerViewAdapter extends RecyclerView.Adapter<SwichItemListRecyclerViewAdapter.ViewHolder> {

    private final List<Switch> mValues;

    public SwichItemListRecyclerViewAdapter(List<Switch> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSwitchItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mShortTitleView.setText(mValues.get(position).short_title);
        holder.mKeywordsView.setText(mValues.get(position).getKeywords());
        holder.mPriceView.setText(mValues.get(position).getPrice());

        Picasso.get()
                .load("http://10.0.2.2:8080/image/" + mValues.get(position).image_name)
                .placeholder(R.drawable.ic_placeholder) // Optional
                .into(holder.mImageView);

        holder.mLayout.setOnClickListener(v -> {
            Bundle bundle = new Bundle();

            bundle.putInt(ProductPageFragment.PRODUCT_ID, holder.mItem.id);
            bundle.putSerializable(ProductPageFragment.PRODUCT_TYPE, ProductType.Switch);

            ((FragmentActivity) v.getContext())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_navigation_layout, ProductPageFragment.class, bundle)
                    .addToBackStack("productpage")
                    .commit();
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mShortTitleView;
        public final TextView mKeywordsView;
        public final TextView mPriceView;
        public final ImageView mImageView;

        public final LinearLayout mLayout;
        public Switch mItem;


        public ViewHolder(FragmentSwitchItemBinding binding) {
            super(binding.getRoot());
            mShortTitleView = binding.switchItemShortTitle;
            mKeywordsView = binding.switchItemKeywords;
            mPriceView = binding.switchItemPrice;
            mImageView = binding.switchItemImageView;
            mLayout = binding.switchItemLayout;
        }
    }
}