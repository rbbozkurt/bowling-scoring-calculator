package com.rbbozkurt.bowlingscoringcalculator;

import static com.rbbozkurt.bowlingscoringcalculator.BowlingUtils.FRAME_FOR_EACH_GAME;
import static com.rbbozkurt.bowlingscoringcalculator.BowlingUtils.PIN_FOR_EACH_FRAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rbbozkurt.bowlingscoringcalculator.adapters.FrameAdapter;
import com.rbbozkurt.bowlingscoringcalculator.adapters.KeyAdapter;
import com.rbbozkurt.bowlingscoringcalculator.bowling.BowlingCalculator;
import com.rbbozkurt.bowlingscoringcalculator.bowling.BowlingFrame;
import com.rbbozkurt.bowlingscoringcalculator.customs.Key;
import com.rbbozkurt.bowlingscoringcalculator.databinding.MainActivityBinding;

/**
 * @author resitberkaybozkurt
 *
 */

/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {
    /**
     * view binding for activity and it's view
     * @see R.layout#main_activity
     */
    private MainActivityBinding binding;
    /**
     * BowlingCalculator calculating the game scores
     */
    BowlingCalculator calculator;
    /**
     * Adapter for frame's recyclerview and frames
     */
    FrameAdapter frameAdapter;
    /**
     * Adapter for key's recyclerview and keys
     */
    KeyAdapter keyAdapter;
    /**
     * frames for current calculation
     */
    BowlingFrame[] frames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initFrames();
        initViews();

    }

    View.OnClickListener restartClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           //TODO clear the calculator

        }
    };
    public void initViews(){

        this.calculator = new BowlingCalculator(frames);
        this.frameAdapter = new FrameAdapter(frames);
        this.binding.framesRecyclerview.setAdapter(frameAdapter);
        this.binding.framesRecyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false));


        this.keyAdapter = new KeyAdapter(Key.createKeyList(PIN_FOR_EACH_FRAME), new KeyAdapter.OnKeyItemClickListener() {
            /**
             * listener listening key recyclerview clicks
             * @param value value of key representing the number of rolled pins
             */
            @Override
            public void onKeyItemClick(int value) {
                //pass the value to calculator
                calculator.roll(value);
                //notify the frame adapter to update the view
                frameAdapter.notifyDataSetChanged();
            }
        });
        this.binding.keysRecyclerview.setAdapter(keyAdapter);
        this.binding.keysRecyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false));

        this.binding.restartButton.setOnClickListener(restartClickListener);
    }

    public void initFrames(){
        this.frames = new BowlingFrame[FRAME_FOR_EACH_GAME];
        for(int i = 0; i < this.frames.length; i++){
            this.frames[i] = new BowlingFrame(PIN_FOR_EACH_FRAME);
        }
    }

}