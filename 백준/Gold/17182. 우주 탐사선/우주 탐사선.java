

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    static ArrayList<int[]>[] arr;
    static int n, result = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        n = Integer.parseInt(numInputs[0]);
        int m = Integer.parseInt(numInputs[1]);
        arr = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            var input = br.readLine().split(" ");

            arr[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                arr[i].add(new int[]{Integer.parseInt(input[j]), j});
            }

            arr[i].sort(Comparator.comparingInt(o -> o[0]));
        }

        var dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[0]));
            pq.addAll(arr[i]);
            var visited = new boolean[n];
            visited[i] = true;

            while (!pq.isEmpty()) {
                var now = pq.poll();
                int weight = now[0], node = now[1];

                if (visited[node]) {
                    continue;
                }

                visited[node] = true;
                dp[i][node] = weight;

                for (var entry : arr[node]) {
                    pq.add(new int[]{entry[0] + weight, entry[1]});
                }
            }
        }

        var visited = new HashSet<Integer>();
        visited.add(m);
        solve(dp, m, 0, visited);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(int[][] dp, int prev, int sum, HashSet<Integer> visited) {
        if (result <= sum) {
            return;
        }

        if (visited.size() == n) {
            result = sum;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }

            visited.add(i);
            solve(dp, i, sum + dp[prev][i], visited);
            visited.remove(i);
        }
    }
}
