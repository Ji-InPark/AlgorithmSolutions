

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    static ArrayList<int[]> blanks = new ArrayList<>();
    static ArrayList<int[]> teachers = new ArrayList<>();
    static char[][] arr;
    static boolean[][] visited;
    static int n;
    static boolean result = false;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            var input = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                arr[i][j] = input[j].charAt(0);

                if (arr[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                }
            }
        }
        for (var teacher : teachers) {
            int y = teacher[0], x = teacher[1];
            for (int i = 0; i < 4; i++) {
                findBlanks(y, x, i);
            }
        }

        solve(0, 0);

        bw.write(result ? "YES" : "NO");
        bw.flush();
    }

    private static boolean findBlanks(int y, int x, int dir) {
        if (!isValid(y, x)) {
            return false;
        }

        if (arr[y][x] == 'S') {
            return true;
        }

        int ny = y + dy[dir], nx = x + dx[dir];
        if (findBlanks(ny, nx, dir)) {
            if (!visited[y][x]) {
                blanks.add(new int[]{y, x});
                visited[y][x] = true;
            }
            return true;
        }

        return false;
    }

    private static void solve(int index, int level) {
        if (result) {
            return;
        }

        if (level == 3) {
            result = check();
        }

        if (index == blanks.size()) {
            return;
        }

        for (int i = index; i < blanks.size(); i++) {
            var blank = blanks.get(i);
            int y = blank[0], x = blank[1];
            arr[y][x] = 'O';
            solve(i + 1, level + 1);
            arr[y][x] = 'X';
        }
    }

    private static boolean check() {
        for (var teacher : teachers) {
            int y = teacher[0], x = teacher[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                while (isValid(ny, nx)) {
                    if (arr[ny][nx] == 'O') {
                        break;
                    }

                    if (arr[ny][nx] == 'S') {
                        return false;
                    }

                    ny += dy[i];
                    nx += dx[i];
                }
            }
        }

        return true;
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

}
