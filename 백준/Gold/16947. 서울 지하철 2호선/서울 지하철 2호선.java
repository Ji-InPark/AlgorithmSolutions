

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static boolean[] isCycle;
    static int[] results;
    static HashMap<Integer, ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 간선이 한 개인 부분은 무조건 지선
        // 지선에서 시작해서 순환선을 구해서 미리 순환선을 체크해둠
        // 이 후 지선들을 세보면서 거리 매기면 될듯???
        var n = Integer.parseInt(br.readLine());
        isCycle = new boolean[n + 1];
        map = new HashMap<>();
        results = new int[n + 1];
        Arrays.fill(results, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            int n1 = Integer.parseInt(inputs[0]), n2 = Integer.parseInt(inputs[1]);

            if (!map.containsKey(n1)) {
                map.put(n1, new ArrayList<>());
            }

            map.get(n1).add(n2);

            if (!map.containsKey(n2)) {
                map.put(n2, new ArrayList<>());
            }

            map.get(n2).add(n1);
        }

        for (int i = 1; i <= n; i++) {
            findCycle(i, -1, new HashSet<>());
        }

        for (int i = 1; i <= n; i++) {
            calculateDist(i, -1, 0);
        }

        for (int i = 1; i <= n; i++) {
            if (isCycle[i]) {
                bw.write("0 ");
            } else {
                bw.write(results[i] + " ");
            }
        }
        bw.flush();
    }

    private static int calculateDist(int node, int pre, int level) {
        if (isCycle[node]) {
            return level;
        }

        for (var next : map.get(node)) {
            if (next == pre) {
                continue;
            }

            var result = calculateDist(next, node, level + 1);

            if (result < 0) {
                continue;
            }

            results[node] = Math.min(result - level, results[node]);

            return result;
        }

        return -1;
    }

    private static int findCycle(int node, int pre, HashSet<Integer> visited) {
        if (visited.contains(node)) {
            isCycle[node] = true;
            return node;
        }

        for (var next : map.get(node)) {
            if (next == pre) {
                continue;
            }

            visited.add(node);
            var result = findCycle(next, node, visited);
            if (result > 0 && visited.contains(result)) {
                isCycle[node] = true;
                visited.remove(node);

                return result;
            }
            visited.remove(node);
        }

        return -1;
    }
}
