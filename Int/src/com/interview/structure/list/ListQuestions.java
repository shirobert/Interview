package com.interview.structure.list;


/** 
 * List related Questions 
 * http://wlcoding.blogspot.com/2015/03/merge-insersion-sort-list.html?view=sidebar
 * @author leish
 *
 */

public class ListQuestions {
	public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}


	
	
	
/**
 * Detect cycle
 */
	// Two pointers
	public boolean hasCycle(ListNode head) {
	    ListNode slow = head, fast = head;
	    while (fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	        if (slow == fast)   return true;
	    }
	    return false;
	}
	
	/**
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
	 * @param head
	 * @return
	 */
	public ListNode detectCycle2(ListNode head) {
	    ListNode slow = head, fast = head;
	    while (fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	        if (slow == fast) {
	            ListNode slow2 = head;
	            while (slow2 != slow) {
	                slow = slow.next;
	                slow2 = slow2.next;
	            }
	            return slow2;
	        }
	    }
	    return null;
	}	
/**
 * No. 143
 * Reorder list	
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.


 
Reorder List
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
You must do this in-place without altering the nodes' values.
For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

Solution

Time ~ O(3N), Space ~ O(1)
1) Find the second half: use two pointers slow and fast (twice faster than slow)
even length: L0→L1→…→Ln/2→Ln/2+1→…→Ln-1→Ln
odd length: L0→L1→…→Ln/2-1→Ln/2→Ln/2+1→…→Ln-1→Ln
2) Reverse the second half, and return its head: fix prev and curr, and keep moving curr.next after prev
L0→L1→…→Ln/2→Ln/2+1→…→Ln-1→Ln
=>
L0→L1→…→Ln/2→Ln→Ln-1→…→Ln/2+1
3) Merge the two halves from their heads:
Connect left head to right head, move both heads one node forward, and then connect right head to left head, until one of the head reaches null;
even length: L0→Ln→L1→Ln-1→…→Ln/2→Ln/2+1
odd length: L0→Ln→L1→Ln-1→…→Ln/2-1→Ln/2+1→Ln/2
Implementation: we can keep connecting left head to right head, and then just swap the head references, and continue connecting heads, i.e.:
Given two heads: curr and slow, always connect curr to slow, and set new slow as curr.next (stored before) and move curr forward.
Note: there's no need to consider even or odd length here.

 * 
 */
	
	public void reorderList(ListNode head) {
	    if (head == null || head.next == null)  return;
	    
	    // find the middle point
	    ListNode prev = null, slow = head, fast = head;
	    while (fast != null && fast.next != null) {
	        prev = slow;
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    
	    // reverse the second half and break two halfs
	    slow = reverse(prev);   // return the beginning node of the second half
	    prev.next = null;
	    
	    // merge two halfs
	    ListNode curr = head;
	    while (curr != null && slow != null) {
	        ListNode nt = curr.next;
	        curr.next = slow;
	        curr = curr.next;
	        slow = nt;
	    }
	}

	public ListNode reverse(ListNode prev) {
	    if (prev == null)   return null;
	    ListNode curr = prev.next;
	    while (curr != null && curr.next != null) {
	        ListNode nt = curr.next.next;
	        curr.next.next = prev.next;
	        prev.next = curr.next;
	        curr.next = nt;
	    }
	    return prev.next;
	}	
	

	/**
	 * reverseList
	 */
	// iteratively
	public ListNode reverseList(ListNode head) {
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    
	    ListNode prev = dummy, curr = head;
	    while (curr != null && curr.next != null) {
	        ListNode nd = curr.next.next;
	        curr.next.next = prev.next;
	        prev.next = curr.next;
	        curr.next = nd;
	    }
	    
	    return dummy.next;
	}
	// recursively
	public ListNode reverseList2(ListNode head) {
	    if (head == null || head.next == null)   return head;
	    ListNode restStart = head.next;
	    head.next = null;
	    ListNode restEnd = reverseList(restStart);
	    restStart.next = head;
	    return restEnd;
	}
	
/**
 *  reverse linked list from m to n
 *  Reverse a linked list from position m to n. Do it in-place and in one-pass.
For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,
return 1->4->3->2->5->NULL.
 */
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    
	    ListNode prev = dummy, curr = dummy;
	    for (int i = 0; i < m - 1; i++)
	        prev = prev.next;
	    curr = prev.next;
	    
	    for (int i = 0; i < n - m; i++) {
	        ListNode temp = curr.next.next;
	        curr.next.next = prev.next;
	        prev.next = curr.next;
	        curr.next = temp;
	    }
	    return dummy.next;
	}
	
/**
 * No 148
 * Sort list	
 * @param head
 * @return
 */
	public ListNode sortList(ListNode head) {
	    if (head == null || head.next == null)  return head;
	    
	    // find the middle point (slow pointing to mid)
	    ListNode slow = head, fast = head;
	    while (fast.next != null && fast.next.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    
	    // mergesort two lists recursively
	    ListNode left = head, right = slow.next;
	    slow.next = null; // break to form two lists
	    left = sortList(left);
	    right = sortList(right);
	    return merge(left, right); // given the beginning nodes of two lists
	}

	private ListNode merge(ListNode left, ListNode right) {
	    if (left == null)   return right;
	    if (right == null)  return left;
	    ListNode dummy = new ListNode(0);
	    ListNode p = dummy;
	    while (left != null && right != null) {
	        if (left.val < right.val) {
	            p.next = left;
	            left = left.next;
	        } else {
	            p.next = right;
	            right = right.next;
	        }
	        p = p.next;
	    }
	    if (left != null)   p.next = left;
	    if (right != null)  p.next = right;
	    return dummy.next;
	}
	
	
	// insertion way.
	/**
	 * No. 147
	 * 
	 * Two pointers: Time ~ O(N^2), Space ~ O(1) 
curr 取 Node i，prev 每次从头开始，
If prev.next.val > curr.next.val, move curr.next after prev;
If prev.next.val <= curr.next.val, move prev forward;
Until prev == curr.
注意：每次 insert node 之前要将 prev 对准开头 dummy；insert 完成后要 break 跳出循环；另外当 prev 到达 curr (prev == curr) 时也结束循环，且将 curr move forward (curr = curr.next;)。
	 * @param head
	 * @return
	 */
	
	public ListNode insertionSortList(ListNode head) {
	    if (head == null || head.next == null)  return head;
	    
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    ListNode prev = dummy, curr = head;
	    while (curr != null && curr.next != null) {
	        prev = dummy;
	        while (prev != curr) {
	            if (prev.next.val > curr.next.val) {    // move curr.next after prev.next
	                ListNode nt = curr.next.next;
	                curr.next.next = prev.next;
	                prev.next = curr.next;
	                curr.next = nt;
	                break;
	            } else
	                prev = prev.next;
	        }
	        if (prev == curr) curr = curr.next; // move curr forward if curr.next was not moved
	    }
	    
	    return dummy.next;
	}
	
/**
 * No. 86
 * Quick Sort list	
 * 
 * 
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.
For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

 */
	
/**
Time ~ O(N), Space ~ O(1)
Move last point to the last continuous node < x (last.next >= x);
Set curr = last, check:
If curr.next < x, insert curr.next after last, and move last forward (last = last.next), no need to move curr forward here;
If curr.next >= x, move curr forward (curr = curr.next).
注意：不要忘记第 1 步，将 curr 对准最近的一个 >= x 的 Node。	
 * @param head
 * @param x
 * @return
 */
	public ListNode partition(ListNode head, int x) {
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    
	    // find the last continuous node that < x
	    ListNode last = dummy;
	    while (last.next != null && last.next.val < x)  last = last.next;
	    
	    // find and move the next node that < x
	    ListNode curr = last;
	    while (curr.next != null) {
	        if (curr.next.val < x) {
	            ListNode nt = curr.next.next;
	            curr.next.next = last.next;
	            last.next = curr.next;
	            curr.next = nt;
	            last = last.next;
	        } else {
	            curr = curr.next;
	        }
	    }
	    
	    return dummy.next;
	}
	
}
