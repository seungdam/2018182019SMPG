package kr.ac.tukorea.ge.sgp02.s2018182019.samplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    
    // 게임뷰 초기화시 단 한번만 수행하는 함수.
    private void initView() {


    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
