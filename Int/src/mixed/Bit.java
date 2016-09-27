package mixed;
import java.util.*;
public class Bit {

	/*
	 * Gray Code
	 */
	
	public List<Integer> grayCode(int n) {
	    List<Integer> list = new ArrayList<>();
	    list.add(0);
	    for (int i = 0; i < n; i++) {
	        int flipper = 1 << i;
	        for (int j = list.size() - 1; j >= 0; j--)  // scan the previous sequence backward
	            list.add(list.get(j) | flipper);        // flip the current highest bit from 0 to 1
	    }
	    return list;
	}
	/**
	 * No. 201
	 * Bitwise AND of Numbers Range
	 * http://www.cnblogs.com/grandyang/p/4431646.html
	 */
	
    public int rangeBitwiseAnd(int m, int n) {
        int d = Integer.MAX_VALUE;
        while ((m & d) != (n & d)) {
            d <<= 1;
        }
        return m & d;
    }
}
