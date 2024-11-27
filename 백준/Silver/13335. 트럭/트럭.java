import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]), l = Integer.parseInt(numInputs[2]);

        var inputs = br.readLine().split(" ");
        var trucks = new int[n];

        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(inputs[i]);
        }

        var bridge = new int[w + 1];
        int index = 0, sum = 0, result = 0;

        while (index < n) {
            sum -= shiftRight(bridge);
            sum += trucks[index];

            while (sum > l) {
                sum -= shiftRight(bridge);
                result++;
            }

            bridge[0] = trucks[index++];
            result++;
        }

        while (sum > 0) {
            sum -= shiftRight(bridge);
            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int shiftRight(int[] arr) {
        int result = arr[arr.length - 2];

        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = 0;

        return result;
    }
}
