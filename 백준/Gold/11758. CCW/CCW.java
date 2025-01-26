

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs1 = br.readLine().split(" ");
        int x1 = Integer.parseInt(numInputs1[0]), y1 = Integer.parseInt(numInputs1[1]);

        var numInputs2 = br.readLine().split(" ");
        int x2 = Integer.parseInt(numInputs2[0]), y2 = Integer.parseInt(numInputs2[1]);

        var numInputs3 = br.readLine().split(" ");
        int x3 = Integer.parseInt(numInputs3[0]), y3 = Integer.parseInt(numInputs3[1]);

        if (x2 - x1 == 0) {
            if (x3 - x2 == 0) {
                bw.write("0");
            } else if (y2 - y1 > 0) {
                if (x3 - x2 > 0) {
                    bw.write("-1");
                } else {
                    bw.write("1");
                }
            } else {
                if (x3 - x2 > 0) {
                    bw.write("1");
                } else {
                    bw.write("-1");
                }
            }
        } else if (y2 - y1 == 0) {
            if (y3 - y2 == 0) {
                bw.write("0");
            } else if (x2 - x1 > 0) {
                if (y3 - y2 > 0) {
                    bw.write("1");
                } else {
                    bw.write("-1");
                }
            } else {
                if (y3 - y2 > 0) {
                    bw.write("-1");
                } else {
                    bw.write("1");
                }
            }
        } else {
            int nx1 = x1, nx2 = x2, ny1 = y1, ny2 = y2;

            if (x1 > x2) {
                nx1 = x2;
                nx2 = x1;
                ny1 = y2;
                ny2 = y1;
            }

            int predictedY = (ny2 - ny1) * (x3 - nx1) + ny1 * (nx2 - nx1);
            int realY = y3 * (nx2 - nx1);

            if (predictedY == realY) {
                bw.write("0");
            } else {
                if (x2 - x1 > 0) {
                    if (predictedY > realY) {
                        bw.write("-1");
                    } else {
                        bw.write("1");
                    }
                } else {
                    if (predictedY > realY) {
                        bw.write("1");
                    } else {
                        bw.write("-1");
                    }
                }
            }

        }

        bw.flush();
    }
}
