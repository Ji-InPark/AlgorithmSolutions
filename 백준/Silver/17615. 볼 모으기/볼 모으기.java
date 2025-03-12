

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
        var arr = br.readLine().toCharArray();

        int sum = 0;
        boolean isPrev = true;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'R') {
                if (!isPrev) {
                    sum++;
                }
            } else {
                isPrev = false;
            }
        }

        var result = sum;

        sum = 0;
        isPrev = true;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'B') {
                if (!isPrev) {
                    sum++;
                }
            } else {
                isPrev = false;
            }
        }

        result = Math.min(result, sum);

        sum = 0;
        isPrev = true;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 'R') {
                if (!isPrev) {
                    sum++;
                }
            } else {
                isPrev = false;
            }
        }

        result = Math.min(result, sum);

        sum = 0;
        isPrev = true;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 'B') {
                if (!isPrev) {
                    sum++;
                }
            } else {
                isPrev = false;
            }
        }

        result = Math.min(result, sum);

        bw.write(result + "\n");
        bw.flush();
    }
}

