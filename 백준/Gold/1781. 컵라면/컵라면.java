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

        var n = Integer.parseInt(br.readLine());
        var pq = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        var resultPq = new PriorityQueue<Integer>();

        for (int i = 0; i < n; i++) {
            var input = br.readLine().split(" ");
            pq.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }

        int result = 0;

        for (int time = 0; time < n; time++) {
            while (resultPq.size() > time) {
                resultPq.poll();
            }

            while (!pq.isEmpty() && pq.peek()[0] <= time + 1) {
                resultPq.add(pq.poll()[1]);
            }
        }

        while (!resultPq.isEmpty()) {
            result += resultPq.poll();
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
