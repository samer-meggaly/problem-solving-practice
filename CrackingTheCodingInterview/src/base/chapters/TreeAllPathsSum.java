package base.chapters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import base.TreeNode;

public class TreeAllPathsSum {

	private static class Path {
		LinkedList<TreeNode> path = new LinkedList<TreeNode>();
		int sum = 0;

		@Override
		public String toString() {
			List<Integer> vals = new ArrayList<Integer>(path.size());
			for (TreeNode tn : path)
				vals.add(tn.val);
			return vals.toString();
		}

		public void extend(TreeNode root) {
			path.addFirst(root);
			sum += root.val;
		}
	}

	// lousy implementation.... O(N^2 lgN) ??
	public static List<Path> allPathsFromRoot(TreeNode root, int targetSum) {
		if (root == null)
			return new ArrayList<Path>();
		List<Path> pathsRootedRoot = new ArrayList<Path>();
		Path rootPath = new Path();
		rootPath.extend(root);
		if (rootPath.sum == targetSum)
			System.out.println(rootPath);
		pathsRootedRoot.add(rootPath);

		List<Path> pathsRootedLeft = allPathsFromRoot(root.left, targetSum);
		for (Path pl : pathsRootedLeft) {
			pl.extend(root);
			if (pl.sum == targetSum)
				System.out.println(pl);
			pathsRootedRoot.add(pl);
		}
		List<Path> pathsRootedRight = allPathsFromRoot(root.right, targetSum);
		for (Path pr : pathsRootedRight) {
			pr.extend(root);
			if (pr.sum == targetSum)
				System.out.println(pr);
			pathsRootedRoot.add(pr);
		}
		return pathsRootedRoot;
	}

	// better ... O(N lgN)
	public static void allPathsSum(TreeNode root, List<Integer> pathToRoot,
			int target) {
		
		if (root == null)
			return;
		pathToRoot.add(root.val);
		int sumRootUpwards = 0;
		LinkedList<Integer> toPrint = new LinkedList<Integer>();
		for (int i = pathToRoot.size() - 1; i >= 0; i--) {
			sumRootUpwards += pathToRoot.get(i);
			toPrint.addFirst(pathToRoot.get(i));
			if (sumRootUpwards == target) {
				System.out.println(toPrint);
			}
		}
		allPathsSum(root.left, pathToRoot, target);
		allPathsSum(root.right, pathToRoot, target);
		pathToRoot.remove(pathToRoot.size() - 1);
	}

	public static void main(String[] args) {
		TreeNode nodelll = new TreeNode(4);
		TreeNode nodellr = new TreeNode(4);
		TreeNode nodelrl = new TreeNode(4);
		TreeNode nodelrr = new TreeNode(4);
		TreeNode noderll = new TreeNode(4);
		TreeNode noderlr = new TreeNode(4);
		TreeNode noderrl = new TreeNode(4);
		TreeNode noderrr = new TreeNode(4);

		TreeNode nodell = new TreeNode(3, nodelll, nodellr);
		TreeNode nodelr = new TreeNode(3, nodelrl, nodelrr);
		TreeNode noderl = new TreeNode(3, noderll, noderlr);
		TreeNode noderr = new TreeNode(3, noderrl, noderrr);

		TreeNode nodel = new TreeNode(2, nodell, nodelr);
		TreeNode noder = new TreeNode(2, noderl, noderr);

		TreeNode node = new TreeNode(1, nodel, noder);
		int target = 3;
		allPathsFromRoot(node, target);
		System.out.println("===================================");
		allPathsSum(node, new ArrayList<Integer>(), target);
	}
}
