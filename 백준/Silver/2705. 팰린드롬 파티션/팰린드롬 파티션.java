import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var arr = new int[1001];
        arr[0] = 0;
        arr[1] = 1;

        var sum = new int[1002];
        sum[0] = 0;
        sum[1] = 1;

        for (int i = 2; i <= 1000; i++) {
            arr[i] = sum[i / 2] - sum[0] + 1;
            sum[i] = sum[i - 1] + arr[i];
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(br.readLine());

            bw.write(arr[num] + "\n");
        }

        bw.flush();
    }
}
