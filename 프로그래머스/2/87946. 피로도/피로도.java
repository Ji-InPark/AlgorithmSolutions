import java.util.*;
class Solution {
    boolean[] visit;
    int[][] arr;
    int result = 0;
    
    public int solution(int k, int[][] dungeons) {
        arr = dungeons;
        visit = new boolean[dungeons.length];
        solve(k, 0);
        return result;
    }
    
    public void solve(int last, int count) {
        result = Math.max(result, count);
        
        for(int i = 0; i < arr.length; i++) {
            if(!visit[i] && arr[i][0] <= last) {
                visit[i] = true;
                solve(last - arr[i][1], count + 1);
                visit[i] = false;
            }
        }
    }
}