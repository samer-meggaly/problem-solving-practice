package leetcode.problems.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import leetcode.utils.LeetPrinter;

public class AlienDictionary {

	public char[] getAlphabet(String[] dictionary) {
		graph = new HashMap<Character, Set<Character>>();
		buildGraph(Arrays.asList(dictionary));
		List<Character> alphabet = findTopologicalOrder();
		char[] alphabetChars = new char[alphabet.size()];
		for (int i = 0; i < alphabetChars.length; i++)
			alphabetChars[i] = alphabet.get(i);
		return alphabetChars;
	}

	private Map<Character, Set<Character>> graph;

	private void buildGraph(List<String> orderedWords) {
		if (orderedWords.isEmpty())
			return;
		char prevChar = orderedWords.get(0).charAt(0);
		List<String> orderedSubWords = new ArrayList<String>();

		for (int i = 0; i < orderedWords.size(); i++) {
			String currWord = orderedWords.get(i);
			char currChar = currWord.charAt(0);
			if (!graph.containsKey(prevChar))
				graph.put(prevChar, new HashSet<Character>());
			if (currChar == prevChar) {
				String subWord = currWord.substring(1, currWord.length());
				if (!subWord.isEmpty())
					orderedSubWords.add(subWord);
			} else {
				buildGraph(orderedSubWords);
				if (!graph.containsKey(currChar))
					graph.put(currChar, new HashSet<Character>());
				graph.get(currChar).add(prevChar);
				prevChar = currChar;
				orderedSubWords = new ArrayList<String>();
				i--;
			}
		}

		buildGraph(orderedSubWords);
	}

	private Map<Character, Integer> visited;
	private List<Character> topologicalOrder;

	private List<Character> findTopologicalOrder() {
		visited = new HashMap<Character, Integer>();
		topologicalOrder = new ArrayList<Character>();
		Set<Character> chars = graph.keySet();
		for (Character c : chars)
			visited.put(c, 0);
		for (Character c : chars) {
			if (visited.get(c) == 0) {
				if (!dfs(c))
					return new ArrayList<Character>();
			}
		}

		return topologicalOrder;
	}

	private boolean dfs(Character s) {
		visited.put(s, 1);
		for (Character sAdj : graph.get(s)) {
			if (visited.get(sAdj) == 0) {
				if (!dfs(sAdj))
					return false;
			} else if (visited.get(sAdj) == 1) {
				return false;
			}
		}
		visited.put(s, 2);
		topologicalOrder.add(s);
		return true;
	}

	public static void main(String[] args) {
		String[] words;
		words = new String[] { "abcd" };
		System.out.println( // {a, b, c, d}
				LeetPrinter.str(new AlienDictionary().getAlphabet(words)));
		words = new String[] { "baa", "abcd", "abca", "cab", "cad" };
		System.out.println( // [b, d, a, c] OR [b, a, d, c] OR [b, a, c, d]
				LeetPrinter.str(new AlienDictionary().getAlphabet(words)));
		words = new String[] { "caa", "aca", "aaa", "aab" };
		System.out.println( // [c, a, b]
				LeetPrinter.str(new AlienDictionary().getAlphabet(words)));
	}

}
