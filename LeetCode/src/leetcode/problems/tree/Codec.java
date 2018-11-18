package leetcode.problems.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import leetcode.utils.LeetPrinter;

public class Codec {

	/*
	 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
	 * 
	 * The Following solution is designed particularly for the use with
	 * serialization strings that LeetCode itself uses. For example, no trailing
	 * null(s), null-root results in EMPTY_STRING ... etc.
	 */

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node != null) {
				sb.append(node.val);
				sb.append(',');
				queue.offer(node.left);
				queue.offer(node.right);
			} else {
				sb.append("null,");
			}
		}

		// remove any trailing nulls (null,)
		Set<Character> termChars = new HashSet<Character>(
				Arrays.asList(new Character[] { ',', 'n', 'u', 'l' }));
		int lastIdx = sb.length() - 1;
		while (lastIdx > -1 && termChars.contains(sb.charAt(lastIdx)))
			lastIdx--;
		sb.delete(lastIdx + 1, sb.length());

		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		data = data.trim();
		if (data.isEmpty())
			return null;
		String[] nodes = data.split(",");
		Integer[] nodesCasted = new Integer[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			if (!nodes[i].trim().equalsIgnoreCase("null"))
				nodesCasted[i] = Integer.parseInt(nodes[i].trim());
		}

		return deserialize(nodesCasted);
	}

	public TreeNode deserialize(Integer[] data) {
		if (data.length == 0)
			return null;
		List<TreeNode> rootLevel = takeNStartingAtI(data, 1, 0);
		return rootLevel.get(0);
	}

	private List<TreeNode> takeNStartingAtI(Integer[] allNodes, int N, int I) {
		List<TreeNode> taken = new ArrayList<TreeNode>();
		int countNotNull = 0;
		for (int i = I; i < Math.min(I + N, allNodes.length); i++) {
			if (allNodes[i] == null) {
				taken.add(null);
			} else {
				taken.add(new TreeNode(allNodes[i]));
				countNotNull++;
			}
		}

		/*
		 * The following condition should be true for all levels except the
		 * bottom level in the tree.
		 */
		if (I + N < allNodes.length) {
			List<TreeNode> takenNextLevel = takeNStartingAtI(allNodes,
					2 * countNotNull, I + taken.size());
			int nextLevelIdx = 0;
			for (TreeNode tn : taken) {
				if (tn != null) {
					if (nextLevelIdx >= takenNextLevel.size())
						break;
					tn.left = takenNextLevel.get(nextLevelIdx++);
					if (nextLevelIdx >= takenNextLevel.size())
						break;
					tn.right = takenNextLevel.get(nextLevelIdx++);
				}
			}
		}

		return taken;
	}

	public static void main(String[] args) {
		Map<String, TreeNode> serlDeserl = new HashMap<String, TreeNode>();

		serlDeserl.put("", null);
		serlDeserl.put("1", new TreeNode(1));
		serlDeserl.put("1,2,3",
				new TreeNode(1, new TreeNode(2), new TreeNode(3)));
		serlDeserl.put("1,null,2,3",
				new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null)));
		serlDeserl.put("5,4,7,3,null,2,null,-1,null,9",
				new TreeNode(5, new TreeNode(4,
						new TreeNode(3, new TreeNode(-1), null), null),
				new TreeNode(7, new TreeNode(2, new TreeNode(9), null), null)));
		serlDeserl.put("3,9,20,null,null,15,17",
				new TreeNode(3, new TreeNode(9),
						new TreeNode(20, new TreeNode(15), new TreeNode(17))));
		serlDeserl.put("3,9,20,1,null,15,17,null,13,100,101,102,103",
				new TreeNode(3,
						new TreeNode(9, new TreeNode(1, null, new TreeNode(13)),
								null),
						new TreeNode(20,
								new TreeNode(15, new TreeNode(100),
										new TreeNode(101)),
								new TreeNode(17, new TreeNode(102),
										new TreeNode(103)))));
		serlDeserl.put("3,9,20,1,null,15,17,null,null,null,null,102,103",
				new TreeNode(3, new TreeNode(9, new TreeNode(1), null),
						new TreeNode(20, new TreeNode(15), new TreeNode(17,
								new TreeNode(102), new TreeNode(103)))));
		serlDeserl.put("3,9,20,1,null,null,17,null,null,102,103",
				new TreeNode(3, new TreeNode(9, new TreeNode(1), null),
						new TreeNode(20, null, new TreeNode(17,
								new TreeNode(102), new TreeNode(103)))));
		serlDeserl.put("1,2,3,4,5,null,7,null,null,10,11",
				new TreeNode(1,
						new TreeNode(2, new TreeNode(4),
								new TreeNode(5, new TreeNode(10),
										new TreeNode(11))),
				new TreeNode(3, null, new TreeNode(7))));

		System.out.println("Testing Serialization...");
		int c = 1;
		for (String expected : serlDeserl.keySet()) {
			LeetPrinter.assertPrint(expected,
					new Codec().serialize(serlDeserl.get(expected)),
					"Case #" + c + "\nExpected: " + expected + "\nFound   : ");
			c++;
		}
		System.out.println("Done Successfully");
		System.out.println("Testing Deserialization...");
		for (String expected : serlDeserl.keySet()) {
			LeetPrinter.assertPrint(serlDeserl.get(expected),
					new Codec().deserialize(expected),
					"Failed on case " + expected + " found >>> ");
			c++;
		}

		System.out.println("Done Successfully");
	}
}
