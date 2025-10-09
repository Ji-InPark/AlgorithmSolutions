import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        var countSet = new HashSet<String>();
        var map = new HashMap<String, Integer>();
        
        for(var gem : gems) {
            countSet.add(gem);
        }
        
        var gemCount = countSet.size();
        int left = 0, minLen = gems.length + 10, minLeft = 0, minRight = 0;
        
        for(int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            while(map.size() == gemCount) {
                if(i - left < minLen) {
                    minLen = i - left;
                    minLeft = left;
                    minRight = i;
                }
                
                if(map.get(gems[left]) == 1) {
                    map.remove(gems[left]);
                } else {
                    map.put(gems[left], map.get(gems[left]) - 1);
                }
                
                left++;
            }
        }
        
        return new int[] {minLeft + 1, minRight + 1};
    }
}