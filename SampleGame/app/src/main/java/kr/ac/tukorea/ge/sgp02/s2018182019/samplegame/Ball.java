package kr.ac.tukorea.ge.sgp02.s2018182019.samplegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Ball {

    // 모든 객체가 동일하게 적용되는 멤버
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect();
    // 각 멤버별로 다르게 적용되는 멤버
    private Rect dstRect = new Rect();
    int dx, dy;

    public Ball(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;

        dstRect.set(0, 0, 200, 200);
    }

    public static void setBitmap(Bitmap bitmap) {
        Ball.bitmap = bitmap;

        srcRect.set(0,0,Ball.bitmap.getWidth(),Ball.bitmap.getHeight());
    }
    public void update() {
        dstRect.offset(dx,dy);
        if (dx > 0) {
            if (dstRect.right > GameView.view.getWidth()) {
                dx = -dx;
            }
        } else {
            if (dstRect.left < 0) {
                dx = -dx;
            }
        }
        if (dy > 0) {
            if (dstRect.bottom > GameView.view.getHeight()) {
                dy = -dy;
            }
        } else {
            if (dstRect.top < 0) {
                dy = -dy;
            }
        }

    }
    public void render(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }

}
