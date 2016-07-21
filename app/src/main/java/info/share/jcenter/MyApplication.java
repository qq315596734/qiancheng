package info.share.jcenter;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created: nuonuo on 16/7/21
 * Company: WWW
 * Contact: zflty081319@126.com
 * Project: https://github.com/pkjueying/zfltjl
 */
public class MyApplication extends Application {
    public static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
    }
}
