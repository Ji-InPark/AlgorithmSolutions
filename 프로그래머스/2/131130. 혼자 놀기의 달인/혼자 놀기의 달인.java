import java.util.*;

class Solution {
    ArrayList<Integer> list = new ArrayList<>();
    
    public int solution(int[] cards) {
        var n = cards.length;
        for(int i = 0; i < n; i++) {
            cards[i]--;
        }
        
        for(int i = 0; i < n; i++) {
            if(cards[i] >= 0) {
                find(cards, i, 0);
            }
        }

        if(list.size() == 1) {
            return 0;
        }
        
        
        list.sort(Collections.reverseOrder());
        
        return list.get(0) * list.get(1);
    }
    

    private void find(int[] arr, int index, int count) {
        if(arr[index] == -1) {
            list.add(count);
            return;
        }
        
        var nextIndex = arr[index];
        
        arr[index] = -1;
        
        find(arr, nextIndex, count + 1);
    }
}