import java.util.*;

class Solution {
    int result = 0, n;
    int[] ans;
    int[][] q;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        solve(1, 0, new HashSet<Integer>());
        
        return result;
    }
    
    private void solve(int index, int level, HashSet<Integer> set) {
        if(level == 5) {
            if(isValid(set)) {
                result++;
            }
            
            return;
        }
        
        for(int i = index; i <= n; i++) {
            set.add(i);
            solve(i + 1, level + 1, set);
            set.remove(i);
        }
    }
    
    private boolean isValid(HashSet<Integer> set) {
        for(int i = 0; i < q.length; i++) {
            var nums = q[i];
            var count = 0;
            for(var num : nums) {
                if(set.contains(num)) {
                    count++;
                }
            }
            
            if(count != ans[i]) {
                return false;
            }
        }
        
        return true;
    }
}