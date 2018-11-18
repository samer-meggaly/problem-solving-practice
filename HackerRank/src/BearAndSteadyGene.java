import java.util.Arrays;
import java.util.Scanner;

public class BearAndSteadyGene {

	private static boolean unsteady(int N, int[] counts) {
		for (int c : counts)
			if (c > (N >>> 2))
				return true;
		return false;
	}

	@SuppressWarnings("unused")
	private static int minToSteadyQuadratic(int[] sChars) {
		int N = sChars.length;
		int[] counts = new int[4];
		for (int c : sChars)
			counts[c]++;
		if (!unsteady(N, counts))
			return 0;

		int minToSteady = Integer.MAX_VALUE;
		for (int startIdx = 0; startIdx < N; startIdx++) {
			int endIdx = startIdx;
			int[] cloneCounts = Arrays.copyOf(counts, 4);
			boolean unsteady = true;
			for (; endIdx < N && unsteady; endIdx++) {
				cloneCounts[sChars[endIdx]]--;
				unsteady = unsteady(N, cloneCounts);
			}
			if (!unsteady)
				minToSteady = Math.min(minToSteady, endIdx - startIdx);
		}

		return minToSteady;
	}

	private static class BIT {
		private int[] tree;
		private int N;

		public BIT(int N) {
			this.N = N;
			this.tree = new int[N + 1];
		}

		public void update(int i) {
			for (i++; i <= N; i += (i & -i))
				tree[i]++;
		}

		public int sum(int i) {
			int sum = 0;
			for (i++; i > 0; i -= (i & -i))
				sum += tree[i];
			return sum;
		}

		public int sum(int i, int j) {
			// i to j both inclusive
			return sum(j) - sum(i - 1);
		}
	}

	@SuppressWarnings("unused")
	private static int minToSteadyLinearLogLog(int[] sChars) {
		int N = sChars.length;
		int[] counts = new int[4];
		BIT[] bits = new BIT[] { new BIT(N), new BIT(N), new BIT(N),
				new BIT(N) };
		for (int ci = 0; ci < N; ci++) {
			counts[sChars[ci]]++;
			bits[sChars[ci]].update(ci);
		}
		if (!unsteady(N, counts))
			return 0;

		int minToSteady = Integer.MAX_VALUE;
		for (int startIdx = 0; startIdx < N; startIdx++) {
			int L = startIdx;
			int R = N - 1;
			while (L <= R) {
				int M = ((R - L) >> 1) + L;
				int[] clonesCounts = Arrays.copyOf(counts, 4);
				for (int i = 0; i < 4; i++)
					clonesCounts[i] -= bits[i].sum(startIdx, M);
				if (unsteady(N, clonesCounts))
					L = M + 1;
				else {
					minToSteady = Math.min(minToSteady, M - startIdx + 1);
					R = M - 1;
				}
			}
		}

		return minToSteady;
	}

	@SuppressWarnings("unused")
	private static int minToSteadyLinearLog(int[] sChars) {
		int N = sChars.length;
		int[] counts = new int[4];
		int[][] prefixCounts = new int[4][N + 1 /* 1-based indexing */];
		for (int ci = 1; ci <= N; ci++) {
			counts[sChars[ci - 1]]++;
			// all prefix counts remain the same
			prefixCounts[0][ci] = prefixCounts[0][ci - 1];
			prefixCounts[1][ci] = prefixCounts[1][ci - 1];
			prefixCounts[2][ci] = prefixCounts[2][ci - 1];
			prefixCounts[3][ci] = prefixCounts[3][ci - 1];
			// except for the prefix of the character at ci
			prefixCounts[sChars[ci - 1]][ci]++;
		}
		if (!unsteady(N, counts))
			return 0;

		int minToSteady = Integer.MAX_VALUE;
		for (int startIdx = 1; startIdx <= N; startIdx++) {
			int L = startIdx;
			int R = N;
			while (L <= R) {
				int M = ((R - L) >> 1) + L;
				int[] clonesCounts = Arrays.copyOf(counts, 4);
				for (int i = 0; i < 4; i++)
					clonesCounts[i] -= prefixCounts[i][M]
							- prefixCounts[i][startIdx - 1];
				if (unsteady(N, clonesCounts))
					L = M + 1;
				else {
					minToSteady = Math.min(minToSteady, M - startIdx + 1);
					R = M - 1;
				}
			}
		}

		return minToSteady;
	}

	private static int minToSteady(int[] sChars) {
		int N = sChars.length;
		int[] counts = new int[4];
		int[][] prefixCounts = new int[4][N + 2 /* 1-based indexing */];
		int[][] suffixCounts = new int[4][N + 2 /* 1-based indexing */];
		int ci = 1;
		int cj = N;
		for (; ci <= N; ci++, cj--) {
			counts[sChars[ci - 1]]++;
			// all prefix counts remain the same
			for (int i = 0; i < 4; i++) {
				prefixCounts[i][ci] = prefixCounts[i][ci - 1];
				suffixCounts[i][cj] = suffixCounts[i][cj + 1];
			}
			// except for the prefix of the character at ci
			prefixCounts[sChars[ci - 1]][ci]++;
			// and the suffix of the character at cj
			suffixCounts[sChars[cj - 1]][cj]++;
		}
		if (!unsteady(N, counts))
			return 0;

		int minToSteady = Integer.MAX_VALUE;
		int L = 1;
		int R = 2;
		while (R <= N) {
			int[] clonesCounts = new int[4];
			for (int i = 0; i < 4; i++)
				clonesCounts[i] = prefixCounts[i][L] + suffixCounts[i][R];
			if (unsteady(N, clonesCounts))
				R++;
			else {
				minToSteady = Math.min(minToSteady, R - L - 1);
				L++;
			}
		}

		return minToSteady;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = Integer.parseInt(scanner.nextLine());
		int[] sChars = new int[N];
		String s = scanner.nextLine();
		for (int i = 0; i < N; i++) {
			char c = s.charAt(i);
			sChars[i] = (c == 'A') ? 0
					: ((c == 'C') ? 1 : ((c == 'G') ? 2 : 3));
		}
		System.out.println(minToSteady(sChars));
		scanner.close();
	}
}