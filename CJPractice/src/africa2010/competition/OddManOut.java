package africa2010.competition;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class OddManOut {
	
	static PrintWriter out;
	static HashSet<Integer> set;

	public static void parseAndSolve() throws Exception {
		String file = "africa2010/competition/A-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		if (scanner.hasNext()) {
			int numberOfCases = scanner.nextInt();
			
			for (int i = 1; i <= numberOfCases; i++) {
				int g = scanner.nextInt();
				set = new HashSet<Integer>(g);
				for (int j = 0; j < g; j++) {
					int c = scanner.nextInt();
					if (set.contains(c)) {
						set.remove(c);
					} else {
						set.add(c);
					}
				}
				
				Integer[] oddOne = new Integer[set.size()];
				oddOne = set.toArray(oddOne);
				out.println("Case #"+i+": "+oddOne[0]+"");
			}
		}
		
		out.close();
	}
	
	public static void main(String[] args) throws Exception {
		parseAndSolve();
		System.out.println("Done");
	}
}
