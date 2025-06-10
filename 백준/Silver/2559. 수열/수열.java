

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]), sum = 0, result = 0;

        var inputs = br.readLine().split(" ");
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        result = sum;

        for (int i = k; i < n; i++) {
            sum += arr[i];
            sum -= arr[i - k];

            result = Math.max(result, sum);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
