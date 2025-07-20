

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var scanner = new Scanner(System.in);

        Loop:
        while (scanner.hasNextInt()) {
            var diff = scanner.nextInt() * 10000000;
            var n = scanner.nextInt();
            var arr = new int[n + 2];

            arr[0] = -1;
            arr[n + 1] = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                arr[i] = scanner.nextInt();
            }

            Arrays.sort(arr);

            for (var num : arr) {
                var index = find(arr, diff - num);
                if (index >= 0) {
                    if (diff - num == num && !(arr[index - 1] == num || arr[index + 1] == num)) {
                        continue;
                    }
                    bw.write("yes " + num + " " + (diff - num) + "\n");
                    continue Loop;
                }
            }

            bw.write("danger\n");
        }

        bw.flush();
    }

    private static int find(int[] arr, int target) {
        int l = 0, h = arr.length - 1;

        while (l <= h) {
            int m = l + (h - l) / 2;

            if (arr[m] == target) {
                return m;
            } else if (arr[m] > target) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }

        return -1;
    }
}
