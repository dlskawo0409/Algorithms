import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (int[] r1, int[] r2) -> r1[0] == r2[0] ? r1[1] - r2[1] : r1[0] - r2[0]);
        
        int[] last = routes[0];
        
        for( int i = 1; i < routes.length; ++i) {
            if(isOverlapping(routes[i], last)) {
                last = getOverlapping(routes[i], last);
            } else {
                ++answer;
                last = routes[i];
            }
        }
        
        
        
        return answer;
    }
    
    boolean isOverlapping (int[] dest, int[] route) {
        if( (dest[0] <= route[0] && route[0] <= dest[1]) 
           ||  (dest[0] <= route[1] && route[1] <= dest[1])
           || (route[0] <= dest[0] && dest[0] <= route[1] )
           || (route[0] <= dest[1] && dest[1] <= route[1])
          ) {
             return true;
        }
        return false;
    }
    
    int[] getOverlapping(int[] a, int[] b) {
        int[] result = new int[2];
        
        result[0] = Math.max(a[0], b[0]);
        result[1] = Math.min(a[1], b[1]);
        
        return result;
    }
}