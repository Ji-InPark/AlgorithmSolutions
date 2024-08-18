import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        inputs = br.readLine().split(" ");
        var set = new HashSet<Integer>();
        for (var input : inputs) {
            set.add(Integer.parseInt(input));
        }

        var arr = new int[n + 1];
        int max = n + 1;

        for (int i = n; i > 0; i--) {
            arr[i] = max;

            if (set.contains(i)) {
                max = i;
            }
        }

        inputs = br.readLine().split(" ");
        for (var input : inputs) {
            var num = Integer.parseInt(input);
            var targetNum = findNum(arr, num);

            bw.write(targetNum + "\n");
        }

        bw.flush();
    }

    private static int findNum(int[] arr, int index) {
        if (arr[index] < 0) {
            return findNum(arr, -arr[index]);
        }

        arr[index] *= -1;

        return -arr[index];
    }
}