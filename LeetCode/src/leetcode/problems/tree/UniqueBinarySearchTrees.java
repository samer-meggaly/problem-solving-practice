package leetcode.problems.tree;

import java.util.HashSet;

public class UniqueBinarySearchTrees {
	
	public static void main(String[] args) {
		System.out.println(new UniqueBinarySearchTrees().numTrees(9));
	}
	
	public int numTrees(int n) {
		return solveAttempt1(n);
	}	
	
	HashSet<String> treesPreorderStrs;
	int taken;
	int numsTaken;
	public int solveAttempt1(int n) {
		treesPreorderStrs = new HashSet<String>();
		
		for (int i = 1; i <= n; i++) {
			taken = 0;
			BSTree root = new BSTree(null, i);
			taken |= (1 << i);
			numsTaken = 1;
			traverseFromRoot(root, n);
		}
		return treesPreorderStrs.size();
	}
	
	public void traverseFromRoot(BSTree root, int n) {
		if (numsTaken == n) {
			String preorder = root.toPreOrderTraversalString();
			if (!treesPreorderStrs.contains(preorder))
				treesPreorderStrs.add(preorder);
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if ((taken & (1 << i)) == 0) {
				taken |= (1 << i);
				numsTaken++;
				root.insertNodeWithValue(i);
				traverseFromRoot(root, n);
				root.removeSubtreeRootedWithValue(i);
				numsTaken--;
				taken ^= (1 << i);
			}
		}
	}

	class BSTree {
		int val;
		BSTree parentNode;
		BSTree leftSubTree;
		BSTree rightSubTree;
		
		public BSTree(BSTree parentNode, int val) {
			this.parentNode = parentNode;
			this.val = val;
		}
		
		public String toPreOrderTraversalString() {
			return this.val
					+ ((leftSubTree != null) ? leftSubTree
							.toPreOrderTraversalString() : "")
					+ ((rightSubTree != null) ? rightSubTree
							.toPreOrderTraversalString() : "");
		}
		
		public void insertNodeWithValue(int v) {
			if (v <= val) {
				if (leftSubTree != null)
					leftSubTree.insertNodeWithValue(v);
				else
					leftSubTree = new BSTree(this, v);
			} else {
				if (rightSubTree != null)
					rightSubTree.insertNodeWithValue(v);
				else
					rightSubTree = new BSTree(this, v);
			}
		}
		
		public BSTree removeSubtreeRootedWithValue(int v) {
			if (val == v) {
				return null;
			} else {
				if (v < val) {
					leftSubTree = leftSubTree.removeSubtreeRootedWithValue(v);
				} else {
					rightSubTree = rightSubTree.removeSubtreeRootedWithValue(v);
				}
				return this;
			}
		}
	}
}
