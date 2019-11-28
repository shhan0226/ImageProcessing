package sobelArguments;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/*소벨마스크 단순 처리*/

public class Main {

	static int greenBlue = new Color(10, 50, 100).getRGB();

	public static void main(String[] args) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(args[0]));
			SobelEdge se = new SobelEdge(image);
			se.Output();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
