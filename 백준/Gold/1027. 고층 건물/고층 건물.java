import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, getMaxLeftLength(arr, i) + getMaxRightLength(arr, i));
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int getMaxRightLength(int[] arr, int index) {
        if (index == arr.length - 1) {
            return 0;
        }

        int maxLength = 1;
        double maxInc = arr[index + 1] - arr[index];

        for (int i = index + 2; i < arr.length; i++) {
            var inc = (arr[i] - arr[index]) / (double) (i - index);

            if (maxInc < inc) {
                maxInc = inc;
                maxLength++;
            }
        }

        return maxLength;
    }

    private static int getMaxLeftLength(int[] arr, int index) {
        if (index == 0) {
            return 0;
        }

        int maxLength = 1;
        double minInc = arr[index] - arr[index - 1];

        for (int i = index - 2; i >= 0; i--) {
            var inc = (arr[index] - arr[i]) / (double) (index - i);

            if (minInc > inc) {
                minInc = inc;
                maxLength++;
            }
        }

        return maxLength;
    }
}
