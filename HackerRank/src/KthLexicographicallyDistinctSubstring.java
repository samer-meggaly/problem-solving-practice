import java.util.Arrays;

public class KthLexicographicallyDistinctSubstring {

	private static class SuffixArray {

		private final String T;
		private SuffixArray.Suffix[] suffixes;
		private int[][] rank;
		private int[] lcp;
		private int[] distinctSubstrings;

		public SuffixArray(String T) {
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
			distinctSubstrings = new int[N];
			distinctSubstrings[0] = suffixes[0].len();
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
				distinctSubstrings[i] = distinctSubstrings[i - 1];
				distinctSubstrings[i] += suffixes[i].len() - lcp;
			}
		}

		private final class Suffix implements Comparable<Suffix> {
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

			public int len() {
				return T.length() - suffixIdx;
			}
		}
	}
	
	private static String findString(SuffixArray sa, int query) {
		int N = sa.T.length();
		if (query > sa.distinctSubstrings[N - 1]) 
			return "INVALID";
		
		int L = 0;
		int R = N - 1;
		while (L < R) {
			int M = ((R - L) >> 1) + L;
			if (query > sa.distinctSubstrings[M])
				L = M + 1;
			else
				R = M;
		}
		
		if (L > 0)
			query -= sa.distinctSubstrings[L - 1];
		int endIndex = sa.lcp[L] + query;
		return sa.suffixes[L].toString().substring(0, endIndex);
	}
	
	private static void findStrings(String T, int[] queries) {
		SuffixArray sa = new SuffixArray(T);
		for (int query : queries)
			System.out.println(findString(sa, query));
	}
	
	public static void main(String[] args) {
		String T = "banana";

		int[] qs = new int[15];
		for (int i = 1; i <= 15; i++)
			qs[i - 1] = i;
		findStrings(T, qs);
	}

}
