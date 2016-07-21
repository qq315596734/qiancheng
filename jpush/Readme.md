### 模块说明

该模块主要使用来执行脚本, 将push库文件同步到jcenter上.

##### 同步到jcenter

1. 将jenkins 上build的jar, so等copy 到相应目录

2. 修改版本号

3. 执行 ./gradlew :jpush:install

4. 执行 ./gradlew :jpush:bintrayUpload

执行成功即上传到了bintray服务器.然后进行相关的同步就好


##### 集成方式

1. 在外围的gradle中配置 jcenter库(AS默认就是,不用管)

2. 在 dependencies { } 中添加如下代码:

    compile 'cn.jiguang:jpush:2.1.8'

3. 在 defaultConfig { } 添加如下代码:

    manifestPlaceholders = [

        // 设置manifest.xml中的变量

        JPUSH_PKGNAME : applicationId,  // JPush上注册的包名,等同于 applicationId

        JPUSH_APPKEY : "8c26e14d446dd28b2ebfef0e", // JPUSH_PKGNAME 下的APPKEY

        JPUSH_CHANNEL : "developer-default", // 暂时填写默认值即可
    ]


4. 在AndroidManifest.xml中不需要添加任何JPush SDK 相关的配置

ps: 需要将自定义的Recevier添加到AndroidManifest.xml,配置如下:

    <!-- User defined.  For test only  用户自定义的广播接收器-->
    <receiver
        android:name="com.example.jpushdemo.MyReceiver"  <!-- 自定义的Receiver-->
        android:enabled="true">
        <intent-filter>
            <action android:name="cn.jpush.android.intent.REGISTRATION" /> <!--Required  用户注册SDK的intent-->
            <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" /> <!--Required  用户接收SDK消息的intent-->
            <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED" /> <!--Required  用户接收SDK通知栏信息的intent-->
            <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED" /> <!--Required  用户打开自定义通知栏的intent-->
            <action android:name="cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK" /> <!--Optional 用户接受Rich Push Javascript 回调函数的intent-->
            <action android:name="cn.jpush.android.intent.CONNECTION" /><!-- 接收网络变化 连接/断开 since 1.6.3 -->
            <category android:name="com.zhangfl.jpush" /> <!-- 同applicationId-->
        </intent-filter>
    </receiver>


##### 富媒体相关

可以在项目中覆盖原有的资源文件: 创建同名的布局文件以及相应的图片资源即可.



