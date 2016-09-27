package com.uber;
import java.io.*;
import java.util.*;


class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;
  
    public Node(int value) {
        this.value = value;
    }
}
 
class BST{
    private Node root;
    
    public BST(Node root) {
        this.root = root;
    }
    
    public Node findNode(int value) {
       return findNode(value, root);
//    	return null; 
    }
    public Node findNode(int value, Node node){
    	if(node == null) return null;
        if(node.value == value){
        	return node;
        }else if(node.value > value){
        	return findNode(value, node.left);
        }else{
        	return findNode(value, node.right);
        }
    }
    
    public boolean deleteNode(int value) {
    	Node n = findNode(value);
    	if(n == null) return false;
    
    	
    	Node paren = n.parent;  	
  	
    	if(paren.left == n) {       	
        	if(n.right == null) {
        		paren.left = n.left;
        	}else{
        		Node leftMost =findLeftMost(n.right);
        		leftMost.parent.left = null;
        		leftMost.parent = paren;
        		leftMost.left = n.left;
        		leftMost.right = n.right;
        		
        		paren.left = leftMost;
        	}
    	}else{
    		if(n.right == null) {
        		paren.right = n.left;
        	}else{
        		Node leftMost =findLeftMost(n.right);
        		leftMost.parent.left = null;
        		leftMost.parent = paren;
        		leftMost.left = n.left;
        		leftMost.right = n.right;
        		paren.right = leftMost;
        	}
    	}
    	
        return true;
    }
    
    public Node findLeftMost(Node node){

    	while(node.left != null){
    		node = node.left;
    	}
    	return node;
    }
    
//    public String toStrign(){}
}

class Solution {
    public static void main(String[] args) {
    	Node n5 = new Node(5);
    	Node n3 = new Node(3);
    	Node n7 = new Node(7);
    	Node n8 = new Node(8);
    	Node n6 = new Node(6);
    	
    	n8.left = n5;
    	n5.parent = n8;
    	n5.left = n3;
    	n5.right = n7;
    	
    	n7.left = n6;
    	n6.parent = n7;
    	
    	n3.parent = n5;
    	n7.parent = n5;
    	
    	
    	
    	BST bst = new BST(n8);
    	bst.deleteNode(6);
    	System.out.println(bst.findNode(6) == null);
//    	System.out.println(bst.findNode(5) == null);
    }
}

