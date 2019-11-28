package sobelArguments;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SobelEdge {
	public BufferedImage image, out_image;
	public int[] MaskX, MaskY;

	public SobelEdge(BufferedImage imageIn) {
		this.image = imageIn;
		this.out_image = imageIn;
		SobelStart();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		this.out_image = image;
	}

	public void SobelStart() {
		int x = image.getWidth();
		int y = image.getHeight();

		int maxGval = 0;
		int[][] edgeColors = new int[x][y];
		int maxGradient = -1;

		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {

				int val00 = getGrayScale(image.getRGB(i - 1, j - 1));
				int val01 = getGrayScale(image.getRGB(i - 1, j));
				int val02 = getGrayScale(image.getRGB(i - 1, j + 1));

				int val10 = getGrayScale(image.getRGB(i, j - 1));
				int val11 = getGrayScale(image.getRGB(i, j));
				int val12 = getGrayScale(image.getRGB(i, j + 1));

				int val20 = getGrayScale(image.getRGB(i + 1, j - 1));
				int val21 = getGrayScale(image.getRGB(i + 1, j));
				int val22 = getGrayScale(image.getRGB(i + 1, j + 1));

				int gx = ((-1 * val00) + (0 * val01) + (1 * val02)) + ((-2 * val10) + (0 * val11) + (2 * val12))
						+ ((-1 * val20) + (0 * val21) + (1 * val22));

				int gy = ((-1 * val00) + (-2 * val01) + (-1 * val02)) + ((0 * val10) + (0 * val11) + (0 * val12))
						+ ((1 * val20) + (2 * val21) + (1 * val22));

				double gval = Math.sqrt((gx * gx) + (gy * gy));
				int g = (int) gval;

				if (maxGradient < g) {
					// System.out.print("x: "+ i +" y: "+ j + " g="+ g +" M:" +
					// maxGradient);
					maxGradient = g;
					// System.out.println(" / M chang : "+ g);
				}
				edgeColors[i][j] = g;
			}
		}

		double scale = 255.0 / maxGradient; // 255�� ������ �ִ� ũ�Ⱑ 255�� ���ü� �ֵ��� ����ȭ��

		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				int edgeColor = edgeColors[i][j];
				edgeColor = (int) (edgeColor * scale);
				edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

				out_image.setRGB(i, j, edgeColor);
			}
		}

	}

	public static int getGrayScale(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;

		// from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
		int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
		// int gray = (r + g + b) / 3;

		return gray;
	}

	public void Output() {
		try {
			ImageIO.write(this.out_image, "jpg", new File("output.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}