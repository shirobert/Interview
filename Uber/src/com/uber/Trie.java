package com.uber;

import java.util.ArrayList;
import java.util.List;




public class Trie {

	public class Node{
		Boolean end;
		Node [] next;
		char c;
		
		public Node(){
			next = new Node[26];
		}
		public Node(char c){
			this.c = c;
			next =new Node[26];
		}
				
	}
	
	Node root;
	
	public Trie(){
		root = new Node();
	}
	public void set(String str){
		if(str == null) return;
		put(str, root);
	}
	
	public void put(String str, Node node){
		if(str.length() != 0){
			char temp = str.charAt(0);
			int index = temp-'a';
			if(node.next[index] == null){
				node.next[index] = new Node(temp);				
			}
			put(str.substring(1), node.next[index]);			
		}else{
			return;
		}
		
	}
	
	
//	public List<String> search(String str){
//		List<String> words = new ArrayList<String>();
//		Node n = getNode(str, root);
//		dfs(n, words);
//		return words;
//	}
	
	
//	public void dfs(Node n, List<String> words){
//		if(n.end) {
//			words.add()
//		}
//	}
	
	
	public Node getNode(String str, Node node){
			Node result = new Node();
			if (str.length() == 0) return node;
			char temp = str.charAt(0);
			int index = temp - 'a';
			if(node.next[index].c == temp){
				node = node.next[index];
				 result = getNode(str.substring(1), node);
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
