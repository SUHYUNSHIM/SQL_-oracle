package day6;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIOFile {
	//file open
	//read/ write
	//close	
	
	public static void main(String[] args) throws IOException {
		File file1 = new File("C:\\Users\\USER\\Pictures\\slide-show\\a.jpg"); //원본 
		BufferedImage buffimage1 = ImageIO.read(file1); //기존 파일 read하여 이미지화한 다음 . 클래스.static메소드
		
		File file2 = new File("C:\\Users\\USER\\Pictures\\slide-show\\a1.jpg");
		ImageIO.write(buffimage1, "jpg", file2); //이미지를 write
		System.out.println("jpg 저장됨");
		
		File file3 = new File("C:\\Users\\USER\\Pictures\\slide-show\\a3.gif");
		ImageIO.write(buffimage1, "gif", file3);
		System.out.println("gif 저장됨");
		
		File file5 = new File("C:\\Users\\USER\\Pictures\\slide-show\\a5.png");
		ImageIO.write(buffimage1, "png", file5);
		System.out.println("png 저장됨");
	}

}
