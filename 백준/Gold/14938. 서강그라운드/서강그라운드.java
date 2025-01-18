import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var nmrInputs = br.readLine().split(" ");
        n = Integer.parseInt(nmrInputs[0]);
        int m = Integer.parseInt(nmrInputs[1]), r = Integer.parseInt(nmrInputs[2]);

        var tInputs = br.readLine().split(" ");
        var items = new int[n];

        var map = new HashMap<Integer, ArrayList<int[]>>();
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(tInputs[i]);
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            var numInputs = br.readLine().split(" ");
            int a = Integer.parseInt(numInputs[0]) - 1, b = Integer.parseInt(numInputs[1]) - 1,
                    dist = Integer.parseInt(numInputs[2]);

            map.get(a).add(new int[]{b, dist});
            map.get(b).add(new int[]{a, dist});
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, bfs(map, items, i, m));
        }

        bw.write(result + "\n");
        bw.flush();
    }


    private static int bfs(HashMap<Integer, ArrayList<int[]>> map, int[] items, int start, int limit) {
        int result = 0;

        var visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        var q = new LinkedList<int[]>();
        q.add(new int[]{start, 0});

        while (!q.isEmpty()) {
            var now = q.poll();

            if (visited[now[0]] <= now[1]) {
                continue;
            }

            visited[now[0]] = now[1];

            for (var edge : map.get(now[0])) {
                q.add(new int[]{edge[0], edge[1] + now[1]});
            }
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] <= limit) {
                result += items[i];
            }
        }

        return result;
    }
}
