import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        long[] nums = new long[input.length()], combinations = new long[5001];
        long mod = 1000000;

        combinations[0] = combinations[1] = 1;
        for (int i = 2; i <= 5000; i++) {
            combinations[i] = (combinations[i - 1] + combinations[i - 2]) % mod;
        }

        for (int i = 0; i < input.length(); i++) {
            nums[i] = input.charAt(i) - '0';
        }

        long prev = 10, result = 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            var calculatedValue = prev * 10 + nums[i];

            if (10 <= calculatedValue && calculatedValue <= 26) {
                count++;
            } else {
                if (nums[i] == 0 && (prev > 2 || prev == 0)) {
                    bw.write("0");
                    bw.flush();
                    return;
                }

                if (prev == 0) {
                    count -= 2;
                }

                if (count > 1) {
                    result *= combinations[count];
                    result %= mod;
                }

                count = 1;
            }

            prev = nums[i];
        }

        if (prev == 0) {
            count -= 2;
        }

        if (count > 1) {
            result *= combinations[count] % mod;
            result %= mod;
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
