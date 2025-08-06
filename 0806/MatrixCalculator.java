public class MatrixCalculator {
    public static int[][] add(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for(int i=0; i<a.length; i++)
            for(int j=0; j<a[0].length; j++)
                result[i][j] = a[i][j] + b[i][j];
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for(int i=0; i<a.length; i++)
            for(int j=0; j<b[0].length; j++)
                for(int k=0; k<a[0].length; k++)
                    result[i][j] += a[i][k] * b[k][j];
        return result;
    }

    public static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[0].length; j++)
                result[j][i] = matrix[i][j];
        return result;
    }

    public static void findMinMax(int[][] matrix) {
        int min = matrix[0][0], max = matrix[0][0];
        for(int[] row : matrix)
            for(int num : row) {
                if(num < min) min = num;
                if(num > max) max = num;
            }
        System.out.println("Min: " + min + " Max: " + max);
    }
}