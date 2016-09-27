package com.interview.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StrFun {
	
/**
 * http://www.mitbbs.com/article_t1/JobHunting/32906379_0_1.html
 * class IntFileIterator {
  boolean hasNext();
  int next();
  
}

class{
  public boolean isDistanceZeroOrOne(IntFileIterator a, IntFileIterator b)；

}
// return if the distance between a and b is at most 1.. 
// Distance: minimum number of modifications to make a=b
// Modification:
//   1. change an int in a
//   2. insert an int to a
//   3. remove an int from a
	
 * @param a
 * @param b
 * @return
 */

	
	
        public boolean isDistanceZeroOrOne(IntFileIterator a, 
IntFileIterator b) {
            boolean ins_a = false, ins_b = false, replace = false, diff = 
false;
            int pre_a = 0;
            int pre_b = 0;
            while (a.hasNext() && b.hasNext()) {
                int cur_a = a.next(), cur_b = b.next();
                if (!ins_a && !ins_b && !replace) {
                    if (cur_a != cur_b) {
                        ins_a = ins_b = replace = diff = true;
                    }
                } else {
                    if (ins_a && pre_b != cur_a) ins_a = false;
                    if (ins_b && pre_a != cur_b) ins_b = false;
                    if (replace && cur_a != cur_b) replace = false;
                    if (!ins_a && !ins_b && !replace) return false;
                }
                pre_a = cur_a;
                pre_b = cur_b;
            }

            if (!a.hasNext() && !b.hasNext()) {
                return !diff || replace;
            } else if (a.hasNext()) {
                int cur_a = a.next();
                return (!diff || (ins_a && pre_b == cur_a)) && !a.hasNext();
            } else if (b.hasNext()) {
                int cur_b = b.next();
                return (!diff || (ins_b && pre_a == cur_b)) && !b.hasNext();
            }
            return false;
        }
   

/**
 * Permutation Sequence        
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
Note: Given n will be between 1 and 9 inclusive.

Time ~ O(N^2), Space ~ O(N) 
No need to find out all permutations! We can use the formula:
[i_0 i_1 ... i_{n - 1}] is the kth permutation,
where k = i_0 * (n - 1)! + i_1 * (n - 2)! + ... + i_{n - 1} * 0!
We only need to determine the coefficients i.
Example:
"123"
4. "231": 4 - 1 = 3 = 1 * 2 + 1 * 1 + 0 * 1
5. "312": 5 - 1 = 4 = 2 * 2 + 0 * 1 + 0 * 1
 */
        
        public String getPermutation(int n, int k) {
            // Time: O(N^2) deleteCharAt() takes linear time, Space: O(N)
            // k = i0 x (n-1)! + i1 x (n-2)! + ... + i{n-1} x 0! => "i0 i1 ... i{n-1}"
            StringBuilder num = new StringBuilder(); // "12..n"
            int[] factorial = new int[n + 1]; // stores 0!, 1!,..., n!
            factorial[0] = 1;
            for (int i = 1; i <= n; i++) {
                num.append(i);
                factorial[i] = factorial[i - 1] * i;
            }
            
            k--;    // start from base 0!!
            
            StringBuilder str = new StringBuilder();
            for (int i = n; i >= 1; i--) {
                int index = k / factorial[i - 1];
                str.append(num.charAt(index));
                num.deleteCharAt(index);
                k = k % factorial[i - 1];
            }
            return str.toString();
        }
 /**
  *     restore IP   
  */

        public List<String> restoreIpAddresses(String s) {
            List<String> list = new ArrayList<>();
            dfs(s, 0, new StringBuilder(), list);
            return list;
        }

        private void dfs(String s, int numPt, StringBuilder path, List<String> list) {
            if (numPt == 3) {
                if (isValidIp(s)) list.add(path.toString() + s);
            } else {
                int len = path.length();
                for (int i = 1; i <= 3 && i <= s.length(); i++) {
                    String num = s.substring(0, i);
                    if (isValidIp(num))
                        dfs(s.substring(i), numPt + 1, path.append(num).append('.'), list);
                    path.delete(len, path.length());
                }
            }
        }

        private boolean isValidIp(String s) {
            if (s.length() == 1 || s.length() >= 1 && s.length() <= 3 && !s.startsWith("0")) {
                int num = Integer.parseInt(s);
                if (num >= 0 && num <= 255) return true;
            }
            return false;
        }        
   
   
 /**
  *        Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

  */
  
        
        public String simplifyPath(String path) {
            if (path.length() == 0) return path;
            
            String[] splits = path.split("/");
            Stack<String> stack = new Stack<>();
            for (String s : splits) {
                if (s.equals("..")) {
                    if (!stack.isEmpty())   stack.pop();
                } else if (s.length() == 0 || s.equals(".")) { // "//" and "/./"
                    continue;
                } else {
                    stack.add(s);
                }
            }
            
            StringBuilder str = new StringBuilder();
            if (stack.isEmpty())    stack.add("");
            while (!stack.isEmpty())
                str.append("/").append(stack.remove(0));
            return str.toString();
        }      
        
        /**
         * Word Ladder.
         * 
         * Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
         */
        
        public int ladderLength(String start, String end, Set<String> dict) {
            
            Set<String> map = new HashSet<>(); // record visited words in dict so as not to modify dict
            Queue<String> word = new LinkedList<String>();
            Queue<Integer> depth = new LinkedList<Integer>();
            word.add(start);
            depth.add(1);
            
            // BFS
            while (!word.isEmpty()) {
                String currWord = word.poll();
                int currDepth = depth.poll();
                // return depth if a match is found
                if (currWord.equals(end))    return currDepth;
                // change each letter of currWord
                for (int i = 0; i < currWord.length(); i++) {
                    char[] currWordArr = currWord.toCharArray();
                    // try every possible char and check if there's a match in dict
                    for (char c = 'a'; c <= 'z'; c++) {
                        currWordArr[i] = c;
                        String newWord = new String(currWordArr);
                        if (dict.contains(newWord) && !map.contains(newWord)) {
                            word.add(newWord);
                            depth.add(currDepth + 1);
                            map.add(newWord);
                        }
                    }
                }
            }
            
            return 0; // no match is found in dict
        }
    
        /**
         * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
         * @param start
         * @param end
         * @param dict
         * @return
         */
        public List<List<String>> findLadders(String start, String end, Set<String> dict) {
            Queue<String> word = new LinkedList<>();
            Map<String, Integer> map = new HashMap<>(); // hashmap<word, depth>
            word.add(start);
            map.put(start, 1);
            
            // BFS: find all the paths of transformation and store the depths in a HashMap
            int minDepth = Integer.MAX_VALUE;
            while (!word.isEmpty()) {
                String currWord = word.poll();
                int currDepth = map.get(currWord);
                if (currDepth >= minDepth)   continue;   // skip adding words to queue
                if (currWord.equals(end))   {
                    minDepth = Math.min(minDepth, currDepth);
                    continue;
                }
                for (int i = 0; i < currWord.length(); i++) {
                    char[] currWordArr = currWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        currWordArr[i] = c;
                        String newWord = new String(currWordArr);
                        if (dict.contains(newWord) && !map.containsKey(newWord)) {
                            word.add(newWord);
                            map.put(newWord, currDepth + 1);
                        }
                    }
                }
            }
            
            // DFS: backtracking from the start to put all the paths into the List
            List<List<String>> pathSet = new ArrayList<List<String>>();
            List<String> path = new ArrayList<>();        
            path.add(start);
            dfs(start, end, map, path, pathSet);
            return pathSet;
        }

        // DFS (backtracking)
        private void dfs(String start, String end, Map<String, Integer> map, List<String> path, List<List<String>> pathSet) {
            if (start.equals(end)) {
                pathSet.add(new ArrayList<String>(path));   // need to initialize a new ArrayList!!
                return;
            }
            int currDepth = map.get(start);
            for (int i = 0; i < start.length(); i++) {
                char[] currWordArr = start.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    currWordArr[i] = c;
                    String newWord = new String(currWordArr);
                    if (map.containsKey(newWord) && map.get(newWord) == currDepth + 1) {
                        int size = path.size();
                        path.add(newWord);
                        dfs(newWord, end, map, path, pathSet);
                        path.remove(size);
                    }
                }
            }
        }        
        
       /** No. 118
        *  Pascal's Triangle
        *  
        *   Time ~ O(k), Space ~ O(k^2 = 1+2+...+k) 
If col == 0 || col == row, T[row][col] = 1；
Otherwise, T[row][col] = T[row - 1][col - 1] + T[row - 1][col].
        */
        
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> listSet = new ArrayList<List<Integer>>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i)   list.add(1);
                    else                    list.add(listSet.get(i - 1).get(j - 1) + listSet.get(i - 1).get(j));
                }
                listSet.add(list);
            }
            return listSet;
        }
        
        /**
         * No. 119
         * Generate kth Row of Pascal's Triangle
         * @param rowIndex
         * @return
         */
        public List<Integer> getRow(int rowIndex) {
            List<Integer> list = new ArrayList<Integer>();
            List<Integer> prev = new ArrayList<Integer>();
            for (int i = 0; i <= rowIndex; i++) {
                list = new ArrayList<Integer>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i)   list.add(1);
                    else                    list.add(prev.get(j - 1) + prev.get(j));
                }
                prev = new ArrayList<Integer>(list);
            }
            return list;
        }

        
        
/**
 * No. 20
 * Valid Parentheses        
 */
        
        public static final Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};

public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (Character c : s.toCharArray()) {
        if (stack.isEmpty() || map.containsKey(c)) {
            stack.push(c);
        }
        else {
            if (!c.equals(map.get(stack.pop()))) return false;
        }
    }
    return stack.isEmpty();
}


 /**
  * No. 139
  * WordBreak
  * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
For example, given
s = "leetcode",
dict = ["leet", "code"].
Return true because "leetcode" can be segmented as "leet code".

1. 1-d DP: Time ~ O(N^2), Space ~ O(N)
Let d(i) = true iff str[0, i - 1] is breakable.
d(i) = d(j) && str[j, i - 1] \in dict,   0 <= j <= i - 1
Return d(N).
  *        
  */
        public boolean wordBreak(String s, Set<String> dict) {
            // d[i]: s[0..i-1] is breakable
            // d(i) = d(j) && s[j, i], j = 0..i-1
            int n = s.length();
            boolean[] f = new boolean[n + 1];
            f[0] = true;
            
            for (int i = 1; i <= n; i++)
                for (int j = 0; j < i; j++)
                    if (f[j] == true && dict.contains(s.substring(j, i))) {
                        f[i] = true;
                        break;
                    }
            return f[s.length()];
        }
 
        
 /*       
  * Word break II
  * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
  */
        
        public List<String> wordBreak2(String s, Set<String> wordDict) {
            int n = s.length();
            boolean[] d = new boolean[n + 1];
            d[0] = true;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j < i; j++) {
                    d[i] = d[j] && wordDict.contains(s.substring(j, i));
                    if (d[i])   break;
                }
            }
            
            List<String> listSet = new ArrayList<>();
            if (d[n])   dfs(s, 0, wordDict, new StringBuilder(), listSet);
            return listSet;
        }

        // DFS
        private void dfs(String s, int j, Set<String> wordDict, StringBuilder str, List<String> listSet) {
            if (j == s.length()) {
                listSet.add(str.toString());
            } else {
                for (int i = j + 1; i <= s.length(); i++) {
                    if (wordDict.contains(s.substring(j, i))) {
                        int len = str.length();
                        if (len != 0)   str.append(" ");
                        str.append(s.substring(j, i));
                        dfs(s, i, wordDict, str, listSet);
                        str.delete(len, str.length());
                    }
                }
            }
        }
        
/**
 * No. 150
 * Evaluate Reverse Polish Notation 
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 */
	private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

	public int evalRPN(String[] tokens) {
	    Stack<Integer> stack = new Stack<>();
	    for (String s : tokens) {
	        if (OPERATORS.contains(s)) {
	            int y = stack.pop();
	            int x = stack.pop();
	            stack.push(eval(x, y, s));
	        } else {
	            stack.push(Integer.parseInt(s));
	        }
	    }
	    return stack.pop();
	    
	}

	private int eval(int x, int y, String operator) {
	    switch (operator) {
	        case "+":   return x + y;
	        case "-":   return x - y;
	        case "*":   return x * y;
	        default :   return x / y;   // case "/":
	    }
	}
	
/**
 * No. 151
 * Reverse Words in a String
 */
	// is there is leading or trailing
	public String reverseWords(String s) {
	    Stack<String> set = new Stack<>();
	    int start = 0;
	    boolean prevIsChar = false;
	    for (int i = 0; i < s.length(); i++) {
	        if (!prevIsChar && s.charAt(i) != ' ') {   // find the start of a word
	            prevIsChar = true;
	            start = i;
	        }
	        if (prevIsChar && s.charAt(i) == ' ') {    // find the end of a word, and add it to the stack
	            prevIsChar = false;
	            set.add(s.substring(start, i));
	        }
	    }
	    if (prevIsChar && s.charAt(s.length()-1) != ' ') { // add the last word (without space afterwards)
	        set.add(s.substring(start));
	    }

	    StringBuilder rev = new StringBuilder();
	    while (!set.isEmpty()) {
	        rev.append(set.pop());
	        if (!set.isEmpty()) rev.append(" ");
	    }
	    return rev.toString();
	}
	// another way inplace reverse
	
	public void reverseWords2(char[] s) {
	    reverse(s, 0, s.length - 1);
	    
	    int start = 0;
	    for (int i = 0; i <= s.length; i++) {
	        if (i == s.length || s[i] == ' ') {
	            reverse(s, start, i - 1);
	            start = i + 1;
	        }
	    }
	}

	private void reverse(char[] c, int start, int end) {
	    while (start < end) {
	        char temp = c[start];
	        c[start++] = c[end];
	        c[end--] = temp;
	    }
	}
	
	//Third Way
	public String reverseStr(String str) {
		StringBuilder reverse = new StringBuilder();
		str.trim();

		int j = str.length();
		for (int i = str.length() - 1; i == 0; i--) {
			if (str.charAt(i) == ' ') {
				j = i;
			} else if (i == 0 || str.charAt(i - 1) == ' ') {
				reverse.append(str.substring(i, j));
			}
		}

		return reverse.toString();
	}
	
/**
 * No. 161
 * OneEditDistance	
 * 
 * Given two strings S and T, determine if they are both one edit distance apart.

Solution

Time ~ O(N), Space ~ O(1) 
One Edit Distance 有三种情况：
Append: S = "abcde", T = "abcdeX"
Modify: S = "abcde", T = "abXde"
Insert: S = "abcde", T = "abcXde"
 * 
 */

	public boolean isOneEditDistance(String s, String t) {
	    int M = s.length(), N = t.length();
	    if (M > N)  return isOneEditDistance(t, s);
	    if (N - M > 1)  return false;
	    
	    int i = 0;
	    while (i < M && s.charAt(i) == t.charAt(i)) i++;
	    if (i == M) return N - M == 1;   // Append case
	    if (M == N) {                    // Modify case
	        i++;
	        while (i < M && s.charAt(i) == t.charAt(i)) i++;
	    } else /* if (N - M == 1) */ {   // Insert case
	        while (i < M && s.charAt(i) == t.charAt(i + 1)) i++;
	    }
	    return i == M;
	}
	
	/**
	 * No.205
	 * Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Hash Table: Time ~ O(2N), Space ~ O(26) 
判断两个 String 是否同构：将 s 的每个字母替换成 t 中对应位置的字母，如果同一字母对应多个替换字母，则不是同构；同样要将 t 替换成 s，判断其是否存在一对多的对应。如果 s -> t 和 t -> s 中的字母替换都是一一对应，则 s 和 t 为同构。
	 */
	public boolean isIsomorphic(String s, String t) {
	    int n = s.length(), nt = t.length();
	    if (n != nt)    return false;
	    
	    // change s to t 
	    Map<Character, Character> map = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	        char cs = s.charAt(i), ct = t.charAt(i);
	        if (!map.containsKey(cs))   map.put(cs, ct);
	        else if (map.get(cs) != ct) return false;
	    }
	    
	    // change t to s
	    map.clear();
	    for (int i = 0; i < n; i++) {
	        char cs = s.charAt(i), ct = t.charAt(i);
	        if (!map.containsKey(ct))   map.put(ct, cs);
	        else if (map.get(ct) != cs) return false;
	    }
	    
	    return true;
	}
	/**
	 * A list of Palindrome related questions http://wlcoding.blogspot.com/2015/03/palindrome-i-valid-num-ii-valid-str-iii.html?view=sidebar
	 * No. 214
	 * Shortest Palindrome
	 * 从中间 s[ (n - 1) / 2] 处开始向左（右边的不用考虑）对每个字符 expand，奇偶的情况都要考虑，如果遇到 palindrome 即为 LPS（因为之后搜到的长度只会更短）。然后将 LPS 之后的 substring 反转加在 s 的前面。
该方法虽然对上题的 Two Pointers 进行了改进，但时间依然是 O(N^2) worst-case，所以不能通过 OJ 中有一个包含大量重复字母的 case。
	 */
	public String shortestPalindrome(String s) {
	    int n = s.length();
	    if (n == 0) return s;
	    
	    int end = 0;
	    for (int i = (n - 1) / 2; i >= 0; i--) {
	        if (isPalindrome(s, 0, 2 * i)) { // [0..i-1] i [i+1..2i]
	            end = 2 * i + 1;
	            break;
	        }
	        if (isPalindrome(s, 0, 2 * i - 1)) {   // [0..i-1] [i..2i-1]
	            end = 2 * i;
	            break;
	        }
	    }
	    if (end == n) return s;
	    StringBuilder str = new StringBuilder(s.substring(end));
	    str.reverse();
	    str.append(s);
	    return str.toString();
	}

	private boolean isPalindrome(String s, int left, int right) { // shrink from two side
	    while (left < right && s.charAt(left) == s.charAt(right)) {
	        left++;
	        right--;
	    }
	    if (left < right)   return false;
	    else                return true;
	}
	/**
	 * Longest Palindrome
	 * palindrome 分两种情况，奇数字符和偶数字符。
选定一个 character，然后以其为中心向两边搜索。
odd palindrome: expandAroundCenter(s, i, i);
even palindrome: expandAroundCenter(s, i, i + 1).
	 * @param s
	 * @return
	 */
	
	public String longestPalindrome(String s) {
	    int start = 0, end = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int len1 = expandAroundCenter(s, i, i);
	        int len2 = expandAroundCenter(s, i, i + 1);
	        int len = Math.max(len1, len2);
	        if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2 + 1;
	        }
	    }
	    return s.substring(start, end);
	}

	private int expandAroundCenter(String s, int left, int right) {
	    int L = left, R = right;
	    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        L--; R++;
	    }
	    return (R - 1) - (L + 1) + 1;
	}

	
	/* No. 125
	 * Given a string, determine if it is a palindrome, considering only
	 * alphanumeric characters and ignoring cases. For example,
	 * "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a
	 * palindrome.
	 */
	public boolean isPalindrome(String str) {
		int i = 0, j = str.length() - 1;
		while (i < j) {
			while (i < j && !Character.isLetterOrDigit(str.charAt(i)))
				i++;
			while (i < j && !Character.isLetterOrDigit(str.charAt(j)))
				j--;
			char head = str.charAt(i);
			char tail = str.charAt(j);
			if (head != tail)
				return false;
			i++;
			j--;
		}
		return true;

	}

	
/**
 * Palindrome Partitioning	
 */
	private List<List<String>> listSet = new ArrayList<List<String>>();

	public List<List<String>> partition(String s) {
	    dfsPartition(s, 0, new ArrayList<String>());
	    return listSet;
	}

	private void dfsPartition(String s, int start, ArrayList<String> list) {
	    if (start >= s.length())
	        listSet.add(new ArrayList<String>(list));
	    
	    for (int i = start; i < s.length(); i++) {
	        if (isPalindrome(s, start, i)) {
	            list.add(s.substring(start, i + 1));
	            dfsPartition(s, i + 1, list);
	            list.remove(list.size() - 1);
	        }
	    }
	    
	}

	
	/**
	 * No. 28
	 * implement strstr
	 */

	public int strStr(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}

	/**
	 * int Data to String
	 * 
	 * for example 19810707 to 07/07/1981
	 */

	public String intDataToString(int num) {
		StringBuilder sb = new StringBuilder();
		int year = num / 10000;
		num = num % 10000;
		int day = num % 100;
		int month = num / 100;

		if (month < 10) {
			sb.append("0");
		}
		sb.append(String.valueOf(month));
		sb.append("/");
		if (day < 10)
			sb.append("0");
		sb.append(String.valueOf(day));
		sb.append("/");

		sb.append(year);

		return sb.toString();
	}

	/**
	 * No. 49 Anagrams get all te anagrams from a collection of words
	 * 
	 */

	public List<List<String>> groupAnagrams(List<String> words) {
        Comparator<String> StringComparator = new Comparator<String>() {

			public int compare(String fruit1, String fruit2) {
			
			String fruitName1 = fruit1.toUpperCase();
			String fruitName2 = fruit2.toUpperCase();
			
			//ascending order
			return fruitName1.compareTo(fruitName2);
			
			//descending order
			//return fruitName2.compareTo(fruitName1);
			}
		};
		Map<String, List<String>> map = new HashMap<>();
		List<List<String>> result = new LinkedList<> ();
		for (String str : words) {
			char[] temp = str.toCharArray();
			Arrays.sort(temp);
			System.out.println(temp);
			if (map.containsKey(temp.toString())) {
				map.get(temp.toString()).add(str);
			} else {
				ArrayList<String> al = new ArrayList<String>();
				al.add(str);
				map.put(temp.toString(), al);
			}

		}
		
        for (Map.Entry<String, List<String>> entry : map.entrySet()){
            List<String> temp = entry.getValue();
            Collections.sort(temp,StringComparator);
            result.add(temp);
        }
        
        return result;
	}

	/**
	 * No 38 Count and Say The count-and-say sequence is the sequence of
	 * integers beginning as follows: 1, 11, 21, 1211, 111221, ...
	 * 
	 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is
	 * read off as "one 2, then one 1" or 1211. Given an integer n, generate the
	 * nth sequence.
	 * 
	 * Note: The sequence of integers will be represented as a string.
	 * 
	 * @param n
	 * @return
	 */
	public String countAndSay(int n) {
        if (n <=0 ) return null;
        String num = "1";
        int i = 1;
        while(i<n){
            int count = 0;
            char index = num.charAt(0);
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<num.length(); j++){
                if(num.charAt(j) == index) count++;
                else{
                    sb.append(count);
                    sb.append(index);
                    index = num.charAt(j);
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(index);
            num = sb.toString();
            i++;
        }
        
        return num;

	}

	// recursive method
	public String countAndSay2(int n) {
		if (n <= 0)
			return null;
		if (n == 1)
			return "1";
		String num = countAndSay2(n - 1);

		int i = 0;
		char index = num.charAt(i);
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while (i < num.length()) {
			if (num.charAt(i) == index) {
				count++;
			} else {
				sb.append(Integer.toString(count));
				sb.append(index);
				index = num.charAt(i);
				count = 1;
			}
			i++;
		}
		sb.append(Integer.toString(count));
		sb.append(index);
		return sb.toString();
	}

	public String longestCommonPrefix(String[] strs) {

        StringBuilder lcp = new StringBuilder();
        if(strs.length == 0 || strs == null) return lcp.toString();
        int start = 0;
        while(start < strs[0].length()){
            char temp = strs[0].charAt(start);
            for (int i=1; i<strs.length; i++){
                if( start< strs[i].length() && strs[i].charAt(start) == temp ){
                    continue;
                }else{
                    return lcp.toString();
                }
            }
            start++;
            lcp.append(temp);
        }
        return lcp.toString();

	}

	/**
	 * No. 165 Compare Version Numbers Compare two version numbers version1 and
	 * version2. If version1 > version2 return 1, if version1 < version2 return
	 * -1, otherwise return 0.
	 * 
	 * You may assume that the version strings are non-empty and contain only
	 * digits and the . character. The . character does not represent a decimal
	 * point and is used to separate number sequences. For instance, 2.5 is not
	 * "two and a half" or "half way to version three", it is the fifth
	 * second-level revision of the second first-level revision.
	 * 
	 * Here is an example of version numbers ordering:
	 * 
	 * 0.1 < 1.1 < 1.2 < 13.37
	 */

	public int compareVersion(String version1, String version2) {
        String [] v1 = version1.split("\\.");
        String [] v2 = version2.split("\\.");
        
        int len = v1.length > v2.length ? v1.length:v2.length;
        for(int i = 0; i<len; i++){
            int v1_val = i < v1.length ? Integer.valueOf(v1[i]) : 0;
            int v2_val = i < v2.length ? Integer.valueOf(v2[i]) : 0;
            
            if(v1_val < v2_val){
                return -1;
            }else if (v2_val < v1_val){
                return 1;
            }else{
                continue;
            }
        }
        
        return 0;
	}





	/**
	 * convert string to number The atoi function first discards as many
	 * whitespace characters as necessary until the first non-whitespace
	 * character is found. Then, starting from this character, takes an optional
	 * initial plus or minus sign followed by as many numerical digits as
	 * possible, and interprets them as a numerical value. The string can
	 * contain additional characters after those that form the integral number,
	 * which are ignored and have no effect on the behavior of this function. If
	 * the first sequence of non-whitespace characters in str is not a valid
	 * integral number, or if no such sequence exists because either str is
	 * empty or it contains only whitespace characters, no conversion is
	 * performed. If no valid conversion could be performed, a zero value is
	 * returned. If the correct value is out of the range of representable
	 * values, the maximum integer value (2147483647) or the minimum integer
	 * value (–2147483648) is returned.
	 * 
	 * @param str
	 * @return
	 */
	public int atoi(String str) {

		int maxDiv10 = Integer.MAX_VALUE / 10;
		if (str == null)
			return 0;
		str = str.trim();
		if (str.length() == 0)
			return 0;

		int flag = 1;
		int i = 0;
		if (str.charAt(0) == '-') {
			i++;
			flag = -1;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		int value = 0;
		while (i < str.length() && Character.isDigit(str.charAt(i))) {
			// pay attention to the overflow
			if (value > maxDiv10 || value == maxDiv10
					&& Character.getNumericValue(str.charAt(i)) >= 8) {
				return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}

			value = value * 10 + Character.getNumericValue(str.charAt(i));
			i++;
		}

		return value * flag;
	}

	/**
	 * Validate if a given string is numeric. "0" -> true, "0.1" -> true, "abc"
	 * -> false. Q: How to account for whitespaces in the string? A: When
	 * deciding if a string is numeric, ignore both leading and trailing
	 * whitespaces. Q: Should I ignore spaces in between numbers – such as “1
	 * 1”? A: No, only ignore leading and trailing whitespaces. “1 1” is not
	 * numeric. Q: If the string contains additional characters after a number,
	 * is it considered valid? A: No. If the string contains any non-numeric
	 * characters (excluding whitespaces and decimal point), it is not numeric.
	 * Q: Is it valid if a plus or minus sign appear before the number? A: Yes.
	 * “+1” and “-1” are both numeric. Q: Should I consider only numbers in
	 * decimal? How about numbers in other bases such as hexadecimal (0xFF)? A:
	 * Only consider decimal numbers. “0xFF” is not numeric. Q: Should I
	 * consider exponent such as “1e10” as numeric? A: No. But feel free to work
	 * on the challenge that takes exponent into consideration. (The Online
	 * Judge problem does take exponent into account.)
	 */

	public boolean isNumber(String str) {
		int i = 0, n = str.length();
		while (i < n && Character.isWhitespace(str.charAt(i)))
			i++;
		if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-'))
			i++;
		boolean isNum = false;
		while (i < n && Character.isDigit(str.charAt(i))) {
			i++;
			isNum = true;
		}
		if (i < n && (str.charAt(i) == '.' || str.charAt(i) == 'e')) {
			i++;
			while (i < n && Character.isDigit(str.charAt(i))) {
				i++;
				isNum = true;
			}
		}

		// if (isNum && i < n && str.charAt(i) == 'e') {
		// i++;
		// isNum = false;
		// if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-'))
		// i++;
		// while (i < n && Character.isDigit(str.charAt(i))) {
		// i++;
		// isNum = true;
		// }
		// }
		while (i < n && Character.isWhitespace(str.charAt(i)))
			i++;
		return isNum && i == n;
	}

	/**
	 * Given a string, find the length of the longest substring without
	 * repeating characters. For example, the longest substring without
	 * repeating letters for “abcabcbb” is “abc”, which the length is 3. For
	 * “bbbbb” the longest substring is “b”, with the length of 1.
	 * 
	 * two methods: 1. How can we look up if a character exists in a substring
	 * instantaneously? The answer is to use a simple table to store the
	 * characters that have appeared. Make sure you communicate with your
	 * interviewer if the string can have characters other than ‘a’–‘z’. (ie,
	 * Digits? Upper case letter? Does it contain ASCII characters only? Or even
	 * unicode character sets?) 2. Instead of using a table to tell if a
	 * character exists or not, we could define a mapping of the characters to
	 * its index. Then we can skip the characters immediately when we found a
	 * repeated character.
	 */

	public int lengthOfLongestSubstring(String s) {
		int[] charMap = new int[256];
		Arrays.fill(charMap, -1);
		int i = 0, maxLen = 0;
		for (int j = 0; j < s.length(); j++) {
			if (charMap[s.charAt(j)] >= i) {
				i = charMap[s.charAt(j)] + 1;
			}
			charMap[s.charAt(j)] = j;
			maxLen = Math.max(j - i + 1, maxLen);
		}
		return maxLen;
	}

	/**
	 * No. 67 add Binary Given two binary strings, return their sum (also a
	 * binary string).
	 * 
	 * For example, a = "11" b = "1" Return "100".
	 */

	public String addBinary(String a, String b) {
	    int carry = 0, i = a.length()-1, j = b.length()-1, x , y;
	    StringBuilder sum = new StringBuilder("");
	    while (i >=0 || j >=0 || carry != 0) {
	        x = i < 0 ? 0 : a.charAt(i--) - '0';
	        y = j < 0 ? 0 : b.charAt(j--) - '0';
	        sum.insert(0,(x + y + carry)%2);
	        carry = (x + y + carry) / 2; 
	    }
	    return sum.toString();
	 }

	/**
	 * Permutate String 
	 * Implement a routine that prints all possible orderings of the characters in a string.
 * In other words, print all permutations that use all the characters from the original string.
	 */
	
	public void permuate(String str){
		int length = str.length();
		int level = 0;
		char [] in = str.toCharArray();
		String out = new String();
		boolean [] map = new boolean[length];
		getPermuate(in, out, length, level, map);
	}
	
	private void getPermuate(char[] in, String out, int length, int level, boolean[] map) {
		if(level == length){
			System.out.println(out.toString());
			return;
		}
		for(int i = 0; i<length ; i++){
			if(map[i] == true) continue;
			map[i] = true;
			out = out + in[i];
			getPermuate(in, out, length, level+1, map);
			map[i] = false;
			out = out.substring(0, out.length()-1) ;
		}
		
	}

/**
 *  do combination of the string
 *  Implement a function that prints all possible combinations of the characters in a string.
 * These combinations range in length from one to the length of the string.
 * Two combinations that differ only in ordering of their characters are the same combination.	
 */
	
	public void combination(String str){
		int length = str.length();
		char [] in = str.toCharArray();
		String out = new String();
		int level = 0;
		getCombination(in,out,level,length);
	}
	
	private void getCombination(char[] in, String out, int level, int length) {
		for(int i = level; i< length ; i++){
			out += in[i];
			System.out.println(out.toString());
			
			if(i+1 <length){
				getCombination(in, out, i+1, length);
			}
			out = out.substring(0, out.length()-1);
		}
	
}

	/**
	 * No. 157 No.158
	 * int read4(char *buf) reads 4 characters at a time from a file. The return
	 * value is the actual number of characters read. For example, it returns 3
	 * if there is only 3 characters left in the file. By using the read4 API,
	 * implement the function int read(char *buf, int n) that reads n characters
	 * from the file.
	 * 
	 */
	public int read4(char[] buf) {
		return 0;
	}
    /**
     * No. 157 No.158
     * int read4(char *buf) reads 4 characters at a time from a file. The return
     * value is the actual number of characters read. For example, it returns 3
     * if there is only 3 characters left in the file. By using the read4 API,
     * implement the function int read(char *buf, int n) that reads n characters
     * from the file.
     *
     *
     * Time ~ O(N), Space ~ O(1)
     考虑两个条件：
     read4(buffer) 是否到达文件末尾 (eof) ？
     一共读取的字符数是否到达 n (numRead < n)？
     * @param buf
     *            Destination buffer
     * @param n
     *            Maximum number of characters to read
     * @return The number of characters read
     */
	public int read(char[] buf, int n) {
		char[] buffer = new char[4];
		int readBytes = 0;
		boolean eof = false;
		while (!eof && readBytes < n) {
			int sz = read4(buffer);
			if (sz < 4)
				eof = true;
			int bytes = Math.min(n - readBytes, sz);
			System.arraycopy(buffer /* src */, 0 /* srcPos */, buf /* dest */,
					readBytes /* destPos */, bytes /* length */);
			readBytes += bytes;
		}
		return readBytes;
	}

	// calling read4 multiple times. 多次调用 read() 时可能会发生：之前一次调用中从 read4() 读取的字符未使用完（已经到达 n bytes），
	//所以要将 buffer 设为全局变量，并记录其大小 buffersize 和上次读到的位置 offset，
	//当 buffer 为空 (buffersize == 0) 时才调用 read4()，并且每次从 buffer 的 offset 开始拷贝到 buff。
	
    private char[] buffer = new char[4];
    private int offset = 0, bufsize = 0;
    
    public int readMultiple(char[] buf, int n) {
        int numRead = 0;
        boolean eof = false;
        while (!eof && numRead < n) {
            if (bufsize == 0) {
                bufsize = read4(buffer);
                eof = bufsize < 4;
            }
            int bytes = Math.min(n - numRead, bufsize);
            System.arraycopy(buffer, offset, buf, numRead, bytes);
            offset = (offset + bytes) % 4;
            bufsize -= bytes;
            numRead += bytes;
        }
        return numRead;
    }
	

	/**
	 * No.187
	 *All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
	 */
	
	public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        HashSet<String> strSet = new HashSet<String>();
        HashSet<Integer> numSet = new HashSet<Integer>();
        for(int i=0; i<s.length() - 9; i++) {

            String substr = s.substring(i, i + 10);
            int curNum = encode(substr);
            if(!numSet.contains(curNum)) {
                numSet.add(curNum);
            } else {
                strSet.add(substr);
            }
        }
        for(String str : strSet) {
            res.add(str);
        }
        return res;
    }
    public int encode(String s) {//Use bit manipulation to avoid MLE
        int sum = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == 'A') {
                sum *= 4;
            } else if(s.charAt(i) == 'C') {
                sum = sum * 4 + 1;
            } else if(s.charAt(i) == 'G') {
                sum = sum * 4 + 2;
            } else if(s.charAt(i) == 'T') {
                sum = sum * 4 + 3;
            }
        }
        return sum;
    }
    
    
    /**No. 76
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
    
    
    
     */
   /* 
    *  have to maintain two arrays one is to check T, the other is to check S. one array or map is not working,
    *  Because for case like "bba", "ab", you have to check the counts of how many you have seen for charactoer "b"
    *  
    */
 
    
    /**
     * No. 6
     * @return
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     * 
     * 
     */
    public String convert(String s, int numRows) {
        if(numRows<=1) return s;
        StringBuilder sb = new StringBuilder();
        int num = 2*numRows - 2; 
        int mod = 0;
        while(mod<numRows) {
            for(int i=0; i<s.length(); i++) {
                if(i%num==mod || i%num==(num-mod)) {
                    sb.append(s.charAt(i));
                }
            }
            mod++;
        }
        return sb.toString();
    }
    
    public String minWindow(String S, String T) {
        if(S==null||S.isEmpty()||T==null||T.isEmpty()) return "";
        int i=0, j=0;
        int[] Tmap=new int[256];
        int[] Smap=new int[256];
        for(int k=0; k< T.length(); k++){
            Tmap[T.charAt(k)]++;
        }
        int found=0;
        int length=Integer.MAX_VALUE;
        String res="";
        while(j<S.length()){
            if(found<T.length()){
                if(Tmap[S.charAt(j)]>0){
                    Smap[S.charAt(j)]++;
                    if(Smap[S.charAt(j)]<=Tmap[S.charAt(j)]){
                        found++;
                    }
                }
                j++;
            }
            while(found==T.length()){
                if(j-i<length){
                    length=j-i; res=S.substring(i,j);
                }
                if(Tmap[S.charAt(i)]>0){
                    Smap[S.charAt(i)]--;
                    if(Smap[S.charAt(i)]<Tmap[S.charAt(i)]){
                        found--;
                    }
                }
                i++;
            }
        }
        return res;
    }
    
    /**
     * No. 10
     * Regular Expression Matching
     * '.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
     * @param s
     * @param p
     * @return
     */
    
    public boolean isMatch(String s, String p) {
        int lenS = s.length(), lenP = p.length();
    boolean[][] d = new boolean[lenS + 1][lenP + 1]; // i, j are the lengths of s[0..i-1] and p[0..j-1]
    d[0][0] = true;
    // initialize the first row
    for (int j = 1; j <= lenP; j++) {
        if (p.charAt(j - 1) == '*') d[0][j] = d[0][j - 2]; // * is deletion
    }
    // fill up the table
    for (int i = 1; i <= lenS; i++) {
        for (int j = 1; j <= lenP; j++) {
            if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.')
                d[i][j] = d[i - 1][j - 1];
            else if (p.charAt(j - 1) == '*') {
                if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
                    d[i][j] = d[i][j - 2] || d[i - 1][j]; // * is deletion or repetition
                else
                    d[i][j] = d[i][j - 2]; // * is deletion
            }
        }
    }
    return d[lenS][lenP];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StrFun sf = new StrFun();
		ArrayList<String> al = new ArrayList<String>(Arrays.asList("abc",
				"cba", "123", "342", "321"));
//		System.out.println(sf.getAllAnagrams(al));
//		sf.permuate("1234");
		sf.combination("123");
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('a', 1);
		map.put('b', 2);
		
		System.out.println(map.get('a'));

	}

}