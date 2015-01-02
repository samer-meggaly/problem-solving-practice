import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A102 {

	public void solve(int[] deepenings) {

		boolean found = false;
		int[][] solu = new int[2][2];
		for (int i = 1; i <= 9 && !found; i++)
			for (int j = 1; j <= 9 && !found; j++)
				for (int k = 1; k <= 9 && !found; k++)
					for (int l = 1; l <= 9 && !found; l++)
						if ((i - j) * (i - k) * (i - l) * (j - k) * (j - l)
								* (k - l) != 0) {
							solu[0][0] = i;
							solu[0][1] = j;
							solu[1][0] = k;
							solu[1][1] = l;
							found = check(deepenings, solu);
						}

		if (found) {
			System.out.println(solu[0][0] + " " + solu[0][1]);
			System.out.println(solu[1][0] + " " + solu[1][1]);
		} else {
			System.out.println("-1");
		}
	}

	private boolean check(int[] deepenings, int[][] solu) {
		boolean rows = (deepenings[0] == (solu[0][0] + solu[0][1]))
				&& (deepenings[1] == (solu[1][0] + solu[1][1]));
		boolean columns = (deepenings[2] == (solu[0][0] + solu[1][0]))
				&& (deepenings[3] == (solu[0][1] + solu[1][1]));
		boolean diagonals = (deepenings[4] == (solu[0][0] + solu[1][1]))
				&& (deepenings[5] == (solu[0][1] + solu[1][0]));

		return rows && columns && diagonals;
	}

	public static void main(String[] args) throws IOException {
		// System.out.println("start");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] deep = new int[6];
		for (int i = 0; i < 5; i += 2) {
			String line = br.readLine();
			String[] parts = line.split("\\s");
			deep[i] = Integer.parseInt(parts[0]);
			deep[i + 1] = Integer.parseInt(parts[1]);
		}

		new A102().solve(deep);
	}

}
