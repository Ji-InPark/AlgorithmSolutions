import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        var result = 0;
        var set = new HashSet<Character>();
        

        
        Loop:
        for(var st : skill_trees) {
            int index = 0, n = skill.length();
            for(var c : skill.toCharArray()) {
                set.add(c);
            }
            
            for(var c : st.toCharArray()) {
                if(index == n) {
                    break;
                }
                
                if(skill.charAt(index) == c) {
                    index++;
                    set.remove(c);
                    continue;
                }
                
                if(set.contains(c)) {
                    continue Loop;
                }
            }
            
            result++;
        }
        
        return result;
    }
}