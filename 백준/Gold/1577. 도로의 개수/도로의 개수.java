import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int w = Integer.parseInt(numInputs[0]), h = Integer.parseInt(numInputs[1]);

        var dp = new long[h + 1][w + 1];
        dp[0][0] = 1;

        var isLeftBlocked = new boolean[h + 1][w + 1];
        var isDownBlocked = new boolean[h + 1][w + 1];

        var k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            var blockedPoints = br.readLine().split(" ");
            int x1 = Integer.parseInt(blockedPoints[0]), y1 = Integer.parseInt(blockedPoints[1]);
            int x2 = Integer.parseInt(blockedPoints[2]), y2 = Integer.parseInt(blockedPoints[3]);
            int blockedX = Math.min(x1, x2);
            int blockedY = Math.min(y1, y2);

            if (x1 == x2) {
                isDownBlocked[blockedY][blockedX] = true;
            } else {
                isLeftBlocked[blockedY][blockedX] = true;
            }
        }

        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                var downCount = y > 0 ? (isDownBlocked[y - 1][x] ? 0 : dp[y - 1][x]) : 0;
                var leftCount = x > 0 ? (isLeftBlocked[y][x - 1] ? 0 : dp[y][x - 1]) : 0;

                dp[y][x] = downCount + leftCount;
            }
        }

        bw.write(dp[h][w] + "\n");
        bw.flush();
    }
}
