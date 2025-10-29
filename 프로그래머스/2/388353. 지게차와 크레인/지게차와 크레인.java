import java.util.*;

class Solution {
    int h,w,result;
    
    int[] dy = new int[]{0, 1, 0, -1};
    int[] dx = new int[]{1, 0, -1, 0};
    
    public int solution(String[] storage, String[] requests) {
        h = storage.length;
        w = storage[0].length();
        result = h * w;
        var arr = new char[h][w];
        
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                arr[i][j] = storage[i].charAt(j);
            }
        }
        
        for(var r : requests) {
            if(r.length() == 1) {
                findOut(arr, r.charAt(0));
            } else {
                findAll(arr, r.charAt(0));
            }
        }
        
        return result;
    }
    
    private void findOut(char[][] arr, char target) {
        var visited = new boolean[h][w];
        var q = new LinkedList<int[]>();
        for(int i = 0; i < h; i++) {
            q.add(new int[] {i, 0});
            q.add(new int[] {i, w - 1});
            visited[i][0] = true;
            visited[i][w - 1] = true;
        }
        
        for(int i = 0; i < w; i++) {
            q.add(new int[] {0, i});
            q.add(new int[] {h - 1, i});
            visited[0][i] = true;
            visited[h - 1][i] = true;
        }
        
        while(!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];
            
            if(arr[y][x] != '-') {
                if(arr[y][x] == target) {
                    result--;
                    arr[y][x] = '-';
                }
                
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                
                if(isValid(ny, nx) && !visited[ny][nx]) {
                    q.add(new int[] {ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
    }
    
    private boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
    
    private void findAll(char[][] arr, char target) {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(arr[i][j] == target) {
                    arr[i][j] = '-';
                    result--;
                }
            }
        }
    }
}