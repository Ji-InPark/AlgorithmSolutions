import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    static HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        boolean[] isBlocked = new boolean[n], visited = new boolean[n];
        var arr = new long[n];
        var pointInputs = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            isBlocked[i] = pointInputs[i].equals("1");
            map.put(i, new ArrayList<>());
        }
        isBlocked[n - 1] = false;
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[0] = 0;

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]), b = Integer.parseInt(inputs[1]), t = Integer.parseInt(inputs[2]);

            if (isBlocked[a] || isBlocked[b]) {
                continue;
            }

            map.get(a).add(new int[]{b, t});
            map.get(b).add(new int[]{a, t});
        }

        var pq = new PriorityQueue<long[]>(Comparator.comparingLong(o -> o[1]));
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            var now = pq.poll();
            int index = (int) now[0];

            if (visited[index]) {
                continue;
            }

            visited[index] = true;

            for (var entry : map.get(index)) {
                arr[entry[0]] = Math.min(arr[entry[0]], entry[1] + arr[index]);
                pq.add(new long[]{entry[0], entry[1] + arr[index]});
            }
        }

        if (arr[n - 1] == Long.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(arr[n - 1] + "\n");
        }
        bw.flush();
    }
}
