package com.example.will.task39;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private boolean mPermissionReady;
    private boolean cameraPreviewing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        cameraPreviewing = false;

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setClickable(true);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cameraPreviewing){
                    myCamera.takePicture(null,null,mPictureListener);
                    button.setText(R.string.openCamera);
                    cameraPreviewing = false;
                } else {
                    if (mPermissionReady) {
                        // カメラプレビューの設定
                        mySurfaceView = (SurfaceView)findViewById(R.id.surfaceView);
                        SurfaceHolder holder = mySurfaceView.getHolder();
                        holder.addCallback(mSurfaceListener);
                        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

                        // センサーを取得する
                        mySensor = (SensorManager)getSystemService(SENSOR_SERVICE);
                        imageView.setVisibility(View.GONE);
                        mySurfaceView.setVisibility(View.VISIBLE);
                        button.setText("撮影");
                        cameraPreviewing = true;
                    }
                }
            }
        });


        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        mPermissionReady = cameraPermission == PackageManager.PERMISSION_GRANTED
                && storagePermission == PackageManager.PERMISSION_GRANTED;
        if (!mPermissionReady) {
            requirePermissions();
        }
    }

    private void requirePermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Map<String, Integer> perm = new HashMap<>();
        perm.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_DENIED);
        perm.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_DENIED);
        for (int i = 0; i < permissions.length; i++) {
            perm.put(permissions[i], grantResults[i]);
        }
        if (perm.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && perm.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mPermissionReady = true;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Camera myCamera;
    private SurfaceView mySurfaceView;

    private final SimpleDateFormat photoName = new SimpleDateFormat("yyy-MM-dd-HHmmss", Locale.JAPAN);

    private SensorManager mySensor;

    private static final int MATRIX_SIZE = 16;
    private static final int DIMENSION = 3;

    private float[] magneticValues = new float[DIMENSION];
    private float[] accelerometerValues = new float[DIMENSION];
    private final float[] orientationValues = new float[DIMENSION];



    private final Camera.PictureCallback mPictureListener =
            new Camera.PictureCallback() {

                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    ContentResolver resolver = getContentResolver();

                    int svWidth = mySurfaceView.getHolder().getSurfaceFrame().width();
                    int cWidth = camera.getParameters().getSupportedPreviewSizes().get(0).width;

                    // データを生成する
                    Bitmap tmp_bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    int width = tmp_bitmap.getWidth();
                    int height = tmp_bitmap.getHeight();

                    // 画像データを回転する
                    int rad_y = radianToDegree(orientationValues[2]);
                    Matrix matrix = new Matrix();
                    if ((rad_y > -45 && rad_y <= 0) || (rad_y > 0 && rad_y <= 45)) {
                        matrix.setRotate(90.0F);
                    } else if (rad_y > 45 && rad_y <= 135) {
                        matrix.setRotate(180.0F);
                    } else if ((rad_y > 135 && rad_y <= 180) || (rad_y >= -180 && rad_y <= -135)) {
                        matrix.setRotate(-90.0F);
                    } else if (rad_y > -135 && rad_y <= -45) {
                        matrix.setRotate(0);
                    }

                    Log.i("Cam",width + " , " + height);

                    // 画像データを保存する
                    Bitmap temp_bitmap = Bitmap.createBitmap(tmp_bitmap, 0, 0, width, height, matrix, true);
//
//                    int temp_width = (width * svWidth) / cWidth ;
//                    Log.i("Cam", temp_width + " : " + width );
//                    Bitmap bitmap;
//                    if(isPortrait()) {
//                        bitmap = Bitmap.createBitmap(temp_bitmap, 0, 0, temp_width, width, null, true);
//                    } else{
//                        bitmap = Bitmap.createBitmap(temp_bitmap, 0, 0, width,temp_width , null, true);
//                    }
//
//                    Log.i("Cam", String.valueOf(cWidth));
//                    Log.i("Cam", String.valueOf(svWidth));

                    imageView.setImageBitmap(temp_bitmap);
                    imageView.setVisibility(View.VISIBLE);
                    mySurfaceView.setVisibility(View.INVISIBLE);
                }
            };

    /**
     * SurfaceView
     * 生成・変更・破棄
     */
    private final SurfaceHolder.Callback mSurfaceListener =
            new SurfaceHolder.Callback() {
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    myCamera.stopPreview();
                    myCamera.release();
                    myCamera = null;
                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    myCamera = Camera.open();
                    try {
                        myCamera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        Log.e("System.err",e.getMessage());
                    } catch (RuntimeException e) {
                        Log.e("System.err",e.getMessage());
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    myCamera.stopPreview();

                    Camera.Parameters parameters = myCamera.getParameters();

                    // 画面の向きを設定
                    boolean portrait = isPortrait();
                    if (portrait) {
                        myCamera.setDisplayOrientation(90);
                    } else {
                        myCamera.setDisplayOrientation(0);
                    }

                    // 対応するプレビューサイズ・保存サイズを取得する
                    List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
                    List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();

                    Camera.Size previewSize = getOptimalPreviewSize(previewSizes, width, height);
                    Camera.Size pictureSize = pictureSizes.get(0);

                    Log.d("CameraTest", "surface = " +
                            String.valueOf(width) + " , " +
                            String.valueOf(height));
                    if (previewSize == null) {
                        throw new AssertionError();
                    }
                    Log.d("CameraTest", "preview = " +
                            String.valueOf(previewSize.width) + " , " +
                            String.valueOf(previewSize.height));
                    Log.d("CameraTest", "picture = " +
                            String.valueOf(pictureSize.width) + " , " +
                            String.valueOf(pictureSize.height));

                    parameters.setPreviewSize(previewSize.width, previewSize.height);
                    parameters.setPictureSize(pictureSize.width, pictureSize.height);

                    // カメラプレビューレイアウトの設定
//                    int previewWidth = previewSize.width;
                    int previewWidth = getWindowManager().getDefaultDisplay().getHeight();
//                    int previewHeight = previewSize.height;
                    int previewHeight = getWindowManager().getDefaultDisplay().getWidth();
                    android.view.ViewGroup.LayoutParams layoutParams = mySurfaceView.getLayoutParams();
                    if (portrait) {
                        layoutParams.width = previewHeight;
                        layoutParams.height = previewWidth;
                    } else {
                        layoutParams.width = previewWidth;
                        layoutParams.height = previewHeight;
                    }
                    mySurfaceView.setLayoutParams(layoutParams);

                    // パラメータを設定してカメラを再開
                    myCamera.setParameters(parameters);
                    myCamera.startPreview();
                }
            };

    /**
     * オートフォーカス処理
     */
    private final Camera.AutoFocusCallback mAutoFocusListener =
            new Camera.AutoFocusCallback() {

                @Override
                public void onAutoFocus(boolean success, Camera camera) { }
            };

    /**
     * センサー制御
     */
    private final SensorEventListener mSensorEventListener =
            new SensorEventListener() {

                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
                        return;
                    }

                    switch (event.sensor.getType()) {
                        case Sensor.TYPE_MAGNETIC_FIELD:
                            // 地磁気センサ
                            magneticValues = event.values.clone();
                            break;
                        case Sensor.TYPE_ACCELEROMETER:
                            // 加速度センサ
                            accelerometerValues = event.values.clone();
                            break;

                        default:
                            break;

                    }

                    if (magneticValues != null && accelerometerValues != null) {
                        float[] rotationMatrix = new float[MATRIX_SIZE];
                        float[] inclinationMatrix = new float[MATRIX_SIZE];

                        // 加速度センサと地磁気センタから回転行列を取得
                        SensorManager.getRotationMatrix(rotationMatrix, inclinationMatrix, accelerometerValues, magneticValues);

                        float[] remapedMatrix = new float[MATRIX_SIZE];
                        SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remapedMatrix);
                        SensorManager.getOrientation(remapedMatrix, orientationValues);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) { }
            };

    /**
     * 画面の向きを取得する
     */
    private boolean isPortrait() {
        return (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
    }

    @Nullable
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        double targetRatio = (double) w / h;
        if (sizes == null) {
            return null;
        }

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        // Try to find an size match aspect ratio and size
        double ASPECT_TOLERANCE = 0.1;
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) {
                continue;
            }
            if (Math.abs(size.height - h) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - h);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            double maxValue = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - h) < maxValue) {
                    optimalSize = size;
                    maxValue = Math.abs(size.height - h);
                }
            }
        }
        return optimalSize;
    }

    private int radianToDegree(float rad) {
        return (int)Math.floor(Math.toDegrees(rad));
    }

    /**
     * 画面タッチ時でオートフォーカスを実装
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Camera.Parameters params = myCamera.getParameters();
            if (!params.getFocusMode().equals(Camera.Parameters.FOCUS_MODE_FIXED)) {
                myCamera.autoFocus(mAutoFocusListener);
            }
        }
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();

        if(cameraPreviewing) {
            // 地磁気センサ
            mySensor.registerListener(mSensorEventListener,
                    mySensor.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                    SensorManager.SENSOR_DELAY_UI);

            // 加速度センサ
            mySensor.registerListener(mSensorEventListener,
                    mySensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(cameraPreviewing) {
            mySensor.unregisterListener(mSensorEventListener);
        }
    }
}
