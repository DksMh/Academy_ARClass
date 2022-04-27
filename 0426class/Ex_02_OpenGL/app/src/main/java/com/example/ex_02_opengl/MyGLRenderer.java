package com.example.ex_02_opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    Square myBox;
    Square myBox2;
    Square myBox3;
    Square myBox4;
    Square myBox5;
    Square myBox6;


    float[] mMVPMatrix = new float[16];
    float[] mProjectionMatrix = new float[16];
    float[] mViewMatrix = new float[16];

    // 앞
    float[] squareCoords = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
    };
    float[] color = {0.5f, 0.5f, 0.5f, 1.0f};
    // 그리는 순서
    short[] drawOrder = {
            0, 1, 3, 3, 1, 2, // Front face.
    };

    // 오
    float[] squareCoords2 = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
    };
    float[] color2 = {1.0f, 1.0f, 0.0f, 1.0f};
    // 그리는 순서
    short[] drawOrder2 = {
            1, 2, 5, 5, 6, 2, // Right face.
    };

    // 아래
    float[] squareCoords3 = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
    };
    float[] color3 = { 0.7f, 0.7f, 1.0f, 1.0f};
    // 그리는 순서
    short[] drawOrder3 = {
            0, 1, 4, 4, 5, 1, // Bottom face.
    };

    // top
    float[] squareCoords4 = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
    };
    float[] color4 = {0.3f, 0.3f, 1.0f, 1.0f};
    // 그리는 순서
    short[] drawOrder4 = {
            2, 3, 6, 6, 7, 3, // Top face.
    };

    // 왼
    float[] squareCoords5 = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
    };
    float[] color5 = { 1.0f,0.1f, 0.2f, 1.0f}; // -> 핑크
    // 그리는 순서
    short[] drawOrder5 = {
            3, 7, 4, 4, 3, 0, // Left face.
    };

    // Rear
    float[] squareCoords6 = {
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f
//             -0.5f,0.5f,0.0f,
//            -0.5f,-0.5f,0.0f,
//            0.5f,-0.5f,0.0f,
//            0.5f,0.5f,0.0f
    };
    float[] color6= {0.0f, 0.1f, 0.0f, 1.0f,}; // -> 핑크
    // 그리는 순서
    short[] drawOrder6 = {
            4, 5, 7, 7, 6, 5, // Rear face.
            // 0,1,2,0,2,3
    };


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);

        myBox = new Square(squareCoords, color, drawOrder);
        myBox2 = new Square(squareCoords2, color2, drawOrder2);
        myBox3 = new Square(squareCoords3, color3, drawOrder3);
        myBox4 = new Square(squareCoords4, color4, drawOrder4);
        myBox5 = new Square(squareCoords5, color5, drawOrder5);
        myBox6 = new Square(squareCoords6, color6, drawOrder6);

    }

    // 화면갱신 되면서 화면에서 배치
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0,
                // x, y, z
                3, 3, 3,         // 카메라 위치로 그리는 위치가 달라진다
                0, 0, 0, // 카메라 시선
                0, 1, 0              // 카메라 윗방향
        );

        // mMVPMatrix = mProjectionMatrix * mViewMatrix
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // 정사각형 그리기
         myBox.draw(mMVPMatrix);
         myBox2.draw(mMVPMatrix);
         myBox3.draw(mMVPMatrix);
         myBox4.draw(mMVPMatrix);
         myBox5.draw(mMVPMatrix);
        myBox6.draw(mMVPMatrix);

    }

    // GPU를 이용하여 그리기를 연산한다.
    static int loadShader(int type, String shaderCode) {

        int res = GLES20.glCreateShader(type);

        GLES20.glShaderSource(res, shaderCode);
        GLES20.glCompileShader(res);
        return res;

    }


}