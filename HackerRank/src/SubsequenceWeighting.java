import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SubsequenceWeighting {

	private static class MaxBIT {
		// binary heap-like implementation.
		// https://notes.tweakblogs.net/blog/9835/fenwick-trees-demystified.html

		private long[] tree;
		private int potShift;

		public MaxBIT(int size) {
			potShift = powerOf2GreaterThan(size);
			this.tree = new long[potShift + size];
		}

		private static int powerOf2GreaterThan(int n) {
			int pot = 1;
			while (pot <= n)
				pot <<= 1;
			return pot;
		}

		private static boolean odd(int n) {
			return (n & 1) == 1;
		}

		public void update(int index, long val) {
			index += potShift;
			while (index > 0 && tree[index] < val) {
				tree[index] = val;
				index >>>= 1;
			}
		}

		public long max(int from, int to) {
			long max = Long.MIN_VALUE;
			from += potShift;
			to += potShift;
			for (; from <= to; from >>>= 1, to >>>= 1) {
				if (odd(from)) {
					max = Math.max(max, tree[from]);
					from++;
				}

				if (!odd(to)) {
					max = Math.max(max, tree[to]);
					to--;
				}
			}

			return max;
		}

		public long max() {
			return tree[1]; // max of all tree, at the root.
		}
	}

	private static void set1basedSortingIndices(int[] as) {
		int N = as.length;
		int[][] asIdx = new int[N][2];
		for (int i = 0; i < N; i++)
			asIdx[i] = new int[] { as[i], i };
		Arrays.sort(asIdx, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int prev = 0; // all a_i(s) are > 0
		int sortingIndex = 0;
		for (int[] aIdx : asIdx) {
			int a = aIdx[0];
			int idx = aIdx[1];

			// all equal a_i, of course, take the same sorting-index.
			if (a != prev) {
				sortingIndex++;
				prev = a;
			}
			as[idx] = sortingIndex;
		}
	}

	private static long maxSequenceWeight(int[] as, int[] ws) {
		// replace every a_i with its sorting index. The actual values, a_i,
		// will no longer be needed after setting sorting-index.
		set1basedSortingIndices(as);

		int N = as.length;
		MaxBIT bit = new MaxBIT(N + 1); // +1 as sorting-indices are 1-based.

		// consider elements in their given original sequence ordering.
		for (int i = 0; i < N; i++) {
			// find the max of all sequence-endings < i
			// to consider endings with value < a_i, we consider only values
			// with sorting-index less than that of a_i, i.e. values that came
			// before a_i in ascending ordering.
			long sum = ws[i] + bit.max(0, as[i] - 1);
			bit.update(as[i], sum);
		}
		return bit.max(); // overall max of all seq-endings stored in the root.
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			int N = scanner.nextInt();
			int[] as = new int[N];
			for (int i = 0; i < N; i++) {
				as[i] = scanner.nextInt();
			}
			int[] ws = new int[N];
			for (int i = 0; i < N; i++) {
				ws[i] = scanner.nextInt();
			}
			System.out.println(maxSequenceWeight(as, ws));
		}
		scanner.close();

	}

}
