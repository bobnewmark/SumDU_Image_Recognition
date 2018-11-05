
public class Calculations {

    public static int[] getVectorFromMatrix(int[][] matrix) {
        int[] vector = new int[matrix.length];
        for (int i = 0; i < vector.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            vector[i] = sum / matrix[i].length;
        }
        return vector;
    }

    public static int[][] getBinaryMatrix(int[][] matrix, int[] upperVector, int[] lowerVector) {
        int[][] binaryMatrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int upperBorder = upperVector[i];
            int lowerBorder = lowerVector[i];

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > lowerBorder && matrix[i][j] < upperBorder) {
                    binaryMatrix[i][j] = 1;
                } else {
                    binaryMatrix[i][j] = 0;
                }
            }
        }
        return binaryMatrix;
    }
}
