

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static int[][] dn = new int[][]{{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var visited = new int[61][61][61];

        var inputs = br.readLine().split(" ");
        var arr = new int[3];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                Arrays.fill(visited[i][j], 1000);
            }
        }

        var q = new LinkedList<int[]>();
        q.add(arr);
        var level = 0;

        Loop:
        while (true) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();

                if (now[0] == 0 && now[1] == 0 && now[2] == 0) {
                    break Loop;
                }

                for (int j = 0; j < 6; j++) {
                    int a1 = Math.max(now[0] - dn[j][0], 0),
                            a2 = Math.max(now[1] - dn[j][1], 0),
                            a3 = Math.max(now[2] - dn[j][2], 0);

                    if (visited[a1][a2][a3] > level) {
                        q.add(new int[]{a1, a2, a3});
                        visited[a1][a2][a3] = level;
                    }
                }
            }

            level++;
        }

        bw.write(level + "\n");
        bw.flush();
    }
}