package sobelGetPointDiv;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SobelEdge {
	public BufferedImage image, out_image;
	public ArrayList<Integer> Result;
	public int imgWidth, imgHeight;
	int maxGradientR = -1;

	public SobelEdge(BufferedImage imageIn) {
		init(imageIn);

	}

	public void init(BufferedImage imageIn) {
		this.image = imageIn;
		imgWidth = imageIn.getWidth();
		imgHeight = imageIn.getHeight();
		this.out_image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
	}

	public BufferedImage sobelOutput() {
		return out_image;
	}

	public static int getGrayScale(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;

		// int gray = (r + g + b) / 3;
		int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);

		return gray;
	}

	public void sobelStart() {
		int maxGval = 0;
		int[][] edgeColors = new int[imgWidth][imgHeight];
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

				int gx = ((-1 * val00) + (0 * val01) + (1 * val02)) + ((-2 * val10) + (0 * val11) + (2 * val12))
						+ ((-1 * val20) + (0 * val21) + (1 * val22));

				int gy = ((-1 * val00) + (-2 * val01) + (-1 * val02)) + ((0 * val10) + (0 * val11) + (0 * val12))
						+ ((1 * val20) + (2 * val21) + (1 * val22));

				double gval = Math.sqrt((gx * gx) + (gy * gy));
				int g = (int) gval;

				if (maxGradient < g) {
					maxGradient = g;
				}
				edgeColors[i][j] = g;
			}
		}

		double scale = 255.0 / maxGradient; // 255로 나누어 최대 크기가 255로 나올수 있도록 정규화함

		for (int j = 1; j < imgHeight - 1; j++) {
			for (int i = 1; i < imgWidth - 1; i++) {
				int edgeColor = edgeColors[i][j];
				edgeColor = (int) (edgeColor * scale);
				edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
				out_image.setRGB(i, j, edgeColor);
			}
		}
	}

	public void sobelStart(int start, int end) {
		int maxGval = 0;
		int[][] edgeColors = new int[imgWidth][imgHeight];
		int maxGradient = -1;

		To2D_GetPoint gp = new To2D_GetPoint();
		int i = 0, j = 0;
		for (int s = start; s < end; s++) {
			gp.reSet(s, imgWidth);
			i = gp.getX();
			j = gp.getY();
			if ((i > 0 && i < imgWidth - 1) && (j > 0 && j < imgHeight - 1)) {
				// System.out.print("<"+i+","+j+"> ");

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
					maxGradient = g;
				}
				edgeColors[i][j] = g;
			}
		}

		double scale = 255.0 / maxGradient; // 255로 나누어 최대 크기가 255로 나올수 있도록 정규화함

		for (j = 1; j < imgHeight - 1; j++) {
			for (i = 1; i < imgWidth - 1; i++) {
				int edgeColor = edgeColors[i][j];
				edgeColor = (int) (edgeColor * scale);
				edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
				out_image.setRGB(i, j, edgeColor);
			}
		}
	}

	public ArrayList<Integer> sobelResult(int start, int end) {
		Result = new ArrayList<>();
		int maxGval = 0;
		int[][] edgeColors = new int[imgWidth][imgHeight];
		int maxGradientR = -1;

		To2D_GetPoint gp = new To2D_GetPoint();
		int i = 0, j = 0;
		for (int s = start; s < end; s++) {
			gp.reSet(s, imgWidth);
			i = gp.getX();
			j = gp.getY();
			if ((i > 0 && i < imgWidth - 1) && (j > 0 && j < imgHeight - 1)) {
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

				if (maxGradientR < g) {
					maxGradientR = g;
				}
				// edgeColors[i][j] = g;
				Result.add(g);
			} else {
				Result.add(getGrayScale(image.getRGB(i, j)));
			}
		}

		/*
		 * double scale = 255.0 / maxGradient; // 255로 나누어 최대 크기가 255로 나올수 있도록
		 * 정규화함
		 * 
		 * for (j = 1; j < imgHeight - 1; j++) { for (i = 1; i < imgWidth - 1;
		 * i++) { int edgeColor = edgeColors[i][j]; edgeColor = (int) (edgeColor
		 * * scale); edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor <<
		 * 8) | edgeColor;
		 * 
		 * } }
		 */

		return Result;
	}

}
