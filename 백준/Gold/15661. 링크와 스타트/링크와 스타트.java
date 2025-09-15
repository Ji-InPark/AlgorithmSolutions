

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    static int n, result = Integer.MAX_VALUE;
    static int[][] sumArr;
    static boolean[] a1;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        var arr = new int[n][n];
        sumArr = new int[n][n];
        a1 = new boolean[n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                sumArr[i][j] = sumArr[j][i] = arr[i][j] + arr[j][i];
            }
        }

        solve(0);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(int index) {
        if (index == n) {
            calculate();
            return;
        }

        a1[index] = true;
        solve(index + 1);
        a1[index] = false;
        solve(index + 1);
    }

    private static void calculate() {
        var l1 = getIndexes(true);
        var l2 = getIndexes(false);

        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < l1.size(); i++) {
            for (int j = i + 1; j < l1.size(); j++) {
                sum1 += sumArr[l1.get(i)][l1.get(j)];
            }
        }

        for (int i = 0; i < l2.size(); i++) {
            for (int j = i + 1; j < l2.size(); j++) {
                sum2 += sumArr[l2.get(i)][l2.get(j)];
            }
        }

        result = Math.min(result, Math.abs(sum1 - sum2));
    }

    private static ArrayList<Integer> getIndexes(boolean flag) {
        var result = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            if (a1[i] == flag) {
                result.add(i);
            }
        }

        return result;
    }


}
