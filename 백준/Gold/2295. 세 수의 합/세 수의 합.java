

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr);

        var set = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                var sum = arr.get(i) + arr.get(j);
                set.add(sum);
            }
        }

        Loop:
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                var diff = arr.get(i) - arr.get(j);

                if (set.contains(diff)) {
                    bw.write(arr.get(i) + "\n");
                    break Loop;
                }
            }
        }

        bw.flush();
    }
}
