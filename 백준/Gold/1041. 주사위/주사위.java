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

        long n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var dice = new int[6];

        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(inputs[i]);
        }

        if (n == 1) {
            var max = Arrays.stream(dice).max().getAsInt();
            var sum = Arrays.stream(dice).sum();

            bw.write((sum - max) + "\n");
            bw.flush();
            return;
        }

        var threeSides = new int[][]{
                {0, 1, 2}, {0, 1, 3}, {0, 2, 4}, {0, 3, 4},
                {5, 1, 2}, {5, 1, 3}, {5, 4, 2}, {5, 4, 3}
        };
        var twoSides = new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {0, 4},
                {5, 1}, {5, 2}, {5, 3}, {5, 4},
                {1, 2}, {1, 3}, {4, 2}, {4, 3}
        };

        long min3 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min1 = Integer.MAX_VALUE;

        for (var sides : threeSides) {
            var sum = 0;
            for (var side : sides) {
                sum += dice[side];
            }

            min3 = Math.min(sum, min3);
        }

        for (var sides : twoSides) {
            var sum = 0;
            for (var side : sides) {
                sum += dice[side];
            }

            min2 = Math.min(sum, min2);
        }

        for (var num : dice) {
            min1 = Math.min(min1, num);
        }

        long result = min3 * 4L;
        result += min2 * 4 * (n - 1);
        result += min2 * 4 * (n - 2);
        result += (n - 2) * (n - 2) * min1;
        result += (n - 2) * (n - 1) * 4 * min1;

        bw.write(result + "\n");
        bw.flush();
    }
}
