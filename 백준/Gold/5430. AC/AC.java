

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        Loop:
        for (int t = 0; t < T; t++) {
            var functions = br.readLine();
            var n = Integer.parseInt(br.readLine());
            var inputs = br.readLine().replace("[", "").replace("]", "").split(",");
            var arr = new ArrayDeque<Integer>();
            for (int i = 0; i < n; i++) {
                arr.add(Integer.parseInt(inputs[i]));
            }

            var isStart = true;
            for (var c : functions.toCharArray()) {
                if (c == 'R') {
                    isStart = !isStart;
                } else {
                    if (arr.isEmpty()) {
                        bw.write("error\n");
                        continue Loop;
                    }

                    if (isStart) {
                        arr.pollFirst();
                    } else {
                        arr.pollLast();
                    }
                }
            }

            if (arr.isEmpty()) {
                bw.write("[]\n");
                continue;
            }

            bw.write("[");
            while (arr.size() > 1) {
                if (isStart) {
                    bw.write(arr.pollFirst() + ",");
                } else {
                    bw.write(arr.pollLast() + ",");
                }
            }
            bw.write(arr.pollFirst() + "]\n");
        }

        bw.flush();
    }
}
