package com.example.ex_03_camera_share;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class CameraPreView {
    // Vertex 버텍스란 하나의 '점'
    // GPU를 이용하여 고속 계산하여 화면 처리하기 위한 코드
    String vertexShaderCode =
            "attribute vec2 aTexCoord;" +
            "varying vec4 vTexCoord;" +
            "attribute vec4 vPosition;" + // vec4 -> 3차원 좌표
            "void main() {" +
            "gl_Position = vPosition;" +
                    "vTexCoord = aTexCoord ;"+
            // gl_Position : OpenGL에 있는 변수 ::> 계산식 uMVPMatrix * vPosition
            "}";

    String fragmentShaderCode =
            "#extension GL_OES_EGL_image_external : require \n" +
            "precision mediump float;" +
            "uniform samplerExternalOES sTexture ;" +
            "varying vec4 vTexCoord;" +
            "void main() {" +
            "gl_FragColor = texture2D(sTexture, vTexCoord);" +
            "}";

    // 직사각형 점의 좌표
    static float [] QUARD_COORDS = {
            // x, y, z
            -1.0f, -1.0f, 0.0f,
            -0.1f, 1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
    };
    static float [] QUARD_TEXCOORDS = {
            // x, y, z
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,
    };


    FloatBuffer vertexBuffer;
    ShortBuffer drawBuffer;
    int mProgram;

}
