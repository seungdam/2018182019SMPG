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
    int ballDX, ballDY; // 공들의 이동변화량 = 속도


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

        ballDX = 10;
        ballDY = 10;

        updateFrame();

    }
    // 축구공을 움직여 보자
    private void updateFrame() {
        update();
        // 화면을 갱신 시키는 함수. 생성자 호출 제외
        invalidate();

        // 핸들러 객체를 따로 생성하지 않고 뷰가 가지는 핸들러로 이를 수행하자.
        post(new Runnable() { // 메세지를 보낸다고 해석하면된다.
            @Override
            public void run() {
                updateFrame(); // updateFrame이라는 기능을 수행하라고 메세지를 보낸다.
            }
        });
    }

    private void update() {
        soccerDstRect.offset(ballDX,ballDY);

        if (ballDX > 0) {
            if (soccerDstRect.right > getWidth()) {
                ballDX = -ballDX;
            }
        } else {
            if (soccerDstRect.left < 0) {
                ballDX = -ballDX;
            }
        }
        if (ballDY > 0) {
            if (soccerDstRect.bottom > getHeight()) {
                ballDY = -ballDY;
            }
        } else {
            if (soccerDstRect.top < 0) {
                ballDY = -ballDY;
            }
        }
    }

    // 모든 그리기 작업
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(soccerBitmap,soccerSrcRect,soccerDstRect,null);
    }
}
