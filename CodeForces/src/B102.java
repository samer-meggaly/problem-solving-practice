import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B102 {

	public void solve(String sign, String integer, String fractional) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(((sign.equals("-")) ? "($" : "$"));

		int len = integer.length();
		int part = len % 3;
		buffer.append(integer.substring(0, part));
		for (int i = part; i < len; i += 3) {
			buffer.append(((part == 0 && i == 0) ? "" : ","));
			String str = integer.substring(i, i + 3);
			buffer.append(str);
		}
		buffer.append(".");

		int fractPart = fractional.length();
		if (fractPart == 0)
			buffer.append("00");
		else if (fractPart == 1)
			buffer.append(fractional + "0");
		else
			buffer.append(fractional.substring(0, 2));

		buffer.append(((sign.equals("-")) ? ")" : ""));

		System.out.println(buffer);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine().trim();
		Pattern pattern = Pattern.compile("(-?)(\\d*)(\\.(\\d+))?");
		Matcher matcher = pattern.matcher(input);
		matcher.matches();
		String s1 = matcher.group(1);
		String s2 = matcher.group(2);
		String s3 = matcher.group(4);

		new B102().solve(s1, s2, ((s3 == null) ? "" : s3));
	}
}
