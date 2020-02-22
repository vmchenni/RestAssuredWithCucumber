import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CheckFAQArticleDetails {
    public static void main(String args[]) throws IOException {
        String sPrice1StringValue="";
        String sFilePath="TestData/Final PM4B FAQ.xlsx";
        File file=new File(sFilePath);
        FileInputStream fileinputstream=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fileinputstream);
        XSSFSheet sheet=workbook.getSheet("FAQ_Final");
        System.setProperty("webdriver.chrome.driver", "C:\\VishwanathChenni\\Project\\ReadDataFromExcel\\ChromeDriver\\chromedriver.exe");	//Set property for chrome browser
        WebDriver driver = new ChromeDriver();//Create chrome driver
        driver.manage().window().maximize();												//Maximize window
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.nseindia.com/products/content/equities/equities/eq_security.htm");
        for (int iStart=1;iStart<99;iStart++){
            XSSFRow row=sheet.getRow(iStart);
            XSSFCell cell=row.getCell(0);
            String sValue=cell.getStringCellValue();
            WebElement elmSymbol=driver.findElement(By.xpath("//input[@name='symbol']"));
            elmSymbol.clear();
            elmSymbol.sendKeys(sValue);
            Select select=new Select(driver.findElement(By.xpath("//select[@name='series']")));
            select.selectByVisibleText("EQ");
            Select sDateRange=new Select(driver.findElement(By.xpath("//select[@name='dateRange']")));
            sDateRange.selectByVisibleText("7 Days");
            driver.findElement(By.xpath("//img[@id='get']")).click();
            int rowSize=driver.findElements(By.xpath("//table/tbody/tr")).size();
            if(rowSize>=6) {


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String sPrice1 = driver.findElement(By.xpath("//table/tbody/tr[6]/td[9]")).getText();
                String sPrice2 = driver.findElement(By.xpath("//table/tbody/tr[5]/td[9]")).getText();

                double intPrice1 = MyConvertToNumber.sConvertToNumber(sPrice1);
                double intPrice2 = MyConvertToNumber.sConvertToNumber(sPrice2);
                if (intPrice1 > intPrice2) {
                    String vol1 = driver.findElement(By.xpath("//table/tbody/tr[6]/td[11]")).getText();
                    String vol2 = driver.findElement(By.xpath("//table/tbody/tr[5]/td[11]")).getText();
                    double intVol1 = MyConvertToNumber.sConvertToNumber(vol1);
                    double intVol2 = MyConvertToNumber.sConvertToNumber(vol2);
                    if (intVol1 > intVol2) {
                        System.out.println("The value is :-" + sValue);
                        System.out.println("Price 1 is:- "+intPrice1+"and Price 2 is:-"+intPrice2+" ");
                        System.out.println("Val 1 is:- "+intVol1+" Val 2 is:-"+intVol2+"");
                    }

                }
            }

        }

    }
}
