package snap;

import com.interview.graph.StdOut;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Arrays;

/**
 * Created by lshi4 on 8/21/16.
 */
public class BigInteger {
    private boolean positive = true;
    private int [] numbers; // reverse position

    BigInteger(String str){
        if(str == null) return;
        if(str.charAt(0) == '-') {
            numbers = new int [str.length()-1];
        }else{
            numbers = new int [str.length()];
        }

        for(int i = numbers.length-1; i>=0; i--){
            int num = str.charAt(i)-'0' ;
            if ( num>=0 && num <=9){
                numbers[i] = num;
            }else{
                System.out.println("error");
            }

        }
    }

    public String doubleMinuse(){
        if(positive == false){
            return "-"+doubleAdd();
        }
        for(int i = numbers.length -1; i>=0; i--){
            if(numbers[i]==0){
                numbers[i]=9;
                continue;
            }else{
                numbers[i] = numbers[i]-1;
                break;
            }
        }
        return TrimZero();

    }

    private String TrimZero(){
        int count = 0;
        StringBuilder strNum = new StringBuilder();
        int i = 0;
        for(;i<=numbers.length-1; i++){
            if(numbers[i] == 0) continue;
            break;
        }
        for(; i< numbers.length; i++){
            strNum.append(numbers[i]);
        }
        return strNum.toString();
    }

    public String doubleAdd (){
        return "";
    }

    public static void main(String[] args){
        BigInteger x = new BigInteger("1023");
        System.out.println(x.positive);
        System.out.println(Arrays.toString(x.numbers));
        System.out.println(x.doubleMinuse());

    }


}
