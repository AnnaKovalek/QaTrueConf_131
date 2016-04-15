package com.trueconf.videochat.test.testActivity;


import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.robotium.solo.Timeout;
import com.trueconf.videochat.test.testJReport.jreport.JReport;

import junit.framework.AssertionFailedError;

import static junit.framework.Assert.assertTrue;

/**
 * Зависания клиента после нажатия "Close chat"
 */

public class TestBug31492 extends JReport {
    //private Solo solo;
    private android.widget.ListView homeListView_1;
    private String errorMessage;

    public TestBug31492(Solo solo) {
        super(solo);
    }

    public void testBug31492() {

        try {
            initReport("testBug31492", "Метод проверки по дискрипшину бага Зависания клиента после нажатия Close chat"); // :1 Begin Report
            Timeout.setSmallTimeout(15000);
            solo.waitForActivity("Login", 2000);
            assertTextCaseActivity("запуск приложения", "Login");
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
            solo.sleep(1500);
            assertTextCaseActivity("Проверка перехода на Activity ContactTabs", "ContactTabs");
            // assertTrue("Activity ContactTabs is not found", solo.waitForActivity("ContactTabs"));
            //Стартовое уведомление
            View menuDialogHeader = null;
            try {
                menuDialogHeader = solo.getView("menuDialogHeader");
            } catch (AssertionFailedError e) {

            }
            if (menuDialogHeader != null) {
                solo.goBack();
            }
            solo.sleep(1000);

            // Message tab
            solo.clickOnImage(5);
            solo.sleep(2000);
            solo.clickOnView(solo.getView("btn_floating"));
            solo.sleep(2000);
            // Нажатие на первого пользователя в списке чатов
            solo.clickInRecyclerView(0, 1);
            solo.sleep(500);
            // assertTrue("Activity Chat is not found", solo.waitForActivity("Chat"));
            assertTextCaseActivity("Проверка перехода на Activity Chat", "Chat");
            solo.sleep(300);
            solo.sendKey(KeyEvent.KEYCODE_MENU);
            solo.sleep(500);
            solo.clickInList(4, 0);
            solo.sleep(500);
            assertTextCaseActivity("Проверка перехода на Activity ContactTabs", "ContactTabs");
            solo.sleep(1000);

/**
 //  CallHistory tab
 solo.clickOnImage(7);
 solo.sleep(1500);

 //  Contacts tab
 solo.clickOnImage(2);
 solo.sleep(1500);
 */

            /** 2. Выход с приложения  */
            //2.0 Нажатимаем на HomeButton
            solo.clickOnActionBarHomeButton();
            solo.sleep(300);
            android.widget.ListView homeListView = solo.getView(ListView.class, 0); //TODO: version 1.30 -> 0
            solo.sleep(1000);

            //2.1  Позиция Logout № 10 в списке
            String itemLogout = findDrawerListElement(10); //TODO: version 1.30 -> 10
            solo.sleep(300);

            //2.2 Прокручиваем список на последнюю позицию
            solo.scrollListToLine(homeListView, homeListView.getLastVisiblePosition());
            solo.sleep(300);

            //2.3 Нажимаем на Logout
            solo.clickOnText(java.util.regex.Pattern.quote(itemLogout));
            solo.sleep(300);
            assertTextCaseActivity("Проверка перехода на Activity Login", "Login");
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

    private String findDrawerListElement(int positionElements) {
        int numList = 0; //TODO: version 1.30 -> 0
        String nameElements = null;
        do {
            homeListView_1 = solo.getView(ListView.class, numList);
            solo.sleep(300);
            // Определяем кнопку
            Object obj = homeListView_1.getItemAtPosition(positionElements);
            if (obj instanceof String) {
                return (String) obj;
            }
            numList++;
        } while (numList < 5);
        return "Logout";
    }

}
