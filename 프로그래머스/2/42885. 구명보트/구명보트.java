import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0, right = people.length - 1, result = 0;
        while(left <= right) {
            var sum = people[left] + people[right];
            
            if(sum > limit) {
                right--;
            } else {
                left++;
                right--;
            }
            
            result++;
        }
        
        return result;
    }
}