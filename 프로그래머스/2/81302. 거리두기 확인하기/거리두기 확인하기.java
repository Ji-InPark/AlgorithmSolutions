import java.util.*;

class Solution {
    int[] dx = new int[] {0, 1, 0, -1};
    int[] dy = new int[] {1, 0, -1, 0};
    
    public int[] solution(String[][] places) {
        var result = new int[]{1, 1, 1, 1, 1};
        
        Loop:
        for(int i = 0; i < 5; i++) {
            var place = places[i];
            var arr = new char[5][5];
            
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    arr[j][k] = place[j].charAt(k);
                }
            }
            
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(arr[j][k] == 'P' && !bfs(arr, j, k)) {
                        result[i] = 0;
                        continue Loop;
                    }
                }
            }
        }
        
        return result;
    }
    
    private boolean bfs(char[][] arr, int sy, int sx) {
        var q = new LinkedList<int[]>();
        q.add(new int[] {sy, sx});
        var visited = new boolean[5][5];
        visited[sy][sx] = true;
        
        var level = 0;
        while(level < 2) {
            var size = q.size();
            
            for(int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x= now[1];
                
                for(int j = 0; j < 4; j++) {
                    int ny = y + dy[j], nx = x + dx[j];
                    
                    if(isValid(ny, nx) && !visited[ny][nx]) {
                        if(arr[ny][nx] == 'P') {
                            return false;
                        }
                        
                        if(arr[ny][nx] == 'X') {
                            continue;
                        }
                        
                        q.add(new int[] {ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
            
            level++;
        }
        
        return true;
    }
    
    private boolean isValid(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }
}