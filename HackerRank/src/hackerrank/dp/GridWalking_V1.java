package hackerrank.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GridWalking_V1 {

	// Correct solution but give TLE on HackerRank.
	// Brute Force solution based on State Space graph walk.

	private static int N = -1;
	private static int[] Ds = null;
	private static final int MOD = (int) (1E9 + 7);

	private static class State {
		private int[] loc;
		private int steps;

		public State(int[] loc, int steps) {
			this.loc = loc;
			this.steps = steps;
		}

		public List<State> nextStates() {
			List<State> nexts = new ArrayList<State>(2 * N);
			for (int d = 0; d < N; d++) {
				if (loc[d] - 1 > 0) {
					State next = new State(Arrays.copyOf(loc, N), steps - 1);
					next.loc[d]--;
					nexts.add(next);
				}
				if (loc[d] + 1 <= Ds[d]) {
					State next = new State(Arrays.copyOf(loc, N), steps - 1);
					next.loc[d]++;
					nexts.add(next);
				}
			}
			return nexts;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(loc);
			result = prime * result + steps;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			State other = (State) obj;
			if (steps != other.steps)
				return false;
			if (!Arrays.equals(loc, other.loc))
				return false;
			return true;
		}
	}

	private static HashMap<State, Integer> walks = null;

	private static int countWalks(State s) {
		if (s.steps == 0)
			return 1;

		if (!walks.containsKey(s)) {
			int sumWalks = 0;
			for (State ns : s.nextStates()) {
				sumWalks += countWalks(ns);
				sumWalks %= MOD;
			}
			walks.put(s, sumWalks);
			return sumWalks;
		}
		return walks.get(s);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			N = scanner.nextInt();
			walks = new HashMap<State, Integer>();
			State init = new State(new int[N], scanner.nextInt());
			for (int d = 0; d < N; d++)
				init.loc[d] = scanner.nextInt();
			Ds = new int[N];
			for (int d = 0; d < N; d++)
				Ds[d] = scanner.nextInt();

			System.out.println(countWalks(init));
		}
		scanner.close();
	}

}