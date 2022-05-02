package com.example.weekend01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;

public class Circle extends View {
    Button btn1,btn2,btn3,btn4,btn5;

    private Paint paint;

    public Circle(Context context) {
        super(context);

        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(); // 페인트 객체 생성
        paint.setColor(Color.GRAY);
        // 좌표값과 페인트 객체를 이용해서 사각형을 그리는 drawRect()
        canvas.drawCircle(400,400,200, paint);
    }


//    /**
//     * 터치 이벤트를 처리
//     * @param event
//     * @return
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Toast.makeText(super.getContext(), "MotionEvent.ACTION_DOWN : " +
//                    event.getX() + ", " + event.getY(), Toast.LENGTH_SHORT).show();
//        }
//        return super.onTouchEvent(event);
//    }
}
