import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputNums = br.readLine().split(" ");
        int n = Integer.parseInt(inputNums[0]), s = Integer.parseInt(inputNums[1]);

        var inputs = br.readLine().split(" ");
        var nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(inputs[i]);
        }

        if (n == 1) {
            var dist = Math.abs(nums[0] - s);

            bw.write(dist + "\n");
        } else {
            var dist1 = Math.abs(nums[0] - s);
            var dist2 = Math.abs(nums[1] - s);

            var result = gcd(Math.max(dist1, dist2), Math.min(dist1, dist2));

            for (int i = 2; i < n; i++) {
                var dist = Math.abs(nums[i] - s);

                var nowGcd = gcd(Math.max(dist, result), Math.min(dist, result));
                result = Math.min(result, nowGcd);
            }

            bw.write(result + "\n");
        }

        bw.flush();
    }

    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }

        return gcd(q, p % q);
    }
}
