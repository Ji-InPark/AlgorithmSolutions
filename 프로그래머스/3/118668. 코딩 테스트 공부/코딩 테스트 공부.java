import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxA = 0, maxC = 0;
        for(var p : problems) {
            maxA = Math.max(maxA, p[0]);
            maxC = Math.max(maxC, p[1]);
        }
        
        var visited = new int[300][300];
        for(int i = 0; i < 300; i++) {
            Arrays.fill(visited[i], 1000000);
        }
        
        var q = new LinkedList<int[]>();
        q.add(new int[] {alp, cop, 0});
        visited[alp][cop] = 0;
        
        var result = 1000000;
        
        while(!q.isEmpty()) {
            var now = q.poll();
            int a = now[0], c = now[1], cost = now[2];
            
            if(a >= maxA && c >= maxC) {
                result = Math.min(result, cost);
                continue;
            }
            
            if(a + 1 <= 150 && visited[a + 1][c] > cost + 1) {
                visited[a + 1][c] = cost + 1;
                q.add(new int[] { a + 1, c, cost + 1});
            }
            
            if(c + 1 <= 150 && visited[a][c + 1] > cost + 1) {
                visited[a][c + 1] = cost + 1;
                q.add(new int[] { a, c + 1, cost + 1});
            }
            
            for(var p : problems) {
                if(a >= p[0] && c >= p[1]) {
                    int na = Math.min(150, a + p[2]), nc = Math.min(150, c + p[3]), nCost = cost + p[4];
                    if(visited[na][nc] > nCost) {
                        visited[na][nc] = nCost;
                        q.add(new int[] {na, nc, nCost});
                    }
                }
            }
        }
        
        return result;
    }
} 