import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        var set = new LinkedHashSet<Integer>();

        for (int i = 2; i <= 1000000; i++) {
            if (!isPrime[i]) {
                continue;
            }

            set.add(i);

            for (int j = i * 2; j <= 1000000; j += i) {
                isPrime[j] = false;
            }
        }

        Loop:
        while (true) {
            var n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            for (var num : set) {
                if (set.contains(n - num)) {
                    bw.write(n + " = " + num + " + " + (n - num) + "\n");
                    continue Loop;
                }
            }

            bw.write("Goldbach's conjecture is wrong.\n");
        }

        bw.flush();
    }
}
