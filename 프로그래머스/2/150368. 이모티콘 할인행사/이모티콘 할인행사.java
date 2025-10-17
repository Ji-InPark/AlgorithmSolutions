class Solution {
    int maxCount = 0, maxPrice = 0, n = 0, m = 0;
    int[][] users;
    int[] emoticons;
    
    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        this.users = users;
        this.emoticons = emoticons;
     
        solve(0, new int[m]);
        
        return new int[] {maxCount, maxPrice};
    }
    
    private void solve(int level, int[] discounts) {
        if(level == m) {
            calculate(discounts);
            return;
        }
        
        for(int i = 10; i <= 40; i += 10) {
            discounts[level] = i;
            solve(level + 1, discounts);
        }
    }
    
    private void calculate(int[] discounts) {
        int totalCount = 0, totalPrice = 0;
        
        for(var user : users) {
            int percent = user[0], target = user[1], sum = 0;
            for(int i = 0; i < m; i++) {
                if(percent <= discounts[i]) {
                    sum += emoticons[i] - (emoticons[i] / 100 * discounts[i]);
                }
            }
            
            if(sum >= target){
                totalCount++;
            } else {
                totalPrice += sum;
            }
        }
        
        if(totalCount > maxCount) {
            maxCount = totalCount;
            maxPrice = totalPrice;
        } else if(totalCount == maxCount) {
            maxPrice = Math.max(maxPrice, totalPrice);
        }
    }
}