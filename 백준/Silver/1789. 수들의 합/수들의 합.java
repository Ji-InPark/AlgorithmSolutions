import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());
        long low = 1, high = 500000;

        while (low + 1 < high) {
            var mid = high - (high - low) / 2;

            if (n >= calculateSum(mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        bw.write(low + "\n");
        bw.flush();
    }

    private static long calculateSum(long n) {
        return (n * n + n) / 2;
    }
}