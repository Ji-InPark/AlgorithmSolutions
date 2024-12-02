import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var input = br.readLine().split(" ");
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        var s = Integer.parseInt(br.readLine());

        var startIndex = 0;
        while (s > 0 && startIndex < n) {
            var maxIndex = startIndex;

            for (int i = startIndex + 1; i < Math.min(n, startIndex + s + 1); i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }

            var maxVal = arr[maxIndex];
            shift(arr, startIndex, maxIndex);
            arr[startIndex] = maxVal;

            s -= maxIndex - startIndex;

            startIndex++;
        }

        for (int i = 0; i < n; i++) {
            bw.write(arr[i] + " ");
        }
        bw.flush();
    }

    private static void shift(int[] arr, int start, int end) {
        for (int i = end; i > start; i--) {
            arr[i] = arr[i - 1];
        }
    }
}
