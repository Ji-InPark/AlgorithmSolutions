

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n + 1][2];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            arr[i][0] = Integer.parseInt(inputs[0]);

            var minute = Integer.parseInt(inputs[1].split(":")[0]);
            var second = Integer.parseInt(inputs[1].split(":")[1]);

            arr[i][1] = minute * 60 + second;
        }

        var scores = new int[3];
        var result = new int[3];
        var index = 0;

        for (int i = 0; i < 48 * 60; i++) {
            if (arr[index][1] == i) {
                scores[arr[index][0]]++;
                index++;
            }

            if (scores[1] == scores[2]) {
                continue;
            }

            if (scores[1] > scores[2]) {
                result[1]++;
            } else {
                result[2]++;
            }
        }

        for (int i = 1; i <= 2; i++) {
            var minute = result[i] / 60;
            var second = result[i] % 60;

            bw.write(String.format("%02d:%02d\n", minute, second));
        }

        bw.flush();
    }
}

