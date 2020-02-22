package pageobjects;

import org.eclipse.jetty.util.IO;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utility.Util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddBannerPage {

    private By elmAppBannerTitle = By.xpath("//div[@class='page-title']");
    private By elmAddButton = By.xpath("//button[@href='#/appBannerAdd']");
    private By elmAddBannerTitle = By.xpath("//div[@class='page-title ng-binding']");
    private By elmShowBannerTitle = By.xpath("//label[contains(text(),'Show Banner')]");
    private By elmStartDate = By.xpath("//label[contains(text(),'Start Date')]");
    private By elmUpdateButton = By.xpath("(//button[contains(text(),'Update')])[1]");
    private By elmUpdateBannerTitle = By.xpath("//div[@class='page-title ng-binding']");
    private By elmBannerStatusTitle = By.xpath("//label[contains(text(),'Banner Status')]");
    private By elmBannerStatusCheckBox = By.xpath("(//div[@class='form-group'])[1]//input[@type='checkbox']");
    private By elmBannerOnTimeTitle = By.xpath("//label[contains(text(),'Banner On Time')]");
    private By elmBannerOffTimeTitle = By.xpath("//label[contains(text(),'Banner Off Time')]");
    private By elmBannerOnTimeInuptBox = By.xpath("//input[@name='bannerOnTime']");
    private By elmBannerOffTimeInputBox = By.xpath("//input[@name='bannerOffTime']");
    private By elmSelectBannerOnTimeDate = By.xpath("//div[@ng-model='startDate']//span[@class='glyphicon glyphicon-calendar']");
    private By elmSaveButton = By.xpath("//button[contains(text(),'Update')]");
    private By elmClickOKButton = By.xpath("//button[@ng-click='ok()']");
    private By elmAddSuccessMsg = By.xpath("//p[contains(text(),'Are you sure to add this app banner?')]");
    private By elmUpdateSuccessMsg = By.xpath("//p[contains(text(),'Are you sure to update this app banner?')]");
    private By elmBannerOnTimeErrorMsg = By.xpath("//label[@id='bannerOnTime-error']");
    private By elmErrorMsgBannerOffTime = By.xpath("//label[@id='bannerOffTime-error']");
    private By elmUpdateButton2 = By.xpath("(//button[contains(text(),'Update')])[3]");
    private By elmUpdateButton4 = By.xpath("(//button[contains(text(),'Update')])[4]");
    private String sTableName = "//*[@id='DataTables_Table_0']";
    private By btnOk=By.xpath("//button[contains(text(),'OK')]");
    private By elmAddButtonForBanner=By.xpath("//button[contains(text(),'Add')]");
    private By elmUpdateBannerTitleUpdated=By.xpath("//div[contains(text(),'Update Banner')]");
    private By bannerStatus=By.xpath("(//th[contains(text(),'Banner Status')])[1]");
    private By elmEngURL=By.xpath("//input[@ng-model='detail.clickUrlUs']");
    private By elmTCURL=By.xpath("//input[@ng-model='detail.clickUrlHk']");
    private By elmCNURL=By.xpath("//input[@ng-model='detail.clickUrlCn']");
    private By titleAddAppBanner=By.xpath("//div[text()='Add App Banner']");
    private By btnCancel=By.xpath("//button[contains(text(),'Cancel')]");
    private By elmConfirmationMessage=By.xpath("//p[@ng-bind-html='message']");
    private By btnConfirm=By.xpath("//button[contains(text(),'Confirm')]");
    private By titleUpdateAppBanner=By.xpath("//div[text()='Update App Banner']");
    private By btnUpdate=By.xpath("//button[contains(text(),'Update')]");;
    private By txtEnglishURL=By.xpath("//label[text()='URL (ENG) - Optional']/parent::div/div/input");
    private By elmUpdateBannerTitleForUpdate=By.xpath("//div[contains(text(),'Update App Banner')]");
    private By chkBannerStatus=By.xpath("//label[text()='Banner Status']/parent::div/div/label/input");
    private By elmPageTile=By.xpath("//div[@class='page-title']");
    private By elmAppBannerTab=By.xpath("//a[contains(text(),'App Banner')]");
    private By elmWebBannerTab=By.xpath("//a[contains(text(),'Web Banner')]");
    private By elmBannerTextCol=By.xpath("//th[contains(text(),'Banner Text (EN)')]");
    private By elWebBannerShowDropdown=By.xpath("//select[@name='webBannerTable_length']");
    private By btnAddWebBanner=By.xpath("//div[@id='webBanner']//button[contains(text(),'Add')]");
    private By btnAddAppBanner=By.xpath("//div[@id='appBanner']//button[contains(text(),'Add')]");
    private By elmAddWebBannerTitle=By.xpath("//div[contains(text(),'Add Web Banner')]");
    private By elmBannerTextEN=By.xpath("//label[contains(text(),'Banner Text (EN)')]");
    private By elmBannerTextTC=By.xpath("//label[contains(text(),'Banner Text (TC)')]");
    private By elmBannerTextSC=By.xpath("//label[contains(text(),'Banner Text (SC)')]");
    private By btnAddWenBannerCancel=By.xpath("//button[contains(text(),'Cancel')]");
    private By elmWebBannnerTextEN=By.xpath("//textarea[@name='bannerTextUs']");
    private By elmWebBannnerTextHK=By.xpath("//textarea[@name='bannerTextHk']");
    private By elmWebBannnerTextCN=By.xpath("//textarea[@name='bannerTextCn']");
    private By elmWebBannnerTextENErrorMsg=By.xpath("//textarea[@name='bannerTextUs']/following-sibling::label");
    private By elmWebBannnerTextHKErrorMsg=By.xpath("//textarea[@name='bannerTextHk']/following-sibling::label");
    private By elmWebBannnerTextCNErrorMsg=By.xpath("//textarea[@name='bannerTextCn']/following-sibling::label");
    private By elmBannerOffTimeErrorMsg=By.xpath("//label[@id='bannerOffTime-error']");
    private By elBannerConfirmationMsg=By.xpath("//p[contains(text(),'Are you sure to add this web banner?')]");
    private By elmTryAgainErrorMsg=By.xpath("//span[@ng-if='isFail']/b");
    private By btnUpdateWebBanner=By.xpath("(//table[@id='webBannerTable']//button[contains(text(),'Update')])[1]");
    private By elmUpdateWebBannerTitle=By.xpath("//div[contains(text(),'Update Web Banner')]");
    private By elmUpdateBannerConfirmationMsg=By.xpath("//p[contains(text(),'Are you sure to update this web banner?')]");
    private By elmPM4CAppBanner=By.xpath("//a[contains(text(),'PM4C App Banner')]");

    public void fnClickOnAddButton() throws IOException {
        try {
            Util.fnGetWebElementText1(elmAppBannerTitle, "App Banner");
            Util.fnClickObject(elmAddButtonForBanner);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyAddBannerPage() throws IOException {
        try {
            Util.fnGetWebElementText1(elmAddBannerTitle, "Add App Banner");
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public boolean fnVerifyTabs(By element) throws IOException {
        boolean bflag = false;
        try {
            WebElement button = Util.driver.findElement(element);
            if (button.isDisplayed()) {
                bflag = true;
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            bflag = false;
        }
        return bflag;
    }

    public void fnShowBannerIsNotDisplayed() throws IOException {
        try {
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmAddBannerTitle);
            boolean bflag1 = false;
            Assert.assertEquals(bflag1, fnVerifyTabs(elmShowBannerTitle));
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnStartDateIsNotDisplayed() throws IOException {
        try {
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmAddBannerTitle);
            boolean bflag1 = false;
            Assert.assertEquals(bflag1, fnVerifyTabs(elmStartDate));
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnClickOnUpdateButton() throws IOException {
        try {
            Util.fnGetWebElementText1(elmAppBannerTitle, "Banner");
            Util.fnClickObject(elmUpdateButton);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyUpdateBannerPage() throws IOException {
        try {

            Util.fnGetWebElementText1(elmUpdateBannerTitle, "Update App Banner");
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnShowBannerIsNotDisplayedOnUpdateBannerPage() throws IOException {
        try {
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmUpdateBannerTitle);
            boolean bflag1 = false;
            Assert.assertEquals(bflag1, fnVerifyTabs(elmShowBannerTitle));
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnStartDateIsNotDisplayedOnUpdateBannerPage() throws IOException {
        try {
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmUpdateButton);
            boolean bflag1 = false;
            Assert.assertEquals(bflag1, fnVerifyTabs(elmStartDate));
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyAppBannerStatus(String sExpectedText) throws IOException {
        try {
            Util.fnGetWebElementText1(elmBannerStatusTitle, sExpectedText);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyCheckBox() throws IOException {
        try {
            Util.fnVerifyRadioButtonExistance(elmBannerStatusCheckBox);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyBannerTime(String sBannerOnTime, String sBannerOffTime) throws IOException {
        By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
        try {
            Util.fnClickObject(objBannerStatus);
            Util.fnGetWebElementText1(elmBannerOnTimeTitle, sBannerOnTime);
            Util.fnGetWebElementText1(elmBannerOffTimeTitle, sBannerOffTime);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyBannerTimefunction() throws IOException {
        try {
            Util.fnCheckObjectIfObjectIsEnabled(elmBannerOnTimeInuptBox);
            Util.fnCheckObjectIfObjectIsEnabled(elmBannerOffTimeInputBox);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnSelectDateFromCalendar(By Object, String sDate) throws IOException {
        try {
            WebElement selectDate = Util.driver.findElement(elmSelectBannerOnTimeDate);
            selectDate.click();
            WebElement enterDate = Util.driver.findElement(Object);
            enterDate.clear();
            enterDate.sendKeys(sDate);
            //selectDate.click();
            Util.driver.findElement(elmBannerStatusTitle).click();
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterStartDate() throws IOException {
        try {
            fnSelectDateFromCalendar(elmBannerOnTimeInuptBox, "2018-12-19 00:00");
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterOffDate() throws IOException {
        try {
            fnSelectDateFromCalendar(elmBannerOffTimeInputBox, "2018-12-25 00:00");
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterOnAndOffTime() throws IOException {
        fnEnterStartDate();
        fnEnterOffDate();
    }

    public void fnUpdateBanner(String sbannerUs, String sbannerHk, String sbannerCn) throws IOException {
        try {
            Util.fnUploadBannerWithName(sbannerUs);
            Util.fnUploadBannerWithName(sbannerHk);
            Util.fnUploadBannerWithName(sbannerCn);

//            Util.fnClickObject(By.xpath("//input[@name='"+bannerUs+"']"));
//            Util.fnFileUploadAutoIt(bannerUs);
//
//            Util.fnClickObject(By.xpath("//input[@name='"+bannerHk+"']"));
//            Util.fnFileUploadAutoIt(bannerHk);
//
//            Util.fnClickObject(By.xpath("//input[@name='"+bannerCn+"']"));
//            Util.fnFileUploadAutoIt(bannerCn);

        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnUncheckBannerStatus() throws IOException {
        By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
        if(Util.driver.findElement(objBannerStatus).isSelected()){
                 Util.fnClickObject(objBannerStatus);
                 Util.fnVerifyWebElementDoesnNotExist(elmBannerOnTimeInuptBox);
                 Util.fnVerifyWebElementDoesnNotExist(elmBannerOffTimeInputBox);
        }


    }

    public void fnSaveAppBannerPage() throws IOException {
        try {
            Thread.sleep(2000);
            Util.fnClickObject(elmSaveButton);
            Thread.sleep(1000);
//            Util.fnClickObject(elmClickOKButton);
//            Thread.sleep(1000);
            Util.fnGetWebElementText1(elmAddSuccessMsg, "Are you sure to add this app banner?");
            Thread.sleep(1000);
            Util.fnClickObject(elmClickOKButton);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnBannerStatusCheckBox() throws IOException {
        try {
            String sExpectedAttribute = "ng-pristine ng-untouched ng-valid ng-not-empty";
            WebElement buttonAttribute = Util.driver.findElement(elmBannerStatusCheckBox);
            String sActualResult = buttonAttribute.getAttribute("class");
            Assert.assertEquals(sExpectedAttribute, sActualResult);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }


    public void fnEnterBannerTimes(String sBannerOnTime, String sBannerOffTime) throws IOException {
        try {
            fnSelectDateFromCalendar(elmBannerOnTimeInuptBox, sBannerOnTime);
            fnSelectDateFromCalendar(elmBannerOffTimeInputBox, sBannerOffTime);

        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnBannerOnTimeIsLessThanBannerOffTime(String sBannerOnTime, String sBannerOffTime) throws IOException {
        try {
            boolean bflag = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(sBannerOnTime);
            Date date2 = sdf.parse(sBannerOffTime);
//            System.out.println("date1 : " + sdf.format(date1));
//            System.out.println("date2 : " + sdf.format(date2));
            if (date1.before(date2)) {
                bflag = true;
            }
            Assert.assertTrue(bflag);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterBannerOnTime(String sBannerOnTime) throws IOException {
        try {
            fnSelectDateFromCalendar(elmBannerOnTimeInuptBox, sBannerOnTime);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterBannerOffTime(String sBannerOffTime) throws IOException {
        try {
            Util.driver.findElement(elmBannerOffTimeInputBox).sendKeys(sBannerOffTime);
            Util.driver.findElement(elmBannerStatusTitle).click();
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnClickOnSaveButton() throws IOException {
        try {
            Util.fnClickObject(elmSaveButton);
            Thread.sleep(2000);

        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }


    public void fnVerifyBannerOnTimeErrorMsg(String sExpectedErrorMsg) throws IOException {
        try {
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmUpdateBannerTitle);
            Util.fnGetWebElementText1(elmBannerOnTimeErrorMsg, sExpectedErrorMsg);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnBannerOnTimeIsGreaterThanBannerOffTime(String sBannerOnTime, String sBannerOffTime) throws IOException {
        try {
            boolean bflag = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(sBannerOnTime);
            Date date2 = sdf.parse(sBannerOffTime);
//            System.out.println("date1 : " + sdf.format(date1));
//            System.out.println("date2 : " + sdf.format(date2));
            if (date1.after(date2)) {
                bflag = true;
            }
            Assert.assertTrue(bflag);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyMsgBelowBannerOffTime(String sErrorMsg) throws IOException {
        try {
            Util.fnGetWebElementText1(elmErrorMsgBannerOffTime, sErrorMsg);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyMsgBelowBannerOffTime1(String sErrorMsg) throws IOException {
        try {
            Util.fnClickObject(elmClickOKButton);
            Thread.sleep(2000);
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmBannerOffTimeInputBox);
            Util.fnGetWebElementText1(elmErrorMsgBannerOffTime, sErrorMsg);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnBannerOnTimeIsLessThanCurrentDate(String sBannerOnTime, String sBannerOffTime) throws IOException {
        try {
            boolean bflag = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(sBannerOnTime);
            Date date2 = sdf.parse(sBannerOffTime);
            Date currentDate = new Date();

//            System.out.println(currentDate);

//            System.out.println("date1 : " + sdf.format(date1));
//            System.out.println("date2 : " + sdf.format(date2));
            if (date1.before(date2) && date1.before(currentDate) && date2.before(currentDate)) {
                bflag = true;
            }
            Assert.assertTrue(bflag);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnUpdateAppBannerPage() throws IOException {
        try {
            Thread.sleep(2000);
            Util.fnClickObject(elmUpdateButton);
            Thread.sleep(1000);
//            Util.fnClickObject(elmClickOKButton);
//            Thread.sleep(1000);
            Util.fnGetWebElementText1(elmUpdateSuccessMsg, "Are you sure to update this app banner?");
            Util.fnClickObject(elmClickOKButton);
            Thread.sleep(3000);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnClickOnAddButton2() throws IOException {
        try {
            Util.fnGetWebElementText1(elmAppBannerTitle, "Banner");
            Util.fnClickObject(elmUpdateButton2);
            Util.waitForWebElement(elmUpdateBannerTitleUpdated);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnClickOnAddButton4() throws IOException {
        try {
            Util.fnGetWebElementText1(elmAppBannerTitle, "App Banner");
            Util.fnClickObject(elmUpdateButton4);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnBannerStatusCheckBoxUpdate() throws IOException {
        try {
            By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
            if(Util.driver.findElement(objBannerStatus).isSelected()){

            }
            else
            {
                Util.fnClickObject(objBannerStatus);
            }
//            String sExpectedAttribute1 = "ng-pristine ng-untouched ng-valid ng-not-empty";
//            Thread.sleep(3000);
//            WebElement buttonAttribute = Util.driver.findElement(elmBannerStatusCheckBox);
//            String sActualResult = buttonAttribute.getAttribute("class");
//            Assert.assertEquals(sExpectedAttribute1, sActualResult);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnSelectDate(By Object, String sDate) throws IOException {
        try {

            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if(sDate.equalsIgnoreCase("Today")){
                today = calendar.getTime();
                String strToday = dateFormat.format(today);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strToday);
            }
            if (sDate.equalsIgnoreCase("Tomorrow")) {
                calendar.add(calendar.DAY_OF_YEAR, 1);
                Date tomorrow = calendar.getTime();
                String strTomorrow = dateFormat.format(tomorrow);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strTomorrow);
            }
            if (sDate.equalsIgnoreCase("OneWeekAfter")) {
                calendar.add(calendar.DAY_OF_YEAR, 7);
                Date OneWeek = calendar.getTime();
                String strOneWeek = dateFormat.format(OneWeek);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strOneWeek);
            }
            if (sDate.equalsIgnoreCase("Yesterday")) {
                calendar.add(calendar.DAY_OF_YEAR, -1);
                Date yesterday = calendar.getTime();
                String strYesterday = dateFormat.format(yesterday);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strYesterday);
            }
            if (sDate.equalsIgnoreCase("OneWeekBefore")) {
                calendar.add(calendar.DAY_OF_YEAR, -7);
                Date OneWeekBefore = calendar.getTime();
                String strOneWeekBefore = dateFormat.format(OneWeekBefore);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strOneWeekBefore);
            }
            if (sDate.equalsIgnoreCase("TwoMonthAfter")) {
                calendar.add(calendar.DAY_OF_YEAR, 60);
                Date OneWeek = calendar.getTime();
                String strOneWeek = dateFormat.format(OneWeek);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strOneWeek);
            }
            if (sDate.equalsIgnoreCase("ThreeMonthAfter")) {
                calendar.add(calendar.DAY_OF_YEAR, 90);
                Date OneWeek = calendar.getTime();
                String strOneWeek = dateFormat.format(OneWeek);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strOneWeek);
            }
            if (sDate.equalsIgnoreCase("FourMonthAfter")) {
                calendar.add(calendar.DAY_OF_YEAR, 120);
                Date OneWeek = calendar.getTime();
                String strOneWeek = dateFormat.format(OneWeek);
                Util.driver.findElement(Object).clear();
                Util.driver.findElement(Object).sendKeys(strOneWeek);
            }
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }



    public void fnBannerOnTime(String sBannerOnTime) throws IOException {
        try {
            fnSelectDate(elmBannerOnTimeInuptBox,sBannerOnTime);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnBannerOffTime(String sBannerOffTime) throws IOException {
        try {
            fnSelectDate(elmBannerOffTimeInputBox,sBannerOffTime);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterBannersTime(String sBannerOnTime, String sBannerOffTime) throws IOException, InterruptedException {
        Thread.sleep(3000);
//        fnBannerOnTime(sBannerOnTime);
//        fnBannerOffTime(sBannerOffTime);
        sBannerOnTime = Util.fnGetCalculatedDate("DayPrevious",7);
        sBannerOffTime = Util.fnGetCalculatedDate("DayPrevious",2);
        Util.fnEnterText(elmBannerOnTimeInuptBox,sBannerOnTime);
        Util.fnEnterText(elmBannerOffTimeInputBox,sBannerOffTime);
    }

    public void fnCompareBannerTime(String sBannerOnTime, String sBannerOffTime, String sCompare) throws IOException {
        try {
            boolean bflag = false;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(sBannerOnTime);
            Date date2 = sdf.parse(sBannerOffTime);
//            Date today = new Date();
//            System.out.println(today);
//            //Calendar calendar = Calendar.getInstance();
            //Date today = calendar.getTime();

            if(sCompare.equalsIgnoreCase("OneWeekAfter")) {
                if (date1.before(date2)) {
                    bflag = true;
                }
            }
//            else if(sCompare.equalsIgnoreCase("OneWeekBefore")) {
//                if (date1.before(today) && date2.before(today)) {
//                    bflag = true;
//                }
//            }
            Assert.assertTrue(bflag);
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnVerifyBannerDates(String sBannerOnTime, String sBannerOffTime) throws IOException{
        try {
            fnCompareBannerTime(sBannerOnTime, sBannerOffTime, "OneWeekAfter");
        } catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnClearBannerOffTimeAndEnterBannerOnTime(String sBannerOnTime) throws IOException {
        try {
            Util.driver.findElement(elmBannerOffTimeInputBox).clear();
            Util.fnEnterText(elmBannerOnTimeInuptBox,sBannerOnTime);
        }
        catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnErrorMsgSmallerThanCurrentTime(String sExpectedError) throws IOException  {
        try{
//            Util.fnClickObject(elmClickOKButton);
            Util.fnScrollDownWebPageByVisibilityOfTheElement(elmUpdateBannerTitle);
            Util.fnGetWebElementText1(elmErrorMsgBannerOffTime, sExpectedError);
        }
        catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnUpdateBannerStatusToEnabled(String sBannerOnTime, String sBannerOffTime) throws IOException {
        try{
            String sActualValue = null;
            boolean bValueFound = false;
            int iColumnNumber = 0;
            boolean bColumnFound = false;
            String sActualText = null;
            String sColumnName = "Banner Status";
            String sExpectedValue = "Disabled";

            List cols = Util.driver.findElements(By.xpath("//thead//tr//th"));
            for (int iColumnStart = 1; iColumnStart <=6; iColumnStart++) {
                Util.waitForWebElement(elmAppBannerTitle);
                String sCoulumnValue = Util.driver.findElement(By.xpath("//thead//tr//th[" + iColumnStart + "]")).getText();
                if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
                    iColumnNumber = iColumnStart;
                    bColumnFound = true;
                    break;
                }

            }
            List rows = Util.driver.findElements(By.xpath("//tbody//tr"));
            for (int iRowStart = 1; iRowStart <= 6; iRowStart++) {
                sActualText = Util.driver.findElement(By.xpath("//tbody//tr[" + iRowStart + "]//td[1]")).getText();
                    if ((sActualText.trim()).equals(sExpectedValue.trim())) {
                            By updateBtn=By.xpath("//tbody//tr[" + iRowStart + "]//td[6]//button");
                            Util.fnClickObject(updateBtn);
                            Thread.sleep(2000);
                            Util.fnClickObject(elmBannerStatusCheckBox);
                            Thread.sleep(2000);
                            sBannerOnTime = Util.fnGetCalculatedDate("DayAfter",(1+iRowStart));
                            sBannerOffTime = Util.fnGetCalculatedDate("DayAfter",(2+iRowStart));
                            Util.fnEnterText(elmBannerOnTimeInuptBox,sBannerOnTime);
                            Util.fnEnterText(elmBannerOffTimeInputBox,sBannerOffTime);
                            Util.fnEnterText(elmEngURL,"https://www.hsbc.com/");
                            Util.fnEnterText(elmTCURL,"https://www.hsbc.com/");
                            Util.fnEnterText(elmCNURL,"https://www.hsbc.com/");
                            Util.fnClickObject(elmUpdateButton);
                            Util.fnSwitchToNewWindow();
                            Thread.sleep(2000);
                            Util.fnClickObject(elmClickOKButton);
                            Thread.sleep(2000);
//                            Util.fnClickObject(elmClickOKButton);
//                            Thread.sleep(2000);
                            Util.fnSwitchToDefaultWindow();
                        }
                }
        }
        catch (Exception e) {
            Util.takeScreenShot(Util.driver);
            e.printStackTrace();
        }
    }

    public void fnEnterBannersTime1(String sBannerOnTime, String sBannerOffTime) throws IOException, InterruptedException {
        Thread.sleep(3000);
        fnBannerOnTime(sBannerOnTime);
        Thread.sleep(3000);
        fnBannerOffTime(sBannerOffTime);
    }

    public void theAgentCannotUpdateAnyAppBannerCantSeeUpdateButton() {
        Util.fnVerifyWebElementDoesnNotExist(elmUpdateButton);

    }

    public void theAgentCannotCreateAnyNewAppBannerCanTSeeButtonAtTheBottom() {
        Util.fnVerifyWebElementDoesnNotExist(elmAddButton);
    }

    public void disableExistingBanners() {
        int iRows= Util.driver.findElements(By.xpath("//table[@id='appBannerTable']//tbody/tr")).size();
        String sBannerStatus="";
        int iStart=0;
        try {
            Util.fnClickObject(bannerStatus);
            Thread.sleep(3000);
            Util.fnClickObject(bannerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//            for (int iStart = 1; iStart <= iRows; iStart++) {

                while((Util.driver.findElements(By.xpath("//table[@id='appBannerTable']//td[contains(text(),'Enabled')]")).size())>=1) {
                    iStart=1;
                    sBannerStatus = Util.driver.findElement(By.xpath("//table[@id='appBannerTable']//tbody/tr["+iStart+"]/td[1]")).getText();
                    if (sBannerStatus.equalsIgnoreCase("Enabled")) {
                        By myObject = By.xpath("//table[@id='appBannerTable']//tbody/tr["+iStart+"]/td[6]/button[contains(text(),'Update')]");
                        By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
                        Util.fnScrollDownWebPageByVisibilityOfTheElement(myObject);
                        Util.fnClickObject(myObject);
                        Thread.sleep(2000);
                        Util.fnClickObject(objBannerStatus);
                        Thread.sleep(2000);
                        By btnUpdate=By.xpath("//button[contains(text(),'Update')]");
                        Util.fnClickObject(btnUpdate);
                        Thread.sleep(2000);
                        Util.fnSwitchToNewWindow();
                        Util.fnClickObject(btnConfirm);
                        Thread.sleep(2000);
//                        Util.fnClickObject(btnOk);
//                        Thread.sleep(2000);
//                        Util.fnSwitchToDefaultWindow();
                        iStart=iStart+1;
                        Util.fnClickObject(bannerStatus);
                        Thread.sleep(3000);
                        Util.fnClickObject(bannerStatus);

                    }
                }
            }
//        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void showTwoTimeSelectionFieldsWithFieldNamesAndForDateFieldCheck(String sBannerOnTime, String sBannerOffTime) {
        By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
        if(Util.driver.findElement(objBannerStatus).isSelected()){
            //Do nothing
        }
        else{
            try {
                Util.fnClickObject(objBannerStatus);
                Util.fnGetWebElementText1(elmBannerOnTimeTitle, sBannerOnTime);
                Util.fnGetWebElementText1(elmBannerOffTimeTitle, sBannerOffTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showTwoTimeSelectionFieldsWithFieldNamesAndForStatusCheckOfFieldNames(String sBannerOnTime, String sBannerOffTime) {
        By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
        if(Util.driver.findElement(objBannerStatus).isSelected()){
            //Do nothing
        }
        else{
            try {
                Util.fnClickObject(objBannerStatus);
                Util.fnGetWebElementText1(elmBannerOnTimeTitle, sBannerOnTime);
                Util.fnGetWebElementText1(elmBannerOffTimeTitle, sBannerOffTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void andAreActivatedIeIsCheckedForBannerCheck() {
        By objBannerStatus = By.xpath("//div/label[contains(text(),'Banner Status')]/following-sibling::div/label/input");
        if(Util.driver.findElement(objBannerStatus).isSelected()){
            //Do nothing
        }
        else{
            try {
                Util.fnClickObject(objBannerStatus);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void theUserClicksAddButton1() {
        try {
            Util.fnClickObject(elmAddButtonForBanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void theUserWillLandOnPageWithTitleAddAppBannerInsteadOfAddBanner() {
        Util.fnVerifyWebElementExistance(titleAddAppBanner);
    }

    public void theUserWillSeeTwoButtonsAddInsteadOfSaveAndCancelAtBottomLeftCorner() {
        Util.fnVerifyWebElementExistance(elmAddButtonForBanner);
        Util.fnVerifyWebElementExistance(btnCancel);
    }

    public void theUserClicksAdd() {
        try {
            Util.fnClickObject(elmAddButtonForBanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void theUserWillLandOnInformationModalDialogueWithCTAConfirmAndCancel(String arg0) {
        try {
            Util.fnGetWebElementText(elmConfirmationMessage,arg0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aClickOnConfirmThisNewAppBannerWillBeAdded() {
        try {
            Util.fnClickObject(btnConfirm);
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void theUserWillLandOnPageWithTitleUpdateAppBannerInsteadOfUpdateBanner() {
           Util.fnVerifyWebElementExistance(titleUpdateAppBanner);
    }

    public void theUserWillSeeTwoButtonsUpdateInsteadOfSaveAndCancelAtBottomLeftCorner() {
        Util.fnVerifyWebElementExistance(btnCancel);
        Util.fnVerifyWebElementExistance(btnUpdate);

    }

    public void updateEnglishURAs(String arg0) {
        try {
            Util.fnEnterText(txtEnglishURL,arg0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickOnUpdateButtonOfBanner() {
        try {
            Util.fnClickObject(btnUpdate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clicksOnUpdateButtonToUpdateBanner() {
        try {
            Util.fnGetWebElementText1(elmAppBannerTitle, "Banner");
            Util.fnClickObject(elmUpdateButton);
            Util.waitForWebElement(elmUpdateBannerTitleForUpdate);
        } catch (Exception e) {
            try {
                Util.takeScreenShot(Util.driver);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void disableBannerStatus() {
        try {
            Util.fnClickObject(chkBannerStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userWillLandBackToBannerPage() {
        try {
            Util.fnGetWebElementText(elmPageTile,"Banner");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addButtonIsDisabledByDefault() {
        Util.fnCheckObjectStatus(elmAddButtonForBanner,false);
    }

    public void updateButtonIsDisabledByDefault() {
        Util.fnCheckObjectStatus(btnUpdate,false);

    }

    public void theUserCanSeeTwoTabsAppBannerAndWebBanner(){
        Util.fnVerifyWebElementExistance(elmAppBannerTab);
        Util.fnVerifyWebElementExistance(elmWebBannerTab);
    }

    public void theUserCanSeeThatByDefaultAppBannerTabWillBeDisplayed(){
        Util.fnVerifyAttributeValue(elmAppBannerTab,"aria-expanded","null");
    }

    public void theUserClicksOnWebBannerTab() throws IOException{
        Util.fnClickObject(elmWebBannerTab);
    }

    public void theUserCanSeeTableWithFollowingColumns(String arg0){
        String sExpectedValues = arg0;
        String[] arryElements = sExpectedValues.split("#");
        Util.fnVerifyWebElementExistance(elmBannerTextCol);
        for (int iStart = 1; iStart < arryElements.length; iStart++) {
            By myObject = By.xpath("(//th[contains(text(),'" + arryElements[iStart] + "')])[2]");
            Util.fnVerifyWebElementExistance(myObject);
        }
    }

    public void theUserCanSeeTableWithColumn(String sExpected) throws IOException{
        Util.fnGetWebElementText(elmBannerTextCol,sExpected);
    }

    public void theUserShouldSeeThatByDefaultTheNumberOfEntriesAvailableOnEachPageIsMaxinum(int arg0) {
        boolean bflag=false;
        try {
            List<WebElement> rows = Util.driver.findElements(By.xpath("//table[@id='webBannerTable']/tbody/tr"));
            int NoOfBanners = rows.size();
            if(NoOfBanners<=arg0){
                bflag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theUserClicksOnTheFilterForNumberOfEntries() throws IOException, InterruptedException{
        Thread.sleep(3000);
        Util.fnClickObject(elWebBannerShowDropdown);
    }

    public void theUserCasSeeByDefaultIsSelected(String arg0){
        Util.fnverifyDropdownSelection(elWebBannerShowDropdown,arg0);
    }

    public void theUserCanSeeFollowingOptionsAfterSingleClickingOnTheFilterDropdown(String arg0){
        String sExpectedValues=arg0;
        String[] arryElements=sExpectedValues.split("#");
        for(int iStart=0;iStart<arryElements.length;iStart++){
            try {
                Util.fnVerifyValueFromDropDown(elWebBannerShowDropdown,arryElements[iStart]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void theNumberOfEntriesAvailableOnEachPageWillBeChangedAccordinglyWhenTheUserHasSelectedDifferentNumber(){
        int arg0 = 10;
        theUserShouldSeeThatByDefaultTheNumberOfEntriesAvailableOnEachPageIsMaxinum(arg0);
    }

    public void theUserCanSeeAddButtonBelowTheTable() throws IOException{
        Util.fnVerifyButtonExist(btnAddWebBanner);
    }

    public void theUserClicksAddButton() throws IOException{
        Util.fnClickObject(btnAddWebBanner);
    }

    public void theUserWillLandOnPage(String sTitle) throws IOException{
        Util.fnGetWebElementText(elmAddWebBannerTitle,sTitle);
    }

    public void theUserCanSeeFollowingFieldsByDefaultAs(String arg0,String arg1,String arg2,String arg3) throws IOException{
        Util.fnGetWebElementText(elmBannerStatusTitle,arg0);
        Util.fnGetWebElementText(elmBannerTextEN,arg1);
        Util.fnGetWebElementText(elmBannerTextTC,arg2);
        Util.fnGetWebElementText(elmBannerTextSC,arg3);
    }

    public void bannerStatusCheckboxIsChecked(){
        try{
            if(Util.driver.findElement(elmBannerStatusCheckBox).isSelected()){
                System.out.println("Banner Status Checkbox is selected");
            } else{
                Util.fnClickObject(elmBannerStatusCheckBox);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void andFieldsAreDisplayed(String arg0,String arg1) throws IOException{
        Util.fnGetWebElementText(elmBannerOnTimeTitle,arg0);
        Util.fnGetWebElementText(elmBannerOffTimeTitle,arg1);
    }

    public void theUserCanAlsoSeeAddAndCancelButtonsAtTheBottomLeftCornorOfTheSection(){
        Util.fnVerifyWebElementExistance(elmAddButtonForBanner);
        Util.fnVerifyWebElementExistance(btnAddWenBannerCancel);
    }

    public void addButtonIsDisabledByDefault1() throws IOException{
        Util.fnVerifyTextboxStatusIsDisable(elmAddButtonForBanner,"Disabled");
    }

    public void addButtonWillBeEnabled() throws IOException, InterruptedException{
        Thread.sleep(3000);
        Util.fnVerifyTextboxStatusIsDisable(elmAddButtonForBanner,"Enabled");
    }

    public void theUserUnchecksBannerStatus(){
        try{
            if(Util.driver.findElement(elmBannerStatusCheckBox).isSelected()){
                Util.fnClickObject(elmBannerStatusCheckBox);
            } else{
                System.out.println("Banner Status Checkbox is unchecked");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fieldsBannerOnTimeWillBeHidden(){
        boolean bflag=false;
        By disbale=By.xpath("//label[contains(text(),'Banner On Time')]/parent::div/parent::div");
        Util.fnVerifyAttributeValue(disbale,"class", "form-group ng-hide");
    }

    public void fieldsBannerOffTimeWillBeHidden(){
        boolean bflag=false;
        By disbale1=By.xpath("//label[contains(text(),'Banner Off Time')]/parent::div/parent::div");
        Util.fnVerifyAttributeValue(disbale1,"class", "form-group ng-hide");
    }

    public void fieldsBannerOnTimeAndBannerOffTimeWillBeHidden(){
        fieldsBannerOnTimeWillBeHidden();
        fieldsBannerOffTimeWillBeHidden();
    }

    public void enterCommentInBannerTextENAs(String arg0) throws IOException{
        Util.fnEnterText(elmWebBannnerTextEN,arg0);
    }

    public void enterCommentInBannerTextTCAs(String arg0) throws IOException{
        Util.fnEnterText(elmWebBannnerTextHK,arg0);
    }

    public void enterCommentInBannerTextSCAs(String arg0) throws IOException{
        Util.fnEnterText(elmWebBannnerTextCN,arg0);
    }

    public void userClicksOnAddButton() throws IOException {
        Util.fnClickObject(elmAddButtonForBanner);
    }

    public void theUserWillSeeErrorMessageUnderTheIncompleteMandatoryFields(String sErrorMsg) throws IOException{
        Util.fnGetWebElementText(elmWebBannnerTextENErrorMsg,sErrorMsg);
        Util.fnGetWebElementText(elmWebBannnerTextHKErrorMsg,sErrorMsg);
        Util.fnGetWebElementText(elmWebBannnerTextCNErrorMsg,sErrorMsg);
    }

    public void theUserWillSeeErrorMessage(String sErrorMsg) throws IOException {
        Util.fnGetWebElementText(elmBannerOffTimeErrorMsg,sErrorMsg);
    }

    public void theUserWillLandOnInformationDialogue(String sMessage) throws IOException{
        Util.fnGetWebElementText(elBannerConfirmationMsg,sMessage);
    }

    public void theUserWillSeeConfirmAndCancelButtonOnCTA(){
        Util.fnVerifyWebElementExistance(btnConfirm);
        Util.fnVerifyWebElementExistance(btnCancel);
    }

    public void userClicksConfirmButton() throws IOException{
        Util.fnClickObject(btnConfirm);
    }

    public void newWebBannerWillBeAdded() throws IOException, InterruptedException{
        Thread.sleep(5000);
        By bannerAdded=By.xpath("//td[contains(text(),'Testing AC 10')]");
        By bannerStatus=By.xpath("//td[contains(text(),'Testing AC 10')]/ancestor::tr/td[1]");
        Util.fnVerifyWebElementExistance(bannerAdded);
        Util.fnGetWebElementText(bannerStatus,"Enabled");
    }

    public void theUserCannotSeeAddButtonBelowBannerTable(){
        Util.fnVerifyWebElementDoesnNotExist(btnAddWebBanner);
    }

    public void byDefaultTheUserCanSeeEnteriesOrderedByInReverseChronologicalOrder(String arg0) throws InterruptedException{
        try {
            Thread.sleep(3000);
            String sTableName = "//div[@id='webBannerTable_wrapper']";
            Util.fnValidateTicketNumberDescendingOrder(sTableName, arg0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void theUserHasEnteredValuesForAndField(String arg0,String arg1){
        try{
            Util.fnEnterText(elmBannerOnTimeInuptBox,arg0);
            Util.fnEnterText(elmBannerOffTimeInputBox,arg1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disableExistingWebBanners() throws IOException, InterruptedException{
        String webTable="//table[@id='webBannerTable']";
        By webBannerStatus=By.xpath("(//th[contains(text(),'Banner Status')])[2]");
        Util.fnSelectValueFromDropDown(elWebBannerShowDropdown,"100");
        Thread.sleep(2000);
        int iRows= Util.driver.findElements(By.xpath(webTable+"//tbody/tr")).size();
        String sBannerStatus="";
        int iStart=0;
        try {
            Util.fnClickObject(webBannerStatus);
            Thread.sleep(3000);
            Util.fnClickObject(webBannerStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while((Util.driver.findElements(By.xpath(webTable+"//td[contains(text(),'Enabled')]")).size())>=1) {
                iStart=1;
                sBannerStatus = Util.driver.findElement(By.xpath(webTable+"//tbody/tr["+iStart+"]/td[1]")).getText();
                if (sBannerStatus.equalsIgnoreCase("Enabled")) {
                    By myObject = By.xpath(webTable+"//tbody/tr["+iStart+"]/td[5]/button[contains(text(),'Update')]");
                    Util.fnScrollDownWebPageByVisibilityOfTheElement(myObject);
                    Util.fnClickObject(myObject);
                    Thread.sleep(2000);
                    Util.fnClickObject(chkBannerStatus);
                    Thread.sleep(2000);
                    Util.fnClickObject(btnUpdate);
                    Thread.sleep(2000);
                    Util.fnSwitchToNewWindow();
                    Util.fnClickObject(btnConfirm);
                    Thread.sleep(2000);
//                    Util.fnClickObject(btnOk);
//                    Thread.sleep(2000);
                    Util.fnSwitchToDefaultWindow();
                    iStart=iStart+1;

                    Util.fnClickObject(webBannerStatus);
                    Thread.sleep(3000);
                    Util.fnClickObject(webBannerStatus);

                }
            }
        }
//        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserWillSeeErrorMessageNextToCancelButton(String sErrorMsg){
        try{
            Util.fnGetWebElementText(elmTryAgainErrorMsg,sErrorMsg);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserCanSeeUpdateButtonInTheActionColumnWithinTheTable(){
        try{
            Util.fnVerifyWebElementExistance(btnUpdateWebBanner);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserCannotSeeUpdateButtonInTheActionColumnWithTheTable(){
        try{
            Util.fnVerifyWebElementDoesnNotExist(btnUpdateWebBanner);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserClicksUpdateForTheEnabledBannerEntry(){
        try{
            Util.fnClickObject(btnUpdateWebBanner);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserWillLandOnPageOfThatClickedBanner(String sExpectedTitle){
        try{
            Util.fnGetWebElementText(elmUpdateWebBannerTitle,sExpectedTitle);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void bannerStatusShouldBeChecked(){
        boolean bflag=false;
        try{
            if(Util.driver.findElement(elmBannerStatusCheckBox).isSelected()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void isMandatoryFieldAndShouldBePrefilled(String sExpected){
        boolean bflag=false;
        try{
            Util.fnGetWebElementText(elmBannerOnTimeTitle,sExpected);
            String bannerOnTime=Util.driver.findElement(elmBannerOnTimeInuptBox).getText().trim();
            if(bannerOnTime.isEmpty()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theIsMandatoryAndShouldBePrefilled(String sExpected){
        boolean bflag=false;
        try{
            Util.fnGetWebElementText(elmBannerOffTimeTitle,sExpected);
            String bannerOffTime=Util.driver.findElement(elmBannerOffTimeInputBox).getText().trim();
            if(bannerOffTime.isEmpty()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theIsMandatoryAndPrefilled(String sExpected){
        boolean bflag=false;
        try{
            Util.fnGetWebElementText(elmBannerTextEN,sExpected);
            String bannerENText=Util.driver.findElement(elmWebBannnerTextEN).getText().trim();
            if(bannerENText.isEmpty()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theIsMandatoryAndShouldBePrefill(String sExpected){
        boolean bflag=false;
        try{
            Util.fnGetWebElementText(elmBannerTextTC,sExpected);
            String bannerENText=Util.driver.findElement(elmWebBannnerTextHK).getText().trim();
            if(bannerENText.isEmpty()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theUserShouldSeeIsMandatoryAndShouldBePrefilled(String sExpected){
        boolean bflag=false;
        try{
            Util.fnGetWebElementText(elmBannerTextSC,sExpected);
            String bannerENText=Util.driver.findElement(elmWebBannnerTextCN).getText().trim();
            if(bannerENText.isEmpty()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theUserCanSeeUpdateAndCancelButtonsAtTheBottomLeftCornor(){
        try{
            Util.fnVerifyWebElementExistance(btnUpdate);
            Util.fnVerifyWebElementExistance(btnCancel);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateButtonIsEnabled(){
        boolean bflag=false;
        try{
            if(Util.driver.findElement(btnUpdate).isEnabled()){
                bflag=true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void bannerStatusShouldBeUnchecked(){
        boolean bflag=false;
        try{
//            Util.fnVerifyAttributeValue(elmBannerStatusCheckBox,"class","ng-valid ng-dirty ng-valid-parse ng-touched ng-empty");
            if(Util.driver.findElement(elmBannerStatusCheckBox).isSelected()){
                bflag=false;
            }
            else{
                bflag=true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(bflag);
    }

    public void theUserChecksBannerStatusCheckbox(){
        try{
            Util.fnClickObject(elmBannerStatusCheckBox);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void fieldsBannerOnTimeAndBannerOffTimeWillAppearBelowBannerStatusCheckbox(){
        try{
            Util.fnVerifyWebElementExistance(elmBannerOnTimeTitle);
            Util.fnVerifyWebElementExistance(elmBannerOffTimeTitle);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserClickOnUpdateButton(){
        try{
            Util.waitForWebElement(btnUpdate);
            Util.fnClickObject(btnUpdate);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void userWillLandOnInformationDialogue(String arg0){
        try{
            Util.fnGetWebElementText(elmUpdateBannerConfirmationMsg,arg0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theWebBannerWillBeUpdated(){
        try{
            Thread.sleep(2000);
            By bannerAdded=By.xpath("//td[contains(text(),'Testing AC 12')]");
            By bannerStatus=By.xpath("//td[contains(text(),'Testing AC 12')]/ancestor::tr/td[1]");
            Util.fnVerifyWebElementExistance(bannerAdded);
            Util.fnGetWebElementText(bannerStatus,"Enabled");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserClicksUpdateForADisabledBannerEntry(){
        By disableBanner=By.xpath("(//table[@id='webBannerTable']//td[contains(text(),'Disabled')]/parent::tr//td[5])[1]");
        try{
            Util.fnClickObject(disableBanner);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserChecksTheBannerStatusCheckbox(){
        try{
            Util.fnClickObject(elmBannerStatusCheckBox);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserClicksAddButtonToAddAppBanner(){
        try{
            Util.fnClickObject(btnAddAppBanner);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserClickOnConfirmButtonToUpdateAppBanner(){
        try{
            Util.fnClickObject(btnConfirm);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void renameTabAppBannerToPMCAppBanner() {

        try {
            Util.fnVerifyWebElementDoesnNotExist(elmAppBannerTab);
            Util.fnVerifyWebElementExistance(elmPM4CAppBanner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyWindowTileAs(String arg0) {
        try {
            Util.fnGetWebElementText(elmAppBannerTitle,arg0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickOnCancelButtonForAddPMCAppBanner() {
        try {
            Util.fnClickObject(btnCancel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void theUserClicksUpdateButtonForPMCAppBanner() {
        try {
            Util.fnClickObject(elmUpdateButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}