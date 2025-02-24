

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
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var inputs = br.readLine().split(" ");
        var arr = new int[n];
        int max = 0;
        var isPositiveMax = true;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);

            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
                isPositiveMax = arr[i] > 0;
            }
        }

        var fpq = new PriorityQueue<Integer>(Comparator.reverseOrder());
        var spq = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (var num : arr) {
            if (num > 0) {
                if (isPositiveMax) {
                    spq.add(num);
                } else {
                    fpq.add(num);
                }
            } else {
                if (isPositiveMax) {
                    fpq.add(-num);
                } else {
                    spq.add(-num);
                }
            }
        }

        var result = 0;
        while (fpq.size() > m) {
            result += fpq.peek() * 2;
            for (int i = 0; i < m; i++) {
                fpq.poll();
            }
        }
        if (!fpq.isEmpty()) {
            result += fpq.poll() * 2;
        }

        result += max;
        while (spq.size() > m) {
            for (int i = 0; i < m; i++) {
                spq.poll();
            }
            result += spq.peek() * 2;
        }

        bw.write(result + "\n");
        bw.flush();
    }

}

