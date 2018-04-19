package com.example.will.task39;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class RecordActivity extends Activity {

    private Camera myCamera;
    private SurfaceView mySurfaceView;

    private final SimpleDateFormat photoName = new SimpleDateFormat("yyy-MM-dd-HHmmss", Locale.JAPAN);

    private SensorManager mySensor;

    private static final int MATRIX_SIZE = 16;
    private static final int DIMENSION = 3;

    private float[] magneticValues = new float[DIMENSION];
    private float[] accelerometerValues = new float[DIMENSION];
    private final float[] orientationValues = new float[DIMENSION];



    private final PictureCallback mPictureListener =
            new PictureCallback() {

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

                    int temp_width = (height * svWidth) / cWidth ;
                    Log.i("Cam", temp_width + " : " + width );
                    Bitmap bitmap = Bitmap.createBitmap(temp_bitmap, 0, 0, temp_width, width, null, true);

                    MyApplication app = (MyApplication) getApplication();
                    app.setObj(bitmap);
                    Intent intent = new Intent();
                    setResult(1010,intent);
                    finish();
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

                    Size previewSize = getOptimalPreviewSize(previewSizes, width, height);
                    Size pictureSize = pictureSizes.get(0);

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
                    int previewWidth = previewSize.width;
//                    int previewWidth = getWindowManager().getDefaultDisplay().getWidth();
                    int previewHeight = previewSize.height;
//                    int previewHeight = getWindowManager().getDefaultDisplay().getHeight();
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
    private final AutoFocusCallback mAutoFocusListener =
            new AutoFocusCallback() {

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
    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        double targetRatio = (double) w / h;
        if (sizes == null) {
            return null;
        }

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        // Try to find an size match aspect ratio and size
        double ASPECT_TOLERANCE = 0.1;
        for (Size size : sizes) {
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
            for (Size size : sizes) {
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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 全画面設定
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_record);

        findViewById(R.id.flushButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCamera.takePicture(null,null,mPictureListener);
            }
        });

        // カメラプレビューの設定
        mySurfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        SurfaceHolder holder = mySurfaceView.getHolder();
        holder.addCallback(mSurfaceListener);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // センサーを取得する
        mySensor = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();

        // 地磁気センサ
        mySensor.registerListener(mSensorEventListener,
                mySensor.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_UI);

        // 加速度センサ
        mySensor.registerListener(mSensorEventListener,
                mySensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        mySensor.unregisterListener(mSensorEventListener);
    }

}