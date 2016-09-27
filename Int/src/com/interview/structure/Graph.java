package com.interview.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph{
	private int nV;
	private int nE;
	private Map <String, LinkedList<String> > map; // use LinkedList (adjacent list) to store pairs 
	public Map <String, Integer> marked; // use makred to check if a vertex has been visited.
	
	public Map<String, String> edgeTo; /* or Int [] edgeTo, use to save the path, edgeTo["b"] = "a", means a->b, you
									can write a function to trace back to print the whole path.
									*/
	
	public Map<String, Integer> components; // used to save the vertex's component, disconnected components.
	
	public Graph(BufferedReader br){
		map = new HashMap<String, LinkedList<String> > ();
		String line;
		try {
			line = br.readLine();
			while(line != null){
				String start = line.split(" ")[0];
				String second = line.split(" ")[1];
				if(map.containsKey(start)){
					map.get(start).add(second);
				}else{
					LinkedList <String> list = new LinkedList<String>();
					list.add(second);
					map.put(start, list);
				}
				if(marked.containsKey(second)){
					marked.put(second, marked.get(second)+1);
				}else{
					marked.put(second, 1);
				}
				
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void findPath(Graph g, String source, String end){
		LinkedList <String> path = new LinkedList<String>();
		dfs(g, source, end, path);
		
	}
	
	// DFS can be used to solve 
	/**
	 * Cycledetection. Supportthisquery:Isagivengraphacylic?
Two-colorability. Supportthisquery:Cantheverticesofagivengraphbeassigned one of two colors in such a way that no edge connects vertices 
of the same color? which is equivalent to this question: Is the graph bipartite ?
	 * @param g
	 * @param v
	 * @param end
	 * @param path
	 */
	
	public void dfs (Graph g, String v, String end, LinkedList <String> path){
		if(v.equalsIgnoreCase(end) && nE == 0){
			System.out.print(path.toString());
		}else if(v.equalsIgnoreCase(end) && nE != 0){
			marked.put(v, marked.get(v)+1);
			return;
		}
		LinkedList <String> items = g.map.get(v);
		if(items == null) return;
		
		for(String str : items){
			if(marked.get(str) != 0){
				path.add(str);
				marked.put(str, marked.get(str)-1);
				dfs(g, str, end, path);
				//marked.put(str, marked.get(str)+1);
				path.removeLast();
			}
		}
		
		
	}
	
	public void BFS(Graph g){
		
	}
	
}
