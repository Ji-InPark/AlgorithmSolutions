

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            var inputs = br.readLine();
            if (inputs.length() == 1) {
                break;
            }

            var inputArr = inputs.split(" ");
            var arr = new int[inputArr.length - 1];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(inputArr[i + 1]);
            }

            solve(arr, new LinkedList<>(), 0, 0);
            bw.write("\n");
        }

        bw.flush();
    }

    private static void solve(int[] arr, LinkedList<Integer> list, int level, int index) throws IOException {
        if (level == 6) {
            for (var num : list) {
                bw.write(num + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = index; i < arr.length; i++) {
            list.add(arr[i]);
            solve(arr, list, level + 1, i + 1);
            list.pollLast();
        }
    }

}
