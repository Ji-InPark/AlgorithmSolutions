

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
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            int x1 = Integer.parseInt(inputs[0]), y1 = Integer.parseInt(inputs[1]), r1 = Integer.parseInt(inputs[2]);
            int x2 = Integer.parseInt(inputs[3]), y2 = Integer.parseInt(inputs[4]), r2 = Integer.parseInt(inputs[5]);

            int x = Math.abs(x1 - x2), y = Math.abs(y1 - y2);
            var dist = Math.sqrt(x * x + y * y);

            if (x1 == x2 && y1 == y2 && r1 == r2) {
                bw.write("-1");
            } else if (r1 + r2 == dist) {
                bw.write("1");
            } else if (r1 + r2 > dist) {
                if ((int) dist + Math.min(r1, r2) == Math.max(r1, r2)) {
                    bw.write("1");
                } else if ((int) dist + Math.min(r1, r2) > Math.max(r1, r2)) {
                    bw.write("2");
                } else {
                    bw.write("0");
                }
            } else {
                bw.write("0");
            }

            bw.write("\n");
        }
        bw.flush();
    }
}
