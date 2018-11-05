import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageRecognizeApp {

    public static void main(String[] args) throws IOException {

        // загружаем файлы
        File img1File = new File("src\\main\\resources\\img1.jpg");
        File img2File = new File("src\\main\\resources\\img2.jpg");
        File img3File = new File("src\\main\\resources\\img3.jpg");
        File img4File = new File("src\\main\\resources\\img4.jpg");

        // получаем матрицы яркости изображений (уже в ч/б формате)
        int[][] img1Matrix = Utils.getMatrixFromFile(img1File);
        int[][] img2Matrix = Utils.getMatrixFromFile(img2File);
        int[][] img3Matrix = Utils.getMatrixFromFile(img3File);
        int[][] img4Matrix = Utils.getMatrixFromFile(img4File);

        // создаем вектор усредненных значений на основе 3-го кадра
        int[] vector = Calculations.getVectorFromMatrix(img3Matrix);
        // и вектора верхнего и нижнего допусков
        int[] upperVector = Arrays.stream(vector).map(operand -> operand + 40).toArray();
        int[] lowerVector = Arrays.stream(vector).map(operand -> operand - 40).toArray();

        // получаем бинарные матрицы на основе сравнения с допусками
        int[][] binaryMatrix1 = Calculations.getBinaryMatrix(img1Matrix, upperVector, lowerVector);
        int[][] binaryMatrix2 = Calculations.getBinaryMatrix(img2Matrix, upperVector, lowerVector);
        int[][] binaryMatrix3 = Calculations.getBinaryMatrix(img3Matrix, upperVector, lowerVector);

        // получаем усредненные бинарные вектора
        int[] normalizedBinVector1 = Calculations.getVectorFromMatrix(binaryMatrix1);
        int[] normalizedBinVector2 = Calculations.getVectorFromMatrix(binaryMatrix2);
        int[] normalizedBinVector3 = Calculations.getVectorFromMatrix(binaryMatrix3);

        System.out.println(Arrays.toString(normalizedBinVector1));
        System.out.println(Arrays.toString(normalizedBinVector2));
        System.out.println(Arrays.toString(normalizedBinVector3));






    }


}
