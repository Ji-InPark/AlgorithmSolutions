

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> -o[1]));

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            pq.add(new int[]{Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])});
        }

        var temp = pq.poll();
        var lastTime = temp[1] - temp[0];

        while (!pq.isEmpty()) {
            var now = pq.poll();

            lastTime = Math.min(lastTime, now[1]) - now[0];
        }

        if (lastTime < 0) {
            lastTime = -1;
        }

        bw.write(lastTime + "\n");
        bw.flush();
    }
}
