package practice;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlienNumbers {

	public static String convert(String[] number, String[] source,
			String[] target) {
		
		// source
		int srcBase = source.length;
		Map<String, Integer> srcMap = new HashMap<String, Integer>();
		int c = 0;
		for (String s : source)
			srcMap.put(s, c++);
		// target
		int tarBase = target.length;
		Map<Integer, String> tarMap = new HashMap<Integer, String>();
		c = 0;
		for (String t : target)
			tarMap.put(c++, t);
		
		// source to Decimal
		long factor = 1L;
		long value = 0L;
		for (int n = number.length - 1; n >= 0; n--) {
			value += srcMap.get(number[n]) * factor;
			factor *= srcBase;
		}
		
		// Decimal to target
		if (value == 0L)
			return tarMap.get(value);
		String result = "";
		while (value > 0) {
			int vMod = (int) (value % tarBase); 
			value /= tarBase;
			result = tarMap.get(vMod) + result;
		}
		
		return result;
	}
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "practice/A-large-practice.in";
//		String file = "input.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0; i < T; i++) {
			String[] NumSrcTar = scanner.nextLine().split("\\s");
			
			char[] temp = NumSrcTar[0].toCharArray();
			String[] number = new String[temp.length];
			for (int p = 0; p < temp.length; p++)
				number[p] = "" + temp[p];
			
			temp = NumSrcTar[1].toCharArray();
			String[] source = new String[temp.length];
			for (int p = 0; p < temp.length; p++)
				source[p] = "" + temp[p];
			
			temp = NumSrcTar[2].toCharArray();
			String[] target = new String[temp.length];
			for (int p = 0; p < temp.length; p++)
				target[p] = "" + temp[p];
			
			String r = convert(number, source, target);
			out.println("Case #"+(i+1)+": "+r+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}

}
