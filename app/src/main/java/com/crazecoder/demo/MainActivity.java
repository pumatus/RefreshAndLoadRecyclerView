package com.crazecoder.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazecoder.library.XRecyclerView;

public class MainActivity extends AppCompatActivity {
    int count = 130;
    XRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (XRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        count =30;
                        adapter.notifyDataSetChanged();
                        recyclerView.refreshComplete();
                    }

                }, 3000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            count += 15;
                            adapter.notifyDataSetChanged();
                            recyclerView.loadMoreComplete();
                        }
                    }, 3000);
            }
        });
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View v) {
                super(v);

            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(new TextView(MainActivity.this));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TextView) holder.itemView).setText("position" + position);
        }

        @Override
        public int getItemCount() {
            return count;
        }
    };
}
