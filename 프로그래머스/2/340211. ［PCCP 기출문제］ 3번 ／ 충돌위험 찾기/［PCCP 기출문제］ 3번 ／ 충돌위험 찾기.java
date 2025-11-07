import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        var robots = new ArrayList<ArrayList<int[]>>();
        int max = 0;
        
        for(var route : routes) {
            var arr = new ArrayList<int[]>();
            int y = points[route[0] - 1][0], x = points[route[0] - 1][1];
            arr.add(new int[] {y, x});
            
            for(int i = 1; i < route.length; i++) {
                int ny = points[route[i] - 1][0], nx = points[route[i] - 1][1];
                while(ny != y || nx != x) {
                    if(ny != y) {
                        if(y < ny) {
                            y++;
                        } else {
                            y--;
                        }
                    } else if(nx != x) {
                        if(x < nx) {
                            x++;
                        } else {
                            x--;
                        }
                    }
                    
                    arr.add(new int[] {y, x});
                }
            }
            
            robots.add(arr);
            max = Math.max(arr.size(), max);
        }
        
        var map = new HashMap<Integer, Integer>();
        var result = 0;
        for(int i = 0; i < max; i++) {
            var conflict = 0;
            map.clear();
            
            for(var robot : robots) {
                if(robot.size() > i) {
                    var key = robot.get(i)[0] * 100 + robot.get(i)[1];
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    if(map.get(key) == 2) {
                        conflict++;   
                    }
                }
            }
            
            result += conflict;
        }
        
        return result;
    }
}