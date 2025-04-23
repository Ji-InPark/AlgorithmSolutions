

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main {

    static int result = Integer.MAX_VALUE, n;
    static int[] energies;
    static int[][] arr;
    static ArrayList<Integer> resultIndexes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        var energyInputs = br.readLine().split(" ");
        energies = new int[4];
        for (int i = 0; i < 4; i++) {
            energies[i] = Integer.parseInt(energyInputs[i]);
        }

        var totalSum = new int[5];
        arr = new int[n][5];
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
                totalSum[j] += arr[i][j];
            }
        }

        if (!isSuccess(energies, totalSum)) {
            bw.write("-1");
            bw.flush();
            return;
        }

        solve(0, 0, new int[4], new ArrayDeque<>());

        bw.write(result + "\n");
        for (var index : resultIndexes) {
            bw.write((index + 1) + " ");
        }
        bw.flush();
    }

    private static void solve(int index, int sum, int[] target, Deque<Integer> indexes) {
        if (isSuccess(energies, target) && sum < result) {
            result = sum;
            resultIndexes.clear();
            resultIndexes.addAll(indexes);
            return;
        }

        for (int i = index; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                target[j] += arr[i][j];
            }
            indexes.addLast(i);
            solve(i + 1, sum + arr[i][4], target, indexes);
            indexes.pollLast();
            for (int j = 0; j < 4; j++) {
                target[j] -= arr[i][j];
            }
        }
    }

    private static boolean isSuccess(int[] arr, int[] target) {
        for (int i = 0; i < 4; i++) {
            if (arr[i] > target[i]) {
                return false;
            }
        }

        return true;
    }

}
