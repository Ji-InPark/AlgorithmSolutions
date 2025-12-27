import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        var result = 0;
        var arr = new int[players.length + k + 1];
        int count = 0;
        
        for(int i = 0; i < players.length; i++) {
            count -= arr[i];
            
            if(count * m >= players[i]) {
                continue;
            }
            
            var diff = players[i] - count * m;
            var plus = diff / m;
            
            result += plus;
            count += plus;
            
            arr[i + k] = plus;
        }
        
        return result;
    }
}