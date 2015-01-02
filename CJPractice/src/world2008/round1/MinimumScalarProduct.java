package world2008.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MinimumScalarProduct {

	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2008/A-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		if (scanner.hasNext()) {
			int numberOfCases = scanner.nextInt();
			
			for (int i = 1; i <= numberOfCases; i++) {
				int sizeOfInput = scanner.nextInt();
				long[] X = new long[sizeOfInput];
				long[] Y = new long[sizeOfInput];
				
				for (int j = 0; j < X.length; j++)
					X[j] = scanner.nextLong();
				for (int j = 0; j < Y.length; j++)
					Y[j] = scanner.nextLong();
				
				solve(X, Y, i);
			}
		}
	}

	public static void solve(long[] x, long[] y, int caseNumber) {
		Arrays.sort(x);
		Arrays.sort(y);
		int size = x.length;
		
		long msp = 0;
		for (int i = 0; i < size; i++) {
			msp += (x[i] * y[size - i - 1]);
		}
		
		out.println("Case #"+caseNumber+": "+msp+"");
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done");
	}
}
