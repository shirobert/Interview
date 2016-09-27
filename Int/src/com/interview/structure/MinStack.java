package com.interview.structure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class MinStack {

	private Stack<Integer> stack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public void push(int x) {
	    stack.push(x);
	    if (minStack.isEmpty() || x <= minStack.peek())    minStack.push(x);
	}

	public void pop() {
	    if (stack.pop().equals(minStack.peek()))    minStack.pop();
	}

	public int top() {
	    return stack.peek();
	}

	public int getMin() {
	    return minStack.peek();
	}
	/**
	 * @param args
	 */
	
	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

	 */
	
	private static final Map<Character, Character> map =
		      new HashMap<Character, Character>() {{
		            put('(', ')');
		            put('{', '}');
		            put('[', ']');
		}};
		public boolean isValid(String s) {
		   Stack<Character> stack = new Stack<>();
		   for (char c : s.toCharArray()) {
		      if (map.containsKey(c)) {
		         stack.push(c);
		} else if (stack.isEmpty() || map.get(stack.pop()) != c) { return false;
		} }
		   return stack.isEmpty();
		}
	
/** No. 225
 * Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 * Implement Stack using Queues 		
 * @author leish
 *
 */
		class MyStack {

		    Queue<Integer> q1 = new LinkedList<Integer>();
		    Queue<Integer> q2 = new LinkedList<Integer>();

		    // Push element x onto stack.
		    public void push(int x) {
		      q1.add(x);  
		     }

		    // Removes the element on top of the stack.
		    public void pop() {
		      while(q1.size()>1){
		          q2.add(q1.remove());
		      }
		      q1.remove();
		      refillQ1();

		    }

		    // Get the top element.
		    public int top() {
		      while(q1.size()>1){
		          q2.add(q1.remove());
		     }
		      int res =  q1.remove();
		      q2.add(res);
		      refillQ1();
		      return res;  
		    }

		    // Return whether the stack is empty.
		    public boolean empty() {
		      return q1.isEmpty();
		    }

		    private void refillQ1(){
		     while(q2.size()>0){
		        q1.add(q2.remove());
		      }

		    }
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
