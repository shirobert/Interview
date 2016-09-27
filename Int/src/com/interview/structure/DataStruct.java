package com.interview.structure;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DataStruct {

	public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }

	/** 
	 * Merge two sorted linked lists and return it as a new list. 
	 * The new list should be made by splicing together the nodes of the first two lists.
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		   ListNode dummyHead = new ListNode(0);
		   ListNode p = dummyHead;
		   while (l1 != null && l2 != null) {
		      if (l1.val < l2.val) {
		         p.next = l1;
		         l1 = l1.next;
		      } else {
		         p.next = l2;
		         l2 = l2.next;
		}
		p = p.next; }
		   if (l1 != null) p.next = l1;
		   if (l2 != null) p.next = l2;
		   return dummyHead.next;
		}
/**
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contains a single digit. 
 * Add the two numbers and return it as a linked list.
 * @param l1
 * @param l2
 * @return
 */
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		   ListNode dummyHead = new ListNode(0);
		   ListNode p = l1, q = l2, curr = dummyHead;
		   int carry = 0;
		   while (p != null || q != null) {
		      int x = (p != null) ? p.val : 0;
		      int y = (q != null) ? q.val : 0;
		      int digit = carry + x + y;
		      carry = digit / 10;
		      curr.next = new ListNode(digit % 10);
		      curr = curr.next;
		      if (p != null) p = p.next;
		      if (q != null) q = q.next;
		   }
		   if (carry > 0) {
		      curr.next = new ListNode(carry);
		   }
		   return dummyHead.next;
		}
	
	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
For example,
Given 1->2->3->4, you should return the list as 2->1->4->3
Example Questions Candidate Might Ask:
Q: What if the number of nodes in the linked list has only odd number of nodes? A: The last node should not be swapped..
	 * @param head
	 * @return
	 */
	
	public ListNode swapPairs(ListNode head) {
		   ListNode dummy = new ListNode(0);
		   dummy.next = head;
		   ListNode p = head;
		   ListNode prev = dummy;
		   while (p != null && p.next != null) {
		      ListNode q = p.next, r = p.next.next;
		      prev.next = q;
		      q.next = p;
		      p.next = r;
		prev = p;
		p = r; }
		   return dummy.next;
		}
	
	
	private static final Comparator<ListNode> listComparator =
		      new Comparator<ListNode>() {
		@Override
		public int compare(ListNode x, ListNode y) {
		      return x.val - y.val;
		   }
		};
		
		public ListNode mergeKLists(List<ListNode> lists) {
			if (lists.isEmpty()) return null;
			Queue<ListNode> queue = new PriorityQueue<>(lists.size(), listComparator); 
			for (ListNode node : lists) {
			      if (node != null) {
			         queue.add(node);
			} }
			   ListNode dummyHead = new ListNode(0);
			   ListNode p = dummyHead;
			   while (!queue.isEmpty()) {
			      ListNode node = queue.poll();
			      p.next = node;
			      p = p.next;
			      if (node.next != null) {
			         queue.add(node.next);
			      }
			}
			   return dummyHead.next;
			}	
		
	/**
	 * No. 160
	 * Intersection of Two Linked Lists 
	 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.	
	 */
		  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		        Set <ListNode> set = new LinkedHashSet<ListNode>();
		        if (headA == headB) return headA;
		        if(headA == null || headB == null)  return null;
		        while(headA!= null && headB != null){
		            
		            if(headA == headB) return headA;
		            if(set.contains(headA)) return headA;
		            if(set.contains(headB)) return headB;
		            set.add(headA);
		            set.add(headB);
		            headA = headA.next;
		            headB = headB.next;
		        }
		        
		        while(headA != null){
		           if(set.contains(headA)) return headA;
		           set.add(headA);
		           headA = headA.next;
		        }
		        
		        while(headB != null){
		            if(set.contains(headB)) return headB;
		            set.add(headB);
		            headB = headB.next;
		        }
		        
		        return null;
		    }
		  
			
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
