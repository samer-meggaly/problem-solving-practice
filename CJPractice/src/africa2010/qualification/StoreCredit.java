package africa2010.qualification;
import java.io.File;
import java.util.Scanner;

public class StoreCredit {

	public static void solve(int credit, int[] itemList, int caseNumber) {
		int[] result = new int[2];
		
		boolean notFound = true;
		for (int i = 0; i < itemList.length && notFound; i++) {
			for (int j = (i+1); j < itemList.length && notFound; j++) {
				if (itemList[j] == credit - itemList[i]) {
					notFound = false;
					result[0] = i+1;
					result[1] = j+1;
				}
			}
		}
		
		System.out.println("Case #" +caseNumber+": " +result[0]+ " " +result[1]);
	}
	
	public static void parseInput() throws Exception {
		String file = "africa2010/qualification/A-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		
		if (scanner.hasNext()) {
			int numberOfCases = scanner.nextInt();
			
			for (int i = 1; i <= numberOfCases; i++) {
				int credit = scanner.nextInt();
				int itemsSize = scanner.nextInt();
				int[] items = new int[itemsSize];
				
				for (int j = 0; j < items.length; j++) {
					items[j] = scanner.nextInt();
				}
				
				solve(credit, items, i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		
	}
}
