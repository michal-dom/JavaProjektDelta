package com.example.michadomagaa.javaprojektdelta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Environment;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class CameraAvtivity extends AppCompatActivity {

    private static final String TAG = "AndroidCameraApi";
    private TextView takePictureButton;
    //TextureView jest rodzajem widoku w który można wsadzić Surface
    private TextureView textureView;
    //SparseIntArray to struktura z dwoma polami - kluczem i wartoscią - oba int
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }
    private String cameraId;
    //https://developer.android.com/reference/android/hardware/camera2/CameraDevice.html
    protected CameraDevice cameraDevice;
    //https://developer.android.com/reference/android/hardware/camera2/CameraCaptureSession.html
    protected CameraCaptureSession cameraCaptureSessions;
    protected CaptureRequest captureRequest;
    //https://developer.android.com/reference/android/hardware/camera2/CaptureRequest.Builder.html
    protected CaptureRequest.Builder captureRequestBuilder;

    private Size imageDimension;
    private ImageReader imageReader;
    private File file;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_avtivity);

        textureView = (TextureView) findViewById(R.id.texture);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);

        takePictureButton = (TextView) findViewById(R.id.btn_takepicture);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { takePicture(); }
        });
    }

    //TextureView stworzone do obslugi streama z kamery (miedzy innymi)
    //implementacja interfejsu
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {//ok
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) { openCamera(); }
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) { }
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) { return false; }
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) { }
    };

    //stateCallback - obiekt obslugujacy zmiane stanu kamery
    //https://developer.android.com/reference/android/hardware/camera2/CameraDevice.StateCallback.html
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {//ok
        @Override
        public void onOpened(CameraDevice c) {
            Log.e("camera ","open");
            cameraDevice = c;
            createCameraPreview();
        }

        @Override
        public void onDisconnected(CameraDevice cameraDevice) { cameraDevice.close(); }

        @Override
        public void onError(CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };

    final CameraCaptureSession.CaptureCallback captureCallbackListener = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Toast.makeText(CameraActivity.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
            createCameraPreview();
        }
    };

    protected void startBackgroundThread(){
        // https://developer.android.com/reference/android/os/Handler.html
        // https://developer.android.com/reference/android/os/HandlerThread.html
        // https://developer.android.com/reference/java/lang/Thread.html
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    protected void stopBackgroundThread() {

        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //takePicture()


    protected void createCameraPreview() {
        try {
            //SurfaceTexture - użyte zamiast SurfaceHolder
            // https://developer.android.com/reference/android/view/SurfaceHolder.html
            // https://developer.android.com/reference/android/graphics/SurfaceTexture.html
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;//assert wyrzuca wyjatek
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            // surface to klasa impementujaca interfejs bedacy konsumentem,
            //https://developer.android.com/reference/android/view/Surface.html
            Surface surface = new Surface(texture);
            //https://developer.android.com/reference/android/hardware/camera2/CameraDevice.html#createCaptureRequest(int)
            //https://developer.android.com/reference/android/hardware/camera2/CameraDevice.html#TEMPLATE_PREVIEW
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            //https://developer.android.com/reference/android/hardware/camera2/CameraDevice.html#createCaptureSession(java.util.List<android.view.Surface>, android.hardware.camera2.CameraCaptureSession.StateCallback, android.os.Handler)
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    if (null == cameraDevice) return;
                    // kiedy sesja gotowa
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(CameraActivity.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void openCamera() {//odpowiada za połączenie z hardwerowa kamera
        // CameraManager - systemowy serwis odpowiadajacy za wykrycie, scharakteryzowanie i polaczenie z CameraDevices.
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        Log.e(TAG, "is camera open");
        //try catcha w szczegolnosci wymagaja:
        //cameraId = manager.getCameraIdList()[0];
        //manager.openCamera(cameraId, stateCallback, null);
        try {
            cameraId = manager.getCameraIdList()[0];//getCameraIdList - zwaraca tablice stringow
            for (int k = 0; k < manager.getCameraIdList().length; k++){
                Log.e(TAG, manager.getCameraIdList()[k]);
            }
            //CameraCharacteristics - wszystkie informacje o danej kamerze
            //StreamConfigurationMap mapa ktora zawiera dane w szczegolnosci o formatach wyjsciowych
            //https://developer.android.com/reference/android/hardware/camera2/CameraCharacteristics.html#SCALER_STREAM_CONFIGURATION_MAP
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            //lista Size'ów kompatybilnych z formatami zdjecia
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            //sprawdzanie czy sa ustawione prawa w manifescie
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            //manager.openCamera - tworzy polaczenie z kamera o podanym cameraId
            //https://developer.android.com/reference/android/hardware/camera2/CameraManager.html#openCamera(java.lang.String, android.hardware.camera2.CameraDevice.StateCallback, android.os.Handler)
            manager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "openCamera X");
    }

    protected void updatePreview() {
        if(null == cameraDevice) { Log.e(TAG, "updatePreview error, return"); }
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
        if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(CameraActivity.this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    protected void onResume() {
        //wywołanie metody onResume
        super.onResume();
        Log.e(TAG, "onResume");
        startBackgroundThread();
        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        closeCamera();
        stopBackgroundThread();
        //wywołanie metody onPause
        super.onPause();
    }

}
