

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

        int n = Integer.parseInt(br.readLine()), dasom = Integer.parseInt(br.readLine());
        var pq = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (int i = 0; i < n - 1; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        var result = 0;
        while (!pq.isEmpty() && dasom <= pq.peek()) {
            var now = pq.poll();
            dasom++;
            pq.add(now - 1);
            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

