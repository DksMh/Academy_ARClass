package com.example.ex_04_pointcloud;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.google.ar.core.Frame;
import com.google.ar.core.Session;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainRenderer implements GLSurfaceView.Renderer {
    final static String TAG = "MainRenderer :";

    Rendercallback myCallBack;
    CameraPreView mCamera;
    PointCloudRenderer mPointCloud;

    //화면이 변화되었다면 true
    boolean viewportChanged;

    int width, height;

    interface Rendercallback{
        void preRender(); //MainActivty에서 재정의하여 호출하게 함
    }

    //생성시 RenderCallBack을 매개변수로 대입받아 자신의 멤버

    MainRenderer(Rendercallback myCallBack){
        mCamera = new CameraPreView();
        mPointCloud = new PointCloudRenderer();

        this.myCallBack = myCallBack;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        Log.d(TAG,  "onSurfaceCreated");
        GLES20.glClearColor(1.0f, 1.0f,0.0f, 1.0f); //노란색
        mCamera.init();
        mPointCloud.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {

        Log.d(TAG,  "onSurfaceChanged");
        GLES20.glViewport(0,0,width, height);
        viewportChanged = true;
        this.width = width;
        this.height = height;
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        //Log.d(TAG,  "onDrawFrame");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT| GLES20.GL_DEPTH_BUFFER_BIT);

        //카메라로부터 새로 받은 영상으로 화면을 업데이트 할 것임
        myCallBack.preRender();
        
        // 카메라로 받은 화면 그리기
        GLES20.glDepthMask(false);
        mCamera.draw();
        GLES20.glDepthMask(true);
        
        // 포인트 클라우드 그리기
        mPointCloud.draw();

    }

    //화면 변환이 되었다는 것을 지시할 메소드 ==> MainActivity 에서 실행할 것이다.
    void onDisplayChanged(){
        viewportChanged = true;
    }

    //session 업데이트시 화면 변환 상태를 보고 session 의 화면을 변경한다.
    //보통 화면 회전에 대한 처리이다.
    void updateSession(Session session, int rotation){
        if(viewportChanged){

            //디스플레이 화면 방향 설정
            session.setDisplayGeometry(rotation,width,height);
            viewportChanged=false;
            Log.d(TAG,"UpdateSession 실행");
        }
    }

    int getTextureId(){
        return mCamera==null ? -1 : mCamera.mTextures[0];
    }

    void transformDisplayGeometry(Frame frame){
        mCamera.transformDisplayGeometry(frame);
    }

}
