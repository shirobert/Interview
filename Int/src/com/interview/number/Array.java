package com.interview.number;
import java.util.*;

public class Array {
/**
 * DeepClone of array	
 * @param original
 * @return
 */
	  public static int[][] deepClone(int[][] original) {
		    int[][] backup = new int[original.length][];  // create top-level array of arrays
		    for (int k=0; k < original.length; k++)
		      backup[k] = original[k].clone();            // copy row k
		    return backup;
		  }
	  
	 /**
	  *  Standard Binary Search 
	  */
	  
	  public boolean binarySearch (int [] data, int target, int low, int high){
		  if(low > high) return false;		
		  
		  int mid = low + (high-low)/2;
		  if (target == data[mid]){
			  return true;
		  }else if(target > data[mid]){
			  return binarySearch(data, target, mid+1, high);
		  }else{
			  return binarySearch(data, target, low, mid-1);
		  }
		  
		  // condition, if it is (low <= high), it is OK to use mid+1, mid-1.
		  // if it is low < high, then just low = mid+1, high = mid;
	  }

	  
/** No. 162
 * find peak element in array	  
 * A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
	  
	    public int findPeakElement(int[] nums, int low, int high){
	        while(low <= high){
	            int mid = low + (high-low)/2;
	            
	            if(mid+1 <= nums.length-1 && nums[mid] < nums[mid+1]){
	                low = mid+1;
	            }else if (mid -1 >= 0 && nums[mid] < nums[mid-1] ){
	                high = mid-1;
	            }else {
	                return mid;
	            }
	            
	        }
	        return -1;
	        
	    }
	  
	/**
	 *  Search roated sorted array
	 *  
	 *  Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

Solution

Time ~ O(logN), Space ~ O(1) 
注意是 sorted array，所以：
1. 如果 num[lo] 不大于 num[hi]（循环附加条件），即 array 没有被 rotate，可停止循环，返回 num[lo] 为 min。
2. 剔除了 unrotated array 后，有两种情况：
num[mid] > num[lo], num[mid] > num[hi]：mid 和 lo 在一侧，则移动 lo；
num[mid] < num[lo], num[mid] < num[hi]：mid 和 hi 在一侧，则移动 hi。
	 *  
	 *  Find min is different from search some item in a array, use condition (lo < hi) is better than (lo<= hi) and 
	 *  update hi = mid and low = hi+1 accordingly.
	 *
	 *  think about how to determine where min is located, which half. and then how to update, should it be lo = mid+1 or mid...
	 * @param
	 * @return
	 */
	
	public int findMinRoatedArray(int[] num) {

		int lo = 0, hi = num.length - 1;
		while (lo < hi && num[lo] > num[hi]) {
			int mid = (lo + hi) / 2;
			if (num[mid] > num[hi]) lo = mid + 1;
			else                    hi = mid;
		}
		return num[lo];
   
	}

	
	/*
	 * Find Minimum in Rotated Sorted Array II: contains duplicates
	 * 
	 * 1. 如果 num[lo] 小于 num[hi]（循环附加条件），可停止循环，num[lo] 即为 min。
2. 若 num[lo] >= num[hi]，当没有 duplicates 只有两种情况：
num[mid] > num[lo], num[mid] > num[hi]：mid 和 lo 在一侧，则移动 lo；
num[mid] < num[lo], num[mid] < num[hi]：mid 和 hi 在一侧，则移动 hi；
当存在 duplicates 时会增加另一种情况：
不论 mid 在哪一侧：num[mid] == num[lo] or num[mid] == num[hi]，则将 hi 后移一位（也可将 lo 前移一位）。
	 */
	
	public int findMinRoatedArrayWithDuplicates(int[] num) {
	    int lo = 0, hi = num.length - 1;
	    while (lo < hi && num[lo] >= num[hi]) {
	        int mid = (lo + hi) / 2;
	        if (num[mid] > num[hi])         lo = mid + 1;
	        else if (num[mid] < num[hi])    hi = mid;
	        else                            hi--;
	    }
	    return num[lo];
	}
	
	
	
	/**
	 * Search in Rotated Sorted Array I: no duplicates

Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.

Solution

Binary Search: Time ~ O(logN), Space ~ O(1) 
	 * @param
	 * @return
	 */
	public int search(int[] A, int target) {
	    int lo = 0, hi = A.length - 1;
	    while (lo <= hi) {
	        int mid = (lo + hi) / 2;
	        if (A[mid] == target) return mid;
	        if (A[mid] < A[hi]) {   // [mid..hi] is inorder (Or using A[mid] >= A[lo])
	            if (A[mid] < target && A[hi] >= target) lo = mid + 1; // target is in [mid..hi]
	            else                                    hi = mid-1;
	        } else {                // [lo..mid] is inorder
	            if (A[lo] <= target && A[mid] > target) hi = mid-1;   // target is in [lo..mid]
	            else                                    lo = mid + 1;
	        }
	    }
	    return -1;
	}
	
	
	/**
	 * Search in Rotated Sorted Array II: contains duplicates

Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?
Write a function to determine if a given target is in the array.

Solution

Binary Search: Time ~ O(logN) worst case ~ O(N), Space ~ O(1) 
Slight modifications:
除上述的两种情况：
num[mid] < num[hi]，若 target 在 num[mid .. hi] 中，则移动 lo，否则移动 hi；
num[mid] > num[hi]，若 target 在 num[lo .. mid] 中，则移动 hi，否则移动 lo；
再增加另一种情况：
num[mid] == num[hi]，将 hi 后移一位（不能将 hi = mid，因为会漏掉 mid 到 hi 中大的元素，比如 [1, 1, 3, 1], target = 3）。
	 * @param
	 * @return
	 */

	public boolean search2(int[] A, int target) {
	    int lo = 0, hi = A.length - 1;
	    while (lo <= hi) {
	        int mid = (lo + hi) / 2;
	        if (A[mid] == target)   return true;
	        if (A[mid] < A[hi]) {   // [mid..hi] is inorder
	            if (A[mid] < target && A[hi] >= target) lo = mid + 1;
	            else                                    hi = mid-1;
	        } else if (A[mid] > A[hi]) {    // [lo..mid] is inorder
	            if (A[lo] <= target && A[mid] > target) hi = mid-1;
	            else                                    lo = mid + 1;
	        } else /* if (A[mid] == A[hi]) */ { // add this part for the duplicated case
	            hi--;
	        }
	    }
	    return false;
	}	
	
	
	
	private List<List<Integer>> listSet = new ArrayList<List<Integer>>();
	private List<Integer> list = new ArrayList<Integer>();

	// subsetsWithoutDup
	
	public List<List<Integer>> subsets(int[] S) {
	    Arrays.sort(S);
	    addUp2(S, 0);
	    return listSet;
	}

	private void addUp2(int[] S, int start) {
	    // if (!listSet.contains(list))         // No need to check duplicate lists!!
	    listSet.add(new ArrayList<Integer>(list));
	    for (int i = start; i < S.length; i++) {
	        list.add(S[i]);
	        addUp2(S, i + 1);
	        list.remove(list.size() - 1);
	    }
	}
	
// subsetsWithDup

	public List<List<Integer>> subsetsWithDup(int[] S) {
	    Arrays.sort(S);
	    addUp(S, 0);
	    return listSet;
	}

	private void addUp(int[] S, int start) {
	    // if (!listSet.contains(list))         // No need to check duplicate lists!! 
	        listSet.add(new ArrayList<Integer>(list));
	    for (int i = start; i < S.length; i++) {
	        if (i > start && S[i - 1] == S[i]) continue; // skip the duplicated elements (important pruning step to increase speed)!!
	        list.add(S[i]);
	        addUp(S, i + 1);
	        list.remove(list.size() - 1);
	    }
	}
	
	
	
// Permutation
	
	public List<List<Integer>> permute(int[] num) {
		List<List<Integer>>  listSet = new ArrayList<List<Integer>>();
	    List<Integer> list = new ArrayList<Integer>();


	    dfs(num, listSet, list);
	    return listSet;
	}

	private void dfs(int[] num, List<List<Integer>> listSet, List<Integer> list) {
	    if (list.size() == num.length) {
	        /** if (!listSet.contains(list)) **/    // No need to check duplicate lists!!    
	            listSet.add(new ArrayList<Integer>(list));
	    } else {
	        for (int i : num) {
	            if (!list.contains(i)) {
	                list.add(i);
	                dfs(num, listSet, list);
	                list.remove(list.size() - 1);
	            }
	        }
	    }
	}
	

// Permutation with duplicate
	
	public List<List<Integer>> permuteUnique(int[] num) {
		List<List<Integer>> listSet = new ArrayList<List<Integer>>();
		List <Integer>list = new ArrayList<Integer>();
	    Set <Integer> visited = new HashSet<>();
	    
	    Arrays.sort(num);   // Need to sort!!
	    dfs2(num, listSet, list, visited);
	    return listSet;
	}

	private void dfs2(int[] num, List<List<Integer>> listSet2, List<Integer> list2, Set<Integer> visited) {
	    if (list.size() == num.length) {
	        /** if (!listSet.contains(list)) **/    // No need to check duplicate lists!!    
	            listSet.add(new ArrayList<Integer>(list));
	    } else {
	        for (int i = 0; i < num.length; i++) {
	            if (i > 0 && !visited.contains(i - 1) && num[i - 1] == num[i]) continue;    // skip the duplicated elements (important pruning step to increase speed)!!
	            if (!visited.contains(i)) {
	                list.add(num[i]);
	                visited.add(i);
	                dfs2(num, listSet, list, visited);
	                list.remove(list.size() - 1);
	                visited.remove(i);
	            }
	        }
	    }
	}
	
	
	
/*
 * 77. 
 * Simple Combination	
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */
	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> set = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer> ();
        Combhelper(n, k, set, list, 1);
        return set;
    }
    
    public void Combhelper(int n, int size, List<List<Integer>> set, List<Integer> list, int start){
        if(size == 0){
            set.add(new ArrayList<Integer> (list));
            return;
        }
        for(int i = start; i<=n; i++){
            list.add(i);
            Combhelper(n, size-1, set, list, i+1);
            list.remove(list.size()-1);
        }
        
    }
	
/*
 * 39. Combination of Sum
 * 	Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
 */
	
	
	 public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        List<List<Integer>> set = new LinkedList<List<Integer>> ();
	        List<Integer> list = new LinkedList<Integer>();
	        if(candidates.length == 0) return set;
	        Arrays.sort(candidates);
	        helper(candidates, target, set, list, 0);
	        // doDFS(0, candidates, target, list, set);
	        return set;
	    }
	    
	    public void helper(int [] candidates, int target,List<List<Integer>> set,List<Integer> list, int start){
	        if(target < 0) return;
	        if(target == 0){
	            List<Integer> sol = new LinkedList<Integer>(list);
	            set.add(sol);
	            return;
	        }
	        
	        for(int i = start; i<candidates.length && candidates[i] <= target ; i++){
	            if (i > start && candidates[i] == candidates[i - 1]) continue; 
	            list.add(candidates[i]);
	            helper(candidates, target-candidates[i], set, list, i);
	            list.remove(list.size()-1);
	        }
	        
	    }

	    
/**
 * Combination 3.  
 * @param k
 * @param n
 * @return
 */
	    public List<List<Integer>> combinationSum3(int k, int n) {
	        
	        List<List<Integer>> set = new LinkedList<List<Integer>> ();
	        List<Integer> list = new LinkedList<Integer>();
	        if(k == 0 || n == 0 ) return set;
	        helper(k, n, set, list, 1);
	        // doDFS(0, candidates, target, list, set);
	        return set;
	    }
	    
	    public void helper(int k, int target,List<List<Integer>> set,List<Integer> list, int start){
	        if(target < 0 || k<0) return;
	        if(target == 0 && k == 0){
	            List<Integer> sol = new LinkedList<Integer>(list);
	            set.add(sol);
	            return;
	        }
	        
	        for(int i = start; i< 10 ; i++){
	            list.add(i);
	            helper(k-1, target-i, set, list, i+1);
	            list.remove(list.size()-1);
	        }
	        
	    
	    }
	
	/* No. 15
	 *  3sum
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
	Note:
	Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
	The solution set must not contain duplicate triplets.
	    For example, given array S = {-1 0 1 2 -1 -4},
	    A solution set is:
	    (-1, 0, 1)
	    (-1, -1, 2)
	 * 
	 */
		
		public List<List<Integer>> threeSum(int[] num) {
		    Arrays.sort(num);
		    
		    List<List<Integer>> listSet = new ArrayList<List<Integer>>();        
		    for (int i = 0; i < num.length - 2; i = increment(num, i)) {
		        int a = num[i], lo = i + 1, hi = num.length - 1;
		        while (lo < hi) {
		            int b = num[lo], c = num[hi];
		            if (a + b + c == 0) {
		                List<Integer> list = Arrays.asList(a, b, c);
		                listSet.add(list); // if (!listSet.contains(list)) listSet.add(list);                     
		                lo = increment(num, lo); // lo++;
		                hi = decrement(num, hi); // hi--;
		            } else if (a + b + c > 0) {
		                hi = decrement(num, hi); // hi--;
		            } else if (a + b + c < 0) {
		                lo = increment(num, lo); // lo++;
		            }
		        }
		    }        
		    return listSet;
		}

		private int increment(int[] num, int lo) {
		    while (lo < num.length - 1 && num[lo] == num[++lo]) {}
		    return lo;
		}
		    
		private int decrement(int[] num, int hi) {
		    while (hi > 0 && num[hi] == num[--hi]) {}
		    return hi;
		}
		
	/**
	 * No. 16
	 * 3sum closet	
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
	    For example, given array S = {-1 2 1 -4}, and target = 1.
	    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

	 */
		
		public int threeSumClosest(int[] num, int target) {
		    Arrays.sort(num);
		    
		    int closest = num[0] + num[1] + num[2];
		    for (int i = 0; i < num.length - 2; i = increment(num, i)) {
		        int a = num[i], lo = i + 1, hi = num.length - 1;
		        while (lo < hi) {
		            int b = num[lo], c = num[hi];
		            int sum = a + b + c;
		            if (sum == target)
		                return sum;
		            else if (Math.abs(target - sum) < Math.abs(target - closest))
		                closest = sum;
		            
		            if (target > sum)   lo = increment(num, lo);
		            else                hi = decrement(num, hi);
		        }
		    }
		    return closest;
		}
		
	/**No.209
	 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.	
	 * @param s
	 * @param nums
	 * @return
	 */
		public int minSubArrayLen(int s, int[] nums) {
		    int prev = 0, sum = 0, len = Integer.MAX_VALUE;
		    for (int i = 0; i < nums.length; i++) {
		        sum += nums[i];
		        while (sum >= s) {
		            len = Math.min(len, i - prev + 1);
		            sum -= nums[prev++];
		        }
		    }
		    return len == Integer.MAX_VALUE ? 0 : len;
		}
	
	/**
	 * No. 35
	 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0	
	 * @param nums
	 * @param target
	 * @return
	 */
		public int searchInsert(int[] nums, int target) {
	        return search(nums, 0, nums.length-1, target);
	    }
	    
	    public int search(int[] nums, int low, int high, int target){
	        while(low <= high){
	            int mid = low + (high-low)/2;
	            if(nums[mid] == target) {return mid;}
	            else if(nums[mid] < target){
	                low = mid+1;
	            }else{
	                high = mid-1;
	            }
	        } 
	        
	        return low;
	    }
}
