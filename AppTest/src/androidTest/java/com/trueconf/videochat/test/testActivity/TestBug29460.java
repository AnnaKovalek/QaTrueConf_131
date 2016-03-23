package com.trueconf.videochat.test.testActivity;

import android.widget.EditText;
import android.widget.RadioButton;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;

import static junit.framework.Assert.assertTrue;

/**
 * Падает при переподключении к сервису us2.trueconf.net
 */
public class TestBug29460 {
    private Solo solo;

    public TestBug29460(Solo solo) {
        this.solo = solo;
    }

    public void testBug29460() {
        Timeout.setSmallTimeout(3000);
        solo.waitForActivity("Login", 1000);
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(300);
        RadioButton rb_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton rb_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");
        EditText editText = (EditText) solo.getView("et_server_ip");
        solo.clickOnView(rb_custom_server);
        solo.sleep(300);
        assertTrue(rb_custom_server.isChecked());
        assertTrue(editText.isEnabled());
        solo.sleep(300);
        solo.clearEditText(editText);
        //Вводим сервис us2.trueconf.net
        solo.enterText(editText, "us2.trueconf.net");
        solo.sleep(300);
        solo.clickOnText(java.util.regex.Pattern.quote("Connect"));
        solo.sleep(300);
        assertTrue(solo.waitForView(solo.getView("RelativeLayout1")));
        solo.sleep(200);
        solo.sleep(500);
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(200);
        solo.clickOnView(rb_default_server);
        solo.sleep(300);
        solo.clickOnText(java.util.regex.Pattern.quote("Connect"));
        Timeout.setSmallTimeout(3000);
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        solo.sleep(200);
        assertTrue(solo.waitForText("connected to service"));
        solo.goBack();


    }
}
