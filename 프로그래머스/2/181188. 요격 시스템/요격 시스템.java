import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        var pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }
                
                return a[0] - b[0];
            }
        });
        
        for(var t : targets) {
            pq.add(t);
        }
        
        int end = pq.peek()[1], result = 1;
        while(!pq.isEmpty()) {
            var now = pq.poll();
            
            if(now[0] >= end) {
                result++;
                end = now[1];
            } else if(now[1] < end) {
                end = now[1];
            }
        }
        
        return result;
    }
}