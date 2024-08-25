import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            arr[i][0] = Integer.parseInt(inputs[0]);
            arr[i][1] = Integer.parseInt(inputs[1]);
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        var result = 1;
        var pq = new PriorityQueue<Integer>();
        for (var nums : arr) {
            if (pq.isEmpty()) {
                pq.add(nums[1]);
                continue;
            }

            while (!pq.isEmpty() && pq.peek() <= nums[0]) {
                pq.poll();
            }
            pq.add(nums[1]);

            result = Math.max(result, pq.size());
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
