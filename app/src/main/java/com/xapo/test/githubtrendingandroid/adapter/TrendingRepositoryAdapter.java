package com.xapo.test.githubtrendingandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xapo.test.githubtrendingandroid.R;
import com.xapo.test.githubtrendingandroid.data.model.Item;

import java.util.List;

/**
 * Created by Jason Zhong on 02/02/2018.
 */

public class TrendingRepositoryAdapter extends RecyclerView.Adapter<TrendingRepositoryAdapter.ViewHolder>{
    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameTextView;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_textView);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item);

            notifyDataSetChanged();
        }
    }

    public TrendingRepositoryAdapter(Context context, List<Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public TrendingRepositoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View thisItemsView = inflater.inflate(R.layout.activity_trending_repository_list_item_layout,
                parent, false);

        ViewHolder viewHolder = new ViewHolder(thisItemsView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrendingRepositoryAdapter.ViewHolder holder, int position) {

        Item item = mItems.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateTrendingRepository(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }
    public interface PostItemListener {
        void onPostClick(Item item);
    }
}
