package com.trueconf.videochat.test.testActivity;

import android.widget.EditText;
import android.widget.RadioButton;
import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import junit.framework.AssertionFailedError;
import static junit.framework.Assert.assertTrue;

/**
 * Не подключается к TCS по доменному имени
 */
public class TestBug31048 {
    private Solo solo;

    public TestBug31048(Solo solo) {
        this.solo = solo;
    }

    public void testBug31048() {
        Timeout.setSmallTimeout(10000);
        solo.waitForActivity("Login", 2000);
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(1000);
        //Определяем элементы
        RadioButton rb_default_server = (RadioButton) solo.getView("rb_select_server_use_default_server");
        RadioButton rb_custom_server = (RadioButton) solo.getView("rb_select_server_use_custom_server");
        EditText editText = (EditText) solo.getView("et_server_ip");
        //Нажимаем на вторую RadioButton
        solo.clickOnView(rb_custom_server);
        solo.sleep(300);
        assertTrue(rb_custom_server.isChecked());
        solo.sleep(1000);
        EditText et = (EditText) solo.getView("et_server_ip");
        solo.sleep(200);
        assertTrue(et.isEnabled());
        solo.sleep(300);
        solo.clearEditText(editText);
        solo.sleep(300);
        //Вписываем сервер
        solo.enterText(editText, "video.trueconf.com");
        solo.sleep(200);
        solo.clickOnText(java.util.regex.Pattern.quote("Connect"));
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        Timeout.setSmallTimeout(3000);
        try {
            solo.clickOnView(solo.getView("et_videochat_id"));
        } catch (AssertionFailedError e) {
            throw new IllegalArgumentException("No connected to the server");
        }
        //Возвращаем к исходному подключению: TrueConfOnline service
        solo.sleep(500);
        solo.clickOnView(solo.getView("select_server_area"));
        solo.sleep(200);
        solo.clickOnView(rb_default_server);
        solo.sleep(300);
        solo.clickOnText(java.util.regex.Pattern.quote("Connect"));
        Timeout.setSmallTimeout(3000);
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        solo.sleep(200);
        //проверка соединения к TrueConf Online (зависит от локализации)
        assertTrue(solo.waitForText("connected to service"));
        solo.goBack();

    }

}
