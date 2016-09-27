package test;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// Hello!

// encode(String) -> integer
//   encode("abc") -> 123
//   a -> 1
//   b -> 2
//   c -> 3
//   ...
//   z -> 26

// Write the decode function
// decode can take either String "123" or int 123
// decode(int) -> String
// decode(1) -> a
// decode(123) -> {"abc", "lc", "aw"}


// 1023

class Solution {
  
  //'a'+num
  public List <String> decode(String str){
    List <String> set = new ArrayList<String>();
    StringBuilder sb = new StringBuilder();
    if(str == null || str.length() == 0) return set;
    helper(set, sb, str, 0);
    return set;
  }
  
  public void helper(List <String> list, StringBuilder sb, String str, int start){
    if(start >= str.length()){
      list.add(sb.toString());
      return;
    }
    int i = start;
      String temp = mapping(str.charAt(i) + "");
      if(temp == null) return;
      int len = sb.length();
      sb.append(temp);
      helper(list, sb, str, i+1);
      sb = new StringBuilder(sb.substring(0,len));
      // two chars
//      System.out.println(" onechar : "+sb);
    
      if(i< str.length()-1){
        temp = mapping(str.substring(i,i+2));
        if(temp == null) return;
        len = sb.length();
        sb.append(temp);
        helper(list, sb, str,i+2);
        sb = new StringBuilder(sb.substring(0, len));
      }     
  }
  
  public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
  
  public String mapping(String str){
    Map<String, String> map = new HashMap<String, String>();
    for (int i=1; i<alphabet.length()+1; i++) {
      map.put(Integer.toString(i), alphabet.substring(i-1, i));
    }
    if (map.containsKey(str)) {
      return map.get(str);
    } else {
      return null;
    }
  }
  
  public static void main(String[] args) {
    Solution sl = new Solution();
    List <String> strings = sl.decode("123");
//    List <String> strings = sl.decode("103");
  //  List <String> strings = sl.decode("013");

    for (String string : strings) {
      System.out.println(string);
    }
  }
}
