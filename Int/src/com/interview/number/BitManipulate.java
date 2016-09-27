package com.interview.number;

public class BitManipulate {

	/**
	 * No. 191 Number of 1 bits Write a function that takes an unsigned integer
	 * and returns the number of ’1' bits it has (also known as the Hamming
	 * weight).
	 * 
	 * For example, the 32-bit integer ’11' has binary representation
	 * 00000000000000000000000000001011, so the function should return 3.
	 * 
	 * @param n
	 * @return
	 */
	public int hammingWeight(int n) {
		int counter = 0;
		while (n != 0) {
			if ((n & 1) == 1) {
				counter += 1;
			}
			// pay attention to the >>> or >>
			n >>>= 1;
		}
		return counter;
	}

	/**
	 * reverse number in bit
	 * Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 return 964176192 (represented in binary as 00111001011110000010100101000000).
	 */
	public int reverseBits(int n) {
		int m = 0;
		int b;
		int i = 0;
		while (i < 32) {
			b = n & 1; // get the last bit of current n
			n = n >> 1;
			m = (m << 1) | b;
			i++;
		}

		return m;
	}
	
	
	/**
	 * No 136
	 * Single Number I
	 * Given an array of integers, every element appears twice except for one. Find that single one.


	 */
	 public int singleNumber(int[] nums) {
	        int result = 0;
	        for (int i = 0; i<nums.length; i++){
	            result = result ^ nums[i];
	        }
	        return result;
	    }
	/**
	 * No. 137
	 * Single number II
	 * Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. 
Could you implement it without using extra memory?
	 */

	public int singleNumber2(int[] nums) {
        int[] count = new int[32];
   int result = 0;
   for (int i = 0; i < nums.length; i++) {
       int cNum = nums[i];
       for (int j = 0; j < 32; j++) {
           count[j] = count[j] + (cNum & 1);
           cNum = cNum >>> 1;
       }
       // System.out.println(Arrays.toString(count));
   }

   for (int i = 0; i < 32; i++) {
       result = result << 1;
       result = result + count[32 - i - 1] % 3;
   }
   return result;
   }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
