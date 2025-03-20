

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), totalSum = 0;
        var arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            totalSum += arr[i];
        }

        int left = 0, sum = 0, result = 0;
        for (int i = 0; i < 2 * n; i++) {
            sum += arr[i % n];
            while (totalSum - sum < sum) {
                sum -= arr[(left++) % n];
            }

            result = Math.max(result, sum);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

