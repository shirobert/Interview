package mixed;
import java.util.List;



 class DynamicPro {
	
	 /**
	  * Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
	  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
	  Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container.
	  * @param height
	  * @return
	  */
	 public int maxArea(int[] height) {
		    int maxArea = 0;
		    int left = 0, right = height.length - 1;
		    while (left < right) {
		        maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
		        if (height[left] < height[right])   left++;
		        else                                right--;
		    }
		    return maxArea;
		} 
	 
/**
 * Trapping water	
 */
	
	 public int trap(int[] A) {
		    int vol = 0;
		    if (A.length <= 2)  return vol;
		    
		    // go from left to right, find the left highest bar for each bar
		    int[] lmh = new int[A.length];
		    lmh[0] = 0;
		    int max = A[0];
		    for (int i = 1; i < A.length; i++) {
		        lmh[i] = max;
		        if (A[i] > max) max = A[i];
		    }
		    
		    // go from right to left, find the right highest bar for each bar
		    max = A[A.length - 1];
		    for (int i = A.length - 2; i >= 0; i--) {
		        int left = lmh[i];
		        int right = max;
		        if (A[i] > max) max = A[i];
		        // calculate the volume if there's a bowl shape
		        int min = Math.min(left, right);
		        if (min > A[i]) vol += min - A[i];
		    }
		    
		    return vol;
		}
	 
	 
/**
 * Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest product.
For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.	
 */
/**
 * 1-d DP: Time ~ O(N), Space ~ O(1) 
因为负数的存在，需要同时记录largest product和smallest product。
Let f(i) be the largest product of the subarray ending at A[i].
Let g(i) be the smallest product of the subarray ending at A[i].
f(i) = max{f(i - 1) * A[i], A[i], g(i - 1) * A[i]}
g(i) = min{f(i - 1) * A[i], A[i], g(i - 1) * A[i]}
Use maxProd and minProd to store f(i) and g(i) at each i, respectively.
Use max and min to store the largest f(i) and the smallest g(i), respectively.	
 */
	public int maxProduct(int[] A) {
	    int maxProd = 1, minProd = 1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	    for (int i = 0; i < A.length; i++) {
	        int temp = maxProd;
	        maxProd = Math.max(Math.max(maxProd * A[i], A[i]), minProd * A[i]);
	        minProd = Math.min(Math.min(minProd * A[i], A[i]), temp * A[i]);
	        max = Math.max(maxProd, max);
	        min = Math.min(minProd, min);
	    }
	    return max;
	}
	
	
	
	
/**
 * Maximum Sum Subarray. 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */
	public int maxSubArray(int[] A) {
	    // d(i) = max{d(i - 1) + A[i], A[i]}
	    int sum = 0, max = Integer.MIN_VALUE;
	    for (int i = 0; i < A.length; i++) {
	        sum = Math.max(sum + A[i], A[i]);
	        max = Math.max(sum, max);
	    }
	    return max;
	}
	
	
/**
 * Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
For "(()", the longest valid parentheses substring is "()", which has length = 2.
Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

	1-d DP: Time ~ O(N), Space ~ O(N) 
Let d(i) be the length of valid parentheses in str[i ... N - 1] (parentheses starts from str[i]).
str[ i, i + 1 .. j - 1,  j,  j + 1.. N - 1 ]
              d(i + 1)            d(j + 1)         where j = i + d(i + 1) + 1
					If str[ i ] = ')',   d(i) = 0
Otherwise,     d(i) = ( str[ j ] == ')' ? (d(i + 1) + 2  +  d(j + 1)) : 0.
另用一 max 记录最大的d(i)。

 */
	
	
	public int longestValidParentheses(String s) {
        // d(i): length of the longest valid parentheses starting from s[i], i = N-1...0
        // d(i) = 0 if s[i] == ')'
        // d(i) = d(i+1) + 2 + d(i+1+d(i+1)+1) if s[i] == '(' and s[i+1+d(i+1)] == ')'
        int n = s.length();
        if (n < 2) return 0;
        int max = 0;
        int[] d = new int[n];
        d[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + 1 + d[i + 1];
                if (j < n && s.charAt(j) == ')') {
                    d[i] = d[i + 1] + 2;
                    if (j + 1 < n)
                        d[i] += d[j + 1];
                }
            }
            max = Math.max(max, d[i]);
        }
        return max;
    }	
	
/**
 * No. 120
 * Triangle .
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * Let d(i,  j) be the min path sum from T[i, j] to the triangle bottom. 
d(i,  j) = min{d(i + 1,  j), d(i + 1,  j + 1)} + T[i,  j] 
Return d(0, 0).
 */
	
	public int minimumTotal(List<List<Integer>> triangle) {
	    int N = triangle.size();
	    int[][] sum = new int[N][N];
	    for (int r = N - 1; r >= 0; r--) {
	        for (int c = 0; c <= r; c++) {
	            if (r == N - 1) 
	                sum[r][c] = triangle.get(r).get(c);
	            else            
	                sum[r][c] = Math.min(sum[r + 1][c], sum[r + 1][c + 1]) + triangle.get(r).get(c);
	        }
	    }
	    return sum[0][0];
	}	
	
/** 
 * Rectangular
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
Let d(i,  j) be the min path sum from grid[0,  0] (top left) to grid[i, j].
d(i, j) = min{d(i - 1, j), d(i, j - 1)} + grid[i, j];
Return d(M - 1, N - 1).
Use top-down approach.	
 * @param grid
 * @return
 */
	public int minPathSum(int[][] grid) {
        // d(i,j) = min{d(i-1,j) if i > 0, d(i,j-1) if j > 0} + g[i,j]
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    sum[i][j] = grid[i][j];
                else
                    sum[i][j] = Math.min((i > 0) ? sum[i - 1][j] : Integer.MAX_VALUE, 
                                         (j > 0) ? sum[i][j - 1] : Integer.MAX_VALUE)
                                + grid[i][j];
            }
        return sum[m - 1][n - 1];
    }
	
	/**
	 * No. 72
	 * Edit Distance 
	 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.) 
You have the following 3 operations permitted on a word: 
a) Insert a character
b) Delete a character
c) Replace a character

Solution
Let d(i, j) be distance between str1 (len = i) and str2 (len = j) 
if min{i, j} = 0, d(i, j) = max{i, j} 
else d(i, j) = min{ d(i - 1, j) + 1,  // add last char in str1
                          d(i, j - 1) + 1,  // add last char in str2
                          d(i - 1, j - 1) + (str1[i] == str2[j]) ? 0 : 1 // replace the last char in str1 with that in str2 when str1[i] != str2[j] }
注：不需要分别考虑 insert, delete, replace 三种情况。
	 */
	
public int minDistance(String word1, String word2) {
	int n1 = word1.length();
    int n2 = word2.length();
    
     int[][] dist = new int[n1 + 1][n2 + 1]; // Levenshtein distance
     for (int i = 0; i <= n1; i++)
         for (int j = 0; j <= n2; j++) {
             if (i == 0 || j == 0)   dist[i][j] = Math.max(i, j);
             else
                 dist[i][j] = Math.min(Math.min(dist[i - 1][j] + 1, dist[i][j - 1] + 1), 
                 dist[i - 1][j - 1] + ((word1.charAt(i -1) != word2.charAt(j - 1)) ? 1 : 0));
         }        
    
     return dist[n1][n2];
    }


public int minDistance2(String word1, String word2) {
    int n1 = word1.length();
    int n2 = word2.length();
    
    if (n1 < n2)    return minDistance(word2, word1);
    int[] dist = new int[n2 + 1];
    int upperleft = 0; // store dist[i - 1][j - 1]
    // initialize the first row
    for (int j = 0; j <= n2; j++)
        dist[j] = j;
    // fill up row by row
    for (int i = 1; i <= n1; i++) {
        upperleft = dist[0];
        dist[0] = i;
        for (int j = 1; j <= n2; j++) {
            int min = Math.min(dist[j - 1], dist[j]) + 1;
            min = Math.min(min, upperleft + (word1.charAt(i - 1) != word2.charAt(j - 1) ? 1 : 0));
            upperleft = dist[j];
            dist[j] = min;
        }
    }
    return dist[n2];
}
	/**
	 * No 221 Maximal Square.
	 * 
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:
	 */
	
	/**
	 * 1. 2-d DP: Time ~ O(MN), Space ~ O(MN) 
Let d(i, j) be the length of the maximal square ending at matrix[ i ][ j ].
Fill up the table:
if        matrix[ i ][ j ] == ' 0 ',   d(i, j) = 0;
else if matrix[ i ][ j ] == ' 1 ', 
          if (i==0 || j==0)
          		d(i,j) =1; {
           		d(0, j) = 1;
           		d(i, 0) = 1;
           		}          
          else d(i, j) = min{d(i - 1, j - 1), d(i - 1, j), d(i, j - 1)} + 1.
Return: maxArea = ( max{d(i, j)} )^2.
	 * @param matrix
	 * @return
	 */

	
	 public int maximalSquare(char[][] matrix) {
		    int m = matrix.length;
		    if (m == 0) return 0;
		    int n = matrix[0].length;
		    
		    int[][] d = new int[m][n];
		    int max = 0;
		    for (int i = 0; i < m; i++) {
		        for (int j = 0; j < n; j++) {
		            if (matrix[i][j] == '0')    d[i][j] = 0;
		            else /* if  (matrix[i][j] == '1') */ {
		                if (i == 0 || j == 0)
		                    d[i][j] = 1;
		                else
		                    d[i][j] = Math.min(Math.min(d[i - 1][j - 1], d[i - 1][j]), d[i][j - 1]) + 1;
		            }
		            max = Math.max(max, d[i][j]);
		        }
		    }
		    return max * max;
		}
	/** No. 198
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping
	 * you from robbing each of them is that adjacent houses have security
	 * system connected and it will automatically contact the police if two
	 * adjacent houses were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of
	 * each house, determine the maximum amount of money you can rob tonight
	 * without alerting the
	 * 
	 * @param
	 * @param
	 * @return if we don't rob this house, the maximum amount of money is the
	 *         bigger one between to rob and not to rob the previous house;
	 * 
	 *         if we rob this house, the maximum amount of money is the amount
	 *         of money that we don't rob the previous house plus the money in
	 *         the current house.
	 */
	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		// m0 is the max at index i-2, m1 at index i-1, m2 at index i-2
		int m0 = nums[0], m1 = Math.max(nums[0], nums[1]), m2;

		for (int i = 2; i < nums.length; i++) {
			m2 = Math.max(m0 + nums[i], m1);
			m0 = m1;
			m1 = m2;
		}
		return m1;
	}

	/**
	 * No. 213
	 * Rob 2
	 * fter robbing those houses on that street, the thief has found himself a new place for his thievery 
	 * so that he will not get too much attention. This time, all houses at this place are arranged in a circle.
	 * That means the first house is the neighbor of the last one.
	 * Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house,
 determine the maximum amount of money you can rob tonight without alerting the police.
	 * @param
	 * @param
	 * @return
	 */

	public int rob2(int[] nums) {
		int len = nums.length;
		if (len == 0)
			return 0;
		if (len == 1)
			return nums[0];
		int[] dp = new int[len];
		int use, inuse;
		// the first element used, so the last element can not be used
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
		}
		use = dp[len - 2];
		// the first element is not used, so the last element can be used
		dp[0] = 0;
		dp[1] = nums[1];
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
		}
		inuse = dp[len - 1];
		return Math.max(use, inuse);
	}

	/*
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start'
	 * in the diagram below).
	 * 
	 * The robot can only move either down or right at any point in time. The
	 * robot is trying to reach the bottom-right corner of the grid (marked
	 * 'Finish' in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 * http://articles.leetcode.com/2010/11/unique-paths.html
	 */

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] steps = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    steps[i][j] = (obstacleGrid[i][j] == 1) ? 0 : 1;
                else
                    steps[i][j] = (obstacleGrid[i][j] == 1) ? 0 : 
                    ( (i > 0) ? steps[i - 1][j] : 0 ) + 
                    ( (j > 0) ? steps[i][j - 1] : 0 );
            }
        return steps[m - 1][n - 1];
    }
	
	public int uniquePaths(int m, int n) {
        // d(i,j) = d(i-1,j) {if i > 0} + d(i,j-1) {if j > 0}
        int[][] steps = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    steps[i][j] = 1;
                else
                    steps[i][j] = ((i > 0) ? steps[i - 1][j] : 0)
                                + ((j > 0) ? steps[i][j - 1] : 0);
            }
        return steps[m - 1][n - 1];
    }

	/**
	 * You have n types of items, where the ith item type has an integer size si
	 * and a real value vi. You need to ﬁll a knapsack of total capacity C with
	 * a selection of items of maximum value.
	 */

	public void knapsack_0_1(int N, int[] weight, int[] value, int cap) {

		int opt[][] = new int[N + 1][cap + 1];
		int sol[][] = new int[N + 1][cap + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= cap; j++) {
				// option 1; if item i is not selected or weight[i] > j
				int option1 = opt[i - 1][j];

				// option 2;
				int option2 = 0;
				if (weight[i - 1] <= j) {
					option2 = opt[i - 1][j - weight[i - 1]] + value[i - 1];
				}

				opt[i][j] = (option1 > option2) ? option1 : option2;
				sol[i][j] = (option2 > option1) ? 1 : 0;

			}
		}

		// determine which item to take
		boolean take[] = new boolean[N];
		for (int n = N, w = cap; n > 0; n--) {
			if (sol[n][w] != 0) {
				take[n] = true;
				w = w - weight[n - 1];
			} else {
				take[n] = false;
			}
		}

		// print out which one to take.

	}

	
	/**
	 * No. 322
	 * coin change
	 * You are given coins of different denominations and a total amount of money amount.
	 * Write a function to compute the fewest number of coins that you need to make up that amount.
	 * If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

d[i] = min(d[i], d[i-coin[j]]+1 ), j is from 0 to coins.length
	 */
	public int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount <= 0)
			return 0;
		int[] minNumber = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			minNumber[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i
						&& minNumber[i - coins[j]] != Integer.MAX_VALUE)
					minNumber[i] = Math.min(minNumber[i], 1 + minNumber[i- coins[j]]);
			}
		}
		if (minNumber[amount] == Integer.MAX_VALUE)
			return -1;
		else
			return minNumber[amount];
   }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
