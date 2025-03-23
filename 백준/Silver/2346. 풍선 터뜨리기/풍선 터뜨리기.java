

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var arr = new int[n];
        var balloons = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
            balloons[i] = i + 1;
        }

        var index = 1000000000;
        for (int i = 0; i < n; i++) {
            var realIndex = index % n;
            var next = arr[realIndex];
            balloons[realIndex] = -1;
            bw.write(realIndex + 1 + " ");

            if (i == n - 1) {
                break;
            }

            if (next < 0) {
                for (int j = 0; j < Math.abs(next); j++) {
                    if (balloons[(--index) % n] == -1) {
                        j--;
                    }
                }
            } else {
                for (int j = 0; j < Math.abs(next); j++) {
                    if (balloons[(++index) % n] == -1) {
                        j--;
                    }
                }
            }
        }

        bw.flush();
    }
}

