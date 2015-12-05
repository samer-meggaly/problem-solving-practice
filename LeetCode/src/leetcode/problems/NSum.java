package leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NSum {

	/*
	 * https://leetcode.com/problems/two-sum/
	 * https://leetcode.com/problems/3sum/
	 * https://leetcode.com/problems/4sum/
	 */

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> diffIdxMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (diffIdxMap.containsKey(target - nums[i]))
				// 1-based indices
				return new int[] { diffIdxMap.get(target - nums[i]) + 1,
						i + 1 };
			else
				diffIdxMap.put(nums[i], i);
		}
		return null;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		// This solution passes on LeetCode. The optimization that I needed was
		// to loop over unique instead of nums (with duplicates).
		Map<Integer, Integer> unique = new HashMap<Integer, Integer>(
				nums.length);
		for (int i = 0; i < nums.length; i++) {
			unique.put(nums[i], unique.getOrDefault(nums[i], 0) + 1);
		}

		Set<List<Integer>> results = new HashSet<List<Integer>>();
		Integer[] uniqueNums = unique.keySet().toArray(new Integer[0]);
		for (int i = 0; i < uniqueNums.length; i++) {
			for (int j = i + 1; j < uniqueNums.length; j++) {
				int diff = -(uniqueNums[i] + uniqueNums[j]);
				int limit = 0;
				if (diff == uniqueNums[i])
					limit++;
				if (diff == uniqueNums[j])
					limit++;
				if (unique.getOrDefault(diff, -1) > limit) {
					Integer[] tuple;
					int min = Math.min(uniqueNums[i], uniqueNums[j]);
					int max = Math.max(uniqueNums[i], uniqueNums[j]);
					if (diff <= min) {
						tuple = new Integer[] { diff, min, max };
					} else if (diff >= max) {
						tuple = new Integer[] { min, max, diff };
					} else {
						tuple = new Integer[] { min, diff, max };
					}
					results.add(Arrays.asList(tuple));
				}
			}
		}
		if (unique.getOrDefault(0, -1) > 2)
			results.add(Arrays.asList(new Integer[] { 0, 0, 0 }));
		return new ArrayList<List<Integer>>(results);
	}

	public List<List<Integer>> fourSum1(int[] nums, int target) {
		// TLE on LeetCode
		Map<Integer, Integer> unique = new HashMap<Integer, Integer>(
				nums.length);
		for (int i = 0; i < nums.length; i++) {
			unique.put(nums[i], unique.getOrDefault(nums[i], 0) + 1);
		}

		Set<List<Integer>> results = new HashSet<List<Integer>>();
		Integer[] uniqueNums = unique.keySet().toArray(new Integer[0]);
		for (int i = 0; i < uniqueNums.length; i++) {
			for (int j = i + 1; j < uniqueNums.length; j++) {
				for (int k = j + 1; k < uniqueNums.length; k++) {
					int diff = target
							- (uniqueNums[i] + uniqueNums[j] + uniqueNums[k]);
					int limit = 0;
					if (diff == uniqueNums[i])
						limit++;
					if (diff == uniqueNums[j])
						limit++;
					if (diff == uniqueNums[k])
						limit++;
					if (unique.getOrDefault(diff, -1) > limit) {
						Integer[] tuple = new Integer[] { diff, uniqueNums[i],
								uniqueNums[j], uniqueNums[k] };
						Arrays.sort(tuple);
						results.add(Arrays.asList(tuple));
					}
				}
			}
		}
		if (unique.getOrDefault(0, -1) > 3)
			results.add(Arrays.asList(new Integer[] { 0, 0, 0, 0 }));
		return new ArrayList<List<Integer>>(results);
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);

		List<List<Integer>> results = new ArrayList<List<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1; j < nums.length; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1])
					continue;
				int diff = target - (nums[i] + nums[j]);
				int left = j + 1;
				int right = nums.length - 1;
				while (left < right) {
					if (diff == nums[left] + nums[right]) {
						Integer[] quad = new Integer[] { nums[i], nums[j],
								nums[left], nums[right] };
						results.add(Arrays.asList(quad));
						while (left < right && nums[left] == nums[left + 1])
							left++;
						while (left < right && nums[right] == nums[right - 1])
							right--;
						left++;
						right--;
					} else if (diff < nums[left] + nums[right]) {
						right--;
					} else {
						left++;
					}
				}
			}
		}

		return results;
	}

	public static void main(String[] args) {
		System.out.println("ThreeSum");
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		System.out.println(new NSum().threeSum(nums));
		nums = new int[] { 0, 0, 0, 0 };
		System.out.println(new NSum().threeSum(nums));
		long start = System.nanoTime();
		nums = new int[] { 4, -9, -13, -9, 0, -12, 12, -14, 12, 1, 3, 5, 5, 8,
				2, -2, 8, 1, 2, -6, -6, 1, 6, -15, -2, 7, -11, 3, -2, 1, 11, 10,
				8, 14, 8, -15, 9, 5, -14, 6, 14, -3, -12, 4, -10, 5, -12, 13,
				14, -3, -15, -7, 5, -2, -15, 10, -10, 11, -2, -5, -2, -5, -10,
				13, -14, 14, 13, 2, 4, 7, -6, 3, 11, -3, -15, -14, 10, 10, 6, 1,
				-8, -2, 1, 12, 11, 1, 7, 8, -10, 13, -11, 3, -15, -6, -7, 8, -7,
				13, -5, 5, -3, 4, -15, -7, 9, -15, -14, -4, 2, 0, 4, 9, 13, -10,
				-2, 10 };
		System.out.println(new NSum().threeSum(nums).size() + " triplets"); // 104
		System.out.println((System.nanoTime() - start) / 1000000 + " ms");

		System.out.println("FourSum");
		nums = new int[] { 1, 0, -1, 0, -2, 2 };
		System.out.println(new NSum().fourSum(nums, 0));
		start = System.nanoTime();
		nums = new int[] { 4, -9, -13, -9, 0, -12, 12, -14, 12, 1, 3, 5, 5, 8,
				2, -2, 8, 1, 2, -6, -6, 1, 6, -15, -2, 7, -11, 3, -2, 1, 11, 10,
				8, 14, 8, -15, 9, 5, -14, 6, 14, -3, -12, 4, -10, 5, -12, 13,
				14, -3, -15, -7, 5, -2, -15, 10, -10, 11, -2, -5, -2, -5, -10,
				13, -14, 14, 13, 2, 4, 7, -6, 3, 11, -3, -15, -14, 10, 10, 6, 1,
				-8, -2, 1, 12, 11, 1, 7, 8, -10, 13, -11, 3, -15, -6, -7, 8, -7,
				13, -5, 5, -3, 4, -15, -7, 9, -15, -14, -4, 2, 0, 4, 9, 13, -10,
				-2, 10 };
		System.out.println(new NSum().fourSum(nums, 0).size() + " quadruplets");
		System.out.println((System.nanoTime() - start) / 1000000 + " ms");
		start = System.nanoTime();
		nums = new int[] { -494, -487, -471, -470, -465, -462, -447, -445, -441,
				-432, -429, -422, -406, -398, -397, -364, -344, -333, -328,
				-307, -302, -293, -291, -279, -269, -269, -268, -254, -198,
				-181, -134, -127, -115, -112, -96, -94, -89, -58, -58, -58, -44,
				-2, -1, 43, 89, 92, 100, 101, 106, 106, 110, 116, 143, 156, 168,
				173, 192, 231, 248, 256, 281, 316, 321, 327, 346, 352, 353, 355,
				358, 365, 371, 410, 413, 414, 447, 473, 473, 475, 476, 481, 491,
				498 };
		System.out.println(new NSum().fourSum(nums, 8511));
		System.out.println((System.nanoTime() - start) / 1000000 + " ms");
		nums = new int[] { -3, -2, -1, 0, 0, 1, 2, 3 };
		System.out.println(new NSum().fourSum(nums, 0));
		// [[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
	}
}
