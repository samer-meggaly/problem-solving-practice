package leetcode.problems;

public class UniquePaths {
	/*
	 * https://leetcode.com/problems/unique-paths/
	 * https://leetcode.com/problems/unique-paths-ii/
	 * 
	 * Size of the grid is <= 100X100 which would definitely exceeds int and
	 * even long for an empty grid, however Leetcode requires the output to be
	 * int!!!
	 */

	private int[][] pathsTo;

	public int uniquePaths1(int m, int n) {
		pathsTo = new int[m][n];
		return countPaths(0, 0, m, n);
	}

	private int countPaths(int r, int c, int maxR, int maxC) {
		if (r == maxR - 1 && c == maxC - 1)
			return 1;

		if (pathsTo[r][c] == 0) {
			if (c + 1 < maxC)
				pathsTo[r][c] += countPaths(r, c + 1, maxR, maxC);
			if (r + 1 < maxR)
				pathsTo[r][c] += countPaths(r + 1, c, maxR, maxC);
		}

		return pathsTo[r][c];
	}

	private int[][] obstacles;

	public int uniquePathsWithObstacles1(int[][] obstacles) {
		/*
		 * Gives TLE on Leetcode
		 */
		if (obstacles == null)
			return 0;
		this.obstacles = obstacles;
		pathsTo = new int[obstacles.length][obstacles[0].length];
		return countPathsWithObstacles(0, 0, obstacles.length,
				obstacles[0].length);
	}

	private int countPathsWithObstacles(int r, int c, int maxR, int maxC) {
		if (r == maxR - 1 && c == maxC - 1)
			return 1 - obstacles[r][c];

		if (obstacles[r][c] == 0 && pathsTo[r][c] == 0) {
			if (c + 1 < maxC)
				pathsTo[r][c] += countPathsWithObstacles(r, c + 1, maxR, maxC);
			if (r + 1 < maxR)
				pathsTo[r][c] += countPathsWithObstacles(r + 1, c, maxR, maxC);
		}

		return pathsTo[r][c];
	}

	public int uniquePaths(int m, int n) {
		pathsTo = new int[m][n];
		for (int i = 0; i < m; i++)
			pathsTo[i][0] = 1;
		for (int i = 0; i < n; i++)
			pathsTo[0][i] = 1;
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				pathsTo[i][j] = pathsTo[i][j - 1] + pathsTo[i - 1][j];
		return pathsTo[m - 1][n - 1];
	}

	public int uniquePathsWithObstacles(int[][] obstacles) {
		int m = obstacles.length;
		int n = obstacles[0].length;
		pathsTo = new int[m][n];
		pathsTo[0][0] = 1 - obstacles[0][0];
		for (int i = 1; i < m; i++)
			pathsTo[i][0] = (1 - obstacles[i][0]) * pathsTo[i - 1][0];
		for (int i = 1; i < n; i++)
			pathsTo[0][i] = (1 - obstacles[0][i]) * pathsTo[0][i - 1];
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				pathsTo[i][j] = (1 - obstacles[i][j])
						* (pathsTo[i][j - 1] + pathsTo[i - 1][j]);
		return pathsTo[m - 1][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(new UniquePaths().uniquePaths(2, 2)); // 2
		System.out.println(new UniquePaths().uniquePaths(1, 2)); // 1
		System.out.println(new UniquePaths().uniquePaths(2, 1)); // 1
		System.out.println(new UniquePaths().uniquePaths(1, 100)); // 1
		System.out.println(new UniquePaths().uniquePaths(3, 7)); // 28
		System.out.println(new UniquePaths().uniquePaths(6, 6)); // 252

		int[][] obstacles = new int[][] { { 0, 0 }, { 0, 0 }, };
		System.out
				.println(new UniquePaths().uniquePathsWithObstacles(obstacles)); // 2
		obstacles = new int[][] { { 0, 1 }, { 0, 0 }, };
		System.out
				.println(new UniquePaths().uniquePathsWithObstacles(obstacles)); // 1
		obstacles = new int[][] { { 1, 0 }, { 0, 0 }, };
		System.out
				.println(new UniquePaths().uniquePathsWithObstacles(obstacles)); // 0
		obstacles = new int[][] { { 0, 0 }, { 0, 1 }, };
		System.out
				.println(new UniquePaths().uniquePathsWithObstacles(obstacles)); // 0
		obstacles = new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		System.out
				.println(new UniquePaths().uniquePathsWithObstacles(obstacles)); // 2
		obstacles = new int[][] { { 0, 0 }, { 0, 1 }, };
		System.out
				.println(new UniquePaths().uniquePathsWithObstacles(obstacles)); // 0

	}
}
