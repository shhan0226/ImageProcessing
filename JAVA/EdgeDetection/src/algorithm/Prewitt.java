package algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Prewitt {

	public BufferedImage image, out_image;
	public ArrayList<Integer> Result;
	public int imgWidth, imgHeight;
	int maxGradientR = -1;

	public Prewitt(BufferedImage imageIn) {
		init(imageIn);

	}

	public void init(BufferedImage imageIn) {
		this.image = imageIn;
		imgWidth = imageIn.getWidth();
		imgHeight = imageIn.getHeight();
		System.out.println("width : " + imgWidth + " Heigh : " + imgHeight);
		this.out_image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

	}

	public static int getGrayScale(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;

		// int gray = (r + g + b) / 3;
		int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);

		return gray;
	}

	public void EdgeStart() {
		int maxGval = 0;
		int[] edgeColors = new int[imgWidth * imgHeight];
		int maxGradient = -1;
		for (int j = 1; j < imgHeight - 1; j++) {
			for (int i = 1; i < imgWidth - 1; i++) {

				int val00 = getGrayScale(image.getRGB(i - 1, j - 1));
				int val01 = getGrayScale(image.getRGB(i - 1, j));
				int val02 = getGrayScale(image.getRGB(i - 1, j + 1));

				int val10 = getGrayScale(image.getRGB(i, j - 1));
				int val11 = getGrayScale(image.getRGB(i, j));
				int val12 = getGrayScale(image.getRGB(i, j + 1));

				int val20 = getGrayScale(image.getRGB(i + 1, j - 1));
				int val21 = getGrayScale(image.getRGB(i + 1, j));
				int val22 = getGrayScale(image.getRGB(i + 1, j + 1));

				int gx = ((-1 * val00) + (-1 * val01) + (-1 * val02)) + ((0 * val10) + (0 * val11) + (0 * val12))
						+ ((1 * val20) + (1 * val21) + (1 * val22));

				int gy = ((1 * val00) + (0 * val01) + (-1 * val02)) + ((1 * val10) + (0 * val11) + (-1 * val12))
						+ ((1 * val20) + (1 * val21) + (1 * val22));

				double gval = Math.sqrt((gx * gx) + (gy * gy));
				int g = (int) gval;

				if (maxGradient < g) {
					maxGradient = g;
				}
				edgeColors[i + j * imgWidth] = g;
			}

		}

		double scale = 255.0 / maxGradient; // 255로 나누어 최대 크기가 255로 나올수 있도록 정규화함

		for (int j = 1; j < imgHeight - 1; j++) {
			for (int i = 1; i < imgWidth - 1; i++) {
				int edgeColor = edgeColors[i + j * imgWidth];
				edgeColor = (int) (edgeColor * scale);
				edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
				this.out_image.setRGB(i, j, edgeColor);
			}
		}
	}

	public void Output(String out) {
		File oFile = new File(out);
		try {
			ImageIO.write(this.out_image, "jpg", oFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
