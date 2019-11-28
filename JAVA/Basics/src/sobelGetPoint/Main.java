package sobelGetPoint;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/*소벨마스크 2D=>1D로 전환하여 일정 구간 소벨마스크 처리하기*/

public class Main {

	public static void main(String[] args) {
		int width = 0, height = 0, weight = 0;
		BufferedImage image = null;
		BufferedImage outimage = null;
		ArrayList<Integer> imgVal = new ArrayList<>();

		// 이미지 입력 후 새로운 outimage 생성(검정색 이미지)
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

		// 입력된 이미지의 RGB값을 인트리스트에 입력
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < weight; w++) {
				imgVal.add(image.getRGB(w, h));
			}
		}

		// 인트 리스트 이용하여 아웃 이미지 출력
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

		// sobel mask 시작
		SobelEdge sb = new SobelEdge(image);
		sb.sobelStart();
		try {
			ImageIO.write(sb.sobelOutput(), "png", new File("img\\3sobelimg.png"));
			ImageIO.write(sb.image, "png", new File("img\\4sobelimg.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 구간 소벨 처리
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
