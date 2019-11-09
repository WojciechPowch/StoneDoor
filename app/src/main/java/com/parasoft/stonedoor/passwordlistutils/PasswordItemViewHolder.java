package com.parasoft.stonedoor.passwordlistutils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PasswordItemViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public PasswordItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView = (TextView) itemView;
    }

}
