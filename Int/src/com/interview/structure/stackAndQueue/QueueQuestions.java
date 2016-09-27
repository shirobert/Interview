package com.interview.structure.stackAndQueue;

import net.datastructures.CircularQueue;
import net.datastructures.LinkedCircularQueue;

public class QueueQuestions {
	  public static <E> E Josephus(CircularQueue<E> queue, int k) {
		    if (queue.isEmpty()) return null;
		    while (queue.size() > 1) {
		      for (int i=0; i < k-1; i++)   // skip past k-1 elements
		        queue.rotate();
		      E e = queue.dequeue();        // remove the front element from the collection
		      System.out.println("    " + e + " is out");
		      }
		    return queue.dequeue();         // the winner
		  }

		  /** Builds a circular queue from an array of objects. */
		  public static <E> CircularQueue<E> buildQueue(E a[]) {
		    CircularQueue<E> queue = new LinkedCircularQueue<>();
		    for (int i=0; i<a.length; i++)
		      queue.enqueue(a[i]);
		    return queue;
		  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
