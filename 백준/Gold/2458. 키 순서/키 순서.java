

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);
        var upperMap = new HashMap<Integer, HashSet<Integer>>();
        var lowerMap = new HashMap<Integer, HashSet<Integer>>();

        for (int i = 1; i <= n; i++) {
            upperMap.put(i, new HashSet<>());
            lowerMap.put(i, new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]), b = Integer.parseInt(inputs[1]);

            upperMap.get(a).add(b);
            lowerMap.get(b).add(a);
        }

        var result = 0;
        for (int i = 1; i <= n; i++) {
            int size = bfs(i, upperMap) + bfs(i, lowerMap);

            if (size == n - 1) {
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int bfs(int start, HashMap<Integer, HashSet<Integer>> map) {
        var q = new LinkedList<Integer>();
        var visited = new HashSet<Integer>();
        q.add(start);

        while (!q.isEmpty()) {
            var now = q.poll();

            for (var next : map.get(now)) {
                if (!visited.contains(next)) {
                    q.add(next);
                    visited.add(next);
                }
            }
        }

        return visited.size();
    }
}

