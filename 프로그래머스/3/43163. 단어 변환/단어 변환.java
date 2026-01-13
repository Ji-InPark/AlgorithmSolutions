import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        var map = new HashMap<String, ArrayList<String>>();
        
        for(int i = 0; i < words.length; i++) {
            map.put(words[i], new ArrayList<>());
            
            for(int j = 0; j < words.length; j++) {
                if(i == j) continue;
                
                var count = 0;
                for(int k = 0; k < words[i].length(); k++) {
                    if(words[i].charAt(k) != words[j].charAt(k)) count++;
                }
                
                if(count == 1) {
                    map.get(words[i]).add(words[j]);
                }
            }
        }
        
        map.put(begin, new ArrayList<>());
        for(int i = 0; i < words.length; i++) {
            var count = 0;
            for(int k = 0; k < words[i].length(); k++) {
                if(words[i].charAt(k) != begin.charAt(k)) count++;
            }

            if(count == 1) {
                map.get(begin).add(words[i]);
            }
        }
        
        var q = new LinkedList<String>();
        q.add(begin);
        var visited = new HashSet<String>();
        visited.add(begin);
        
        var level = 0;
        while(!q.isEmpty()) {
            var size = q.size();
            for(int i = 0; i < size; i++) {
                var now = q.poll();
                
                if(now.equals(target)) {
                    return level;
                }
                
                for(var next : map.get(now)) {
                    if(visited.contains(next)) {
                        continue;
                    }
                    
                    visited.add(next);
                    q.add(next);
                }
            }
            
            level++;
        }
        
        return 0;
    }
}