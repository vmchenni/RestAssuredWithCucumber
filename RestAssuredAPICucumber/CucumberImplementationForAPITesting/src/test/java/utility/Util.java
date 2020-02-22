package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Util {

	public static Properties prop;
	public static WebDriver driver;
	public static String sFromXPath = null;
	public static String sToXPath = null;
	public static int iToRow = 0;
	public static String sFromXPathForArticle;
	public static String sToXPathForArticle;
	public static int iArticle;
	public static ChromeOptions options;
	public static String sDownloadedFilesLocation;
	private static By btnSubmit;
	//Function to do set up of properties file

	public static void fnLoadPropertyFile() throws IOException {
		File src = new File("config.properties");                        //Creation of file object
		FileInputStream fis = new FileInputStream(src);                //Create  FileInputStream object
		prop = new Properties();                                        //Create properties object
		prop.load(fis);                                                //Load properties file
	}

	public static void fnDeleteOldTopEnquiryReport() {
		String sActualResult = "Old top_enquiry_report file is deleted";
		String sExpectedResult = "Old top_enquiry_report file is deleted";
		String sCurrentDirectory = System.getProperty("user.home");
		String Marker_Source_path = "" + sCurrentDirectory + "\\Downloads";
		System.out.println("Marker Source Path is :-" + Marker_Source_path);
		String sExpectedFileName = "top_enquiry_report";
		File folder = new File(Marker_Source_path);
		File[] listOfFiles = folder.listFiles();
		int i = 0;
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String Filename = listOfFiles[i].getName();
				if (Filename.indexOf("top_enquiry_report") >= 0) {
					String Filename_path = listOfFiles[i].getAbsolutePath();
					File File_with_path = new File(Filename_path);
					System.out.println("File found is :-" + File_with_path);
					Boolean check_del = FileUtils.deleteQuietly(File_with_path);
					if (check_del) {
						System.out.println("Is File deleted : " + check_del);
					} else {
						sExpectedResult = "Old top_enquiry_report file is not deleted";
					}
					break;
				}
			}
		}

	}

	public static void fnSetBrowserOptions() {
		String sCurrentDirectory = System.getProperty("user.dir");
		sDownloadedFilesLocation = sCurrentDirectory + "/DownloadedFiles";
		try {

			FileUtils.cleanDirectory(new File(sDownloadedFilesLocation));
			String data = "Test data";

			FileOutputStream out = new FileOutputStream(""+sDownloadedFilesLocation+"/sample.txt");
			out.write(data.getBytes());
			out.close();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		options = new ChromeOptions();

		String sOSName=System.getProperty("os.name");
		if(sOSName.contains("Windows")){
			System.out.println("Working On Windows OS");
		}
		else{
//			options.addArguments("headless"); //headless browser
		}

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", sDownloadedFilesLocation);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("safebrowsing.enabled", true);
		options.setExperimentalOption("prefs", prefs);
	}

	public static void fnLoadBrowser() {
		System.out.println("*******************");
		;
		System.out.println("Launching chrome browser");
		String sOSName=System.getProperty("os.name");
		System.out.println("OS is :-"+sOSName);
		if(sOSName.contains("Windows")){
			System.out.println("Accessing Windows driver");
			System.setProperty("webdriver.chrome.driver", "./chromedriver/chromedriver.exe");    //Set property for chrome browser
			System.out.println("Windows driver successfully loaded");
		}
		else if(sOSName.contains("Mac OS X")){
			System.out.println("Accessing Mac driver");
			System.setProperty("webdriver.chrome.driver", "./chromedriver/chromedriver");
			System.out.println("Mac driver successfully loaded");
		}
		else{
			System.out.println("Accessing Linux driver");
			System.setProperty("webdriver.chrome.driver", "./chromedriver/chromedriverLinux");
			System.out.println("Linux driver successfully loaded");
		}
		fnSetBrowserOptions();                                                                //Call to set broswer options
		driver = new ChromeDriver(options);                                                    //Create chrome driver
		// Maximize window
		if(sOSName.contains("Windows")){
			driver.manage().window().maximize();
		}
		else{
			System.out.println("Executing scripts on MacBook");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);                    //Set wait time
		try {
			fnLoadPropertyFile();                                                            //Load property file
			Util.takeScreenShot(Util.driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void fnEnterText(By sUserID, String sValue) throws IOException {    //Function to set value in edit box
		boolean bfnEnterText = false;
		String sExpectedValue = "Value :-" + sValue + " entered in edit box :-" + sUserID;
		String sActual = "Value :-" + sValue + " entered in edit box :-" + sUserID;
		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(sUserID);
			if (Util.driver.findElement(sUserID).isDisplayed()) {            //Check if edit box is displayed
				Util.waitForWebElement(sUserID);
				Util.driver.findElement(sUserID).clear();
				Thread.sleep(1000);
				Util.driver.findElement(sUserID).sendKeys(sValue);        //Enter value in edit box.
				bfnEnterText = true;
				sExpectedValue = "Value :-" + sValue + " entered in edit box :-" + sUserID;
			}
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			sActual = "Value :-" + sValue + " not entered in edit box :-" + sUserID;
			e.printStackTrace();
		}

	}


	public static void fnClickObject(By eObjectName) throws IOException {                    //Function to click on object.

		String sExpectedValue = "User clicked on object :-" + eObjectName;
		String sActual = "User clicked on object :-" + eObjectName;


		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(eObjectName);
			Util.driver.findElement(eObjectName).click();

		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			sActual = "User not clicked on object :-" + eObjectName;
			e.printStackTrace();
		}
		assertEquals(sExpectedValue, sActual);
	}

	public static void fnSwitchToNewWindow() {                                                //Function to switch windows
		// Switch to new window opened
		try {
			Thread.sleep(2000);
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void fnCheckEditBoxContent(By eObject, String sExpectedValue1) throws IOException {// Function to check edit box contents
		String sActualValue = null;
		String sExpectedValue = sExpectedValue1;
		try {
			Util.waitForWebElement(eObject);
			sActualValue = driver.findElement(eObject).getAttribute("value");
//			assert sExpectedValue.equals(sActualValue);
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals(sExpectedValue, sActualValue);

	}

	public static void takeScreenShot(WebDriver driver) throws IOException {
//
//
//		String path = "./Screenshot/";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//		String filename = path + sdf.format(new Date()) + RandomStringUtils.randomNumeric(16) + ".png";
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File(filename));
	}

	public static void fnUploadFile() {

		//Find the element of upload button and send the path
		try {
			WebElement element = driver.findElement(By.id("topicIcon"));
			element.sendKeys("C:\\VishwanathChenni\\Project\\workspace\\CSOPortalAutomation\\digital-peak-api-tests\\CS Portal Automation\\UploadIcon\\poSTL.png");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void fnVerifyValueFromDropDown(By elmTicketTypeDropdown, String sExpectedValue) throws IOException {
		String sExpectedValue1 = null;
		String sActualValue1 = null;
		String sTempvalue = null;
		String sUpdatedString = null;
		String sExpectedValueAdjusted = null;
		String sActualValue = null;
		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmTicketTypeDropdown);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Boolean found = false;

			Util.waitForWebElement(elmTicketTypeDropdown);
			Thread.sleep(2000);
			Select select = new Select(driver.findElement(elmTicketTypeDropdown));
			List<WebElement> allOptions = select.getOptions();

			for (WebElement winHandle : allOptions) {
				sTempvalue = winHandle.getText();
				sUpdatedString = (sTempvalue.replaceAll("[\\n\\t\\r\\s]", "")).trim();
				sExpectedValueAdjusted = (sExpectedValue.replaceAll("[\\n\\t\\r\\s]", "")).trim();
				if (sExpectedValueAdjusted.equalsIgnoreCase(sUpdatedString)) {
					sActualValue = sUpdatedString;
					break;

				}

			}
//			System.out.println("Actual Value:-"+sActualValue);
//			System.out.println("Expected Value:-"+sExpectedValue);
			sExpectedValue1 = sExpectedValue;
			sActualValue1 = sActualValue;

			//assert sExpectedValue.equalsIgnoreCase(sActualValue);


		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals(sExpectedValueAdjusted, sActualValue);
	}

	public static void fnVerifyRadioButtonExistance(By elmRadioButton) throws IOException {
		String sExpectedValue = "Radio button " + elmRadioButton + " exist";
		String sActualValue = "Radio button " + elmRadioButton + " exist";
		try {
			Util.waitForWebElement(elmRadioButton);
			assert driver.findElement(elmRadioButton).isDisplayed();
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			sActualValue = "Radio button " + elmRadioButton + " doesn't exist";
			e.printStackTrace();
		}
		assertEquals(sExpectedValue, sActualValue);
	}

	public static void fnClickLinkByText(String sLinkText) throws IOException {
		String sExpectedValue = "User clicked on link :-" + sLinkText;
		String sActualValue = "User clicked on link :-" + sLinkText;
		try {

			driver.findElement(By.linkText(sLinkText)).click();
		} catch (Exception e) {
			sActualValue = "User not clicked on link :-" + sLinkText;
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals(sExpectedValue, sActualValue);
	}

	public static void fnClickLinkByPartialText(String sPartialLinkText) throws IOException {
		String sExpectedValue = "User clicked on link with partial text:-" + sPartialLinkText;
		String sActualValue = "User clicked on link with partial text:-" + sPartialLinkText;
		try {
			driver.findElement(By.partialLinkText(sPartialLinkText));
		} catch (Exception e) {
			sActualValue = "User not clicked on link with partial text:-" + sPartialLinkText;
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals(sExpectedValue, sActualValue);
	}

	public static void fnCheckCheckBox(By elmCheckBox, String sCheckBoxName) throws IOException {
		// Find the checkbox or radio button element by Name
		try {

			Util.waitForWebElement(elmCheckBox);
			List oCheckBox = driver.findElements(elmCheckBox);

			// This will tell you the number of checkboxes are present

			int iSize = oCheckBox.size();

			// Start the loop from first checkbox to last checkboxe

			for (int i = 0; i < iSize; i++) {

				// Store the checkbox name to the string variable, using 'Value' attribute

				String sValue = ((WebElement) oCheckBox.get(i)).getAttribute("value");

				// Select the checkbox it the value of the checkbox is same what you are looking for

				if (sValue.equalsIgnoreCase(sCheckBoxName)) {

					((WebElement) oCheckBox.get(i)).click();

					// This will take the execution out of for loop

					break;

				}

			}

		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
	}

	public static void fnSelectValueFromDropDown(By elmDropDown, String svalueToBeSelected) throws IOException {
		String sActualValue = "The value :-" + svalueToBeSelected + " is selected from dropdown:-" + elmDropDown + "";
		String sExpctedValue = "The value :-" + svalueToBeSelected + " is selected from dropdown:-" + elmDropDown + "";
		try {
			Util.waitForWebElement(elmDropDown);
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmDropDown);
			Select select = new Select(driver.findElement(elmDropDown));
			select.selectByVisibleText(svalueToBeSelected.trim());
		} catch (Exception e) {
			sActualValue = "The value :-" + svalueToBeSelected + " is not selected from dropdown:-" + elmDropDown + "";
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals(sExpctedValue, sActualValue);
	}

	public static void fnGetWebElementText(By elmWebElement, String sExpectedText) throws IOException {
		String sActualTextAdjusted="";
		String sExpectedTextAdjusted=sExpectedText.replaceAll("[\\n\\t\\r\\s\\-\\(\\) ]", "");
		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmWebElement);
			String sActualText;
			driver.switchTo().defaultContent();
			sActualText = driver.findElement(elmWebElement).getText().trim();
			sActualTextAdjusted = sActualText.replaceAll("[\\n]", "");
			sActualTextAdjusted = sActualTextAdjusted.replaceAll("[\\n\\t\\r\\s\\-\\(\\) ]", "");

		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals((sExpectedTextAdjusted.trim()), (sActualTextAdjusted.trim()));
	}

	public static void fnGetWebElementText1(By elmWebElement, String sExpectedText) throws IOException {
		String sActualText="";
		try {

			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmWebElement);
//			Thread.sleep(2000);
			sActualText = driver.findElement(elmWebElement).getText();
			sActualText = sActualText.trim();
			sExpectedText = sExpectedText.trim();
			System.out.println(sActualText);
			System.out.println(sExpectedText);


		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
		assertEquals((sExpectedText.trim()), (sActualText.trim()));
	}

	public static String fnGetCurrentURL() {
		String currentUrl = null;
		try {

			currentUrl = Util.driver.getCurrentUrl();
			System.out.println(currentUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentUrl;
	}

	public static void fnCompareURL(String URL1, String URL2) throws IOException {

		try {
			String sURL1 = URL1.trim();
			String sURL2 = URL2.trim();
			System.out.println(sURL1);
			System.out.println(sURL2);
			assert sURL1.equalsIgnoreCase(sURL2);

		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}

	}

	public static void fnGetEditBoxText(By elmWebElement, String sExpectedText) throws IOException {
		String sActualValue = "Edit box " + elmWebElement + "contains" + sExpectedText;
		String sExpectedValue = "Edit box " + elmWebElement + "contains" + sExpectedText;
		try {
			String sActualText;
			Util.waitForWebElement(elmWebElement);
			sActualText = driver.findElement(elmWebElement).getAttribute("value");
			sActualText = sActualText.trim();
			sExpectedText = sExpectedText.trim();
			System.out.println(sActualText);
			System.out.println(sExpectedText);
			assertEquals(sExpectedText, sActualText);

		} catch (Exception e) {
			sExpectedValue = "Edit box " + elmWebElement + " doesn't contains " + sExpectedText;
			Util.takeScreenShot(Util.driver);
			//e.printStackTrace();
		}
		assertEquals(sExpectedValue, sActualValue);

	}

	public static void fnClickWebTableRow(By eObjectName, int sRowNumber) throws InterruptedException {
		//Thread.sleep(2000L);
		Util.waitForWebElement(eObjectName);
		org.openqa.selenium.WebElement htmltable = driver.findElement(eObjectName);
		java.util.List<org.openqa.selenium.WebElement> rows = htmltable.findElements(By.tagName("tr"));
		System.out.println("Number of rows:" + rows.size());
		for (int rnum = 1; rnum < rows.size(); rnum++) {
			if (rnum == sRowNumber) {
				java.util.List<org.openqa.selenium.WebElement> columns = rows.get(rnum).findElements(By.tagName("td"));
				System.out.println("Number of columns:" + columns.size());

				for (int cnum = 1; cnum < columns.size(); cnum++) {
					System.out.println(columns.get(cnum).getText());
					columns.get(cnum).click();
					break;
				}
			}
			break;
		}

	}

	public static void fnSwitchToFrame(int index) {                                                //Function to switch frames
		driver.switchTo().frame(index);

	}

	public static void fnSwitchToFrame(String nameOrId) {                                                //Function to switch frames
		driver.switchTo().frame(nameOrId);

	}

	public static void fnSwitchToFrame(WebElement element) {                                                //Function to switch frames
		driver.switchTo().frame(element);

	}

	public static void fnEditorTextInEditor(By eObjectName, String sInputText) throws InterruptedException {
		Util.waitForWebElement(eObjectName);

		WebElement iframe1 = driver.findElement(eObjectName);
		driver.switchTo().frame(iframe1);
		WebElement web1 = driver.findElement(By.xpath("//div[contains(text(),'Please enter')]"));
		web1.clear();
		Actions act1 = new Actions(driver);
		act1.sendKeys(web1, sInputText).build().perform();
		;

	}

	public static void fnGetTextInEditor(By eObjectName, By eEditorObjectName, String sInputText) throws InterruptedException {
		Util.waitForWebElement(eObjectName);
		WebElement iframe1 = driver.findElement(eObjectName);
		driver.switchTo().frame(iframe1);
		WebElement web1 = driver.findElement(eEditorObjectName);
		web1.clear();
		Actions act1 = new Actions(driver);
		act1.sendKeys(web1, "").build().perform();
		act1.sendKeys(web1, sInputText).build().perform();
	}

	public static void fnGetTextInEditorforchi(By eObjectName, By eEditorObjectName, String sInputText, By iFrame) throws InterruptedException {
		Util.waitForWebElement(eObjectName);
		WebElement iframe1 = driver.findElement(eObjectName);
		driver.switchTo().frame(iframe1);
		WebElement web1 = driver.findElement(eEditorObjectName);
		web1.clear();
		driver.findElement(iFrame).sendKeys(sInputText);
	}

	public static void fnScrollDownWebPageByVisibilityOfTheElement(By eObjectName) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(eObjectName);
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Util.waitForWebElement(eObjectName);
		Thread.sleep(2000);
	}

	public static void fnClearTextBoxContent(By eObjectName) throws IOException {                    //Function to click on object.

		try {
			Util.waitForWebElement(eObjectName);
			Util.driver.findElement(eObjectName).clear();                                    //Click on object
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}

	}

	public static void fnSearchRecordFromFAQTopicTableAndClick1(String strTopicWebTable, String sColumnName, String sExpectedValue) {

		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;

		List cols = driver.findElements(By.xpath(strTopicWebTable + "//thead//tr//th"));
		for (int iColumnStart = 2; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(strTopicWebTable + "//thead//tr//th[" + iColumnStart + "]")).getText();
			// System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(strTopicWebTable + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			List pSlots = driver.findElements(By.xpath(strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//p"));
			int pCounts = pSlots.size();
			for (int pStart = 1; pStart <= pCounts; pStart++) {


				sActualText = driver.findElement(By.xpath(strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P[" + pStart + "]")).getText();
				System.out.println("Xpath of actual:-" + strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P[" + pStart + "]");
				if ((sActualText.trim()).equals(sExpectedValue.trim())) {

					//System.out.println("Actual value at line 292 " + sActualText);
					// System.out.println("Expected value at line 293 " + sExpectedValue);
					driver.findElement(By.xpath(strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P[" + pStart + "]")).click();
					bValueFound = true;
					break;
				}
			}
			if (bValueFound == true) {
				break;
			}
		}
		assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));


	}


	public static void fnFetchWebTableData1(String sTableName, String sColumnName, String sExpectedValue) {
		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));

		for (int iColumnStart = 2; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			List pSlots = driver.findElements(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//span"));
			int pCounts = pSlots.size();
			for (int pStart = 1; pStart <= pCounts; pStart++) {


				sActualText = driver.findElement(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//span[" + pStart + "]")).getText();
				//System.out.println("Xpath of actual:-"+sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
				if ((sActualText.trim()).equals(sExpectedValue.trim())) {

					// System.out.println("Actual value at line 292 " + sActualText);
					//System.out.println("Expected value at line 293 " + sExpectedValue);
					bValueFound = true;
					break;
				}
			}
			if (bValueFound == true) {
				break;
			}
		}
		assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));
	}


	public static void fnFetchWebTableData(String sTableName, String sColumnName, String sExpectedValue) {
		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 2; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			List pSlots = driver.findElements(By.xpath(sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//p"));
			int pCounts = pSlots.size();
			for (int pStart = 1; pStart <= pCounts; pStart++) {


				sActualText = driver.findElement(By.xpath(sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P[" + pStart + "]")).getText();
				//System.out.println("Xpath of actual:-"+sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
				if ((sActualText.trim()).equals(sExpectedValue.trim())) {

					// System.out.println("Actual value at line 292 " + sActualText);
					//System.out.println("Expected value at line 293 " + sExpectedValue);
					bValueFound = true;
					break;
				}
			}
			if (bValueFound == true) {
				break;
			}
		}
		assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));


	}

	public static void fnValidateTicketsWebTableData(String sTableName, String sColumnName, String sExpectedValue) {
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 1; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			sActualText = driver.findElement(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			if ((sActualText.trim()).equals(sExpectedValue.trim())) {
				bValueFound = true;
				break;
			}

			if (bValueFound == true) {
				break;
			}
		}
//		assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));
		assertEquals(sExpectedValue, sActualText);
	}


	public static void fnValidateTicketsWebTableColumnNames(String sTableName, String sExpectedColumnName) {
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sColumnValue = "";
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 1; iColumnStart <= cols.size(); iColumnStart++) {
			sColumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			if (sColumnValue.equalsIgnoreCase(sExpectedColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}
			if (bValueFound == true) {
				break;
			}
		}
		//assertEquals(sColumnValue.trim()).equalsIgnoreCase((sExpectedColumnName.trim()));
		assertEquals((sColumnValue.trim()), (sExpectedColumnName.trim()));
	}

	public static void fnValidateTicketNumberDescendingOrder(String sTableName, String sColumnName) {
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		ArrayList arrTicketNumbers = new ArrayList();
		ArrayList sortedArrTicketNumbers = new ArrayList();
		String previous = "";
		String current = "";
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 1; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			List<WebElement> sTicketNumbers = driver.findElements(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]"));
			int iTicketNumberSize = sTicketNumbers.size();
			for (int i = 0; i < iTicketNumberSize; i++) {
				current = sTicketNumbers.get(i).getText();
				arrTicketNumbers.add(current);

			}
		}

		sortedArrTicketNumbers = arrTicketNumbers;
		Collections.sort(arrTicketNumbers, Collections.reverseOrder());
		assert sortedArrTicketNumbers.equals(arrTicketNumbers);

	}


	public static void fnValidateTicketAssigneeGroupAndAssigneeUserWebTableData(String sTableName, String sColumnName, String sExpectedValue) {
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		String sAssigneeWorkgroup = null;
		String sAssigneeUser = null;
		String sExpectedAssigneeWorkgroup = null;
		String sExpectedAssigneeUser = null;
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 2; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			sActualText = driver.findElement(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			String Usergroup[];
			Usergroup = sActualText.split("/");
			sAssigneeWorkgroup = Usergroup[0].trim();
			sAssigneeUser = Usergroup[1].trim();
			String sExpectedUserGroup[] = sExpectedValue.split("/");
			sExpectedAssigneeWorkgroup = sExpectedUserGroup[0].trim();
			sExpectedAssigneeUser = sExpectedUserGroup[1].trim();
			if ((sAssigneeWorkgroup.equals(sExpectedAssigneeWorkgroup)) && ((sAssigneeUser).equals(sExpectedAssigneeUser))) {
				bValueFound = true;
				break;
			}

			if (bValueFound == true) {
				break;
			}
		}
		assert (sAssigneeWorkgroup.equals(sExpectedAssigneeWorkgroup));
		assert (sAssigneeUser.equals(sExpectedAssigneeUser));
	}

	public static void fnVerifyButtonExist(By elmButton) throws IOException {

		try {
			Util.waitForWebElement(elmButton);
			driver.findElement(elmButton).isDisplayed();
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}


	}

	public static void fnVerifyLabel(By elmErrorLable) throws IOException {
		// TODO Auto-generated method stub
		try {
			Util.waitForWebElement(elmErrorLable);
			//System.out.println("fnVerifyLabel start");
			driver.findElement(elmErrorLable).isDisplayed();
			//System.out.println("fnVerifyLabel end");
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}

	}

	public static void fnUploadNewFile() {

		//System.out.println("Inside upload new file ");
		WebElement element = driver.findElement(By.id("topicIcon"));
		element.sendKeys("C:\\VishwanathChenni\\Project\\workspace\\CSOPortalAutomation\\digital-peak-api-tests\\CS Portal Automation\\UploadIcon\\replace-updated.png");
	}

	public static void fnSwitchToDefaultWindow() {

		driver.switchTo().defaultContent();
	}


	public static void fnSearchRecordFromFAQTopicTableAndClick(String strTopicWebTable, String sColumnName, String sExpectedValue) throws InterruptedException {

		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		Thread.sleep(5000);
		sExpectedValue=sExpectedValue.replaceAll("[\\n\\t\\r\\s\\-\\(\\) ]", "");
		List cols = driver.findElements(By.xpath(strTopicWebTable + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 1; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(strTopicWebTable + "//thead//tr//th[" + iColumnStart + "]")).getText();
			// System.out.println("Columns name is :-"+sCoulumnValue);
			if ((sCoulumnValue.trim()).equalsIgnoreCase(sColumnName.trim())) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}


		}
		List rows = driver.findElements(By.xpath(strTopicWebTable + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			List pSlots = driver.findElements(By.xpath(strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//p"));
			int pCounts = pSlots.size();
			for (int pStart = 1; pStart <= pCounts; pStart++) {


				sActualText = driver.findElement(By.xpath(strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P[" + pStart + "]")).getText();
				sActualText=sActualText.replaceAll("[\\n\\t\\r\\s\\-\\(\\) ]", "");
				//System.out.println("Xpath of actual:-"+strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
				if ((sActualText.trim()).equalsIgnoreCase(sExpectedValue.trim())) {

					//System.out.println("Actual value at line 292 " + sActualText);
					// System.out.println("Expected value at line 293 " + sExpectedValue);
					driver.findElement(By.xpath(strTopicWebTable + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P[" + pStart + "]")).click();
					bValueFound = true;
					break;
				}
			}
			if (bValueFound == true) {
				break;
			}
		}
		assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));


	}

	public static void waitForWebElement(By elmEditFAQ) throws InterruptedException {
		try {
			boolean bFlag = false;
			int iCount = 0;
			WebDriverWait wait = new WebDriverWait(Util.driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(elmEditFAQ));
		} catch (Exception e) {
			e.printStackTrace();
		}

//		do {
//			if (Util.driver.findElement(elmEditFAQ).isDisplayed()) {
//				bFlag = true;
//			}
//
//			Thread.sleep(100);
//			iCount = iCount + 1;
//			if (iCount > 5) {
//				break;
//			}
//
//		} while (bFlag != true);

	}

	public static void fnUploadNewFileUpdated() {

		//System.out.println("Inside upload new file ");
		WebElement element = driver.findElement(By.id("topicIcon"));
		element.sendKeys("C:\\VishwanathChenni\\Project\\workspace\\CSOPortalAutomation\\digital-peak-api-tests\\CS Portal Automation\\UploadIcon\\lync.png");
	}

	public static void fnVerifyLinkText(By elmLinkElement, String lnkFAQManagement) {
		String sActualText;
		try {
			Util.waitForWebElement(elmLinkElement);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sActualText = driver.findElement(elmLinkElement).getText();
		//System.out.println("Actual text at line 429 is :-"+sActualText);
		//assert lnkFAQManagement.equalsIgnoreCase(sActualText);
		assertEquals(lnkFAQManagement, sActualText);
	}

	public static void fnUploadFileWithFileName(String sFileName) {

//		WebElement element = driver.findElement(By.xpath("//input[@type='file']"));
		File file = new File("UploadIcon/" + sFileName + ".png");
		String absolutePath = file.getAbsolutePath();
//		element.sendKeys(absolutePath);
		Util.driver.findElement(By.xpath("//button[contains(text(),'Upload')]")).click();
		try {
			Runtime.getRuntime().exec("AutoITScripts/UploadFileOriginal.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void fnSearchBusinessClientsResults(String sTableName, String sColumnName, String sExpectedValue) {
		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 2; iColumnStart <= cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			sActualText = driver.findElement(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			//System.out.println("Xpath of actual:-"+sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
			if ((sActualText.trim()).equals(sExpectedValue.trim())) {

				// System.out.println("Actual value at line 292 " + sActualText);
				//System.out.println("Expected value at line 293 " + sExpectedValue);
				bValueFound = true;
				break;
			}

			if (bValueFound == true) {
				break;
			}
		}
		//assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));
		assertEquals((sExpectedValue.trim()), (sActualText.trim()));


	}

	public static void fnSearchBusinessClientsResultsUsingUserEmail(String sTableName, String sColumnName, String sExpectedValue) {
		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 2; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			//sActualText = driver.findElement(By.xpath(sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			sActualText = driver.findElement(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]" + "//td[" + iColumnNumber + "]")).getText();

			//System.out.println("Xpath of actual:-"+sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
			if ((sActualText.trim()).equals(sExpectedValue.trim())) {

				// System.out.println("Actual value at line 292 " + sActualText);
				//System.out.println("Expected value at line 293 " + sExpectedValue);
				bValueFound = true;
				break;
			}

			if (bValueFound == true) {
				break;
			}
		}
		//assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));
		assertEquals((sExpectedValue.trim()), (sActualText.trim()));

	}


	public static void fnVerifyWebElementExistance(By elmTicketTypeDropDown) {
		boolean bFlag = false;
		String sExpectedResult = "Element :-" + elmTicketTypeDropDown + " exist";
		String sActualResult = "Element :-" + elmTicketTypeDropDown + " exist";

		try {
			//Util.waitForWebElement(elmTicketTypeDropDown);
			Util.waitForWebElement(elmTicketTypeDropDown);
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmTicketTypeDropDown);
			bFlag = driver.findElement(elmTicketTypeDropDown).isDisplayed();

		} catch (Exception e) {
			sActualResult = "Element :-" + elmTicketTypeDropDown + " doesn't exist";
//			e.printStackTrace();
		}
		assertEquals(sExpectedResult, sActualResult);
	}

	public static void fnCheckObjectIfObjectIsEnabled(By elmSelectBusiness) {
		boolean bIsEnabled = false;
		try {
			Util.waitForWebElement(elmSelectBusiness);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (driver.findElement(elmSelectBusiness).isEnabled()) {
			bIsEnabled = true;
		}
		assert bIsEnabled;
	}

	public static void fnVerifyElementsCountsForMoreThanOne(By elmViewDetail) {
		boolean bFlagCount = false;
		List iItemsCount = driver.findElements(elmViewDetail);
		if (iItemsCount.size() > 1) {
			bFlagCount = true;
		}
		assert bFlagCount;
	}

	public static void fnSearchRecordFromTableAndClickOnARow(String sTableId, String columnName, String sTicketNumber) {
		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		List cols = driver.findElements(By.xpath(sTableId + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 1; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableId + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(columnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableId + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			sActualText = driver.findElement(By.xpath(sTableId + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			//System.out.println("Xpath of actual:-"+sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
			if ((sActualText.trim()).equals(sTicketNumber.trim())) {

				// System.out.println("Actual value at line 292 " + sActualText);
				//System.out.println("Expected value at line 293 " + sExpectedValue);
				bValueFound = true;
				By myElement = By.xpath(sTableId + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]");
				Util.fnDoubleClick(myElement);
				break;
			}

			if (bValueFound == true) {
				break;
			}
		}
		//assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));


	}

	public static void fnVerifyCheckboxIsSelected(By elmCheckbox) {

		try {
			Util.waitForWebElement(elmCheckbox);
			assert driver.findElement(elmCheckbox).isSelected() == false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void fnDragAndDrop(String elmDragElement, String elmDropElement) throws InterruptedException {
		Actions act = new Actions(driver);

		// find element which we need to drag
		WebElement drag = driver.findElement(By.xpath(elmDragElement));

		// find element which we need to drop
		WebElement drop = driver.findElement(By.xpath(elmDropElement));

		// this will drag element to destination

		act.dragAndDrop(drag, drop).build().perform();
	}

	public static void fnVerifyTextboxStatusIsDisable(By elmEditBox, String expectedStatus) throws IOException {
		//boolean bFlag = false;
		String stPassFail;
		try {
			Util.waitForWebElement(elmEditBox);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (driver.findElement(elmEditBox).isEnabled()) {
			//bFlag = true;
			stPassFail = "Enabled";
		} else {
			//bFlag = false;
			stPassFail = "Disabled";
		}
		assert (stPassFail.equalsIgnoreCase(expectedStatus));
	}

	//Added By VC on 20 Sep 2018 start
	public static void fnVerifyPartialTextOfEditBox(By elmEditBoxName, String sExpectedValue) {


		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmEditBoxName);
			String sActualText = driver.findElement(elmEditBoxName).getAttribute("value").trim();
			String sActualTextUpdated = (sActualText.replaceAll("[\\n\\t\\r\\s\\- ]", "")).trim();
			String sUpdatedValueUpdated = (sExpectedValue.replaceAll("[\\n\\t\\r\\s\\- ]", "")).trim();
			if (sActualTextUpdated.contains(sUpdatedValueUpdated.trim())) {
				assert (true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void fnPressEnter(By elmSearchButton) {
		try {
			Util.waitForWebElement(elmSearchButton);
			driver.findElement(elmSearchButton).sendKeys(Keys.ENTER);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

	public static void fnDoubleClick(By elmFirstRecord) {
		try {
			Util.waitForWebElement(elmFirstRecord);
			Actions actions = new Actions(driver);
			actions.doubleClick(driver.findElement(elmFirstRecord)).perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//Added By VC on 20 Sep 2018 end

	//*********Copy text displayed on Alert message - AG - Start***********
	public static void fnCopyAndCompareTextOnAlertMsg(String sExpectedErrorMsg) throws IOException {
		String alertMessage = null;
		try {
			alertMessage = driver.switchTo().alert().getText().trim();
			assert alertMessage.equalsIgnoreCase(sExpectedErrorMsg);
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}

	}

	public static void fnSearchUserResultsUsingPhoneNumber(String sTableName, String sColumnName, String sExpectedValue) {
		String sActualValue = null;
		boolean bValueFound = false;
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		//System.out.print("Total number of coulumns is :-"+cols.size());
		for (int iColumnStart = 1; iColumnStart < cols.size(); iColumnStart++) {

			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			//System.out.println("Columns name is :-"+sCoulumnValue);
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {

			//sActualText = driver.findElement(By.xpath(sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			sActualText = driver.findElement(By.xpath(sTableName + "//tr[" + iRowStart + "]" + "//td[5]")).getText();

			//System.out.println("Xpath of actual:-"+sTableName + "//tr[" + iRowStart + "]//td[" + iColumnNumber + "]//P["+pStart+"]");
			if ((sActualText.trim()).equals(sExpectedValue.trim())) {

				// System.out.println("Actual value at line 292 " + sActualText);
				//System.out.println("Expected value at line 293 " + sExpectedValue);
				bValueFound = true;
				break;
			}

			if (bValueFound == true) {
				break;
			}
		}
		assert (sExpectedValue.trim()).equalsIgnoreCase((sActualText.trim()));


	}

	public static void fnverifyDropdownSelection(By elmIssueCategory, String sExpectedSelection) {
		String sActualSelection = null;
		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmIssueCategory);
			Select select = new Select(driver.findElement(elmIssueCategory));
			sActualSelection = select.getFirstSelectedOption().getText().trim();
//			assertEquals(sExpectedSelection, sActualSelection);
		} catch (Exception e) {

			e.printStackTrace();
		}

		Assert.assertEquals(sExpectedSelection,sActualSelection);
	}

	public static void fnCheckObjectStatus(By elmBusinessName, boolean sExpectedStatus) {
		boolean sActualStatus = true;

		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmBusinessName);
			sActualStatus = Util.driver.findElement(elmBusinessName).isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(sExpectedStatus, sActualStatus);

	}
	//*********Copy text displayed on Alert message - AG - End***********

	public static void fnVerifyDefaultButtonSelect(By elmButton, String sExpectedAttribute) throws IOException {
		try {
			WebElement buttonAttribute = Util.driver.findElement(elmButton);
			String sActualResult = buttonAttribute.getAttribute("class");
			Assert.assertEquals(sExpectedAttribute, sActualResult);
		} catch (Exception e) {
			Util.takeScreenShot(Util.driver);
			e.printStackTrace();
		}
	}

	public static void fnUploadBannerWithName(String sName) {

		//WebElement element = driver.findElement(By.id("topicIcon"));
		WebElement element = driver.findElement(By.name(sName));
		File file = new File("UploadIcon/" + sName + ".png");
		String absolutePath = file.getAbsolutePath();
		element.sendKeys(absolutePath);

	}

	public static void fnUploadBannerWithID(String sName, String sId) {

		//WebElement element = driver.findElement(By.id("topicIcon"));
		WebElement element = driver.findElement(By.id(sId));
		File file = new File("UploadIcon/" + sName + ".png");
		String absolutePath = file.getAbsolutePath();
		element.sendKeys(absolutePath);

	}

	public static String fnGetCalculatedDate(String sDayAfterPrevious, int iDate) {

		Calendar calendar = Calendar.getInstance();
		//  Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (sDayAfterPrevious.equalsIgnoreCase("DayAfter")) {
			calendar.add(calendar.DAY_OF_YEAR, +iDate);
		} else if (sDayAfterPrevious.equalsIgnoreCase("DayPrevious")) {
			calendar.add(calendar.DAY_OF_YEAR, -iDate);
		}

		Date sDate = calendar.getTime();
		String sCalculatedDate = dateFormat.format(sDate);
		return sCalculatedDate;

	}

	public static void fnVerifyCheckboxIsSelectedUpdated(By chkEnquiry) {
		boolean bFlag = false;
		bFlag = Util.driver.findElement(chkEnquiry).isSelected();
		if (bFlag) {
			System.out.println("Check box is selected");
		} else {
			assertEquals("Check box is selected", "Check box is not selected");
		}
	}

	public static void fnVerifyAttributeValue(By elmObject, String sAttribute, String sExpectedValue) {
		boolean bFlag = false;
		String sActualAttribute = Util.driver.findElement(elmObject).getAttribute("" + sAttribute + "");
		assertEquals(sExpectedValue, sActualAttribute);
	}

	public static void fnVerifyWebElementDoesnNotExist(By elmSearchGadget) {
		String sActualResult = "Webelement " + elmSearchGadget + " doesn't exist";
		String sExpctedResult = null;

		try {
			if (Util.driver.findElement(elmSearchGadget).isDisplayed()) {
				sExpctedResult = "Webelement " + elmSearchGadget + " exist";
			} else {
				sExpctedResult = "Webelement " + elmSearchGadget + " doesn't exist";
			}
		} catch (Exception e) {
			sExpctedResult = "Webelement " + elmSearchGadget + " doesn't exist";
//			e.printStackTrace();
		}
		assertEquals(sExpctedResult, sActualResult);
	}

	public static void fnEnterbackSpaceInAField(By txtPayMeEmail) {

		Util.driver.findElement(txtPayMeEmail).sendKeys(Keys.BACK_SPACE);

	}

	public static String fnGetRandonString(String sPrefix) {

		String sQuestion = "" + sPrefix + "-";
		Random r = new Random();
		for (int iStart = 0; iStart < 10; iStart++) {
			char c = (char) (r.nextInt(26) + 'a');
			sQuestion = sQuestion + "" + c;
		}
		System.out.println("Random string is :-" + sQuestion);
		return sQuestion;

	}

	public static void fnUpdaloadAttachmentToTicket(String sOriginalFile) {


		WebElement element = driver.findElement(By.xpath("//input[@ng-model='createTicketForm.attachment']"));
		File file = new File("UploadIcon/" + sOriginalFile + ".png");
		String absolutePath = file.getAbsolutePath();
		element.sendKeys(absolutePath);


	}

	public static void fnVerifyFileExtension(String sFileType) {
	}

	public static void fnMouseHoverEvent(By elmTicketDescription) {
		Actions action = new Actions(Util.driver);
		WebElement we = Util.driver.findElement(elmTicketDescription);
		action.moveToElement(we).moveToElement(Util.driver.findElement(elmTicketDescription)).click().build().perform();
	}


	public static void fnVerifyWebElementTextIsNotNull(String sActualValue) {

		if (sActualValue != null) {
			Assert.assertEquals("Not null", "Not null");
		} else {
			Assert.assertEquals(sActualValue, "Not null");
		}

	}


	public static void fnVerifyDownloadedFileAs(String recon_result) {
//		String sCurrentDirectory=System.getProperty("user.home");
		String sActulResult = "File " + recon_result + "Exist";
		String sExpectedResult = "File " + recon_result + " Doesn't Exist";
//		sDownloadedFilesLocation=sCurrentDirectory+"\\DownloadedFiles";
//		Util.sDownloadedFilesLocation
//		String Marker_Source_path=""+sCurrentDirectory+"\\Downloads";
		File folder = new File(Util.sDownloadedFilesLocation);
		File[] listOfFiles = folder.listFiles();
		int i = 0;
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String Filename = listOfFiles[i].getName();
				if (Filename.indexOf("" + recon_result) >= 0) {
					sExpectedResult = "File " + recon_result + "Exist";
					break;
				}
			}
		}
		Assert.assertEquals(sExpectedResult, sActulResult);


	}

	public static void fnVerifyDateTimeFormatInWebTable(String sColumnName, String sFormat) {
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		String sActulResult = "Date is in the given format";
		String sExpectedResult = "Date is in the given format";

		List cols = driver.findElements(By.xpath("//thead//tr//th"));
		for (int iColumnStart = 2; iColumnStart < cols.size(); iColumnStart++) {
			String sCoulumnValue = driver.findElement(By.xpath("//thead//tr//th[" + iColumnStart + "]")).getText();
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}
		}
		List rows = driver.findElements(By.xpath("//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {
			sDate = driver.findElement(By.xpath("//tr[" + iRowStart + "]//td[" + iColumnNumber + "]")).getText();
			try {
				sdf.parse(sDate);
				System.out.println("Date is in the given format");
				break;
			} catch (ParseException e) {
				sExpectedResult="Date is NOT in the given format";
			}
		}
		Assert.assertEquals(sExpectedResult, sActulResult);
	}

	public static void fnVerifyColour(By Object,String Expected,String colorFormat){
		String sExpectedColour=Expected;
		String actual_hex="";
		try{
			fnScrollDownWebPageByVisibilityOfTheElement(Object);
			if(colorFormat.equalsIgnoreCase("Hex")){
			String color=Util.driver.findElement(Object).getCssValue("background-color").trim();
			String color_hex[]=color.replace("rgba(", "").split(",");
			actual_hex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()), Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));

			}
			else if(colorFormat.equals("RGB")){
				fnVerifyAttributeValue(Object,"bgColor",Expected);
			}
			Assert.assertEquals("actual_hex should equal to: ",sExpectedColour,actual_hex);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void fnFileUploadAutoIt(String fileName){
		String uploadFile=fileName;
		File file = new File("UploadIcon/" + fileName + ".png");
		String absolutePath = file.getAbsolutePath();
		ProcessBuilder pb = null;
		try{
			pb=new ProcessBuilder("AutoITScripts/AutoITFileUploadWithParam.exe",absolutePath);
			pb.start();
			System.out.println("File is uploaded");
			Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fnVerifyPartialTxtBox(By elmEditBoxName, String arg0) {
		String sActualText="";
		String sExpectedValue="value "+arg0+" exist";
		try {
			Util.fnScrollDownWebPageByVisibilityOfTheElement(elmEditBoxName);
			String sTemp=driver.findElement(elmEditBoxName).getAttribute("value").trim();
			if(sTemp.indexOf(arg0)!=-1){
				sActualText="value "+arg0+" exist";
			}
			else{
				sActualText="value "+arg0+" doesn't exist";
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(sExpectedValue,sActualText);
	}


	public static void selectWorkGroup(By selectWorkGroup) {
		Select select=new Select(Util.driver.findElement(selectWorkGroup));
		String sActualText="";
		List<WebElement> lst=select.getOptions();
		for(int iStart=0;iStart<lst.size();iStart++){
			sActualText=lst.get(iStart).getText().trim();
			if(sActualText.contains("SuperAdminAuto1")){
				select.selectByIndex(iStart);
				break;

			}
		}
	}

	public static void fnValidateAscendingOrder(String sTableName, String sColumnName) {
		int iColumnNumber = 0;
		boolean bColumnFound = false;
		String sActualText = null;
		ArrayList arrTicketNumbers = new ArrayList();
		ArrayList sortedArrTicketNumbers = new ArrayList();
		String previous = "";
		String current = "";
		List cols = driver.findElements(By.xpath(sTableName + "//thead//tr//th"));
		for (int iColumnStart = 1; iColumnStart < cols.size(); iColumnStart++) {
			String sCoulumnValue = driver.findElement(By.xpath(sTableName + "//thead//tr//th[" + iColumnStart + "]")).getText();
			if (sCoulumnValue.equalsIgnoreCase(sColumnName)) {
				iColumnNumber = iColumnStart;
				bColumnFound = true;
				break;
			}

		}
		List rows = driver.findElements(By.xpath(sTableName + "//tr"));
		for (int iRowStart = 1; iRowStart < rows.size(); iRowStart++) {
			List<WebElement> sTicketNumbers = driver.findElements(By.xpath(sTableName + "//tbody//tr[" + iRowStart + "]//td[" + iColumnNumber + "]"));
			int iTicketNumberSize = sTicketNumbers.size();
			for (int i = 0; i < iTicketNumberSize; i++) {
				current = sTicketNumbers.get(i).getText();
				arrTicketNumbers.add(current);

			}
		}

		sortedArrTicketNumbers = arrTicketNumbers;
		Collections.sort(arrTicketNumbers);
		assert sortedArrTicketNumbers.equals(arrTicketNumbers);

	}

}
