package snap;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by lshi4 on 8/23/16.
 */
public class MixMatrix {

    public static void main(String [] args){

//        int [][] matrix = new int [][]{
//                {1,2,3,4,5},
//                {6,7,8,9,10},
//                {11,12,13,14,15},
//                {16,17,18,19,20}
//        };
//
//        printMatrix(matrix);

        System.out.println(isPartAnagram("abcdefg", "def"));
        System.out.println(isPartAnagram("fefage", "fae"));
        System.out.println(isPartAnagram("fefage", "cae"));
    }

    public static void printMatrix(int [][] matrix){
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        if(n == 0) return;

        for(int i=0;i<m;i++){
            int j = 0;
            int p = i;
            while( p>=0 && j<n){
                System.out.print(matrix[p][j]);
                System.out.print(',');
                p--;
                j++;
            }

            System.out.println();

        }

        for (int i = 1; i<n; i++){
            int j = m-1;
            int p = i;
            while(j>=0 && p<=n-1){
                System.out.print(matrix[j][p]);
                System.out.print(',');
                j--;
                p++;
            }
            System.out.println();
        }
    }


    public static boolean isPartAnagram (String A, String B){
        int a_len = A.length();
        int b_len = B.length();

        if(a_len < b_len) return false;
        for(int i = 0; i<= (a_len-b_len); i++){
            String c = A.substring(i,i+b_len);
            String sort_c = strSort(c);
            if(sort_c.equalsIgnoreCase(strSort(B)))
                return true;
        }

        return false;

    }

    public static String strSort (String str){
        char [] char_arrays = str.toCharArray();
        Arrays.sort(char_arrays);
        return new String(char_arrays);
    }

    public static boolean isPartAnagram2 (String A, String B) {
        int a_len = A.length();
        int b_len = B.length();

        if (a_len < b_len) return false;

        HashMap<Character, Integer> map_b = new HashMap<>();
        for (char i : B.toCharArray()) {
            if (map_b.containsKey(i)) {
                map_b.put(i, map_b.get(i).intValue() + 1);
            } else {
                map_b.put(i, 1);
            }
        }

        for (int i = 0; i < b_len; i++) {
            char key = A.charAt(i);
            if (map_b.containsKey(key)) {
                int temp = map_b.get(key) - 1;
                if (temp == 0) {
                    map_b.remove(key);
                } else {
                    map_b.put(key, map_b.get(i));
                }
            } else {
                map_b.put(key, -1);
            }
        }

        if (map_b.size() == 0) return true;

        for (int i = 1, j = b_len; j < a_len; i++, j++) {

            char key = A.charAt(i - 1);
            if (map_b.containsKey(key)) {
//                map_b

            }
        }

        for (int i = 0, j = b_len - 1; j <= a_len - 1; i++, j++) {
            for (int m = i; m <= j; m++) {

            }
        }

        return false;
    }

}
