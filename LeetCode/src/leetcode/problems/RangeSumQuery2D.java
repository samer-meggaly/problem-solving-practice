package leetcode.problems;

public class RangeSumQuery2D {
	// https://leetcode.com/problems/range-sum-query-2d-immutable/

	private int[][] sumsMat;

	public RangeSumQuery2D(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		sumsMat = new int[matrix.length][matrix[0].length];
		sumsMat[0][0] = matrix[0][0];
		for (int i = 1; i < matrix.length; i++)
			sumsMat[i][0] = sumsMat[i - 1][0] + matrix[i][0];

		for (int i = 1; i < matrix[0].length; i++)
			sumsMat[0][i] = sumsMat[0][i - 1] + matrix[0][i];

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				sumsMat[i][j] = matrix[i][j];
				sumsMat[i][j] += sumsMat[i - 1][j];
				sumsMat[i][j] -= sumsMat[i - 1][j - 1];
				sumsMat[i][j] += sumsMat[i][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int row11 = row1 - 1;
		int col11 = col1 - 1;
		int result = sumsMat[row2][col2];
		if (row11 >= 0)
			result -= sumsMat[row11][col2];
		if (col11 >= 0)
			result -= sumsMat[row2][col11];
		if (row11 >= 0 && col11 >= 0)
			result += sumsMat[row11][col11];
		return result;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 },
				{ 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } };
		RangeSumQuery2D query = new RangeSumQuery2D(matrix);
		System.out.println(query.sumRegion(0, 0, 0, 0)); // 3
		System.out.println(query.sumRegion(1, 0, 1, 0)); // 5
		System.out.println(query.sumRegion(2, 1, 4, 3)); // 8
		System.out.println(query.sumRegion(1, 1, 2, 2)); // 11
		System.out.println(query.sumRegion(1, 2, 2, 4)); // 12
		System.out.println(query.sumRegion(0, 0, 4, 4)); // 58
		matrix = new int[][] { { 1 }, { -7 } };
		query = new RangeSumQuery2D(matrix);
		System.out.println(query.sumRegion(0, 0, 0, 0)); // 1
		System.out.println(query.sumRegion(1, 0, 1, 0)); // -7
		System.out.println(query.sumRegion(0, 0, 1, 0)); // -6
	}
}
