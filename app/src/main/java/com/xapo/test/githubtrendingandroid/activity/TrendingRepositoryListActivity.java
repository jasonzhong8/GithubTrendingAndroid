package com.xapo.test.githubtrendingandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.xapo.test.githubtrendingandroid.R;
import com.xapo.test.githubtrendingandroid.adapter.TrendingRepositoryAdapter;
import com.xapo.test.githubtrendingandroid.data.model.Item;
import com.xapo.test.githubtrendingandroid.data.model.TrendingRepositoryResponse;
import com.xapo.test.githubtrendingandroid.data.remote.SOService;
import com.xapo.test.githubtrendingandroid.util.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.xapo.test.githubtrendingandroid.adapter.TrendingRepositoryAdapter.PostItemListener;

/**
 * Created by Jason Zhong on 02/02/2018.
 */

public class TrendingRepositoryListActivity extends AppCompatActivity {

    private TrendingRepositoryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_repository_list_layout);

        initViews();
        loadTrendingRepositories();
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mService = ApiUtils.getSOService();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new TrendingRepositoryAdapter(this, new ArrayList<Item>(0), new PostItemListener() {

            @Override
            public void onPostClick(Item item) {
                Intent intent = new Intent(TrendingRepositoryListActivity.this, TrendingRepositoryDetailActivity.class);
                intent.putExtra("Item", item);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    private void loadTrendingRepositories() {
        mService.getTrendingRepositories().enqueue(new Callback<TrendingRepositoryResponse>() {
            @Override
            public void onResponse(Call<TrendingRepositoryResponse> call, Response<TrendingRepositoryResponse> response) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                if (response.isSuccessful()) {
                    mAdapter.updateTrendingRepository(response.body().getItems());
                    Log.d("ListActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<TrendingRepositoryResponse> call, Throwable t) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                Log.d("ListActivity", "error loading from API");

            }
        });
    }
}
