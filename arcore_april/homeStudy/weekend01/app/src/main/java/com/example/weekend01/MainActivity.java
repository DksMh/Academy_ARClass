package com.example.weekend01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GLSurfaceView mySurView;

    TextView my_textView;
    Circle myCircle;
    Button btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myCircle = new Circle(this);
        setContentView(myCircle);


        float [] mColor1 = {1.0f, 0.0f, 0.0f, 0.8f}; // 빨강
        float [] mColor2 = {1.0f, 1.0f, 0.0f, 1.0f}; // 노랑
        float [] mColor3 = {1.0f, 0.0f, 1.0f, 1.0f}; // 핑크
        float [] mColor4 = {0.0f, 0.0f, 1.0f, 1.0f}; // 파랑
        float [] mColor5 = {0.0f, 1.0f, 0.0f, 1.0f}; // 라임

        mySurView = (GLSurfaceView) findViewById(R.id.glsurfaceview);
        my_textView = (TextView) findViewById(R.id.my_textView);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);

        btn1.setOnClickListener(click);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_main,null);
        addContentView(v , new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    // btn1 동작;
                    myCircle.paint.setColor(Color.RED);
                    break;
                case R.id.btn2:
                    // btn2 동작
                    myCircle.setBackgroundColor(Color.YELLOW);
                    break;
                case R.id.btn3:
                    // btn2 동작
                    myCircle.setBackgroundColor(Color.MAGENTA);
                    break;
                case R.id.btn4:
                    // btn2 동작
                    myCircle.setBackgroundColor(Color.BLUE);
                    break;
                case R.id.btn5:
                    // btn2 동작
                    myCircle.setBackgroundColor(Color.GREEN);
                    break;
            }
        }
    };

    public class Circle extends View {

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
            canvas.drawCircle(400, 400, 200, paint);
        }
    }

}
