package com.rbbozkurt.bowlingscoringcalculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rbbozkurt.bowlingscoringcalculator.BowlingUtils;
import com.rbbozkurt.bowlingscoringcalculator.R;
import com.rbbozkurt.bowlingscoringcalculator.bowling.BowlingFrame;
/**
 * @author resitberkaybozkurt
 *
 */

/**
 * The FrameAdapter representing a adapter for BowlingFrame class and frame card layout
 * @see BowlingFrame
 * @see R.layout#frame_card_view
 *
 */

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.ViewHolder> {

    /**
     * list of frames
     */
    private BowlingFrame[] frameList;

    public FrameAdapter(BowlingFrame[] frameList) {
        this.frameList = frameList;
    }

    @NonNull
    @Override
    public FrameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View keyView = inflater.inflate(R.layout.frame_card_view
                , parent, false);

        return new FrameAdapter.ViewHolder(keyView);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameAdapter.ViewHolder holder, int position) {

        //get the frame shown in view
        BowlingFrame frame = this.frameList[holder.getAdapterPosition()];

        //frame has a strike
        if(frame.isStrike()){
            //set first roll blank
            holder.firstRollScoreText.setText("");
            //set second roll with strike symbol 'X'
            holder.secondRollScoreText.setText("X");
        }
        //frame has a spare
        else if (frame.isSpare()){
            //set score of first roll to view
            holder.firstRollScoreText.setText(String.valueOf(frame.getFirstRoll()));
            //set second roll with spare symbol 'X'
            holder.secondRollScoreText.setText("/");
        }
        //frame without strike nor spare
        else{
            //first roll of the frame is done
            if(frame.getFirstRoll() != BowlingUtils.UNROLLED_SCORE){
                //set score of first roll to view
                holder.firstRollScoreText.setText(String.valueOf(frame.getFirstRoll()));
            }
            //second roll of the frame is done
            if(frame.getSecondRoll() != BowlingUtils.UNROLLED_SCORE){
                //set score of first roll to view
                holder.secondRollScoreText.setText(String.valueOf(frame.getSecondRoll()));
            }
        }
        //frame is finished
        if(frame.getEndTotalFrameScore() != BowlingUtils.UNCALCULATED_FRAME_SCORE){
            //set end score of frame
            holder.totalScoreText.setText(String.valueOf(frame.getEndTotalFrameScore()));

        }
    }

    @Override
    public int getItemCount() {
        return this.frameList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView firstRollScoreText;
        AppCompatTextView secondRollScoreText;
        AppCompatTextView totalScoreText;

        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.firstRollScoreText = (AppCompatTextView) view.findViewById(R.id.firstRollScoreText);
            this.secondRollScoreText = (AppCompatTextView) view.findViewById(R.id.secondRollScoreText);
            this.totalScoreText = (AppCompatTextView) view.findViewById(R.id.totalFrameScoreText);

        }
    }
}
