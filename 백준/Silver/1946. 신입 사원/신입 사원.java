import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            var arr = new int[n][3];

            for (int i = 0; i < n; i++) {
                var inputs = br.readLine().split(" ");

                arr[i][0] = Integer.parseInt(inputs[0]);
                arr[i][1] = Integer.parseInt(inputs[1]);
                arr[i][2] = 1;
            }

            Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
            var min = arr[0][1];
            for (int i = 1; i < n; i++) {
                if (arr[i][1] > min) {
                    arr[i][2] = 0;
                }

                min = Math.min(min, arr[i][1]);
            }

            Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));
            min = arr[0][0];
            for (int i = 1; i < n; i++) {
                if (arr[i][0] > min) {
                    arr[i][2] = 0;
                }

                min = Math.min(min, arr[i][0]);
            }

            var result = 0;
            for (var nums : arr) {
                result += nums[2];
            }
            bw.write(result + "\n");
        }

        bw.flush();
    }
}