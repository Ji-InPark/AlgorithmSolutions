class Solution {
    public int[] solution(int[][] arr) {
        var n = arr.length;
        var result = new int[2];
        for(var nums : arr) {
            for(var num : nums) {
                result[num]++;
            }
        }
        
        for(int size = 2; size <= n; size *= 2) {
            for(int i = 0; i < n; i += size) {
                for(int j = 0; j < n; j += size) {
                    if(isQuadArea(arr, i, j, size)) {
                        result[arr[i][j]] -= 3;
                    }
                }
            }
        }
        
        return result;
    }
    
    private boolean isQuadArea(int[][] arr, int x, int y, int size) {
        var num = arr[x][y];
        
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(arr[x + i][y + j] != num) return false;
            }
        }
        
        return true;
    }
}