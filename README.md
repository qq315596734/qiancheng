# qiancheng
模块说明
该模块主要使用来执行脚本, 将push库文件同步到jcenter上.

同步到jcenter
将jenkins 上build的jar, so等copy 到相应目录

修改版本号

执行 ./gradlew :jpush:install

执行 ./gradlew :jpush:bintrayUpload

执行成功即上传到了bintray服务器.然后进行相关的同步就好

集成方式
在外围的gradle中配置 jcenter库(AS默认就是,不用管)

在 dependencies { } 中添加如下代码:

 implementation 'com.qckj.qnjsdk:qnjsdk:0.0.1'



在AndroidManifest.xml中不需要添加任何JPush SDK 相关的配置

<meta-data
     android:name="QNJSDK_APPID"
     android:value="您的appid"/>
<meta-data
     android:name="QNJSDK_APPKEY"
     android:value="您的appkey"/>

<service
	android:name="com.moxie.client.accessible.AccessibleCrawlerService"
    android:accessibilityFlags="flagReportViewIds"
    android:enabled="true"
    android:exported="true"
    android:label="@string/accessibility_name"
    android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
    	<intent-filter>
    		<action android:name="android.accessibilityservice.AccessibilityService" />
        </intent-filter>
        <meta-data
             android:name="android.accessibilityservice"
             android:resource="@xml/accessible_crawler_config" />
</service>
