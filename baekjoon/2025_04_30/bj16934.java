import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;



public class Main {
   
   int N;
   
   class Node{
	   char now;
	   Node[] child;
	   
	   Node(char c){
		   this.now = c;
		   child = new Node[26];
	   }
	   
   }
   
   HashMap<String, Integer> map;
   
   public void solution() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       N = Integer.parseInt(br.readLine());
       
       Node start = new Node('a');
       map = new HashMap<>();
       
       
       for(int n = 0; n <N; ++n) {
    	   String input = br.readLine();

    	   if(!insert(start, 0, false, input)) {
    		   if(!map.containsKey(input)) {
        		   System.out.println(input);
        		   map.put(input, 1);   
    		   }
    		   else {
    			   int count = map.get(input) + 1;
    			   map.put(input, count);
    			   System.out.println(input+count);
    		   }
    	   }
    	   else {
    		   if(!map.containsKey(input)) {
    			   map.put(input, 1);
    		   }
    	   }


       }

   }
   
   boolean insert(Node node, int now, boolean print, String input) {
	   
	   if(now == input.length()) {
		   if(!print) {
			   return false;
		   }
		   return true;
	   }
	   
	   char nowChar = input.charAt(now);
	   int nowIndex = nowChar - 'a';
	   
	   
	   if(node.child[nowIndex] == null) {
		   node.child[nowIndex] = new Node(nowChar);
		   if(!print) {
			   System.out.println(input.substring(0,now+1));
			   print = true;
		   }
	   }
	   
	   return insert(node.child[nowIndex], now + 1, print, input);
	   
   }
   
   
   public static void main(String[]args) throws Exception{
       new Main().solution();
   }
}