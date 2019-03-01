# OpenCVImageProcessing

### 1.  导入Opencv的 androrid SDK

灰度算法 OpenCVImageProcessing

导入opencv Jar包，配置OpenCVLibrary340 的 bulid.gradle ， 配置Module：app 的 build.gradle , 在依赖里添加 implementation fileTree(dir: "$buildDir/native-libs", include: 'native-libs.jar')

在Gradle Scripts 里修改 `dependencies`

​							

```java
dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation fileTree(dir: "$buildDir/native-libs", include: 'native-libs.jar')
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation project(':openCVLibrary340')
}


task nativeLibsToJar(type: Jar, description: 'create a jar archive of the native libs') {
    destinationDir file("$buildDir/native-libs")
    baseName 'native-libs'
    from fileTree(dir: 'libs', include: '**/*.so')
    into 'lib/'
}

tasks.withType(JavaCompile) {
    compileTask -> compileTask.dependsOn(nativeLibsToJar)
}
```

![image-20190227223705931](https://ws3.sinaimg.cn/large/006tKfTcly1g0noeh1j8ij31bn0u0qka.jpg)

### 2.  灰度算法

```java

    @Override
    public void onClick(View v) {
        convert2Gray();
    }

    private void convert2Gray() {
        Mat src = new Mat();
        Mat temp = new Mat();
        Mat dst = new Mat();
        Bitmap image = BitmapFactory.decodeResource(this.getResources(),R.drawable.tantuo);
        Utils.bitmapToMat(image,src);
        Imgproc.cvtColor(src, temp , Imgproc.COLOR_RGBA2BGR);
        Log.i( "CV", "image type:" + (temp.type() == CvType.CV_8UC3));
        Imgproc.cvtColor(temp, dst, Imgproc.COLOR_BGR2GRAY);
        Utils.matToBitmap(dst,image);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(image);
        
```

点击按钮，Imgproc.cvtColor(src, temp , Imgproc.COLOR_RGBA2BGR)  执行结果如下：

<img src="https://ws3.sinaimg.cn/large/006tKfTcly1g0lurhbem9j30u01ale81.jpg" width="50%" height="50%" /><img src="https://ws2.sinaimg.cn/large/006tKfTcly1g0lvc824wgj30u01akwkw.jpg" width="50%" height="50%" />
