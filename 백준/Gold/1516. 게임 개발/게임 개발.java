

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var times = new int[n + 1];
        var visited = new boolean[n + 1];
        var curTime = 0;

        var map = new HashMap<Integer, HashSet<Integer>>();
        var q = new LinkedList<int[]>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());

            var inputs = br.readLine().split(" ");
            times[i] = Integer.parseInt(inputs[0]);

            for (int j = 1; j < inputs.length - 1; j++) {
                map.get(i).add(Integer.parseInt(inputs[j]));
            }

            if (map.get(i).isEmpty()) {
                map.remove(i);
                q.add(new int[]{i, times[i]});
                visited[i] = true;
            }
        }

        var result = new int[n + 1];
        while (!q.isEmpty()) {
            q.sort(Comparator.comparingInt(o -> o[1]));

            var removeKeys = new ArrayList<Integer>();

            var first = q.poll();
            int node = first[0], time = first[1];
            removeKeys.add(node);
            result[node] = curTime + time;

            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                now[1] -= time;
                if (now[1]== 0) {
                    removeKeys.add(now[0]);
                }

                q.add(now);
            }

            curTime += time;

            for (var entry : map.entrySet()) {
                removeKeys.forEach(entry.getValue()::remove);

                if (entry.getValue().isEmpty() && !visited[entry.getKey()]) {
                    q.add(new int[]{entry.getKey(), times[entry.getKey()]});
                    visited[entry.getKey()] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
    }
}
