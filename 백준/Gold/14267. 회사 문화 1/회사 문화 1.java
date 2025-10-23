

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static int[][] dn = new int[][]{{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        var arr = new long[n + 1];
        var inputs = br.readLine().split(" ");
        for (int i = 2; i <= n; i++) {
            var parent = Integer.parseInt(inputs[i - 1]);

            map.get(parent).add(i);
        }

        for (int i = 0; i < m; i++) {
            var nums = br.readLine().split(" ");
            int node = Integer.parseInt(nums[0]), w = Integer.parseInt(nums[1]);

            arr[node] += w;
        }

        var q = new LinkedList<Integer>();
        q.add(1);

        while (!q.isEmpty()) {
            var now = q.poll();

            for (var next : map.get(now)) {
                arr[next] += arr[now];
                q.add(next);
            }
        }

        for (int i = 1; i <= n; i++) {
            bw.write(arr[i] + " ");
        }
        bw.flush();
    }
}