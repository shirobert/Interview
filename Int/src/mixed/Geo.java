package mixed;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Geo {
	
	
	
	
/**
 * No. 218 	The Skyline Problem
 * https://leetcode.com/problems/the-skyline-problem/
 * @author leish
 *
 */
	class EdgePoint {
	    int height;
	    boolean isStart;    // start point or end point
	    
	    public EdgePoint(int height, boolean isStart) {
	        this.height = height;
	        this.isStart = isStart;
	    }
	}

	public List<int[]> getSkyline(int[][] buildings) {
	    List<int[]> skyline = new ArrayList<int[]>();
	    int n = buildings.length;
	    if (n == 0) return skyline;
	    
	    // Map: key - x coordinate, value - a list of EdgePoints (contain height and isStart)
	    Map<Integer, List<EdgePoint>> map = new HashMap<>();
	    // use PQ to sort all x coordinates
	    PriorityQueue<Integer> pq = new PriorityQueue<>();
	    for (int i = 0; i < n; i++) {
	        int left = buildings[i][0];
	        int right = buildings[i][1];
	        int height = buildings[i][2];            
	        if (!map.containsKey(left)) {   // start point
	            map.put(left, new ArrayList<EdgePoint>());
	            pq.add(left);
	        }
	        map.get(left).add(new EdgePoint(height, true));            
	        if (!map.containsKey(right)) {  // end point
	            map.put(right, new ArrayList<EdgePoint>());
	            pq.add(right);
	        }
	        map.get(right).add(new EdgePoint(height, false));
	    }
	    
	    // BST: key - height, value - number of buildings with same height
	    TreeMap<Integer, Integer> tree = new TreeMap<>();
	    int prevHeight = 0, currHeight = 0;
	    while (!pq.isEmpty()) {
	        int x = pq.poll();  // get a x coordinate
	        currHeight = 0;
	        // check all EdgePoints on x
	        for (EdgePoint pt : map.get(x)) {
	            int height = pt.height;
	            if (pt.isStart) {   // start point: add the height to BST
	                if (!tree.containsKey(height))  tree.put(height, 1);
	                else                            tree.put(height, tree.get(height) + 1);
	            } else {            // end point: remove the height from BST
	                if (tree.get(height) == 1)  tree.remove(height);
	                else                        tree.put(height, tree.get(height) - 1);
	            }
	        }
	        // find the largest height in BST, set height = 0 if tree is empty
	        currHeight = tree.isEmpty() ? 0 : tree.lastKey();
	        if (currHeight != prevHeight) { // add to the skyline for different height
	            skyline.add(new int[]{x, currHeight});
	            prevHeight = currHeight;
	        }
	    }        
	    return skyline;
	}
}
