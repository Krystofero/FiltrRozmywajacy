package FiltrRozmywajacy;

import GrayScale.GrayScale;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class FiltrRozmywajacy {
    BufferedImage image;
    int width;
    int height;
    int [][] I_r, I_g, I_b;
    int [][] M = {{ 1 , 1 , 1}, { 1 , 1 , 1}};
    int pomoc_red = 0;
    int pomoc_green = 0;
    int pomoc_blue = 0;

    public FiltrRozmywajacy() {

        try {
            //odczyt obrazu z pliku
            File input = new File("C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\FiltrRozmywajacy\\pic1.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            I_r = new int[height][width];
            I_g = new int[height][width];
            I_b = new int[height][width];

            int k = -1,l = -1;

            //odczyt pixeli obrazu w dwóch pętlach po kolumnach i wierszach
            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++) {

                    //odczyt składowych koloru RGB
                    Color c = new Color(image.getRGB(j, i));
                    I_r[i][j] = (int) (c.getRed());
                    I_g[i][j] = (int) (c.getGreen());
                    I_b[i][j] = (int) (c.getBlue());
                }
            }

            for(int i=0; i<height; i++){
                for(int j=0; j<width; j++){

                    pomoc_red = 0;
                    pomoc_green = 0;
                    pomoc_blue = 0;

                    for(l=-1; l<=1; l++){
                        for(k=-1; k<=1; k++) {
                            pomoc_red += I_r[i+l][j+k]*M[l+1][k+1];
                            pomoc_green += I_g[i+l][j+k]*M[l+1][k+1];
                            pomoc_blue += I_b[i+l][j+k]*M[l+1][k+1];
                        }
                    }
                            pomoc_red /= 9; pomoc_green /= 9; pomoc_blue /= 9;
                            I_r[i+l][j+k] = pomoc_red;
                            I_g[i+l][j+k] = pomoc_red;
                            I_b[i+l][j+k] = pomoc_red;

                    Color newColor = new Color(pomoc_red, pomoc_green, pomoc_blue);
                    image.setRGB(j,i,newColor.getRGB());

                }
            }

//            for(int i=0; i<height; i++) {
//                for (int j = 0; j < width; j++) {
//
//                    Color newColor = new Color(pomoc_red, pomoc_green, pomoc_blue);
//                    image.setRGB(j,i,newColor.getRGB());
//
//                }
//            }

            //zapis do pliku zmodyfikowanego obrazu
            File ouptut = new File("C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\FiltrRozmywajacy\\pic1rozmyte.jpg");
            ImageIO.write(image, "jpg", ouptut);

        } catch (Exception e) {}
    }

    static public void main(String args[]) throws Exception
    {
        FiltrRozmywajacy obj = new FiltrRozmywajacy();
    }
}
