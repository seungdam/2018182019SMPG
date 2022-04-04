package kr.ac.tukorea.ge.sgp02.s2018182019.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class GameView extends View {
    private Bitmap soccerBitmap;
    private Rect soccerSrcRect = new Rect();
    private Rect soccerDstRect = new Rect();

    private Handler handler = new Handler();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    // 게임뷰 초기화시 단 한번만 수행하는 함수.
    private void initView() {
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        soccerSrcRect.set(0,0,soccerBitmap.getWidth(),soccerBitmap.getHeight());
        soccerDstRect.set(0,0,200,200);

        updateFrame();

    }
    // 축구공을 움직여 보자
    private void updateFrame() {
        soccerDstRect.offset(1,1);

        // 화면을 갱신 시키는 함수. 생성자 호출 제외
        invalidate();
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateFrame();
            }
        });

    }

    // 모든 그리기 작업
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(soccerBitmap,soccerSrcRect,soccerDstRect,null);
    }
}
