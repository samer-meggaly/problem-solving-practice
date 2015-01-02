package world2009.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class AlienLanguage {
	
	static int L = -1;
	
	public static char[][] parsePattern(String inText) {
		char[][] lists = new char[L][];
		int index = 0;
		for (int i = 0; i < L; i++) {
			if (inText.charAt(index) == '(') {
				int to = inText.indexOf(')', index);
				lists[i] = inText.substring(index + 1, to).toCharArray();
				index = to + 1;
			} else {
				lists[i] = new char[] { inText.charAt(index) };
				index++;
			}
		}
		return lists;
	}
	
	public static char[][] filter(char[][] domain, char[] filter, int at) {
		ArrayList<char[]> temp = new ArrayList<char[]>(domain.length);
		
		for (char[] cand : domain) {
			boolean takeIt = false;
			for (char c : filter) {
				if (cand[at] == c) {
					takeIt = true;
					break;
				}
			}
			
			if (takeIt)
				temp.add(cand);
		}
		char[][] out = new char[temp.size()][L];
		out = temp.toArray(out);
		return out;
	}
	
	public static int count(char[][] dict, char[][] pattern) {
		for (int i = 0; i < L; i++) {
			dict = filter(dict, pattern[i], i);
		}
		
		return dict.length;
	}
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2009/qualification/A-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		String[] consts = scanner.nextLine().split("\\s");
		L = Integer.parseInt(consts[0]);
		int D = Integer.parseInt(consts[1]);
		int N = Integer.parseInt(consts[2]);
		
		char[][] dictionary = new char[D][L];
		for (int i = 0; i < D; i++)
			dictionary[i] = scanner.nextLine().toCharArray();
		
		for (int i = 0; i < N; i++) {
			char[][] pattern = parsePattern(scanner.nextLine());
			int count = count(dictionary.clone(), pattern);
			out.println("Case #"+(i+1)+": "+count+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}
	
}
