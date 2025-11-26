

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int h = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]);

        var inputs = br.readLine().split(" ");
        var arr = new int[w];

        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        var result = 0;
        for (int i = h; i > 0; i--) {
            var hasLeft = false;
            var prevIndex = -1;

            for (int j = 0; j < w; j++) {
                if (arr[j] >= i) {
                    if (hasLeft) {
                        result += j - prevIndex - 1;
                    }

                    hasLeft = true;
                    prevIndex = j;
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
