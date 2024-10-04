import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        var map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        while (true) {
            var inputs = br.readLine().split(" ");

            int n1 = Integer.parseInt(inputs[0]), n2 = Integer.parseInt(inputs[1]);

            if (n1 == n2 && n2 == -1) {
                break;
            }

            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        var resultArr = new ArrayList<Integer>();
        var resultCount = n;

        for (int i = 1; i <= n; i++) {
            var count = bfs(map, i);

            if (resultCount < count) {
                continue;
            }

            if (resultCount > count) {
                resultArr.clear();
                resultCount = count;
            }

            resultArr.add(i);
        }

        bw.write(--resultCount + " " + resultArr.size() + "\n");
        for (var num : resultArr) {
            bw.write(num + " ");
        }

        bw.flush();
    }

    private static int bfs(HashMap<Integer, ArrayList<Integer>> map, int start) {
        int level = 0;
        var visited = new boolean[n + 1];
        var q = new LinkedList<Integer>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();

                for (var child : map.get(now)) {
                    if (visited[child]) {
                        continue;
                    }

                    q.add(child);
                    visited[child] = true;
                }
            }

            level++;
        }

        return level;
    }
}
