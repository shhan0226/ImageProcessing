package ui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import algorithm.*;


public class Main {

	public static void main(String[] args) {
		BufferedImage image = null;
		try {
			File oFile = new File(args[0]);
			image = ImageIO.read(oFile);

			/*
			 * long L = oFile.length(); L= L / 1024 / 1024;
			 * System.out.println("file size: "+ L + " Mbyte");
			 */

			/*
			 * DataBuffer dataBuffer = image.getData().getDataBuffer(); long
			 * sizeBytes = ((long) dataBuffer.getSize()) * 4l;
			 * System.out.println("buffer size: "+ sizeBytes + " byte"); long
			 * sizeMB = sizeBytes/(1024l * 1024l);
			 * System.out.println("buffer size: "+ sizeMB + " Mbyte");
			 */

			/*
			 * BufferedImage testImage = new BufferedImage(image.getWidth(),
			 * image.getHeight(), BufferedImage.TYPE_INT_RGB); DataBuffer
			 * dataBuffer2 = testImage.getData().getDataBuffer(); long
			 * sizeBytes2 = ((long) dataBuffer2.getSize()) * 4l;
			 * System.out.println("test buffer size: "+ sizeBytes2 + " byte");
			 * long sizeMB2 = sizeBytes2/(1024l * 1024l);
			 * System.out.println("test buffer size: "+ sizeMB2 + " Mbyte");
			 */

			/*
			 * System.out.println("Sobel Start!"); long start =
			 * System.currentTimeMillis(); Sobel sobel = new Sobel(image);
			 * sobel.EdgeStart(); sobel.Output(args[1]); long end =
			 * System.currentTimeMillis(); System.out.println( "Time : " + ( end
			 * - start )/1000.0 +"s" );
			 */

			/*
			 * System.out.println("Prewitt Start!"); long start =
			 * System.currentTimeMillis(); Prewitt pr = new Prewitt(image);
			 * pr.EdgeStart(); pr.Output(args[1]); long end =
			 * System.currentTimeMillis(); System.out.println( "Time : " + ( end
			 * - start )/1000.0 +"s" );
			 */

			System.out.println("Roberts Start!");
			long start = System.currentTimeMillis();
			Roberts ro = new Roberts(image);
			ro.EdgeStart();
			ro.Output(args[1]);
			long end = System.currentTimeMillis();
			System.out.println("Time : " + (end - start) / 1000.0 + "s");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
