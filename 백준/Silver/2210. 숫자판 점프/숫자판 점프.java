import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.TreeSet;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var arr = new int[5][5];
        var q = new LinkedList<int[]>();

        for (int i = 0; i < 5; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
                q.add(new int[]{i, j, arr[i][j]});
            }
        }

        for (int i = 0; i < 5; i++) {
            var size = q.size();
            for (int j = 0; j < size; j++) {
                var now = q.poll();
                int y = now[0], x = now[1], val = now[2];

                for (int k = 0; k < 4; k++) {
                    int ny = y + dy[k], nx = x + dx[k];

                    if (isValid(ny, nx)) {
                        q.add(new int[]{ny, nx, val * 10 + arr[ny][nx]});
                    }
                }
            }
        }

        var set = new TreeSet<Integer>();
        while (!q.isEmpty()) {
            set.add(q.poll()[2]);
        }

        bw.write(set.size() + "\n");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }
}
