

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);

        var inputs = br.readLine().split(" ");
        var pq = new PriorityQueue<Integer>();
        for (var raw : inputs) {
            pq.add(Integer.parseInt(raw));
        }

        while (!pq.isEmpty()) {
            if (pq.poll() <= k) {
                k++;
            } else {
                break;
            }
        }

        bw.write(k + "\n");
        bw.flush();
    }
}
