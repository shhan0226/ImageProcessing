package imageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*이미지 읽어와 붙연 넣기*/

public class ImageReadWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// File sourceimage = new File("\img\1.jpg");
		// System.out.println(new File("src\\img\\1.jpg").exists());
		// File path = new File(".");
		// System.out.println(path.getAbsolutePath());

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File("img\\origin.jpg"));

			ImageIO.write(image, "jpg", new File("img\\result.jpg"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}