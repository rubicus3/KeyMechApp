package com.rubicus.keymechapp.adatpers;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.helper.Switch;
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
                .fit()
                .into(holder.mImageView);
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
        public Switch mItem;

        public ViewHolder(FragmentSwitchItemBinding binding) {
            super(binding.getRoot());
            this.mShortTitleView = binding.switchItemShortTitle;
            this.mKeywordsView = binding.switchItemKeywords;
            this.mPriceView = binding.switchItemPrice;
            this.mImageView = binding.switchItemImageView;
        }
    }
}