import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class B104 {

	public void solve(int a, int b) {
		char[] ac = ("" + a).toCharArray();
		char[] bc = ("" + b).toCharArray();
		char[] base = new char[Math.max(ac.length, bc.length)];
		Arrays.fill(base, '4');
		if (ac.length > bc.length) {
			for (int i = 0; i < ac.length; i++)
				base[i] = ac[i];
		} else if (ac.length == bc.length) {
			for (int i = 0; i < ac.length; i++)
				if (ac[i] - '0' > 4)
					base[i] = ac[i];
		}
		int len = bc.length;
		int i = Integer.parseInt(new String(base));
		while (i <= a) i++;
		for (;;i++) {
			if (checkMask(("" + i).toCharArray(), b, len)) {
				System.out.println(i);
				return;
			}
		}
	}
	
	public boolean checkMask(char[] c, int intb, int len) {
		int idx = 0;
		int i = 0;
		while(i < c.length) {
			if (c[i] == '4' || c[i] == '7') {
				idx *= 10;
				idx += c[i] - '0';
			}
			i++;
		}
		return idx == intb;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(br);
		int a = scan.nextInt();
		int b = scan.nextInt();
		new B104().solve(a, b);
	}
	
}
