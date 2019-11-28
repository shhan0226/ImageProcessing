package sobelGetPointDivSum;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;

//소벨 구간 나누기
public class Main {

	public static void main(String[] args) {
		ArrayList<Node> ndList = new ArrayList<>();
		ndList.add(new Node());
		ndList.add(new Node());
		ndList.add(new Node());

		ndList.get(0).setCpuUse("20.20");
		ndList.get(1).setCpuUse("20.80");
		ndList.get(2).setCpuUse("10.17");

		int workload = 10000;
		float Ratio = 0;
		int divWorkload = 0;
		int StartLoad, NextLoad = 0;
		ArrayList<Integer> SumResult = new ArrayList<>();
		int Width = 0;
		int Height = 0;
		SobelEdge sb = null;
		ArrayList<BufferedImage> imageList = new ArrayList<>();

		// 전체 CPU 사용량
		float Total_CPU = 0;
		for (int i = 0; i < ndList.size(); i++) {
			Total_CPU += Float.parseFloat(ndList.get(i).getCpuUse());
		}
		System.out.println("Total CPU : " + Total_CPU);

		// 소벨 결과 병합
		System.out.println("");

		BufferedImage image = null;
		BufferedImage returnImage = null;

		try {
			image = ImageIO.read(new File("img\\origin.png"));
			sb = new SobelEdge(image);
			Width = image.getWidth();
			Height = image.getHeight();
			workload = image.getWidth() * image.getHeight();
			StartLoad = NextLoad = 0;

			System.out.println("workload : " + workload);
			System.out.println("W: " + image.getWidth() + " H: " + image.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < ndList.size(); i++) {
			StartLoad = NextLoad;
			Ratio = Float.parseFloat(ndList.get(i).getCpuUse()) / Total_CPU;
			divWorkload = (int) (workload * Ratio);
			NextLoad += divWorkload;
			// 분할한거 보내기
			System.out.println("Start-End: " + StartLoad + " ~ " + NextLoad + " / Node[" + i + "] divWorklaod : "
					+ String.valueOf(divWorkload));

			// 분할한거 처리
			sb.sobelStart(StartLoad, NextLoad);

			returnImage = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
			for (int k = 0; k < Width; k++) {
				for (int l = 0; l < Height; l++) {
					returnImage.setRGB(k, l, sb.sobelOutput().getRGB(k, l));
				}
			}

			imageList.add(returnImage);

			try {
				ImageIO.write(sb.sobelOutput(), "png", new File("img\\sobelResult-" + i + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 병합처리
		BufferedImage resultImage = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);

		for (int s = 0; s < imageList.size(); s++) {
			System.out.println("s:" + s);
			try {
				ImageIO.write(imageList.get(s), "png", new File("img\\sobelResult2-" + s + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int i = 0; i < Width; i++) {
				for (int j = 0; j < Height; j++) {
					if (-16777216 != imageList.get(s).getRGB(i, j)) {
						resultImage.setRGB(i, j, imageList.get(s).getRGB(i, j));
					}
				}
			}

		}

		try {
			ImageIO.write(resultImage, "png", new File("img\\sumResult.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
