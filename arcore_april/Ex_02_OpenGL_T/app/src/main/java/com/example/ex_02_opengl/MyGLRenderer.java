package com.example.ex_02_opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    //Square  myBox;
    ObjRenderer myTable;

    float [] mMVPMatrix = new float[16];
    float [] mProjectionMatrix = new float[16];
    float [] mViewMatrix = new float[16];
    
    // 새로 생성자를 만드는 이유
    MyGLRenderer(Context context){
        myTable = new ObjRenderer(context,"Desk OBJ.obj","Legs Texture.jpg");
        //myTable = new ObjRenderer(context,"table.obj","table.jpg");
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);

        myTable.init(); // 초기화 시켜준다.
        //myBox = new Square();
    }

    //화면갱신 되면서 화면 에서 배치
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0,0,width, height);

        float ratio = (float) width*30 / height;

        Matrix.frustumM(mProjectionMatrix, 0,-ratio,ratio,-10,10,20,300);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0,
                //x, y, z
                  80,80,150,  //카메라 위치 // -> 빨간상자
                //100,100,200,  //카메라 위치
                0,0,-30, //카메라 시선
                0,1,0//카메라 윗방향
        );


        //Matrix.multiplyMM(mMVPMatrix, 0,mProjectionMatrix,0, mViewMatrix,0);

        //정사각형 그리기
        //myBox.draw(mMVPMatrix);

        Matrix.setIdentityM(mMVPMatrix, 0);
        myTable.setProjectionMatrix(mProjectionMatrix);
        myTable.setViewMatrix(mViewMatrix);
        myTable.setModelMatrix(mMVPMatrix);
        myTable.draw(); // setModelMatrix , setProjectionMatrix , setViewMatrix로 그려진다.

    }


    //GPU를 이용하여 그리기를 연산한다.
    static int loadShader(int type, String shaderCode){

        int res = GLES20.glCreateShader(type);

        GLES20.glShaderSource(res, shaderCode);
        GLES20.glCompileShader(res);
        return res;
    }
}
