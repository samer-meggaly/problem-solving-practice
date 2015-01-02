package africa2010.competition;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GetToWork {
	
	static PrintWriter out;

	ArrayList<ArrayList<Integer>> licensed;
	int[] notLicensed;
	int e;
	int n;
	int t;

	public void solve(int caseNumber) {
		for (int i = 0; i < licensed.size(); i++)
			Collections.sort(licensed.get(i));

		int notTransported = e;
		int[] cars = new int[n];
		for (int i = 0; i < n; i++) {
			if (i != t) {
				int nles = notLicensed[i];
				Integer[] availableCars = new Integer[licensed.get(i).size()]; 
				availableCars = licensed.get(i).toArray(availableCars);
				int les = licensed.get(i).size();
				for (int j = (availableCars.length - 1); j >= 0 && les > 0; j--) {
					les--; // for the car owner
					availableCars[j]--;
					cars[i]++;
					while (nles > 0 && availableCars[j] > 0) {
						// car owner will take another college with him
						nles--;
						availableCars[j]--;
					}
					while (les > 0 && availableCars[j] > 0) {
						// car owner will take a college, who also has a car
						les--;
						availableCars[j]--;
					}
				}
				notTransported -= notLicensed[i] - nles;
				notTransported -= licensed.get(i).size() - les;
			} else {
				notTransported -= notLicensed[t];
				notTransported -= licensed.get(i).size();
				cars[t] = 0;
			}
		}
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("Case #"+caseNumber+":");
		
		if (notTransported > 0)
			buffer.append(" IMPOSSIBLE");
		else {
			for (int i : cars) {
				buffer.append(" " + i);
			}
		}
		
		out.println(buffer);
	}
	
	public static void parseInput() throws Exception {
		GetToWork obj = new GetToWork();		
		String file = "africa2010/competition/B-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		if (scanner.hasNext()) {
			int numberOfCases = scanner.nextInt();
			
			for (int i = 1; i <= numberOfCases; i++) {
				obj.n = scanner.nextInt();
				obj.t = scanner.nextInt() - 1;
				obj.e = scanner.nextInt();
				obj.notLicensed = new int[obj.n];
				obj.licensed = new ArrayList<ArrayList<Integer>>();
				for (int k = 0; k < obj.n; k++) {
					obj.licensed.add(new ArrayList<Integer>());
				}
				for (int j = 0; j < obj.e; j++) {
					int empTown = scanner.nextInt() - 1;
					int p = scanner.nextInt();
					if (p > 0) {
						obj.licensed.get(empTown).add(p);		
					} else {
						obj.notLicensed[empTown]++;		
					}
				}
				
				obj.solve(i);
			}
		}
		
		out.close();
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		System.out.println("Done");
	}
}
