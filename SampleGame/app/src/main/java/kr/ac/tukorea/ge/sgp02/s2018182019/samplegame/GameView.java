package kr.ac.tukorea.ge.sgp02.s2018182019.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;


public class GameView extends View implements Choreographer.FrameCallback {
    private Bitmap soccerBitmap;
    private Rect soccerSrcRect = new Rect();
    private Rect soccerDstRect = new Rect();
    int ballDX, ballDY; // 공들의 이동변화량 = 속도
    private long previousTimeNanos;
    private int framesPerSecond; // 전역 변수 선언시 항상 풀네임으로 작성하도록 한다.
    private Paint fpsTextPaint = new Paint();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    // 게임뷰 초기화시 단 한번만 수행하는 함수.
    private void initView() {
        // 공의 비트맵 불러오기
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        soccerSrcRect.set(0,0,soccerBitmap.getWidth(),soccerBitmap.getHeight());
        soccerDstRect.set(0,0,200,200);

        // 프레임 출력 텍스트 컨텐츠
        fpsTextPaint.setColor(Color.GREEN);
        fpsTextPaint.setTextSize(100);


        ballDX = 10;
        ballDY = 10;
        Choreographer.getInstance().postFrameCallback(this);
    }
    // 축구공을 움직여 보자


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
        canvas.drawText("FPS: " + framesPerSecond, framesPerSecond * 10, 100, fpsTextPaint);
    }

    @Override
    public void doFrame(long currentTimeNanos) {

            long now = currentTimeNanos;
            int elapsed = (int) (now - previousTimeNanos);
            framesPerSecond = 1_000_000_000 / elapsed;
            //Log.v(TAG, "Elapsed: " + elapsed + " FPS: " + framesPerSecond);
            previousTimeNanos = now;
            update();
            invalidate();
            Choreographer.getInstance().postFrameCallback(this);
    }
}
