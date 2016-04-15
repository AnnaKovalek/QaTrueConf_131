package com.trueconf.videochat.test.testActivity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.trueconf.videochat.test.testJReport.jreport.JReport;

import junit.framework.AssertionFailedError;

/**
 * Недоступен Личный кабинет в меню профайла, если авторизован пользователем
 * созданным на trueconf.ru
 */
public class TestBug32223 extends JReport {
    private String errorMessage;

    public TestBug32223(Solo solo) {
        super(solo);
    }

    public void testBug32223() {
        try {
            initReport("testBug32223", "Метод проверки Недоступен Личный кабинет в меню профайла, если авторизирован" +
                    "пользователем созданным на trueconf.ru"); // :1 Begin Report

            Timeout.setSmallTimeout(12000);
            solo.waitForActivity("Login", 2000);
            assertTextCaseActivity("Запуск приложения:", "Login"); // :1 case
            solo.clickOnView(solo.getView("tv_is_have_account"));
            solo.sleep(300);
            assertTextCaseActivity("Проверка перехода на Activity Login", "Login");
            solo.clickOnView(solo.getView("et_videochat_id"));
            solo.clearEditText((android.widget.EditText) solo.getView("et_videochat_id"));
            solo.enterText((android.widget.EditText) solo.getView("et_videochat_id"), "android.test");
            solo.clickOnView(solo.getView("et_password"));
            solo.clearEditText((android.widget.EditText) solo.getView("et_password"));
            solo.enterText((android.widget.EditText) solo.getView("et_password"), "ast456zx");
            solo.sleep(300);
            solo.clickOnView(solo.getView("btn_login_ll"));
            solo.sleep(2000);
            assertTextCaseActivity("Проверка перехода на Activity ContactTabs", "ContactTabs");

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
            solo.clickOnActionBarHomeButton();
            solo.sleep(1000);
            //android.widget.ListView homeListView_1 = solo.getView(ListView.class, 0);
            android.widget.ListView homeList = getListView();
            assertTextCase("Проверка перехода на NavigationDrawer при нажатии HomeButton");
            String itemProfile = (String) homeList.getItemAtPosition(0);
            solo.sleep(300);
            solo.clickOnText(java.util.regex.Pattern.quote(itemProfile));
            assertTextCaseActivity("Проверка перехода на Activity UserProfile", "UserProfile");
            solo.sleep(500);
            // нажимает на меню "три точки"
            solo.clickOnImage(1);
            solo.sleep(1000);
            assertTextCase("Проверка нажатия на меню на Action Bar");
            solo.clickInList(2);
            solo.sleep(10000);
            assertTextCaseActivity("Проверка перехода на Activity: WebActivity", "WebActivity");
            solo.sleep(200);
            solo.clickOnActionBarHomeButton();
            assertTextCaseActivity("Проверка перехода на Activity: UserProfile", "UserProfile");
            solo.clickOnView(solo.getView("tv_plan_name"));
            solo.sleep(10000);
            assertTextCaseActivity("Проверка перехода на Activity: WebActivity", "WebActivity");
            solo.sleep(200);
            solo.clickOnActionBarHomeButton();
            assertTextCaseActivity("Проверка перехода на Activity: UserProfile", "UserProfile");
            solo.clickOnActionBarHomeButton();
            solo.sleep(200);
            assertTextCaseActivity("Проверка перехода на Activity: ContactTabs", "ContactTabs");

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

            //2.4 Проверка на переход Activity Login
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
