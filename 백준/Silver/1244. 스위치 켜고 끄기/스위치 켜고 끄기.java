import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var arr = new boolean[n + 1];
        var inputs = br.readLine().split(" ");

        for (int i = 1; i <= n; i++) {
            arr[i] = inputs[i - 1].equals("1");
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            var num = Integer.parseInt(inputs[1]);

            if (inputs[0].equals("1")) {
                flipByBoy(arr, num);
            } else {
                flipByGirl(arr, num, num);
            }
        }

        for (int i = 1; i <= n; i++) {
            bw.write(arr[i] ? "1 " : "0 ");

            if (i % 20 == 0) {
                bw.write("\n");
            }
        }
        bw.flush();
    }

    private static void flipByBoy(boolean[] arr, int index) {
        for (int i = index; i < arr.length; i += index) {
            arr[i] = !arr[i];
        }
    }

    private static void flipByGirl(boolean[] arr, int l, int r) {
        if (l <= 0 || r >= arr.length) {
            return;
        }

        if (arr[l] != arr[r]) {
            return;
        }

        var flag = arr[l];
        arr[l] = arr[r] = !flag;
        flipByGirl(arr, l - 1, r + 1);
    }
}
