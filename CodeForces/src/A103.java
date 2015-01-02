import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class A103 {

	public void solve(int[] squad) {
		// complex algorithm but with better time
		boolean needMin = true;
		boolean needMax = true;

		int[] copy = squad.clone();
		Arrays.sort(copy);
		int min = copy[0];
		int max = copy[copy.length - 1];

		int secs = 0;
		for (int j = squad.length - 1; j >= 0 && needMin; j--)
			if (squad[j] == min) {
				for (int i = j; i < squad.length - 1; i++)
					if (squad[i] < squad[i + 1]) {
						int temp = squad[i];
						squad[i] = squad[i + 1];
						squad[i + 1] = temp;
						secs++;
					}
				needMin = false;
			}
		
		for (int j = 0; j < squad.length && needMax; j++)
			if (squad[j] == max) {
				for (int i = j; i > 0; i--)
					if (squad[i] > squad[i - 1]) {
						int temp = squad[i];
						squad[i] = squad[i - 1];
						squad[i - 1] = temp;
						secs++;
					}
				needMax = false;
			}
		
		System.out.println(secs);
	}
	
	public void solve2(int[] squad) {
		// easy algorithm but with bad time
		int secs = 0;
		int max = squad[0];
		int maxIdx = 0;
		for (int i = 1; i < squad.length; i++) {
			if (squad[i] > max) {
				max = squad[i];
				maxIdx = i;
			}
		}
		int min = squad[squad.length - 1];
		int minIdx = squad.length - 1;
		for (int i = squad.length - 2; i >= 0; i--) {
			if (squad[i] < min) {
				min = squad[i];
				minIdx = i;
			}
		}
		
		secs += maxIdx;
		secs += squad.length - minIdx - 1;
		
		if (maxIdx > minIdx)
			secs--;

		System.out.println(secs);
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(br);
		int n = scan.nextInt();
		int[] squad = new int[n];
		for (int i = 0; i < n; i++)
			squad[i] = scan.nextInt();
		
		new A103().solve(squad);
	}
}
