#include "algorithms.h"
#include <math.h>
#define MIN(a,b)  ((a) > (b) ? (b) : (a))

using namespace std;

QImage sobel(const QImage &temp, int image_width, int image_height )
{

   /*-----------------------------Sobel Mask*/
    int Gx[9], Gy[9];
    Gx[0] = -1; Gx[1] = 0; Gx[2] = 1;
    Gx[3] = -2; Gx[4] = 0; Gx[5] = 2;
    Gx[6] = -1; Gx[7] = 0; Gx[8] = 1;

    Gy[0] = 1; Gy[1] = 2; Gy[2] = 1;
    Gy[3] = 0; Gy[4] = 0; Gy[5] = 0;
    Gy[6] = -1; Gy[7] = -2; Gy[8] = -1;

    //QImage edges = image_lbl->pixmap()->toImage();
    QImage image = temp;
    QImage edges = image;

    /*------(r,g,b)->(0~255) 변환 formula =(r * 11 + g * 16 + b * 5)/32 */
    for(int i=0; i<image_width; i++)
    {
        for(int j=0; j<image_height; j++)
        {
            image.setPixel(i, j, qGray(image.pixel(i, j)));
        }
    }

    /*-----------------------------Sobel Algorithm*/
    for(int i=1; i<image_width-1; i++)//image_width-1
    {
        for(int j=1; j<image_height-1; j++)
        {
            float sumX =
                Gx[8] * qGray(image.pixel(i+1, j+1)) +
                Gx[7] * qGray(image.pixel(i, j+1)) +
                Gx[6] * qGray(image.pixel(i-1, j+1)) +

                Gx[5] * qGray(image.pixel(i+1, j)) +
                Gx[4] * qGray(image.pixel(i, j)) +
                Gx[3] * qGray(image.pixel(i-1, j)) +

                Gx[2] * qGray(image.pixel(i+1, j-1)) +
                Gx[1] * qGray(image.pixel(i, j-1)) +
                Gx[0] * qGray(image.pixel(i-1, j-1));

            float sumY =
                Gy[8] * qGray(image.pixel(i+1, j+1)) +
                Gy[7] * qGray(image.pixel(i, j+1)) +
                Gy[6] * qGray(image.pixel(i-1, j+1)) +

                Gy[5] * qGray(image.pixel(i+1, j)) +
                Gy[4] * qGray(image.pixel(i, j)) +
                Gx[3] * qGray(image.pixel(i-1, j)) +

                Gy[2] * qGray(image.pixel(i+1, j-1)) +
                Gy[1] * qGray(image.pixel(i, j-1)) +
                Gy[0] * qGray(image.pixel(i-1, j-1));

            //float v = MIN(sqrt((double)(sumX*sumX + sumY*sumY)/4), 255);
            float v = sqrt((double)(sumX*sumX + sumY*sumY));
            edges.setPixel(i,j, qRgb(v, v, v));
        }
    }
    return edges;
}
