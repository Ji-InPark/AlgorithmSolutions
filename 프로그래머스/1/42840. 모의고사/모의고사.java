import java.util.*;
 
class Solution {
    public int[] solution(int[] answers) {
        var arr = new int[][] {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        var sizes = new int[] {5, 8, 10};
        
        var counts = new int[3];
        for(int i = 0; i < answers.length; i++) {
            for(int j = 0; j < 3; j++) {
                if(answers[i] == arr[j][i % sizes[j]]) {
                    counts[j]++;
                }
            }
        }
        
        var max = 0;
        for(int j = 0; j < 3; j++) {
            max = Math.max(counts[j], max);
        }
        
        var size = 0;
        for(int j = 0; j < 3; j++) {
            if(counts[j] == max) {
                size++;
            }
        }
        
        var result = new int[size];
        var index = 0;
        for(int j = 0; j < 3; j++) {
            if(counts[j] == max) {
                result[index++] = j + 1;
            }
        }
        
        return result;
    }
}