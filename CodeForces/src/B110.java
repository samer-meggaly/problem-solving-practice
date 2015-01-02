import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class B110 {
	
	public void solve(int[] radii) {
		Arrays.sort(radii);
		
		int len = (radii.length - 1);
		long sum = ((radii.length % 2 == 1))? radii[0] * radii[0] : 0L;
		for (int i = len; (i - 1) >= 0; i -= 2) {
			sum += (radii[i] * radii[i]) - (radii[i - 1] * radii[i - 1]);
		}
		
		System.out.println(Math.PI * sum);
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(br);
		int n = scan.nextInt();
		int[] r = new int[n];
		for (int i = 0; i < n; i++)
			r[i] = scan.nextInt();
		
		new B110().solve(r);
	}
}
