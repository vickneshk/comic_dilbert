package comic_downloader.side_project;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.print.DocFlavor.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	System.setProperty("webdriver.gecko.driver","D:\\Selenium\\Software\\geckodriver-v0.20.1-win64\\geckodriver.exe");  
        WebDriver driver = new FirefoxDriver();
        try{
        driver.get("http://dilbert.com/strip/1994-05-22");
       driver.findElement(By.xpath("//a[@id='cookieChoiceDismiss']")).click();
        while (driver.getCurrentUrl() != "http://dilbert.com/strip/1994-01-31")
        {	
        System.out.println(driver.getCurrentUrl());
        if(driver.findElement(By.xpath("//img[@class='img-responsive img-comic']")).isDisplayed())
        {
        	WebElement element = driver.findElement(By.xpath("//img[@class='img-responsive img-comic']"));
        	            
//            String src = image.getAttribute("src");
//        System.out.println(src);
//        URL imageURL = new URL(src);
//        BufferedImage saveImage = null;
//        saveImage = ImageIO.read((ImageInputStream) imageURL);
//        
//        ImageIO.write(saveImage, "gif", new File("D:\\Selenium\\Dilbert\\logo-forum.gif"));
       // 	   WebElement element_year =  driver.findElement(By.xpath("//span[@itemprop='copyrightYear']"));
         //      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_year);
           //    Thread.sleep(500); 
               //JavascriptExecutor executor = (JavascriptExecutor) driver;
              // Long value = (Long) executor.executeScript("return window.pageYOffset;");
            Point p = element.getLocation();
            int width = element.getSize().getWidth();
            //System.out.println(width+"\t"+p.getX());
            int height = element.getSize().getHeight();
           // System.out.println(height+"\t"+p.getY());
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage img = ImageIO.read(screen);
            BufferedImage dest = null;
            if(img.getSubimage(p.getX(),p.getY(), width,height) != null)
            	{
            	dest = img.getSubimage(p.getX(),p.getY(), width,height);
            	}
            else
            {
            	JavascriptExecutor executor = (JavascriptExecutor) driver;
            	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            	Thread.sleep(500);
            	Long value = (Long) executor.executeScript("return window.pageYOffset;");
            	dest = img.getSubimage(p.getX(),(int) (p.getY()-value), width,height);
            }
            ImageIO.write(dest, "png", screen);
            String numbering = null;
            numbering = driver.getCurrentUrl();
            numbering = numbering.substring(25);
            File f = new File("D:\\Selenium\\Dilbert\\"+numbering+".jpg");

            FileUtils.copyFile(screen, f);
        }
        if(driver.findElement(By.xpath("//div[@class='nav-comic nav-right']")).isDisplayed())
        {
        driver.findElement(By.xpath("//div[@class='nav-comic nav-right']")).click();
        }
        else{        
        	break;
        }
        }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
}
