

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        for (int i = 1; i <= n; i++) {
            map.put(i, new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int a1 = Integer.parseInt(inputs[0]), a2 = Integer.parseInt(inputs[1]),
                    weight = Integer.parseInt(inputs[2]);

            map.get(a1).put(a2, Math.max(weight, map.get(a1).getOrDefault(a2, 0)));
            map.get(a2).put(a1, Math.max(weight, map.get(a2).getOrDefault(a1, 0)));
        }

        var endPointInputs = br.readLine().split(" ");
        int start = Integer.parseInt(endPointInputs[0]), end = Integer.parseInt(endPointInputs[1]);

        int l = 0, r = 2000000000;
        while (l + 1 < r) {
            var mid = l + (r - l) / 2;

            if (isValid(start, end, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        bw.write(l + "\n");
        bw.flush();
    }

    private static boolean isValid(int start, int end, int target) {
        var q = new LinkedList<Integer>();
        q.add(start);
        var visited = new HashSet<Integer>();
        visited.add(start);

        while (!q.isEmpty()) {
            var now = q.poll();

            if (now == end) {
                return true;
            }

            for (var entry : map.get(now).entrySet()) {
                int nextNode = entry.getKey(), nextWeight = entry.getValue();

                if (!visited.contains(nextNode) && nextWeight >= target) {
                    visited.add(nextNode);
                    q.add(nextNode);
                }
            }
        }

        return false;
    }
}

