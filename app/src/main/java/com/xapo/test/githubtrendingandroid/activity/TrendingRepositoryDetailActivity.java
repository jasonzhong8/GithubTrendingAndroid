package com.xapo.test.githubtrendingandroid.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xapo.test.githubtrendingandroid.R;
import com.xapo.test.githubtrendingandroid.data.model.Item;

/**
 * Created by Jason Zhong on 02/02/2018.
 */

public class TrendingRepositoryDetailActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView htmlUrlTextView;
    private TextView desTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_repository_detail_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameTextView = findViewById(R.id.name_value_textView);
        htmlUrlTextView = findViewById(R.id.html_url_value_textView);
        desTextView = findViewById(R.id.des_value_textView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        Item item = (Item) getIntent().getSerializableExtra("Item");
        initView(item);
    }

    private void initView(Item item) {
        if (item != null) {
            nameTextView.setText(item.getName());
            htmlUrlTextView.setText(item.getHtmlUrl());
            desTextView.setText(item.getDescription());
        }
    }
}
