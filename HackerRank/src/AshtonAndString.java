import java.util.Arrays;
import java.util.Scanner;

public class AshtonAndString {

	private static class SuffixArray {

		private final String T;
		private Suffix[] suffixes;
		private int[][] rank;
		private int[] lcp;

		public SuffixArray(String T) {
			this.T = T;
			computeSuffixes();
			computeLcps();
		}

		private void computeSuffixes() {
			int N = T.length();
			int LgN = (int) Math.ceil(Math.log10(N) / Math.log10(2));
			suffixes = new Suffix[N];
			rank = new int[LgN + 1][N];
			int stp = 0;
			for (int i = 0; i < N; i++) {
				suffixes[i] = new Suffix(i);
				suffixes[i].firstHalfRank = T.charAt(i) - 'a';
				rank[stp][i] = suffixes[i].firstHalfRank;
			}

			stp++;
			for (int K = 1; K < N; K <<= 1, stp++) {
				for (int i = 0; i < N; i++) {
					int secondHalfIdx = suffixes[i].suffixIdx + K;
					suffixes[i].secondHalfRank = (secondHalfIdx < N)
							? rank[stp - 1][secondHalfIdx] : -1;
				}

				Arrays.sort(suffixes);

				int rankCtr = 0;
				int prevSuffixFirstHalfRank = -1;
				int prevSuffixSecondHalfRank = -1;
				for (int i = 0; i < N; i++) {
					if (i > 0
							&& (suffixes[i].firstHalfRank != prevSuffixFirstHalfRank
									|| suffixes[i].secondHalfRank != prevSuffixSecondHalfRank))
						rankCtr++;

					prevSuffixFirstHalfRank = suffixes[i].firstHalfRank;
					prevSuffixSecondHalfRank = suffixes[i].secondHalfRank;
					suffixes[i].firstHalfRank = rankCtr;
					rank[stp][suffixes[i].suffixIdx] = rankCtr;
				}
			}
		}

		private void computeLcps() {
			int N = T.length();
			lcp = new int[N];
			for (int i = 1; i < N; i++) {
				lcp[i] = lcp(suffixes[i - 1].suffixIdx, suffixes[i].suffixIdx);
			}
		}

		private int lcp(int si1, int si2) {
			int N = T.length();
			int lcp = 0;
			for (int stp = rank.length - 1; stp > -1 && si1 < N
					&& si2 < N; stp--) {
				if (rank[stp][si1] == rank[stp][si2]) {
					lcp += (1 << stp);
					si1 += (1 << stp);
					si2 += (1 << stp);
				}
			}
			return lcp;
		}

		private class Suffix implements Comparable<Suffix> {

			private int suffixIdx;
			private int firstHalfRank;
			private int secondHalfRank;

			public Suffix(int suffixIdx) {
				this.suffixIdx = suffixIdx;
			}

			@Override
			public int compareTo(Suffix o) {
				if (this.firstHalfRank == o.firstHalfRank)
					return this.secondHalfRank - o.secondHalfRank;
				else
					return this.firstHalfRank - o.firstHalfRank;
			}

			public int len() {
				return T.length() - suffixIdx;
			}

			public String toString() {
				return T.substring(suffixIdx);
			}

			public char chatAt(int l) {
				return T.charAt(suffixIdx + l);
			}
		}
	}

	private static char charAt(String T, long K) {
		SuffixArray sa = new SuffixArray(T);
		int N = T.length();
		for (int i = 0; i < N; i++) {
			int siLen = sa.suffixes[i].len();
			for (int ci = sa.lcp[i] + 1; ci <= siLen; ci++) {
				if (K <= ci)
					return sa.suffixes[i].chatAt((int) (K - 1));
				else
					K -= ci;
			}
		}
		
		return 0; // should never be executed if K was valid
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int t = 0; t < T; t++) {
            String s = scanner.nextLine();
            long K = Long.parseLong(scanner.nextLine());
            System.out.println(charAt(s, K));
        }
        scanner.close();
	}
}
