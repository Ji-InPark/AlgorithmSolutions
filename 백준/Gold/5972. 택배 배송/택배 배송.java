

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var map = new HashMap<Integer, ArrayList<int[]>>();
        var dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]), b = Integer.parseInt(inputs[1]), length = Integer.parseInt(inputs[2]);

            map.get(a).add(new int[]{b, length});
            map.get(b).add(new int[]{a, length});
        }

        for (int i = 1; i <= n; i++) {
            map.get(i).sort(Comparator.comparingInt(o -> o[1]));
        }

        var q = new LinkedList<Integer>();
        q.add(1);
        dp[1] = 0;

        while (!q.isEmpty()) {
            var now = q.poll();

            for (var next : map.get(now)) {
                int nextNode = next[0], nextLength = dp[now] + next[1];

                if (dp[nextNode] <= nextLength) {
                    continue;
                }

                dp[nextNode] = nextLength;

                q.add(nextNode);
            }
        }

        bw.write(dp[n] + "\n");
        bw.flush();
    }
}
