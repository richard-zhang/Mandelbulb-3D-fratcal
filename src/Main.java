import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedImage mandel = new BufferedImage(640, 480,1);
        for (int i = 0; i < 480; i++) {
            for (int j = 0; j < 640; j++) {
                Vector col = Vector.trace((double) i, (double) j).mul(255);
                int red = (int) col.x;
                int green = (int) col.y;
                int blue = (int) col.z;
                mandel.setRGB(j, i, 0xff000000 | (((0xff & red) << 16)
                        | ((0xff & green) << 8) | (0xff & blue)));
            }
        }

        File output = new File("saved.png");
        ImageIO.write(mandel,"png",output);


    }



}
