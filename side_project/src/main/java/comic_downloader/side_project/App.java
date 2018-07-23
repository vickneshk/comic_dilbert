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
    	
    	System.setProperty("webdriver.gecko.driver","D:\\Selenium\\Software\\selenium-java-3.12.0\\geckodriver.exe");  
        WebDriver driver = new FirefoxDriver();
        try{
        driver.get("http://dilbert.com/strip/1993-10-28");
        while (driver.getCurrentUrl() != "http://dilbert.com/strip/1993-10-30")
        {	
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
            Point p = element.getLocation();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage img = ImageIO.read(screen);

            BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,   
                                     height);

            ImageIO.write(dest, "png", screen);
            String numbering = null;
            numbering = driver.getCurrentUrl();
            numbering = numbering.substring(25);
            File f = new File("D:\\Selenium\\Dilbert\\"+numbering+".jpg");

            FileUtils.copyFile(screen, f);
        }
        driver.findElement(By.xpath("//div[@class='nav-comic nav-right']")).click();
        }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
}
