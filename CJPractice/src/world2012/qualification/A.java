package world2012.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class A {
	
	static String alpha = "abcdefghijklmnopqrstuvwxyz";
	static String[] cipher = new String[] {
		"ejp mysljylc kd kxveddknmc re jsicpdrysi",
		"rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
		"de kr kd eoya kw aej tysr re ujdr lkgc jv",
		"y qee z"
	};
	
	static String[] plain = new String[] {
		"our language is impossible to understand",
		"there are twenty six factorial possibilities",
		"so it is okay if you want to just give up",
		"a zoo q"
	};
	
	static PrintWriter out;
	static Map<String, String> map;
	
	public static void initMap() {
		map = new HashMap<String, String>();
		int n = cipher.length;
		
		for (int i = 0; i < n; i++) {
			char[] c = cipher[i].toCharArray();
			char[] p = plain[i].toCharArray();
			for (int j = 0; j < c.length; j++) {
				if (!map.containsKey(c[j] + ""))
						map.put(c[j] + "", p[j] + "");
			}
		}
		
		System.out.println("Size: " + map.size());
	}
	
	public static String decipher(String cipher) {
		char[] c = cipher.toCharArray();
		String plain = "";
		
		for (int i = 0; i < c.length; i++)
			if (map.containsKey(c[i] + ""))
				plain = plain + map.get(c[i] + "");
		if (cipher.length() != plain.length())
			System.out.println("Gota -> " + plain);
		return plain;
	}
	
	public static void parseInput() throws Exception {
		String file = "world2012/qualification/A-small-attempt1.in";
//		String file = "input.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		initMap();
		
		for (int i = 0; i < T; i++) {
			String ciphertext = scanner.nextLine();
			
			String r = decipher(ciphertext);
			out.println("Case #"+(i+1)+": "+r+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}

}
