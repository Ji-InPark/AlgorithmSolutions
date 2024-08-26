import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]),
                v = Integer.parseInt(inputs[2]);
        var map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int n1 = Integer.parseInt(inputs[0]), n2 = Integer.parseInt(inputs[1]);

            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(map.get(i));
        }

        dfs(map, new HashSet<>(), v);
        bw.write("\n");
        bfs(map, v);
        bw.flush();
    }

    private static void dfs(HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> visited, int node)
            throws IOException {
        if (visited.contains(node)) {
            return;
        }

        bw.write(node + " ");
        visited.add(node);

        for (var num : map.get(node)) {
            dfs(map, visited, num);
        }
    }

    private static void bfs(HashMap<Integer, ArrayList<Integer>> map, int node)
            throws IOException {
        var visited = new HashSet<Integer>();
        var q = new LinkedList<Integer>();
        q.add(node);

        while (!q.isEmpty()) {
            var now = q.poll();
            if (visited.contains(now)) {
                continue;
            }

            visited.add(now);
            bw.write(now + " ");

            q.addAll(map.get(now));
        }
    }
}
