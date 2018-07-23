package comic_downloader.side_project;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
 

public class Screencapture<image_name> {
 
	String image_name= null;
	String location_name = null;
	

	public void imagecapture(String location,String imgname)
	{
		 image_name=imgname;
		 location_name = location;
		// This code will capture screenshot of current screen		

		BufferedImage image = null;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		// This will store screenshot on Specific location
		try {
			ImageIO.write(image, "png", new File(location_name+"\\"+image_name+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) throws Exception
	{

 
	}
 
}