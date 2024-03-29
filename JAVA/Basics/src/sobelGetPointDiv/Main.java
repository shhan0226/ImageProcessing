package sobelGetPointDiv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

		// 전체 CPU 사용량
		float Total_CPU = 0;
		for (int i = 0; i < ndList.size(); i++) {
			Total_CPU += Float.parseFloat(ndList.get(i).getCpuUse());
		}
		System.out.println("Total CPU : " + Total_CPU);

		// 개별 CPU 사용량
		/*
		 * for (int i = 0; i < ndList.size(); i++) { Ratio =
		 * Float.parseFloat(ndList.get(i).getCpuUse()) / Total_CPU;
		 * System.out.println("Node[" + i + "] Rate : " +
		 * String.valueOf(Ratio)); }
		 */

		// 작업량 * 개별 CPU 사용량 System.out.println("");
		/*
		 * for (int i = 0; i < ndList.size(); i++) { StartLoad = NextLoad; Ratio
		 * = Float.parseFloat(ndList.get(i).getCpuUse()) / Total_CPU;
		 * divWorkload = (int) (workload * Ratio); NextLoad += divWorkload;
		 * System.out.println("Start-End: " + StartLoad + " ~ " + NextLoad +
		 * " Node[" + i + "] divWorklaod : " + String.valueOf(divWorkload)); }
		 */

		// 나머지 작업량 처리

		/*
		 * // 소벨 마스크의 작업량에 분할 적용 System.out.println("");
		 * 
		 * BufferedImage image = null; try { image = ImageIO.read(new
		 * File("src\\img\\origin.png")); workload = image.getWidth() *
		 * image.getHeight(); StartLoad = NextLoad = 0;
		 * 
		 * System.out.println("workload : " + workload);
		 * System.out.println("W: " + image.getWidth() + " H: " +
		 * image.getHeight()); } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * SobelEdge sb = new SobelEdge(image); for (int i = 0; i <
		 * ndList.size(); i++) { StartLoad = NextLoad; Ratio =
		 * Float.parseFloat(ndList.get(i).getCpuUse()) / Total_CPU; divWorkload
		 * = (int) (workload * Ratio); NextLoad += divWorkload; // 분할한거 보내기
		 * System.out.println("Start-End: " + StartLoad + " ~ " + NextLoad +
		 * " / Node[" + i + "] divWorklaod : " + String.valueOf(divWorkload));
		 * 
		 * // 분할한거 처리 sb.sobelStart(StartLoad, NextLoad); try {
		 * ImageIO.write(sb.sobelOutput(), "png", new
		 * File("src\\img\\sobelResult-" + i + ".png")); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */

		// 소벨 결과 병합
		System.out.println("");

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File("img\\origin.png"));
			Width = image.getWidth();
			Height = image.getHeight();
			workload = image.getWidth() * image.getHeight();
			StartLoad = NextLoad = 0;

			System.out.println("workload : " + workload);
			System.out.println("W: " + image.getWidth() + " H: " + image.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}

		SobelEdge sb = new SobelEdge(image);
		ArrayList<BufferedImage> imageList = new ArrayList<>();

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
			imageList.add(sb.sobelOutput());
			try {
				ImageIO.write(sb.sobelOutput(), "png", new File("img\\sobelResult-" + i + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 병합처리
		BufferedImage resultImage = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);

		for (int s = 0; s < imageList.size(); s++) {
			try {
				ImageIO.write(imageList.get(s), "png", new File("img\\sobelResult2-" + s + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * System.out.println("s:" + s); for (int i = 0; i < Width; i++) { for
		 * (int j = 0; j < Height; j++) { if (temp == resultImage.getRGB(i, j))
		 * { resultImage.setRGB(i, j, imageList.get(s).getRGB(i, j)); } } }
		 */

		/*
		 * try { ImageIO.write(resultImage, "png", new
		 * File("src\\img\\sumResult.png")); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

	}

}
