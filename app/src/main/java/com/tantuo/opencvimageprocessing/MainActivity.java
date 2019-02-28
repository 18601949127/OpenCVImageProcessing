package com.tantuo.opencvimageprocessing;

import android.app.Dialog;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.LogisticRegression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button processBtn = findViewById(R.id.gray_btn);
        Button goCameraDemoBtn = findViewById(R.id.camera_btn);

        processBtn.setOnClickListener(this);
        goCameraDemoBtn.setOnClickListener(this);
        staticLoadCVLibraries();
    }

    //静态加载初始化opencv库
    private void staticLoadCVLibraries() {
        boolean load = OpenCVLoader.initDebug();
        if (load) {
            Log.i("cv", "OPEN CV Libraries load......");
        }

    }


    @Override
    public void onClick(View view) {
            if(view.getId()==R.id.camera_btn){
                Intent intent = new Intent(this.getApplicationContext(),CameraViewActivity.class);
                startActivity(intent);
            }

            if(view.getId()==R.id.gray_btn){
                convert2Gray();
            }
        }



    private void convert2Gray() {
        Mat src = new Mat();
        Mat temp = new Mat();
        Mat dst = new Mat();
        Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.tantuo);
        Utils.bitmapToMat(image, src);
        Imgproc.cvtColor(src, temp, Imgproc.COLOR_RGBA2BGR);
        Log.i("CV", "image type:" + (temp.type() == CvType.CV_8UC3));
        Imgproc.cvtColor(temp, dst, Imgproc.COLOR_BGR2GRAY);
        Utils.matToBitmap(dst, image);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(image);

        //release memory
        src.release();
        temp.release();
        dst.release();

    }
}
