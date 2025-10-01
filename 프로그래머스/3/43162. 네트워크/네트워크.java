import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        var arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = i;
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(computers[i][j] == 0) {
                    continue;
                }
                
                var a = find(arr, i);
                var b = find(arr, j);
                
                if(a < b) {
                    arr[b] = a;
                } else {
                    arr[a] = b;
                }
            }
        }
        
        var result = new HashSet<Integer>();
        for(int i = 0; i < n; i++) {
            result.add(find(arr, i));
        }
        
        return result.size();
    }
    
    private int find(int[] arr, int index) {
        if(arr[index] == index) {
            return index;
        }
        
        return arr[index] = find(arr, arr[index]);
    }
}