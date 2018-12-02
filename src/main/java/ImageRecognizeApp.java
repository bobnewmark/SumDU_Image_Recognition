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

        // матрицы расстояний
        int [][] dist1 = new int[2][50];
        int [][] dist2 = new int[2][50];
        int [][] dist3 = new int[2][50];

        dist1[0] = Calculations.getDistance(normalizedBinVector1, binaryMatrix1);
        dist1[1] = Calculations.getDistance(normalizedBinVector1, binaryMatrix2);

        dist2[0] = Calculations.getDistance(normalizedBinVector2, binaryMatrix2);
        dist2[1] = Calculations.getDistance(normalizedBinVector2, binaryMatrix3);

        dist3[0] = Calculations.getDistance(normalizedBinVector3, binaryMatrix3);
        dist3[1] = Calculations.getDistance(normalizedBinVector3, binaryMatrix1);

        //получение Е
        float[] e1 = Calculations.getE(dist1);
        float[] effectiveE1 = Calculations.getEffectiveE(e1);
        new Chart("Result 1", e1, effectiveE1);

        float[] e2 = Calculations.getE(dist2);
        float[] effectiveE2 = Calculations.getEffectiveE(e2);
        new Chart("Result 2", e2, effectiveE2);

        float[] e3 = Calculations.getE(dist3);
        float[] effectiveE3 = Calculations.getEffectiveE(e3);
        new Chart("Result 3", e3, effectiveE3);

        //єкзамен для 4-го файла
        int[][] img4Matrix = Utils.getMatrixFromFile(img4File);
        int[][] binaryMatrix4 = Calculations.getBinaryMatrix(img4Matrix, upperVector, lowerVector);
        int[] normalizedBinVector4 = Calculations.getVectorFromMatrix(binaryMatrix4);
        int [][] dist4 = new int[2][50];
        dist4[0] = Calculations.getDistance(normalizedBinVector4, binaryMatrix4);
        dist4[1] = Calculations.getDistance(normalizedBinVector4, binaryMatrix1);
        float[] e4 = Calculations.getE(dist4);
        float[] effectiveE4 = Calculations.getEffectiveE(e4);
        new Chart("EXAM", e4, effectiveE4);

    }

}
