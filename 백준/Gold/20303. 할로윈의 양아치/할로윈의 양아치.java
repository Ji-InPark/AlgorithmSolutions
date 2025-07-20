

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), k = Integer.parseInt(numInputs[2]);

        numInputs = br.readLine().split(" ");
        var candies = new int[n + 1];

        var arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
            candies[i] = Integer.parseInt(numInputs[i - 1]);
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int n1 = Integer.parseInt(inputs[0]), n2 = Integer.parseInt(inputs[1]);

            int u1 = find(arr, n1), u2 = find(arr, n2);

            if (u1 < u2) {
                arr[u2] = u1;
            } else {
                arr[u1] = u2;
            }
        }

        var map = new HashMap<Integer, int[]>();
        for (int i = 1; i <= n; i++) {
            if (!map.containsKey(find(arr, i))) {
                map.put(find(arr, i), new int[2]);
            }

            var temp = map.get(find(arr, i));
            temp[0]++;
            temp[1] += candies[i];
        }

        var counts = new ArrayList<>(map.values());
        var dp = new int[k][map.size() + 1];
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= counts.size(); j++) {
                var count = counts.get(j - 1);
                if (i - count[0] < 0) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    continue;
                }

                dp[i][j] = Math.max(dp[i - count[0]][j - 1] + count[1], Math.max(dp[i][j - 1], dp[i - 1][j]));
            }
        }

        bw.write(dp[k - 1][map.size()] + "\n");
        bw.flush();
    }

    private static int find(int[] arr, int index) {
        if (arr[index] == index) {
            return index;
        }

        return arr[index] = find(arr, arr[index]);
    }
}
