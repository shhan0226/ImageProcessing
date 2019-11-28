package imageBuffer;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import sobelArguments.SobelEdge;

/*버퍼 이미지 초기화*/

public class ImageBufferInit {

	public static void main(String[] args) {
		BufferedImage image = null;
		BufferedImage image2 = null;

		try {
			image = ImageIO.read(new File("img\\origin.png"));
			int w = image.getWidth();
			int h = image.getHeight();

			image2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					image2.setRGB(j, i, image.getRGB(j, i));
				}
			}

			ImageIO.write(image2, "png", new File("img\\origin2.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
