import java.math.BigInteger;
import java.util.Scanner;

public class AorB {
    
    private static String bytesToHex(byte[] bytes) {
        int j = 0;
        while (j < bytes.length && bytes[j] == 0) j++;
        StringBuilder sb = new StringBuilder();
        if (j < bytes.length)
        	sb.append(String.format("%X", bytes[j++]));
        for (; j < bytes.length; j++)
        	sb.append(String.format("%02X", bytes[j]));
        if (sb.length() == 0)
        	return "0";
        return sb.toString();
    }
    
    private static byte[] resize(byte[] arr, int size) {
        byte[] newArr = new byte[size];
        int offset = size - arr.length;
        for (int i = 0; i < arr.length; i++)
            newArr[offset + i] = arr[i];
        return newArr;
    }
    
    private static void aorb(int K, BigInteger A, BigInteger B, BigInteger C) {
        byte[] AA = A.toByteArray();
        byte[] BB = B.toByteArray();
        byte[] CC = C.toByteArray();
        int maxLen = Math.max(AA.length, Math.max(BB.length, CC.length));
        AA = resize(AA, maxLen);
        BB = resize(BB, maxLen);
        CC = resize(CC, maxLen);
        for (int i = 0; i < maxLen && K > -1; i++) {
            for (int s = 7; s > -1 && K > -1; s--) {
                byte bit = (byte) (1 << s);
                if ((CC[i] & bit) == 0) {
                    if ((AA[i] & bit) != 0) {
                        AA[i] ^= bit;
                        K--;
                    }
                    if ((BB[i] & bit) != 0) {
                        BB[i] ^= bit;
                        K--;
                    }
                } else {
                    if ((AA[i] & bit) == 0 && (BB[i] & bit) == 0) {
                        BB[i] ^= bit;
                        K--;
                    }
                }
            }
        }
        
        if (K < 0) {
            System.out.println(-1);
            return;
        }
        
        for (int i = 0; i < maxLen && K > 0; i++) {
            for (int s = 7; s > -1 && K > 0; s--) {
                byte bit = (byte) (1 << s);
                if ((CC[i] & bit) != 0) {
                    if ((AA[i] & bit) != 0 && (BB[i] & bit) != 0) {
                        AA[i] ^= bit;
                        K--;
                    } else if ((AA[i] & bit) != 0 && (BB[i] & bit) == 0 && K > 1) {
                        AA[i] ^= bit;
                        BB[i] ^= bit;
                        K -= 2;
                    }
                }
            }
        }
        
        System.out.println(bytesToHex(AA));
        System.out.println(bytesToHex(BB));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int t = 0; t < T; t++) {
            int K = scanner.nextInt();
            BigInteger A = scanner.nextBigInteger(16);
            BigInteger B = scanner.nextBigInteger(16);
            BigInteger C = scanner.nextBigInteger(16);
            aorb(K, A, B, C);
        }
        scanner.close();
    }
}