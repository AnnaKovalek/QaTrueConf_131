package com.trueconf.videochat.test.testActivity;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import static junit.framework.Assert.assertTrue;

/**
 * класс проверки id floatingButton
 */
public class TestFloatingButton {
    private Solo solo;

    public TestFloatingButton (Solo solo){this.solo= solo;}

    public void testFloatingButton (){
        Timeout.setSmallTimeout(10000);
        solo.waitForActivity("Login", 2000);
        solo.clickOnView(solo.getView("tv_is_have_account"));
        solo.sleep(300);
        assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
        solo.clickOnView(solo.getView("et_videochat_id"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
        solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "android.test");
        solo.clickOnView(solo.getView("et_password"));
        solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
        solo.enterText((android.widget.EditText) solo.getView("et_password"),"ast456zx");
        solo.sleep(500);
        solo.clickOnView(solo.getView("btn_login_ll"));
        solo.sleep(1500);
        assertTrue("Activity ContactTabs is not found",solo.waitForActivity("ContactTabs"));
        solo.sleep(200);
        solo.clickOnView(solo.getView("btn_floating"));
        solo.sleep(500);
        solo.clickOnView(solo.getView("btn_add_members"));
        solo.sleep(200);
        assertTrue("Dialog Invite friends via is not found", solo.waitForDialogToOpen());
        solo.sleep(1000);
        solo.goBack();
        solo.clickOnView(solo.getView("btn_start_conference"));
        solo.sleep(500);
        assertTrue("Activity ConferenceSettings is not found", solo.waitForActivity("ConferenceSettings"));
        solo.sleep(300);
        solo.clickOnView(solo.getView("btn_floating"));
        solo.sleep(300);







    }
}
