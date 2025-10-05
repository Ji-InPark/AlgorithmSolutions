import java.util.*;

class Solution {
    public int solution(int origin, int t1, int t2, int a, int b, int[] onboard) {
        int lowTemp = Math.min(Math.abs(origin - t1), Math.abs(origin - t2)), length = onboard.length;
        int highTemp = lowTemp + Math.abs(t1 - t2);
        var visited = new int[length][highTemp + 10];
        for(int i = 0; i < length; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        var q = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));
        q.add(new int[] {0, 0});
        
        for(int i = 0; i < length; i++) {
            var size = q.size();
            var nq = new ArrayList<int[]>();
            for(int j = 0; j < size; j++) {
                var now = q.poll();
                int temp = now[0], cost = now[1];
                
                if(onboard[i] == 1 && temp < lowTemp) {
                    continue;
                }
                
                if(temp + 1 <= highTemp && visited[i][temp + 1] > cost + a) {
                    visited[i][temp + 1] = cost + a;
                    nq.add(new int[] {temp + 1, cost + a});
                }
                
                if(temp == 0) {
                    visited[i][temp] = cost;
                    nq.add(new int[] {temp, cost});
                    continue;
                }
                
                if(visited[i][temp] > cost + b) {
                    visited[i][temp] = cost + b;
                    nq.add(new int[] {temp, cost + b});
                }
                
                if(temp - 1 >= 0 && visited[i][temp - 1] > cost) {
                    visited[i][temp - 1] = cost;
                    nq.add(new int[] {temp - 1, cost});
                }
            }
            q.addAll(nq);
        }
        
        var result = Integer.MAX_VALUE;
        System.out.println(q.size());
        while(!q.isEmpty()) {
             var now = q.poll();
            int temp = now[0], cost = now[1];
            
            // System.out.println("temp: " + temp + ", cost: " + cost);
            result = Math.min(result, cost);
        }
        
        return result;
    }
}