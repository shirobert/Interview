package com.interview.number;

import java.util.Arrays;
import java.util.Comparator;

public class Sort {

	/**
	 * insertion sort. 
	 * @param data
	 */
	public void insertionSort(char [] data){
		int n = data.length;
		for(int i = 1; i<n; i++){
			char curr = data[i];
			int j = i;
			while(j>0 && data[j-1] > curr){
				data[j] = data[j-1];
				j--;
			}
			data[j] = curr;
		}
	}
	
	
	  public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
		    int i = 0, j = 0;
		    while (i + j < S.length) {
		      if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
		        S[i+j] = S1[i++];                     // copy ith element of S1 and increment i
		      else
		        S[i+j] = S2[j++];                     // copy jth element of S2 and increment j
		    }
		  }

		  /** Merge-sort contents of array S. */
		  public static <K> void mergeSort(K[] S, Comparator<K> comp) {
		    int n = S.length;
		    if (n < 2) return;                        // array is trivially sorted
		    // divide
		    int mid = n/2;
		    K[] S1 = Arrays.copyOfRange(S, 0, mid);   // copy of first half
		    K[] S2 = Arrays.copyOfRange(S, mid, n);   // copy of second half
		    // conquer (with recursion)
		    mergeSort(S1, comp);                      // sort copy of first half
		    mergeSort(S2, comp);                      // sort copy of second half
		    // merge results
		    merge(S1, S2, S, comp);               // merge sorted halves back into original
		  }
	
		  
		  public static <K> void quickSortInPlace(K[] S, Comparator<K> comp) {
			    quickSortInPlace(S, comp, 0, S.length-1);
			  }

			  /** Sort the subarray S[a..b] inclusive. */
			  private static <K> void quickSortInPlace(K[] S, Comparator<K> comp,
			                                                                   int a, int b) {
			    if (a >= b) return;                // subarray is trivially sorted
			    int left = a;
			    int right = b-1;
			    K pivot = S[b];
			    K temp;                            // temp object used for swapping
			    while (left <= right) {
			      // scan until reaching value equal or larger than pivot (or right marker)
			      while (left <= right && comp.compare(S[left], pivot) < 0) left++;
			      // scan until reaching value equal or smaller than pivot (or left marker)
			      while (left <= right && comp.compare(S[right], pivot) > 0) right--;
			      if (left <= right) {             // indices did not strictly cross
			        // so swap values and shrink range
			        temp = S[left]; S[left] = S[right]; S[right] = temp;
			        left++; right--;
			      }
			    }
			    // put pivot into its final place (currently marked by left index)
			    temp = S[left]; S[left] = S[b]; S[b] = temp;
			    // make recursive calls
			    quickSortInPlace(S, comp, a, left - 1);
			    quickSortInPlace(S, comp, left + 1, b);
			  }	  
		  
		  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
