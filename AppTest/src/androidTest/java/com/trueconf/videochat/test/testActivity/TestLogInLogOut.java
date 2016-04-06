package com.trueconf.videochat.test.testActivity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.trueconf.videochat.test.testJReport.jreport.JReport;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

/**
 *
 */
public class TestLogInLogOut extends JReport {
    private String errorMessage;

    public TestLogInLogOut(Solo solo) {
        super(solo);
    }

    public void testLogInLogOut() {
        try {
            initReport("testCorrectLogin", "Метод проверки первой авторизации при введении корректных значений TrueConfId и password, проверки уведомления menuDialogHeader"); // :1 Begin Report

            Timeout.setSmallTimeout(10000);
            solo.waitForActivity("Login", 2000);
            assertTextCaseActivity("Запуск приложения:", "Login"); // :1 case
            solo.clickOnView(solo.getView("tv_is_have_account"));
            solo.sleep(300);
            assertTrue("Activity Login is not found", solo.waitForActivity("Login"));
            solo.clickOnView(solo.getView("et_videochat_id"));
            solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
            solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "fardini");
            solo.clickOnView(solo.getView("et_password"));
            solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
            solo.enterText((android.widget.EditText) solo.getView("et_password"), "123az12");
            solo.sleep(300);
            solo.clickOnView(solo.getView("btn_login_ll"));
            solo.sleep(1500);
            assertTrue("Activity ContactTabs is not found", solo.waitForActivity("ContactTabs"));
            //Стартовое уведомление
            View menuDialogHeader = null;
            try {
                menuDialogHeader = solo.getView("menuDialogHeader");
            } catch (AssertionFailedError ignored) {
            }
            if (menuDialogHeader != null) {
                solo.goBack();
            }
            solo.sleep(1000);

            /** 3. Выход с приложения  */
            solo.clickOnActionBarHomeButton();
            solo.sleep(500);
            solo.clearLog();

            solo.sleep(500);
            android.widget.ListView homeListView = getListView();
            solo.sleep(300);

            //2.1 Определяем Logout
            String itemLogout = (String) homeListView.getItemAtPosition(10); //

            solo.sleep(300);

            solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
            solo.sleep(300);

            //2.3 Нажимаем на Logout
            solo.clickOnText(java.util.regex.Pattern.quote(itemLogout));
            solo.sleep(300);

            //2.4 проверка на переход Activity Login
            assertTextCaseActivity("Проверка перехода на Activity: Login", "Login"); // :8 case
            solo.sleep(300);
            solo.goBack();
        } catch (Exception | AssertionFailedError e) {
            errorMessage = e.getMessage();
            throw new RuntimeException(e);
        } finally {
            // :11 END Report
            destroyReport(errorMessage);
        }
    }

    private ListView getListView() {
        android.widget.ListView homeListView = null;

        for (int i = 0; i < 6; i++) {
            homeListView = solo.getView(ListView.class, i);

            if (homeListView.getChildCount() >= 10 && homeListView.getChildCount() < 16) {
                Log.d("myLog", " id  " + homeListView.getChildCount());
                return homeListView;
            }
        }
        return homeListView;

    }
}
