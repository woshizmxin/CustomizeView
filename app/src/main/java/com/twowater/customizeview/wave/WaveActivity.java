package com.twowater.customizeview.wave;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.twowater.customizeview.R;

/**
 * Created by zhoumao on 2018/8/13.
 * Description:
 */

public class WaveActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        WaveView waveView = (WaveView)findViewById(R.id.waveView);
        waveView.startAnim();
    }
}
