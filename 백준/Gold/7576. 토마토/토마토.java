import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine().split(" ");
        var dir = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int R = Integer.parseInt(input[0]), C = Integer.parseInt(input[1]), zeros = 0;
        var v = new boolean[C][R];
        var q = new LinkedList<int[]>();

        for (int i = 0; i < C; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < input.length; j++) {
                int n = Integer.parseInt(input[j]);

                if(n == 0) {
                    zeros++;
                    continue;
                }

                if(n == 1) q.add(new int[]{j, i});

                v[i][j] = true;
            }
        }

        int days = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            days++;

            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int x = now[0], y = now[1];

                for(var d : dir) {
                    int newX = x + d[0], newY = y + d[1];
                    if(newX < 0 || newY < 0 || newX >= R || newY >= C || v[newY][newX]) continue;
                    q.add(new int[]{newX, newY});
                    v[newY][newX] = true;
                    zeros--;
                }
            }
        }

        bw.write(zeros == 0 ? String.valueOf(days - 1) : "-1");
        bw.flush();
    }
}
