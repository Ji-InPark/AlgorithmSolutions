import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]), r = Integer.parseInt(inputs[1]), c = Integer.parseInt(inputs[2]);

        var result = solve((int) Math.pow(2, n), r, c, 0);
        bw.write(result + "\n");
        bw.flush();
    }

    private static int solve(int n, int r, int c, int start) {
        if (n == 1) {
            return start;
        }

        var half = n / 2;

        if (r < half && c < half) {
            return solve(half, r, c, start);
        } else if (r < half && c >= half) {
            return solve(half, r, c - half, start + half * half);
        } else if (r >= half && c < half) {
            return solve(half, r - half, c, start + half * half * 2);
        } else {
            return solve(half, r - half, c - half, start + half * half * 3);
        }
    }
}
