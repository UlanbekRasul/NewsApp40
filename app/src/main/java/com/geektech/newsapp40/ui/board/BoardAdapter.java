package com.geektech.newsapp40.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.geektech.newsapp40.R;

import java.util.ArrayList;
import java.util.List;

import models.News;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private String[] titles = new String[]{"свайпни влево, чтобы узнать больше \n" +
            "          о приложении", "свайпни ещё раз", "а теперь нажми кнопку"};
    private String java = "Java - Строго типизированный объектно-ориентированный язык программирования общего назначения, разработанный компанией Sun Microsystems";
    private String python = "Язык является полностью объектно-ориентированным - всё является объектами. Необычной особенностью языка является выделение блоков кода пробельными отступами";
    private String kotlin = "Статически типизированный, объектно-ориентированный язык программирования, работающий поверх Java Virtual Machine и разрабатываемый компанией JetBrains";
    private String[] info = new String[]{java, python, kotlin};

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private Button button;
        private TextView textDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            button = itemView.findViewById(R.id.btnGet);

        }
        public void bind(int position) {
            textTitle.setText(titles[position]);
            textDesc.setText(info[position]);
            if (position == titles.length - 1) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.INVISIBLE);
            }
        }
    }
}