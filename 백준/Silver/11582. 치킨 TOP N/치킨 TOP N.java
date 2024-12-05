import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(inputs[i]);
        }

        var k = n / Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i += k) {
            Arrays.sort(nums, i, i + k);
        }

        for (int i = 0; i < n; i++) {
            bw.write(nums[i] + " ");
        }
        bw.flush();
    }
}
