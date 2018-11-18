package hackerrank.utils;
import java.util.Arrays;

public class SuffixArray {

	private static class SuffixArrayNLgNLgN {

		private final String T;
		private SuffixArrayNLgNLgN.Suffix[] suffixes;
		private int[][] rank;
		private int[] lcp;

		public SuffixArrayNLgNLgN(String T) {
			this.T = T;
			createSuffixes();
			computeLcps();
		}

		private void createSuffixes() {
			int N = T.length();
			int LgN = (int) Math.ceil(Math.log10(N) / Math.log10(2));
			suffixes = new Suffix[N];
			rank = new int[LgN + 1][N];
			int stp = 0;
			for (int i = 0; i < N; i++) {
				suffixes[i] = new Suffix();
				suffixes[i].suffixIdx = i;
				suffixes[i].firstHalfRank = T.charAt(i) - 'a';
				rank[stp][i] = suffixes[i].firstHalfRank;
			}
			
			stp++;
			for (int K = 1; K < N; K <<= 1, stp++) {
				// compute second half ranks
				for (Suffix s : suffixes) {
					s.secondHalfRank = (s.suffixIdx + K < N)
							? rank[stp - 1][s.suffixIdx + K] : -1;
				}

				// sort suffixes based on first-halves and second-halves ranks
				Arrays.sort(suffixes);

				// update ranks of the entire suffix
				int rankCtr = 0;
				int prevSfxFirstHR = -1;
				int prevSfxSecondHR = -1;
				for (int i = 0; i < N; i++) {
					if (i > 0 && (suffixes[i].firstHalfRank != prevSfxFirstHR
							|| suffixes[i].secondHalfRank != prevSfxSecondHR))
						rankCtr++; // if i does NOT have same rank as i-1
					prevSfxFirstHR = suffixes[i].firstHalfRank;
					prevSfxSecondHR = suffixes[i].secondHalfRank;
					suffixes[i].firstHalfRank = rankCtr;
					rank[stp][suffixes[i].suffixIdx] = rankCtr;
				}
			}
		}
		
		private void computeLcps() {
			int N = T.length();
			lcp = new int[N];
			for (int i = 1; i < N; i++) {
				int si1 = suffixes[i - 1].suffixIdx;
				int si2 = suffixes[i].suffixIdx;
				int stp = rank.length - 1;
				int lcp = 0;				
				for (; stp > -1 && si1 < N && si2 < N; stp--) {
					if (rank[stp][si1] == rank[stp][si2]) {
						si1 += (1 << stp);
						si2 += (1 << stp);
						lcp += (1 << stp);
					}
				}
				this.lcp[i] = lcp;
			}
		}

		private final class Suffix
				implements Comparable<SuffixArrayNLgNLgN.Suffix> {
			private int suffixIdx;
			private int firstHalfRank;
			private int secondHalfRank;

			@Override
			public int compareTo(Suffix o) {
				if (this.firstHalfRank == o.firstHalfRank)
					return this.secondHalfRank - o.secondHalfRank;
				else
					return this.firstHalfRank - o.firstHalfRank;
			}

			public String toString() {
				return T.substring(suffixIdx);
			}
			
			@SuppressWarnings("unused")
			public char charAt(int i) {
				return T.charAt(suffixIdx + i);
			}
		}
	}

	private static class SuffixArrayNNLgN {

		private final String T;
		private final Suffix[] suffixes;

		public SuffixArrayNNLgN(String T) {
			this.T = T;
			int N = T.length();
			this.suffixes = new Suffix[N];
			for (int i = 0; i < N; i++)
				suffixes[i] = new Suffix(i);
			Arrays.sort(suffixes);
		}

		private final class Suffix implements Comparable<Suffix> {
			private final int i;

			public Suffix(int i) {
				this.i = i;
			}

			public char charAt(int j) {
				return T.charAt(i + j);
			}

			public int length() {
				return T.length() - i;
			}

			@Override
			public int compareTo(Suffix that) {
				if (this == that)
					return 0;
				int N = Math.min(this.length(), that.length());
				for (int i = 0; i < N; i++) {
					if (this.charAt(i) < that.charAt(i))
						return -1;
					if (this.charAt(i) > that.charAt(i))
						return +1;
				}
				return this.length() - that.length();
			}

			public String toString() {
				return T.substring(i);
			}
		}
	}

	public static void main(String[] args) {
		// String T = "aaaaaa";
		// String T = "aa";
		// String T = "a";
		String T = "abcabcddd";

		SuffixArrayNNLgN sa = new SuffixArrayNNLgN(T);
		int ord = 0;
		for (SuffixArrayNNLgN.Suffix s : sa.suffixes)
			System.out.println(String.format("%2d: %2d => %s", ord++, s.i, s));
		System.out.println("========================");
		SuffixArrayNLgNLgN sa2 = new SuffixArrayNLgNLgN(T);
		int ord2 = 0;
		for (SuffixArrayNLgNLgN.Suffix s : sa2.suffixes)
			System.out.println(String.format("%2d: %2d => %2d - %s", ord2,
					s.suffixIdx, sa2.lcp[ord2++], s));

		// time test
		int len = (int) 1E5;
		StringBuilder sb = new StringBuilder(len);
		sb.append(T);
		while (sb.length() < len) {
			sb.append(sb.toString());
		}
		T = sb.toString();
		long start = System.currentTimeMillis();
		new SuffixArrayNNLgN(T);
		System.out.println(String.format("%s   @ %6d millisecs",
				SuffixArrayNNLgN.class, System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		new SuffixArrayNLgNLgN(T);
		System.out.println(String.format("%s @ %6d millisecs",
				SuffixArrayNLgNLgN.class, System.currentTimeMillis() - start));

	}
}