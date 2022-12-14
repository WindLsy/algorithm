package cn.linshiyou.algorithm.leetcode;

/**
 * @Author: LJ
 * @Description: 304. 二维区域和检索 - 矩阵不可变
 *
 * 前缀和技巧：一维前缀和，这个题可以使用二维前缀和
 */
public class L304 {


    public static void main(String[] args) {


        int[][] matrix = {
            {3,0,1,4,2},
            {5,6,3,2,1},
            {1,2,0,1,5},
            {4,1,0,1,7},
            {1,0,3,0,5}
        };
        int row1=2;
        int col1=1;
        int row2=4;
        int col2=3;

        NumMatrix obj = new NumMatrix(matrix);
        int param1 = obj.sumRegion(row1,col1,row2,col2);
        System.out.println(param1);
    }
    // 二维前缀和

    static class NumMatrix {

        int[][] preSumMatrix;

        public NumMatrix(int[][] matrix) {

            int n = matrix.length;
            int m = matrix[0].length;

            preSumMatrix = new int[n+1][m+1];

            for (int i=1; i<=n; i++){
                for (int j=1; j<=m; j++){
                    preSumMatrix[i][j] = matrix[i-1][j-1] + preSumMatrix[i-1][j] + preSumMatrix[i][j-1] - preSumMatrix[i-1][j-1];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1++;
            col1++;
            row2++;
            col2++;
            return preSumMatrix[row2][col2] - preSumMatrix[row1-1][col2] - preSumMatrix[row2][col1-1] + preSumMatrix[row1-1][col1-1];

        }
    }

/**
 * 一维前缀和
    static class NumMatrix {

        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            int cor = matrix[0].length;

            preSum = new int[row][cor+1];

            for (int i=0; i<row; i++){
                for (int j=1; j<cor+1; j++){
                    preSum[i][j] = preSum[i][j-1] + matrix[i][j-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int ans = 0;

            for (int i=row1; i<=row2; i++){
                ans += preSum[i][col2+1]-preSum[i][col1];
            }

            return ans;
        }
    }
*/
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

}
