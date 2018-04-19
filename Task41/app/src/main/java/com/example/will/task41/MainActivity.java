package com.example.will.task41;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private SeekBar alphaSeekBar;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alphaSeekBar = (SeekBar) findViewById(R.id.alphaSeekBar);
        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);

        alphaSeekBar.setOnSeekBarChangeListener(this);
        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);

        imageView = (ImageView) findViewById(R.id.imageView);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean bo) {
        int newColor = (alphaSeekBar.getProgress() << 24)
                + (redSeekBar.getProgress() << 16)
                + (greenSeekBar.getProgress() << 8)
                + blueSeekBar.getProgress();
        imageView.setColorFilter(newColor, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
