

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

    public static int[] getDistance(int[] a, int[][] b) {
        int[] result = new int[a.length];

        for (int i = 0; i < b.length; i++) {
            int distance = 0;
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j] != a[i]) {
                    distance++;
                }
            }
            result[i] = distance;
        }
        return result;
    }

    public static float[] getE(int[][] matrix) {
        int[] k1 = new int[50];
        int[] k2 = new int[50];
        int[] k3 = new int[50];
        int[] k4 = new int[50];
        float[] d1 = new float[50];
        float[] d2 = new float[50];
        float[] alpha = new float[50];
        float[] beta = new float[50];

        float[] e = new float[50];


        for (int d = 1; d <= 50; d++) {
            int k1Count = 0;
            int k3Count = 0;

            for (int i = 0; i < 50; i++) {
                if (matrix[0][i] <= d) k1Count++;
                if (matrix[1][i] <= d) k3Count++;
            }
            k1[d - 1] = k1Count;
            k3[d - 1] = k3Count;

            k2[d - 1] = Math.abs(d - k1Count);
            k4[d - 1] = Math.abs(d - k3Count);
            d1[d - 1] = k1Count / 50f;
            d2[d - 1] = k3Count / 50f;
            alpha[d - 1] = k2[d - 1] / 50f;
            beta[d - 1] = k4[d - 1] / 50f;

            e[d-1] = countE(alpha[d - 1], beta[d - 1], d1[d - 1], d2[d - 1]);
        }
        return e;
    }

    static float log(float x, int base) {
        if (x == 0) return 0;
        //System.out.println("number:" + x + " log:" + (float) (Math.log(x) / Math.log(base)));
        return (float) (Math.log(x) / Math.log(base));
    }

    static float log(int x, int base) {
        if (x == 0) return 0;
        return (float) (Math.log(x) / Math.log(base));
    }

    static float countE(float alpha, float beta, float d1, float d2) {

        return 1 + 0.5f * (
                alpha / (alpha + d2) * log(alpha / (alpha + d2), 2) +
                        d1 / (d1 + beta) * log(d1 / (d1 + beta), 2) +
                        beta / (beta + d1) * log(beta / (beta + d1), 2) +
                        d2 / (alpha + d2) * log(d2 / (alpha + d2), 2)
        );
    }

    public static float[] getEffectiveE(float[] e) {
        float[] effective = new float[50];
        for (int i = 0; i < e.length; i++) {
            if(e[i] > 0.5) effective[i] = e[i];
        }
        return effective;
    }

}
