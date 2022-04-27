package com.example.ex_02_opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {



    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2); // 버전 2 사용

        setRenderer(new MyGLRenderer());

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); // 언제 바뀔꺼야??

    }
}
