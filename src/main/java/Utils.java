import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils extends Component {


    public static int[][] getBrightnessMatrix(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[][] greyMatrix = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = image.getRGB(j, i);
                greyMatrix[i][j] = (pixel >> 16) & 0xff;
            }
        }
        return greyMatrix;
    }

    public static BufferedImage fileToImage(File file) throws IOException {
        File grey = Grayscale.processFile(file);
        return ImageIO.read(grey.toURI().toURL());
    }

    public static int[][] getMatrixFromFile(File file) throws IOException {
        BufferedImage img = fileToImage(file);
        return Utils.getBrightnessMatrix(img);
    }

}