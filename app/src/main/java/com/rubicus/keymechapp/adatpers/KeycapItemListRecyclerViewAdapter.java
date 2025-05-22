package com.rubicus.keymechapp.adatpers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.databinding.FragmentProductItemBinding;
import com.rubicus.keymechapp.helper.Keycap;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Keycap}.
 * TODO: Replace the implementation with code for your data type.
 */
public class KeycapItemListRecyclerViewAdapter extends RecyclerView.Adapter<KeycapItemListRecyclerViewAdapter.ViewHolder> {

    private final List<Keycap> mValues;

    public KeycapItemListRecyclerViewAdapter(List<Keycap> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).title);
        holder.mKeywordsView.setText(mValues.get(position).getKeywords());
        holder.mPriceView.setText(mValues.get(position).getPrice());

        Picasso.get()
                .load("http://10.0.2.2:8080/image/" + mValues.get(position).image_name)
                .placeholder(R.drawable.ic_placeholder) // Optional
                .fit()
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mKeywordsView;
        public final TextView mPriceView;
        public final ImageView mImageView;

        public Keycap mItem;


        public ViewHolder(FragmentProductItemBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.textProductItemTitle;
            mKeywordsView = binding.textProductItemKeywords;
            mPriceView = binding.textProductItmePrice;
            mImageView = binding.imageProductItem;

        }

    }
}