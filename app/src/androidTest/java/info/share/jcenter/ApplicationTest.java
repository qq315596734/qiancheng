package info.share.jcenter;

import android.app.Application;
import android.test.ApplicationTestCase;

import cn.jpush.android.api.JPushInterface;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test_JPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplication());
    }
}