

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), l = Integer.parseInt(numInputs[2]);
        var inputs = br.readLine().split(" ");
        var arr = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(inputs[i]));
        }

        Collections.sort(arr);

        if (n == 0) {
            var result = l / (m + 1) + (l % (m + 1) == 0 ? 0 : 1);
            bw.write(result + "\n");
        } else {
            int left = 0, right = l;
            while (left + 1 < right) {
                var mid = left + (right - left) / 2;

                if (isValid(arr, m, l, mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }

            bw.write(right + "\n");
        }

        bw.flush();
    }

    private static boolean isValid(ArrayList<Integer> arr, int m, int l, int diff) {
        int count = 0, prev = 0;

        for (var num : arr) {
            while (prev + diff < num) {
                count++;
                prev += diff;
            }
            prev = num;
        }

        while (prev + diff < l) {
            count++;
            prev += diff;
        }

        return count <= m;
    }
}

