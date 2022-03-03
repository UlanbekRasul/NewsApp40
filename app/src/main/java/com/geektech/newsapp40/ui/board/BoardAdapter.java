package com.geektech.newsapp40.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp40.R;

import java.util.ArrayList;
import java.util.List;

import models.News;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private List<News> list=new ArrayList<>();
    private String[] titles = new String[]{"Салам", "Привет", "Hello"};
    private String[] texts = new String[]{"Чайдан алыныз", "Угощайтесь", "Help yourself"};

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public  void addItem(News news) {
        list.add(news);
        notifyItemInserted(list.size());
    }
    public void delete( int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            button = itemView.findViewById(R.id.btnGet);
        }

        public void bind(int position) {
            textTitle.setText(titles[position]);
            if (position == 2) {
                button.setVisibility(View.VISIBLE);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick();
                }
            });
        }
    }
}
