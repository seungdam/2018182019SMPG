package kr.ac.tukorea.ge.sgp02.s2018182019.card_fliper;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] BUTTON_IDS = new int[] {
            R.id.card_00,R.id.card_01,R.id.card_02,R.id.card_03,
            R.id.card_10,R.id.card_11,R.id.card_12,R.id.card_13,
            R.id.card_20,R.id.card_21,R.id.card_22,R.id.card_23,
            R.id.card_30,R.id.card_31,R.id.card_32,R.id.card_33
    };

    private int[] imageResourceIds = new int[] {
            R.mipmap.card_as,R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,
            R.mipmap.card_5s,R.mipmap.card_jc,R.mipmap.card_qh,R.mipmap.card_kd,
            R.mipmap.card_as,R.mipmap.card_2c,R.mipmap.card_3d,R.mipmap.card_4h,
            R.mipmap.card_5s,R.mipmap.card_jc,R.mipmap.card_qh,R.mipmap.card_kd,
    };
    private ImageButton previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();
    }

    public void startGame() {
        for(int i  = 0; i < imageResourceIds.length; ++i) {
            int resourceId = imageResourceIds[i];
            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setTag(resourceId);
        }


    }

    public void onBtnCard(View view) {
        int btnIndex = findButtonIndex(view.getId());
        if(!(view instanceof ImageButton)) {
            Log.e(TAG, "Not an imagebutton: " + view);
            return;
        }

        ImageButton currentButton = (ImageButton) view;
        if(previousButton == currentButton) {
            Log.d(TAG, "This card Already pressed");
            return;
        }
        Log.d(TAG,"card pressed " + btnIndex);

        int resourceId = (Integer) currentButton.getTag();
        currentButton.setImageResource(resourceId);

        if(previousButton != null) {
            previousButton.setImageResource(R.mipmap.card_blue_back);
        }
        previousButton = currentButton;
    }

    private int findButtonIndex(int id) {
        for(int i = 0; i < BUTTON_IDS.length; ++i) {
            if(id == BUTTON_IDS[i]) {
                return i;
            }
        }
        return -1;
    }

    public void onBtnRestart(View view) {
        Log.d(TAG,"restart pressed");
    }
}



