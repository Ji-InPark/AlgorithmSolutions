import java.util.*;

class Solution {
    int[][] dices;
    int n = 0, max = 0;
    boolean[] diceArr;
    int[] result;
    
    public int[] solution(int[][] dice) {
        dices = dice;
        n = dice.length;
        diceArr = new boolean[n];
        result = new int[n / 2];
        
        selectDice(0, 0);
        
        return result;
    }
    
    private void selectDice(int index, int count) {
        if(count < n / 2 && index >= n) {
            return;
        }
        
        if(count == n / 2) {
            var sum = calculate();
            System.out.println("sum: " + sum);
            for(int i = 0; i < n; i++) {
                if(diceArr[i]) {
                    System.out.print(i + " ");
                }
            }
            
            if(max < sum) {
                max = sum;
                
                System.out.println("-----" + max);
                
                var resultIndex = 0;
                for(int i = 0; i < n; i++) {
                    if(diceArr[i]) {
                        System.out.print(i + " ");
                        result[resultIndex++] = i + 1;
                    }
                }
                System.out.print("\n");
            }
            
            return;
        }
        
        for(int i = index; i < n; i++) {
            diceArr[i] = true;
            selectDice(i + 1, count + 1);
            diceArr[i] = false;
        } 
    }
    
    private int calculate() {
        var tArr = getDiceArr(true);
        var fArr = getDiceArr(false);
        
        var map = new HashMap<Integer, Integer>();
        var set = new TreeSet<Integer>();
        var arr = new ArrayList<Integer>();
        
        addAll(fArr, arr, 0, 0);
        Collections.sort(arr);
        set.addAll(arr);
        for(int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), i);
        }
        
        var winArr = new ArrayList<Integer>();
        addAll(tArr, winArr, 0, 0);
        
        int winCount = 0;
        for(var num : winArr) {
            var low = set.lower(num);
            
            if(low == null) {
                continue;
            }
            
            winCount += map.get(low);
        }
        
        return winCount;
    }
    
    private void addAll(int[] dArr, ArrayList<Integer> arr, int level, int sum) {
        if(level == n / 2) {
            arr.add(sum);
            return;
        }
        
        for(int i = 0; i < 6; i++) {
            addAll(dArr, arr, level + 1, sum + dices[dArr[level]][i]);
        }
    }
    
    private int[] getDiceArr(boolean flag) {
        var arr = new int[n / 2];
        var index = 0;
        
        for(int i = 0; i < n; i++) {
            if(diceArr[i] == flag) {
                arr[index++] = i;
            }
        }
        
        return arr;
    }
}