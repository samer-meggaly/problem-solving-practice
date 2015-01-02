package world2011.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FreeCellStatistics {
	
	public static String check(long N, int pd, int pg) {
		if (pd != 100 && pg == 100)
			return "Broken";
		
		int dGcd = gcd(pd, 100);
		int dNumer = pd / dGcd;
		int dDenom = 100 / dGcd;
		
		if (N < dDenom)
			return "Broken";
		
		int gGcd = gcd(pg, 100);
		int gNumer = pg / gGcd;
		
		if (dNumer != 0 && gNumer == 0)
			return "Broken";
		
		return "Possible";
	}

	public static int gcd(int a, int b) {
		
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		
		return a;
	}
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2011/1A/A-large-practice.in";
//		String file = "input.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0; i < T; i++) {
			String[] NM = scanner.nextLine().split("\\s");
			long N = Long.parseLong(NM[0]);
			int PD = Integer.parseInt(NM[1]);
			int PG = Integer.parseInt(NM[2]);
			
			out.println("Case #"+(i+1)+": "+check(N, PD, PG)+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
//		System.out.println(gcd(64, 100));
		System.out.println("Done!");
	}
}
