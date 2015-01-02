package world2011.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class TheKillerWord {

	
	public static String pickWord(String[] words, char[] charList) {

		int wordsLeft = words.length;
		HashSet<Integer> set = new HashSet<Integer>(wordsLeft);
		String returnStr = "";
		for (char c : charList) {
			ArrayList<Integer> tempSet = new ArrayList<Integer>();

			for (int w = 0; w < words.length; w++) {
				if (!set.contains(w)) {
					if (words[w].contains("" + c)) {
						String[] parts = words[w].split("" + c);
						String pattern = "#";
						int ctr = 0;
						for (String s : parts) {
							ctr += s.length();
							pattern = pattern + ctr + "#";
							ctr++;
						}
						HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
						map.put(pattern, new ArrayList<Integer>());
						map.get(pattern).add(w);
						
						w++;
						for (; w < words.length; w++) {
							if (!set.contains(w) && words[w].contains("" + c)) {
								parts = words[w].split("" + c);
								pattern = "#";
								ctr = 0;
								for (String s : parts) {
									ctr += s.length();
									pattern = pattern + ctr + "#";
									ctr++;
								}
								if (map.containsKey(pattern))
									map.get(pattern).add(w);
								else {
									map.put(pattern, new ArrayList<Integer>());
									map.get(pattern).add(w);
								}
							}
						}
						
						// now remove weak words
						Iterator<String> keySet = map.keySet().iterator();
						while (keySet.hasNext()) {
							ArrayList<Integer> al = map.get(keySet.next()); 
							if (al.size() == 1) {
								for (Integer integ : al)
									tempSet.add(integ);
							}
						}
					}
				}
			}

			wordsLeft -= tempSet.size();
			if (wordsLeft == 0) {
				int min = Integer.MAX_VALUE;
				for (Integer integ : tempSet)
					min = Math.min(min, integ);
				returnStr = words[min];
				break;
			} else {
				set.addAll(tempSet);
			}
			
		}
		
		if (returnStr.equals("")) {
			int idx = 0;
			for (; idx < words.length; idx++)
				if (!set.contains(idx))
					break;
			returnStr = words[idx];
		}
		
		return returnStr;
	}
	
	public static String solve(String[] dict, String[] lists) {
		int maxLenWords = 0;
		int manLex = Integer.MAX_VALUE;
		HashMap<Integer, ArrayList<String>> hashing = new HashMap<Integer, ArrayList<String>>();
		for (int i = 0; i < dict.length; i++) {
			if (hashing.containsKey(dict[i].length())) {
				hashing.get(dict[i].length()).add(dict[i]);
				int wordsOfLen = hashing.get(dict[i].length()).size();
				if (wordsOfLen > maxLenWords) {
					maxLenWords = wordsOfLen;
					manLex = dict[i].length();
				}
			} else {
				hashing.put(dict[i].length(), new ArrayList<String>());
				hashing.get(dict[i].length()).add(dict[i]);
			}
		}
		
		String out = "";
		if (maxLenWords == 0) {
			for (int j = 0; j < lists.length; j++)
				out = out + dict[0] + " ";
		} else {
			String[] newDict = new String[maxLenWords];
			ArrayList<String> temp = hashing.get(manLex);
			for (int j = 0; j < maxLenWords; j++)
				newDict[j] = temp.get(j);
			for (String l : lists) {
				String t = pickWord(newDict, l.toCharArray());
				out = out + t + " ";
			}
		}
		
		return out.trim();
	}
	
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2011/1B/B-small-practice.in";
//		String file = "input.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0; i < T; i++) {
			String[] NM = scanner.nextLine().split("\\s");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			String[] dictionary = new String[N];
			for (int n = 0; n < N; n++)
				dictionary[n] = scanner.nextLine();
			String[] lists = new String[M];
			for (int m = 0; m < M; m++)
				lists[m] = scanner.nextLine();
			
			String output = solve(dictionary, lists);
			out.println("Case #"+(i+1)+": "+output+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}
}
