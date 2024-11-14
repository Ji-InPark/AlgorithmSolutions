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

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var n = Integer.parseInt(br.readLine());
            var inputs = br.readLine().split(" ");
            var nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(inputs[i]);
            }

            Arrays.sort(nums);

            int left = nums[1], right = nums[2], result = Math.max(nums[1] - nums[0], nums[2] - nums[0]);
            for (int i = 3; i < n; i++) {
                int ld = nums[i] - left, rd = nums[i] - right;

                if (ld > rd) {
                    result = Math.max(result, ld);
                    left = nums[i];
                } else {
                    result = Math.max(result, rd);
                    right = nums[i];
                }
            }
            result = Math.max(result, Math.abs(left - right));

            bw.write(result + "\n");
        }

        bw.flush();
    }
}
