package leetcode.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicates {
	/*
	 * https://leetcode.com/problems/contains-duplicate/
	 * https://leetcode.com/problems/contains-duplicate-ii/
	 */

	public boolean containsDuplicate(int[] nums) {
		Set<Integer> unique = new HashSet<Integer>();
		boolean distinct = true;
		for (int i = 0; i < nums.length && distinct; i++) {
			if (unique.contains(nums[i]))
				distinct = false;
			else
				unique.add(nums[i]);
		}

		return !distinct;
	}

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> uniqueIdx = new HashMap<Integer, Integer>();
		boolean duplicateWithinK = false;
		for (int i = 0; i < nums.length && !duplicateWithinK; i++) {
			if (uniqueIdx.containsKey(nums[i])) {
				duplicateWithinK = i - uniqueIdx.get(nums[i]) <= k;
			}
			uniqueIdx.put(nums[i], i);
		}

		return duplicateWithinK;
	}

	// TLE on LeetCode.
	public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
		boolean nearDuplicateNearby = false;
		for (int i = 0; i < nums.length && !nearDuplicateNearby; i++) {
			for (int j = i + 1; j < nums.length && j <= i + k
					&& !nearDuplicateNearby; j++) {
				nearDuplicateNearby = Math.abs(nums[i] - nums[j]) <= t;
			}
		}

		return nearDuplicateNearby;
	}

	public static void main(String[] args) {

	}
}
