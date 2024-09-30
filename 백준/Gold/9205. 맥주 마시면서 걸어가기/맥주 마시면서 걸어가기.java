import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    static int endX, endY;
    static int startX, startY;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var n = Integer.parseInt(br.readLine());
            var visited = new boolean[n];
            var startInputs = br.readLine().split(" ");
            var cons = new ArrayList<int[]>();
            for (int i = 0; i < n; i++) {
                var inputs = br.readLine().split(" ");

                cons.add(new int[]{Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])});
            }
            var endInputs = br.readLine().split(" ");

            startX = Integer.parseInt(startInputs[0]);
            startY = Integer.parseInt(startInputs[1]);
            endX = Integer.parseInt(endInputs[0]);
            endY = Integer.parseInt(endInputs[1]);

            var result = false;
            var q = new LinkedList<int[]>();
            q.add(new int[]{startX, startY});

            while (!q.isEmpty()) {
                var now = q.poll();
                int x = now[0], y = now[1];

                if (canGo(x, y, endX, endY)) {
                    result = true;
                    break;
                }

                for (int i = 0; i < n; i++) {
                    if (visited[i]) {
                        continue;
                    }

                    var con = cons.get(i);

                    if (canGo(x, y, con[0], con[1])) {
                        q.add(new int[]{con[0], con[1]});
                        visited[i] = true;
                    }
                }
            }

            if (result) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }

        }
        bw.flush();
    }

    private static boolean canGo(int x1, int y1, int x2, int y2) {
        return getDistance(x1, y1, x2, y2) <= 1000;
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
