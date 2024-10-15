import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int result = 0, s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        s = Integer.parseInt(inputs[1]);
        inputs = br.readLine().split(" ");
        var arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        solve(arr, 0, 0);

		if (s == 0) {
			result--;
		}

        bw.write(result + "\n");
        bw.flush();

    }

    private static void solve(int[] arr, int index, int sum) {
        if (index == arr.length) {
            if (sum == s) {
                result++;
            }
            return;
        }

        solve(arr, index + 1, sum);
        solve(arr, index + 1, sum + arr[index]);
    }

}
