package com.rubicus.keymechapp.adatpers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubicus.keymechapp.R;
import com.rubicus.keymechapp.databinding.FragmentCartItemBinding;
import com.rubicus.keymechapp.schemas.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartItemListRecyclerViewAdapter extends RecyclerView.Adapter<CartItemListRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final OnItemChangeListener changeListener;
    public interface OnItemChangeListener {
        void onItemRemove(Product product);
        void onItemAmountAdd(Product product);
        void onItemAmountSubtract(Product product);
    }

    public CartItemListRecyclerViewAdapter(List<Product> items, OnItemChangeListener changeListener) {
        mValues = items;
        this.changeListener = changeListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), changeListener);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Product item = mValues.get(position);
        holder.mItem = item;
        holder.mPriceView.setText(item.getPriceString());
        holder.mKeywordsView.setText(item.getKeywords());
        holder.mTextAmonut.setText(item.amount.toString());
        holder.mTitleView.setText(item.title);

        Picasso.get()
                .load("http://10.0.2.2:8080/image/" + item.image_name)
                .placeholder(R.drawable.ic_placeholder) // Optional
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
        public final ImageView mButtonDelete;

        public final TextView mButtonPlus;
        public final TextView mButtonSubtract;
        public final TextView mTextAmonut;

        public Product mItem;

        public ViewHolder(FragmentCartItemBinding binding, OnItemChangeListener changeListener) {
            super(binding.getRoot());
            mButtonPlus = binding.buttonCartItemAdd;
            mButtonSubtract = binding.buttonCartItemSubtract;
            mTextAmonut = binding.textCartItemAmount;
            mButtonDelete = binding.buttonCartItemDelete;
            mImageView = binding.imageCartItem;
            mTitleView = binding.textCartItemTitle;
            mKeywordsView = binding.textCartItemKeywords;
            mPriceView = binding.textCartItemPrice;

            mButtonDelete.setOnClickListener(v -> {
                changeListener.onItemRemove(mItem);
            });

            mButtonPlus.setOnClickListener(v -> {
                changeListener.onItemAmountAdd(mItem);
            });

            mButtonSubtract.setOnClickListener(v -> {
                changeListener.onItemAmountSubtract(mItem);
            });
        }

   }
}