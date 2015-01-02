package africa2010.qualification;

import java.io.File;
import java.util.Scanner;

public class ReverseWords {

	public static void parseAndSolve() throws Exception {
		String file = "africa2010/qualification/B-large-practice.in";
		Scanner scanner = new Scanner(new File(file));

		while (scanner.hasNext()) {
			int numberOfCases = Integer.parseInt(scanner.nextLine().trim());

			for (int i = 1; i <= numberOfCases; i++) {
				String inputList = scanner.nextLine().trim();
				String[] tokens = inputList.split(" ");
				
				StringBuilder buffer = new StringBuilder(tokens.length * 2);
				int size = tokens.length;
				int j = 0;
				for (; j < (size/2); j++) {
					buffer.append(" ");
					buffer.append(tokens[size - j - 1]);
					tokens[size - j - 1] = tokens[j];
				}
				
				for (; j < size; j++) {
					buffer.append(" ");
					buffer.append(tokens[j]);
				}
				System.out.println("Case #"+i+":"+buffer+"");
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseAndSolve();
	}
}
