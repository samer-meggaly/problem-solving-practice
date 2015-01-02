package world2010.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class FileFixIt {
	
	
	public static int count(String[] exists, String[] toCreate) {
		HashSet<String> hash = new HashSet<String>();
		for (String path : exists)
			hash.add(path);
		
		int counter = 0;
		for (String newPath : toCreate) {
			int pathEnd = 1;
			char[] temp = newPath.toCharArray();
			while (pathEnd < temp.length) {
				while (pathEnd < temp.length && temp[pathEnd] != '/')
					pathEnd++;
				if (!hash.contains(newPath.substring(0, pathEnd))) {
					counter++;
					hash.add(newPath.substring(0, pathEnd));
				}
				pathEnd++;
			}
		}
		return counter;
	}

	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2010/1B/A-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0; i < T; i++) {
			String[] NM = scanner.nextLine().split("\\s");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			String[] exists = new String[N];
			String[] toCreate = new String[M];
			
			for (int n = 0; n < N; n++)
				exists[n] = scanner.nextLine();
			for (int m = 0; m < M; m++)
				toCreate[m] = scanner.nextLine();
			
			out.println("Case #"+(i+1)+": "+count(exists, toCreate)+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}

}
