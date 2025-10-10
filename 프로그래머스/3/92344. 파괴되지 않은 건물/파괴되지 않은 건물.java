class Solution {
    public int solution(int[][] boards, int[][] skill) {
        int h = boards.length, w = boards[0].length;
        var diff = new int[h + 1][w + 1];
        
        for(var s : skill) {
            var num = s[0] == 1 ? -s[5] : s[5];
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            
            diff[r1][c1] += num;
            diff[r1][c2 + 1] -= num;
            diff[r2 + 1][c1] -= num;
            diff[r2 + 1][c2 + 1] += num;
        }
        
        for(int i = 0; i < h; i++) {
            for(int j = 1; j < w; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }
        
        for(int i = 0; i < w; i++) {
            for(int j = 1; j < h; j++) {
                diff[j][i] += diff[j - 1][i];
            }
        }
        
        var result = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(boards[i][j] + diff[i][j] > 0) {
                    result++;
                }
            }
        }
        
        return result;
    }
}