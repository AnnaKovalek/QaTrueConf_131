package com.trueconf.videochat.test;

import com.robotium.solo.*;
import com.trueconf.videochat.test.testActivity.TestBug29460;
import com.trueconf.videochat.test.testActivity.TestBug31048;
import com.trueconf.videochat.test.testActivity.TestBug31492;
import com.trueconf.videochat.test.testActivity.TestBug32223;
import com.trueconf.videochat.test.testActivity.TestFloatingButton;
import com.trueconf.videochat.test.testActivity.TestLogInLogOut;

import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class RunTest extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
	private TestFloatingButton testFloatingButton;
	private TestBug31492 testBug31492;
	private TestBug31048 testBug31048;
	private TestBug29460 testBug29460;
	private TestLogInLogOut testLogInLogOut;
	private TestBug32223 testBug32223;

  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.trueconf.gui.activities.Login";
    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public RunTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		testFloatingButton = new TestFloatingButton(solo);
		testBug31492 = new TestBug31492(solo);
		testBug31048 = new TestBug31048(solo);
		testBug29460 = new TestBug29460(solo);
		testLogInLogOut = new TestLogInLogOut(solo);
		testBug32223 = new TestBug32223(solo);
		getActivity();

  	}

	public void testFloatingButton (){testFloatingButton.testFloatingButton();}
	public void testBug31492 (){testBug31492.testBug31492();}
	public void testBug31048 () {testBug31048.testBug31048();}
	public void testBug29460 (){testBug29460.testBug29460();}
	public void testBug32223 (){testBug32223.testBug32223();}

	/** TestLogInLogOut*/
	public void testLogInLogOut (){testLogInLogOut.testLogInLogOut();}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
		super.tearDown();

  	}
  /**
	public void testRun() {
        //Wait for activity: 'com.trueconf.gui.activities.Login'
		solo.waitForActivity("Login", 2000);
        //Click on Сменить сервер
		solo.clickOnView(solo.getView("select_server_area"));
        //Click on Использовать другой сервер
		solo.clickOnView(solo.getView("rb_select_server_use_custom_server"));
        //Click on Соединиться
		solo.clickOnText(java.util.regex.Pattern.quote("Соединиться"));
        //Click on Использовать сервис TrueConf Online
		solo.clickOnView(solo.getView("rb_select_server_use_default_server"));
        //Click on Соединиться
		solo.clickOnText(java.util.regex.Pattern.quote("Соединиться"));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageView.class, 1));
        //Click on Выход
		solo.clickInList(2, 0);
	}
   */
}
