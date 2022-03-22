package kr.ac.tukorea.ge.sgp02.s2018182019.card_fliper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int MYDURATION = 1000;
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
    private TextView scoreTextView;
    private int flips;
    private Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        scoreTextView = findViewById(R.id.scoreTextView);
        startGame();
    }

    public void startGame() {
        Log.d(TAG, "prevCard : " + previousButton);
        previousButton = null;
        int randomIdx[] = new int[16];
        r = new Random();
        for(int i = 0; i < 16; ++i) {
            randomIdx[i] = r.nextInt(16);
            for(int j = 0; j < i; ++j) {
                if(randomIdx[i] == randomIdx[j]) {
                    --i;
                }
            }
        }

        for(int i  = 0; i < imageResourceIds.length; ++i) {
            int resourceId = imageResourceIds[randomIdx[i]];
            ImageButton btn = findViewById(BUTTON_IDS[i]);
            btn.setImageResource(R.mipmap.card_blue_back);
            btn.setVisibility(View.VISIBLE);
            btn.setTag(resourceId);
        }
        setScore(0);
    }

    private void setScore(int flips) {
        this.flips = flips;
        scoreTextView.setText("Filps : " + flips);
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
        // Log.d(TAG,"card pressed " + btnIndex);

        int prevResourceId = 0;

        if(previousButton != null) {
            prevResourceId =  (Integer) previousButton.getTag();
        }

        int curResourceId = (Integer) currentButton.getTag();
        currentButton.setImageResource(curResourceId);

        if(curResourceId != prevResourceId) {

            currentButton.setImageResource(curResourceId);
            if(previousButton != null) {
                previousButton.setImageResource(R.mipmap.card_blue_back);
            }
            previousButton = currentButton;
        }
        else {
            Toast.makeText(getApplicationContext(), "Greate", Toast.LENGTH_SHORT).show();

            currentButton.setVisibility(View.INVISIBLE);
            previousButton.setVisibility(View.INVISIBLE);
            setScore(flips + 1);
            if (flips == 8) {
                askRetry();

            }
        }
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
        askRetry();
    }

    private void askRetry() {
      new AlertDialog.Builder(this)
               .setTitle("RESTART")
               .setMessage("Are you really wanna restart game?")
               .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       startGame();

                   }
               })
               .setNegativeButton("no",null)
               .create()
               .show();
    }
}

