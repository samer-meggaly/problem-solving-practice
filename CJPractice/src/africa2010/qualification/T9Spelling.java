package africa2010.qualification;

import java.io.File;
import java.util.Scanner;

public class T9Spelling {
	

	public static void main(String[] args) throws Exception {
		int end = mapper.length - 1;
		mapper[0][0] = (int) 'a';
		for (int i = 1; i < end; i++)
			mapper[i][0] = mapper[i-1][0] + 1;
		
		parseInput();
	}
	
	public static void parseInput() throws Exception {
		String file = "africa2010/qualification/C-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		
		if (scanner.hasNext()) {
			int numberOfCases = Integer.parseInt(scanner.nextLine().trim());
			
			for (int i = 1; i <= numberOfCases; i++) {
				String desiredMessage = scanner.nextLine();
				
				solve(desiredMessage, i);
			}
		}
	}
	
	public static void solve(String desiredMessage, int caseNumber) {
		char[] characters = desiredMessage.toCharArray();
		int previousKey = -1;
		int currentKey = -1;
		int base = (int) 'a';
		int index;
		String code = "";
		
		System.out.print("Case #"+caseNumber+": ");
		for (char c : characters) {
			if (c == ' ') {
				index = mapper.length - 1;
			} else {
				index = c - base;
			}
			
			code = "" + mapper[index][1];
			currentKey = mapper[index][2];			
			System.out.print((currentKey == previousKey)? " " : "");
			System.out.print(code);
			previousKey = currentKey;
		}
		System.out.println();
	}

	static int[][] mapper = new int[][] {
		new int[] {0, 2, 2},
		new int[] {0, 22, 2},
		new int[] {0, 222, 2},
		new int[] {0, 3, 3},
		new int[] {0, 33, 3},
		new int[] {0, 333, 3},
		new int[] {0, 4, 4},
		new int[] {0, 44, 4},
		new int[] {0, 444, 4},
		new int[] {0, 5, 5},
		new int[] {0, 55, 5},
		new int[] {0, 555, 5},
		new int[] {0, 6, 6},
		new int[] {0, 66, 6},
		new int[] {0, 666, 6},
		new int[] {0, 7, 7},
		new int[] {0, 77, 7},
		new int[] {0, 777, 7},
		new int[] {0, 7777, 7},
		new int[] {0, 8, 8},
		new int[] {0, 88, 8},
		new int[] {0, 888, 8},
		new int[] {0, 9, 9},
		new int[] {0, 99, 9},
		new int[] {0, 999, 9},
		new int[] {0, 9999, 9},
		new int[] {' ', 0, 0},
	};
}
