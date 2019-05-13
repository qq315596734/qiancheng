# 去哪借SDK集成流程（Android Studio版本）

## 开始集成

### 1.导入sdk

在project目录下build.gradle文件中添加：

```java
allprojects {
    repositories {
        maven {
           url 'https://dl.bintray.com/xiaosaobo/maven'
        }
    }
}
```

在项目的build.gradle文件中添加：

```java
android {
    defaultConfig {
        ndk {
            abiFilters 'armeabi-v7a'
        }
    }
}
//如果您的项目中没有使用过魔蝎sdk和face++ sdk 
dependencies {
  // gradle >= 3.0
  implementation 'com.qckj.qnjsdk:qnjsdk-v2:1.0.0'

  // gradle < 3.0
  compile 'com.qckj.qnjsdk:qnjsdk-v2:1.0.0'
}
```

### 2.配置build.gradle

```java
android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = true    //加上这行即可
            }
        }
    }
}
```

### 3. 配置工程

请在AndroidManifest.xml中添加：

```java
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
```

### 4. 添加混淆代码

```java
-dontwarn com.qnj.**
```

### 5. 打开SDK并接收回调

第一步：在 `Application.oncreate()` 里面调用 QNJSdk.init(this); 完成初始化

第二步：在需要启动sdk的地方，通过调用QNJSdk.start(); 方法启动qnjsdk;

示例如下：

```java
/*
 Context context           上下文
 String phone              用户手机号
 String uid                用户uid
 QnjsdkCallback callback   sdk启动成功失败的回调
*/
QNJSdk.start(MainActivity.this, phone, uuid, new QnjsdkCallback() {
    @Override
    public void onResult(QnjsdkResult result) {
        if (result.getCode() == 1000) {
            //sdk启动成功
        } else {
            //sdk启动失败
        }
    }
});
```

回调中的返回码解释如下：

| 1000 | 去哪借sdk启动成功        |
| ---- | ------------------------ |
| 1001 | qnjsdk没有初始化         |
| 1002 | AppId 或 AppKey 不能为空 |
| 1003 | phone 或uuid 不能为空    |
| 1004 | 服务器出错               |

### ————————–-————接入完成——–-————————–-



## 在接入sdk时可能遇到的问题

1，如果您的项目中已经接入了魔蝎sdk或者face++ ,请在build.gradle文件中添加

```
//如果您的项目中使用过魔蝎sdk或face++ sdk 
dependencies {
  // gradle >= 3.0
    implementation （'com.qckj.qnjsdk:qnjsdk-v2:1.0.0'）{
    	//解决support包冲突
    	exclude group: "com.android.support"
        //如果您的项目中已经具有face++ sdk 添加
        exclude group: "com.qckj.qnjsdk", module : 'face'
        //如果您的项目中已经具有魔蝎 sdk 添加
        exclude group: "com.qckj.qnjsdk", module : 'moxie'
    }

  // gradle < 3.0
    compile （'com.qckj.qnjsdk:qnjsdk-v2:1.0.0'）{
       	//解决support包冲突
    	exclude group: "com.android.support"
        //如果您的项目中已经具有face++ sdk 添加
        exclude group: "com.qckj.qnjsdk", module : 'face'
        //如果您的项目中已经具有魔蝎 sdk 添加
        exclude group: "com.qckj.qnjsdk", module : 'moxie'
    }
}

```

并且在AndroidMenifest.xml文件中删除

```
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
```

如果使用了上述方法，还是会报错，请联系我司。