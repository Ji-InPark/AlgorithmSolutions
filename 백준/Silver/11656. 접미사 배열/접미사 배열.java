

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        var pq = new PriorityQueue<String>();

        pq.add(input);
        for (int i = 1; i < input.length(); i++) {
            pq.add(input.substring(i));
        }

        while (!pq.isEmpty()) {
            bw.write(pq.poll() + "\n");
        }

        bw.flush();
    }

}
