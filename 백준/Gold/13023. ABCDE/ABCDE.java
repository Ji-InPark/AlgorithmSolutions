

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int a1 = Integer.parseInt(inputs[0]), a2 = Integer.parseInt(inputs[1]);

            map.get(a1).add(a2);
            map.get(a2).add(a1);
        }

        var isSuccess = false;
        for (int i = 0; i < n && !isSuccess; i++) {
            var visited = new boolean[n];
            visited[i] = true;
            isSuccess = dfs(i, 1, visited);
        }

        bw.write(isSuccess ? "1" : "0");
        bw.flush();
    }

    private static boolean dfs(int now, int level, boolean[] visited) {
        if (level >= 5) {
            return true;
        }

        for (var next : map.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                var result = dfs(next, level + 1, visited);
                if (result) {
                    return true;
                }
                visited[next] = false;
            }
        }

        return false;
    }

}
