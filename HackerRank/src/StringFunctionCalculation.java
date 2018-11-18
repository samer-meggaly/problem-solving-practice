import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class StringFunctionCalculation {

	private static class SuffixArray {

		private final String T;
		private Suffix[] suffixes;
        private int[] lcp;
		private int[][] rank;

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
			for (int i = 1; i < N; i++) {
				this.lcp[i] = lcp(suffixes[i - 1].suffixIdx, suffixes[i].suffixIdx);
			}
		}
        
        public int[] getLcp() {
            return this.lcp;
        }
        
        public Suffix[] getSuffixes() {
        	return this.suffixes;
        }
		        
        public int lcp(int si1, int si2) {
            int N = T.length();
            if (si1 == si2)
                return N - si1;
            int stp = rank.length - 1;
            int lcp = 0;				
            for (; stp > -1 && si1 < N && si2 < N; stp--) {
                if (rank[stp][si1] == rank[stp][si2]) {
                    si1 += (1 << stp);
                    si2 += (1 << stp);
                    lcp += (1 << stp);
                }
            }
            return lcp;
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
    
    private static long maxF(String T) {
        SuffixArray sa = new SuffixArray(T);
        int N = T.length();
        int[] lcp = sa.getLcp();
        long max = Long.MIN_VALUE;
        int[] farBefore = new int[N];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < N; i++) {
        	while (!stack.isEmpty() && lcp[stack.peek()] > lcp[i]) {
        		int ii = stack.pop();
        		int freq = lcp[ii];
        		int range = i - farBefore[ii];
        		max = Math.max(max, freq * range);
        	}
        	
        	farBefore[i] = stack.isEmpty() ? 0 : stack.peek();
        	stack.push(i);
        }
        
        SuffixArray.Suffix[] suffixes = sa.getSuffixes();
        while (!stack.isEmpty()) {
        	int ii = stack.pop();
    		if (lcp[ii] == 0) {
    			max = Math.max(max, suffixes[ii].len());
    		} else {
    			int range = N - farBefore[ii];
        		max = Math.max(max, lcp[ii] * range);
    		}
        }
        
        return max;
    }
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.println(maxF(scanner.nextLine().trim()));
        scanner.close();
	}
}