

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), result = 0;

        var arr = new int[m][2];
        for (int i = 0; i < 2; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < m; j++) {
                arr[j][i] = Integer.parseInt(inputs[j]);
            }
        }

        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> -Math.min(o[0], o[1])));
        for (int i = 0; i < m; i++) {
            result += arr[i][0];

            arr[i][0] = 100 - arr[i][0];

            pq.add(arr[i]);
        }

        for (int i = 0; i < n * 24 && !pq.isEmpty(); i++) {
            var now = pq.poll();

            if (now[0] <= now[1]) {
                result += now[0];
            } else {
                result += now[1];
                now[0] -= now[1];
                pq.add(now);
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

