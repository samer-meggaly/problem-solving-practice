package world2009.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class AllYourBase {

	public static long getMinSeconds(String[] cipher) {
		
		HashSet<String> distinct = new HashSet<String>();
		ArrayList<String> list = new ArrayList<String>();
		for (int digit = 0; digit < cipher.length; digit++)
			if (!distinct.contains(cipher[digit])) {
				distinct.add(cipher[digit]);
				list.add(cipher[digit]);
			}
		
		int base = list.size();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int b = 0;
		for (String s : list)
			map.put(s, b++);
		map.put(list.get(0), 1);
		if (list.size() > 1)
			map.put(list.get(1), 0);
		else
			base = 2;
		
		long factor = 1L;
		long sum = 0L;
		for (int ptr = cipher.length - 1; ptr >= 0; ptr--) {
			sum += factor * map.get(cipher[ptr]);
			factor *= base;
		}
		
		return sum;
	}
	
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2009/1C/A-large-practice.in";
//		String file = "input.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0; i < T; i++) {
			char[] c = scanner.nextLine().toCharArray();
			String[] cipher = new String[c.length];
			for (int j = 0; j < c.length; j++)
				cipher[j] = c[j] + "";
			
			long r = getMinSeconds(cipher);
			out.println("Case #"+(i+1)+": "+r+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}


}
