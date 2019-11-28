package sobelGetPoint;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/*�Һ�����ũ 2D=>1D�� ��ȯ�Ͽ� ���� ���� �Һ�����ũ ó���ϱ�*/

public class Main {

	public static void main(String[] args) {
		int width = 0, height = 0, weight = 0;
		BufferedImage image = null;
		BufferedImage outimage = null;
		ArrayList<Integer> imgVal = new ArrayList<>();

		// �̹��� �Է� �� ���ο� outimage ����(������ �̹���)
		try {
			image = ImageIO.read(new File("img\\origin.png"));
			width = image.getWidth();
			height = image.getHeight();
			weight = width;

			outimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			ImageIO.write(outimage, "png", new File("img\\1outImageTest.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// �Էµ� �̹����� RGB���� ��Ʈ����Ʈ�� �Է�
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < weight; w++) {
				imgVal.add(image.getRGB(w, h));
			}
		}

		// ��Ʈ ����Ʈ �̿��Ͽ� �ƿ� �̹��� ���
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < weight; w++) {
				outimage.setRGB(w, h, imgVal.get(w + h * weight));
			}
		}

		try {
			ImageIO.write(outimage, "png", new File("img\\2outImageInt.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// sobel mask ����
		SobelEdge sb = new SobelEdge(image);
		sb.sobelStart();
		try {
			ImageIO.write(sb.sobelOutput(), "png", new File("img\\3sobelimg.png"));
			ImageIO.write(sb.image, "png", new File("img\\4sobelimg.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ���� �Һ� ó��
		sb.init(image);
		System.out.println(String.valueOf(width * height));

		System.out.println("go!!");
		sb.sobelStart(19639, 109639);
		try {
			ImageIO.write(sb.sobelOutput(), "png", new File("img\\5sobelimg.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
