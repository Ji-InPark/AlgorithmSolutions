

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            var now = Integer.parseInt(br.readLine());

            arr[i][0] = now;
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        var result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, arr[i][1] - i + 1);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
