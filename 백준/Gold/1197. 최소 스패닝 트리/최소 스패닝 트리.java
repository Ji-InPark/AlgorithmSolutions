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

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]);
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[2]));
        var arr = new int[n + 1];
        
        for(int i = 1; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            var lines = br.readLine().split(" ");
            pq.add(new int[]{Integer.parseInt(lines[0]), Integer.parseInt(lines[1]), Integer.parseInt(lines[2])});
        }

        var result = 0L;
        while (!pq.isEmpty()) {
            var now = pq.poll();

            int t1 = find(arr, now[0]), t2 = find(arr, now[1]);

            if (t1 == t2) {
                continue;
            }

            result += now[2];

            if (t1 < t2) {
                arr[t2] = t1;
            } else {
                arr[t1] = t2;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int find(int[] arr, int index) {
        if (arr[index] == index) {
            return index;
        }

        return arr[index] = find(arr, arr[index]);
    }
}
