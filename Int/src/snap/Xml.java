package snap;

/**
 * Created by lshi4 on 8/22/16.
 */
// XML PARSER
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Employment.
 *
 * If you need more classes, simply define them inline.
 */


class Xml {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<String> XML = new ArrayList<>();
        XML.add("open/html");
        XML.add("open/id");
        XML.add("text/robert");
        XML.add("close/id");
        XML.add("open/meta");
        XML.add("text/scarlett");
        XML.add("close/meta");
        XML.add("close/html");
        Iterator<String> tokenizer = XML.iterator();
        Stack <XMLnode>  q = new Stack<>();
        if(!tokenizer.hasNext()) System.out.println("empty");
        String next = tokenizer.next();
        String[] items = next.split("/");
        if(!items[0].equals("open")) throw new IllegalArgumentException("error input");
        String tag = items[0];
        String text = items[1];
        XMLnode root = new XMLnode(tag,text);
        q.push(root);
        XMLnode treeXML = dese(q, tokenizer);
//        print(treeXML);
        print2(treeXML,"");

    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int [][] Upath = new int[m][n];

        for(int i = 0; i< m ; i++) {
            if (obstacleGrid[i][0] == 1) {
                Upath[i][0] = 0;
            } else {
                if (i == 0) Upath[i][0] = 1;
                else {
                    Upath[i][0] = Upath[i - 1][0];
                }
            }
        }

        for (int j=1; j<n; j++){
            if(obstacleGrid[0][j] ==1){
                Upath[0][j] =0;
            }else{
                if(j==0) Upath[0][j] =1;
                else{
                    Upath[0][j] = Upath[0][j-1];
                }
            }
        }

        for (int i = 1; i<m; i++){
            for (int j=1; j<n; j++){
                Upath[i][j]= Upath[i-1][j]+ Upath[i][j-1];
            }
        }

        return Upath[m-1][n-1];



    }


    static XMLnode dese(Stack<XMLnode> q,  Iterator<String> tokenizer){
        XMLnode pre = new XMLnode("","");
        while(tokenizer.hasNext()){
            String next = tokenizer.next();
            String[] items = next.split("/");
            String tag = items[0];
            String text = items[1];

            if (tag.equals("close")) {
                pre = q.pop();
                if(!pre.text.equalsIgnoreCase(text)){
                    throw new IllegalArgumentException("error input");
                }
                continue;
            }else if(tag.equals("open") || tag.equals("text")){
                XMLnode x = new XMLnode(tag,text);
                XMLnode temp = q.peek();
                temp.content.add(x);
                q.push(x);
                if(tag.equals("text")) q.pop();

            }else {
                throw new IllegalArgumentException("error input");
            }

        }
        return pre;

    }

    static XMLnode deserialize(XMLnode x, Iterator<String> tokenizer){
        String next = tokenizer.next();
        String[] items = next.split("/");
        if (items[0].equals("close")) {
            if(x.tag.equalsIgnoreCase(items[1])){
                return null;
            }else{
                throw new IllegalArgumentException("error input");
            }
        }

        String tag = items[0].equals("open")? items[1] : "";
        String text = items[0].equals("text")? items[1] : "";

        XMLnode root = new XMLnode(tag,text);

        while(tokenizer.hasNext()){
            XMLnode node = deserialize(root,tokenizer);
            if(node!=null) {
                root.content.add(node);
                if(!node.text.equals("")) break;
            } else
                break;
        }
        return root;
    }


    static class XMLnode{
        String tag;
        String text;
        ArrayList<XMLnode> content;
        public XMLnode(String element, String text){
            this.tag = element;
            this.text = text;
            this.content = new  ArrayList<XMLnode>();
        }
    }

    public static void print(XMLnode root){

        Queue<XMLnode> q = new LinkedList<XMLnode>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            System.out.println("  ");
            for(int i=0; i<size; i++ ){
                XMLnode node = q.poll();
                System.out.println(node.tag +" "+node.text);
                for(XMLnode n : node.content){
                    q.offer(n);
                }
            }
        }
    }

    static void print2(XMLnode root, String prefix){
        System.out.println(prefix + "#"+root.tag + " " + root.text);
        int n = root.content.size()-1;
        for(int i = 0; i <= n; i++){
            print2(root.content.get(i), prefix+"++");
        }
    }
}
