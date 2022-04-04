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
    private Ball ball1,ball2;

    private long previousTimeNanos;
    private int framesPerSecond; // 전역 변수 선언시 항상 풀네임으로 작성하도록 한다.
    private Paint fpsTextPaint = new Paint();

    public static GameView view;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    // 게임뷰 초기화시 단 한번만 수행하는 함수.
    private void initView() {
        // 공의 비트맵 불러오기
        Resources res = getResources();
        Bitmap soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        Ball.setBitmap(soccerBitmap);


        view = this;
        // 프레임 출력 텍스트 컨텐츠
        fpsTextPaint.setColor(Color.GREEN);
        fpsTextPaint.setTextSize(100);

        ball1 = new Ball(10,10);
        ball2 = new Ball(7, 15);

        Choreographer.getInstance().postFrameCallback(this);
    }
    // 축구공을 움직여 보자


    private void update() {
        ball1.update();
        ball2.update();
    }

    // 모든 그리기 작업
    @Override
    protected void onDraw(Canvas canvas) {
        ball1.render(canvas);
        ball2.render(canvas);
        canvas.drawText("FPS: " + framesPerSecond, framesPerSecond * 10, 100, fpsTextPaint);
    }

    @Override
    public void doFrame(long currentTimeNanos) {

            long now = currentTimeNanos;
            int elapsed = (int) (now - previousTimeNanos);
            framesPerSecond = 1_000_000_000 / elapsed;
            previousTimeNanos = now;
            update();
            invalidate();
            Choreographer.getInstance().postFrameCallback(this);
    }
}
