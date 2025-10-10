import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        var tree = new HashMap<String, String>();
        var countMap = new HashMap<String, Integer>();
        countMap.put("-", 0);
        
        for(int i = 0; i < enroll.length; i++) {
            tree.put(enroll[i], referral[i]);
            countMap.put(enroll[i], 0);
        }
        
        for(int i = 0; i < seller.length; i++) {
            var name = seller[i];
            var money = amount[i] * 100;
            
            while(money >= 10 && !name.equals("-")) {
                countMap.put(name, countMap.get(name) + money - (money / 10));
                money = money / 10;
                name = tree.get(name);
            }
            
            countMap.put(name, countMap.get(name) + money);
        }
        
        var result = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            result[i] = countMap.get(enroll[i]);
        }
        
        return result;
    }
}