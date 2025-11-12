package com.web.base.pages;

import com.web.base.backend.BaseAutomationContext;
import com.web.base.backend.ContextKeys;
import com.web.base.backend.WebAutomationException;
import com.web.base.pageElement.BeinButton;
import com.web.base.pageElement.BeinTextBox;
import com.web.base.pageElement.PageElementModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public abstract class BaseMasterPage {
    private static final Log log = LogFactory.getLog(BaseMasterPage.class);
    private static BeinButton BTN_AccountProfile = new BeinButton(PageElementModel.selectorNames.CLASS_NAME,"member-name");



    private static BeinButton BTN_WatchLive = new BeinButton(PageElementModel.selectorNames.CLASS_NAME, "idx-livetv");
    private static BeinButton BTN_Logout = new BeinButton(PageElementModel.selectorNames.CLASS_NAME, "//idx-livetv')]");
    private static BeinButton BTN_Subscription = new BeinButton(PageElementModel.selectorNames.XPATH, "//a[contains(@href,'purchases')]");
    private static BeinButton BTN_Profile = new BeinButton(PageElementModel.selectorNames.XPATH, "//a[contains(@href,'profile')]");
    private static BeinButton BTN_Voucher = new BeinButton(PageElementModel.selectorNames.XPATH, "//a[contains(@href,'couponcode')]");
    public static BeinButton BTN_Mailbox= new BeinButton(PageElementModel.selectorNames.CLASS_NAME, "ico-login");

    private static BeinTextBox TXT_TC = new BeinTextBox(PageElementModel.selectorNames.ID, "tridField");
    private static BeinTextBox TXT_Password = new BeinTextBox(PageElementModel.selectorNames.ID,"egpField");
    private static BeinTextBox TXT_Guvenlikkodu = new BeinTextBox(PageElementModel.selectorNames.ID, "captchaField");
    public static String USER_TC =System.getenv("USER_TC");
    public static String USER_Guvenlikkodu =System.getenv("USER_Guvenlikkodu");
    public static String USER_Password =System.getenv("USER_Password");
    public static BeinButton BTN_GIRISYAP= new BeinButton(PageElementModel.selectorNames.CLASS_NAME, "btn btn-send");

    public void logout(){
        log.info("ENTERING logout");
        BTN_AccountProfile.waitUntilVisibleAndClick();
        BTN_Logout.waitUntilVisibleAndClick();
        log.info("USED E-MAIL IS: " + BaseAutomationContext.getContextValue(ContextKeys.EMAIL));
    }

    public boolean checkLoggedIn(){
        log.info("ENTERING checkLoggedIn");
        return BTN_AccountProfile.isDisplayed();
    }

    public void subscription(){
        log.info("ENTERING subscription");
        BTN_AccountProfile.waitUntilVisibleAndClick();
        BTN_Subscription.waitUntilVisibleAndClick();
    }

    public void clickMailbox() {
        log.info("ENTERING clickMailbox");
        BTN_AccountProfile.waitUntilVisibleAndClick();
        BTN_Mailbox.waitUntilVisibleAndClick();

    }

    public void profile(){
        log.info("ENTERING subscription");
        BTN_AccountProfile.waitUntilVisibleAndClick();
        BTN_Profile.waitUntilVisibleAndClick();
    }

    public void checkNonExistenceOfSubscription(){
        log.info("ENTERING checkNonExistenceOfSubscription");
        BTN_AccountProfile.waitUntilVisibleAndClick();
        if(BTN_Subscription.isDisplayed()){
            String error = "SUBSCRIBE BUTTON SHOULD NOT BE DISPLAYED FOR PARTNER LOGIN";
            log.error(error);
            throw new WebAutomationException(error);
        } else {
            log.info("CHECK NON EXISTENCE OF SUBSCRIPTION IS SUCCESSFUL");
        }
    }

    public void checkNonExistenceOfVoucher(){
        log.info("ENTERING checkNonExistenceOfSubscription");
        BTN_AccountProfile.waitUntilVisibleAndClick();
        if(BTN_Voucher.isDisplayed()){
            String error = "VOUCHER BUTTON SHOULD NOT BE DISPLAYED FOR PARTNER LOGIN";
            log.error(error);
            throw new WebAutomationException(error);
        } else {
            log.info("CHECK NON EXISTENCE OF VOUCHER IS SUCCESSFUL");
        }
    }

    public void clickWatchLive() {
        log.info("ENTERING clickWatchLive");
        BTN_WatchLive.waitFor(5);
        BTN_WatchLive.waitUntilVisible();
        BTN_WatchLive.clickAndWait();
        TXT_TC.type(USER_TC);
        TXT_Password.type(USER_Password);
        TXT_Guvenlikkodu.type(USER_Guvenlikkodu);
        BTN_GIRISYAP.waitUntilVisibleAndClick();

    }

}
