import java.util.Scanner;

public class PalindromeIndex {
    
    private static boolean palindrome(String s) {
        int M = s.length() >> 1;
		for (int i = 0; i < M; i++)
			if (s.charAt(i) != s.charAt(s.length() - 1 - i))
				return false;
		return true;
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int t = 0; t < T; t++) {
            int idx = -1;
            String s = scanner.nextLine();
            int M = s.length() >> 1;
            for (int i = 0; i < M && idx < 0; i++) {
                int idxF = i;
                int idxB = s.length() - i - 1;
                char cF = s.charAt(idxF);
                char cB = s.charAt(idxB);
                if (cF != cB) {
                    if (s.charAt(idxF + 1) == cB && s.charAt(idxB - 1) == cF)
                        idx = (palindrome(s.substring(0, idxF) + s.substring(idxF + 1))) ? idxF : idxB;
                    else
                        idx = (s.charAt(idxF + 1) == cB) ? idxF : idxB;
                }
            }
            System.out.println(idx);
        }
        scanner.close();
    }
}
