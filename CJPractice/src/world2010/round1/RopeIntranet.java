package world2010.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RopeIntranet {

	
	public static int count(Wire[] wires) {
		Arrays.sort(wires);
		
		int counter = 0;
		for (int i = 0; i < wires.length; i++) {
			int wireRight = wires[i].rightHeight;
			for (int j = i + 1; j < wires.length; j++) {
				if (wires[j].rightHeight > wireRight)
					counter++;
			}
		}
		return counter;
	}
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		RopeIntranet ri = new RopeIntranet();
		String file = "world2010/1C/A-large-practice.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = scanner.nextInt();
		
		for (int i = 0; i < T; i++) {
			int N = scanner.nextInt();
			Wire[] wires = new Wire[N];
			for (int j = 0; j < N; j++) {
				wires[j] = ri.new Wire(scanner.nextInt(), scanner.nextInt());
			}
			out.println("Case #"+(i+1)+": "+count(wires)+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}
	
	class Wire implements Comparable<Wire> {

		int leftHeight;
		int rightHeight;
		
		public Wire(int left, int right) {
			this.leftHeight = left;
			this.rightHeight = right;
		}
		
		@Override
		public int compareTo(Wire o) {
			return -1 * (this.leftHeight - o.leftHeight);
		}
		
	}

}
