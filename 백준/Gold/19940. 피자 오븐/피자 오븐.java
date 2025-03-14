

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(br.readLine());
            var arr = new int[5];

            var hCount = num / 60;

            if (Math.abs(num - 60 * hCount) <= Math.abs(num - 60 * (hCount + 1)) + 10) {
                arr[0] = hCount;
            } else {
                arr[0] = hCount + 1;
            }
            num -= 60 * arr[0];

            var tCount = Math.abs(num) / 10;
            var absNum = Math.abs(num);
            if (Math.abs(absNum - 10 * tCount) <= Math.abs(absNum - 10 * (tCount + 1))) {
                if (num < 0) {
                    arr[2] = tCount;
                    arr[4] = absNum - 10 * tCount;
                } else {
                    arr[1] = tCount;
                    arr[3] = absNum - 10 * tCount;
                }
            } else {
                if (num < 0) {
                    arr[2] = tCount + 1;
                    arr[3] = Math.abs(absNum - 10 * (tCount + 1));
                } else {
                    arr[1] = tCount + 1;
                    arr[4] = Math.abs(absNum - 10 * (tCount + 1));
                }
            }

            for (int j = 0; j < 5; j++) {
                bw.write(arr[j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
    }
}

