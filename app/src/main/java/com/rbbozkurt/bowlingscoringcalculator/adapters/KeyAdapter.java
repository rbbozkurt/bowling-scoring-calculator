package com.rbbozkurt.bowlingscoringcalculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rbbozkurt.bowlingscoringcalculator.R;
import com.rbbozkurt.bowlingscoringcalculator.bowling.BowlingFrame;
import com.rbbozkurt.bowlingscoringcalculator.customs.Key;


import java.util.List;
/**
 * @author resitberkaybozkurt
 *
 */

/**
 * The FrameAdapter representing a adapter for Key class and key card view
 * @see Key
 * @see R.layout#key_card_view
 *
 */
public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.ViewHolder> {
    /**
     * list of keys
     */
    private Key[] keyList;
    /**
     * listener for the key click events
     */
    OnKeyItemClickListener onKeyItemClickListener;

    public KeyAdapter(Key[] keyList, OnKeyItemClickListener onKeyItemClickListener) {
        this.keyList = keyList;
        this.onKeyItemClickListener = onKeyItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View keyView = inflater.inflate(R.layout.key_card_view
                , parent, false);
        return new ViewHolder(keyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the key shown in view
        Key key = this.keyList[position];
        //set the sign of the key to view
        holder.keyTextView.setText(key.getSign());
    }

    @Override
    public int getItemCount() {
        return this.keyList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView keyTextView;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.view.setOnClickListener(this);
            this.keyTextView = (AppCompatTextView) view.findViewById(R.id.keyText);

        }

        @Override
        public void onClick(View v) {
            //get position of clicked item
            int position = getBindingAdapterPosition();
            if (position >= 0) {
                //pass the value of clicked key to listener
                onKeyItemClickListener.onKeyItemClick(keyList[position].getValue());
            }
        }
    }

    public interface OnKeyItemClickListener {
        void onKeyItemClick(int value);
    }
}
