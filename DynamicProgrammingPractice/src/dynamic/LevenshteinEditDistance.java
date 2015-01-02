package dynamic;

public class LevenshteinEditDistance {
	
	String s;
	String t;
	int[][] c;
	
	public LevenshteinEditDistance(String str1, String str2) {
		s = str1;
		t = str2;
		c = new int[s.length() + 1][t.length() + 1];
	}
	
	public int getEditDist() {
		for (int i = 0; i < t.length() + 1; i++)
			c[0][i] = i;
		for (int i = 0; i < s.length() + 1; i++)
			c[i][0] = i;
		
		for (int i = 1; i < s.length() + 1; i++) {
			for (int j = 1; j < t.length() + 1; j++) {
				if ((s.charAt(i - 1) + "").equalsIgnoreCase(t.charAt(j - 1) + "")) {
					c[i][j] = c[i - 1][j - 1]; 
				} else {
					c[i][j] = 
						Math.min(c[i - 1][j - 1], 
								Math.min(c[i][j - 1], 
										c[i - 1][j]))
										+ 1;
				}
			}
		}
		return c[s.length()][t.length()];
	}
	
	public static void main(String[] args) {
		String str_1;
		String str_2;
		
		//Test 1
		str_1 = "kitten";
		str_2 = "sitting";
		System.out.println(str_1 + " -> " + str_2 + " in " + new LevenshteinEditDistance(str_1, str_2).getEditDist());
		System.out.println("--------------------------------");
		//Test 2
		str_1 = "Saturday";
		str_2 = "Sunday";
		System.out.println(str_1 + " -> " + str_2 + " in " + new LevenshteinEditDistance(str_1, str_2).getEditDist());
		System.out.println("--------------------------------");
		//Test 3
		str_1 = "String";
		str_2 = "String";
		System.out.println(str_1 + " -> " + str_2 + " in " + new LevenshteinEditDistance(str_1, str_2).getEditDist());
		System.out.println("--------------------------------");
		//Test 4
		str_1 = "12346";
		str_2 = "78901";
		System.out.println(str_1 + " -> " + str_2 + " in " + new LevenshteinEditDistance(str_1, str_2).getEditDist());
		System.out.println("--------------------------------");
	}

}
