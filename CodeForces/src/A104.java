import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class A104 {

	public void checkTicket(char[] ticket) {
		int limit = ticket.length / 2;
		
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < limit; i++) {
			if ((ticket[i] == '4' || ticket[i] == '7')
					&& (ticket[i + limit] == '4' || ticket[i + limit] == '7')) {
				sum1 += ticket[i] - '0';
				sum2 += ticket[i + limit] - '0';
			} else {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println((sum1 == sum2)? "YES":"NO");
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		String in = br.readLine().trim();
		new A104().checkTicket(in.toCharArray());
	}
}
