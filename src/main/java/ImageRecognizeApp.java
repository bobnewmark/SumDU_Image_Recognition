import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageRecognizeApp {

    public static void main(String[] args) throws IOException {

        File img1File = new File("src\\main\\resources\\img1.jpg");
        File img2File = new File("src\\main\\resources\\img2.jpg");
        File img3File = new File("src\\main\\resources\\img3.jpg");
        File img4File = new File("src\\main\\resources\\img4.jpg");

        int[][] img1Matrix = Utils.getMatrixFromFile(img1File);
        int[][] img2Matrix = Utils.getMatrixFromFile(img2File);
        int[][] img3Matrix = Utils.getMatrixFromFile(img3File);
        int[][] img4Matrix = Utils.getMatrixFromFile(img4File);

        int[] vector = Calculations.getVectorFromMatrix(img1Matrix);
        int[] upperVector = Arrays.stream(vector).map(operand -> operand + 20).toArray();
        int[] lowerVector = Arrays.stream(vector).map(operand -> operand - 20).toArray();

        int[][] binaryMatrix1 = Calculations.getBinaryMatrix(img1Matrix, upperVector, lowerVector);
        int[][] binaryMatrix2 = Calculations.getBinaryMatrix(img2Matrix, upperVector, lowerVector);
        int[][] binaryMatrix3 = Calculations.getBinaryMatrix(img3Matrix, upperVector, lowerVector);

        int[] normalizedBinVector1 = Calculations.getVectorFromMatrix(binaryMatrix1);
        int[] normalizedBinVector2 = Calculations.getVectorFromMatrix(binaryMatrix2);
        int[] normalizedBinVector3 = Calculations.getVectorFromMatrix(binaryMatrix3);

        System.out.println(Arrays.toString(normalizedBinVector1));
        System.out.println(Arrays.toString(normalizedBinVector2));
        System.out.println(Arrays.toString(normalizedBinVector3));





        //System.out.println("mainMatrix: " + mainMatrix.length + " " + mainMatrix[0].length);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < img1Matrix.length; i++) {
            for (int j = 0; j < img1Matrix[i].length; j++) {
                min = img1Matrix[i][j] < min ? img1Matrix[i][j] : min;
                max = img1Matrix[i][j] > max ? img1Matrix[i][j] : max;
            }
        }


    }

//    private static BufferedImage fileToImage(File file) throws IOException {
//        File grey = Grayscale.processFile(file);
//        return ImageIO.read(grey.toURI().toURL());
//    }
//
//    private static int[][] getMatrixFromFile(File file) throws IOException {
//        BufferedImage img = fileToImage(file);
//        return Utils.getBrightnessMatrix(img);
//    }
}
