import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int n, result = 0;
    static int[][] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        scores = new int[n][9];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < 9; j++) {
                scores[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        solve(new int[9], new boolean[9], 0);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(int[] players, boolean[] usedPlayer, int level) {
        if (level == 9) {
            result = Math.max(result, getScore(players));
            return;
        }

        if (level == 3) {
            players[level] = 0;
            solve(players, usedPlayer, level + 1);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!usedPlayer[i]) {
                usedPlayer[i] = true;
                players[level] = i;
                solve(players, usedPlayer, level + 1);
                usedPlayer[i] = false;
            }
        }
    }

    private static int getScore(int[] players) {
        int score = 0, playerIndex = 0;

        for (int i = 0; i < n; i++) {
            int outCount = 0;
            var bases = new boolean[3];

            while (outCount < 3) {
                switch (scores[i][players[playerIndex]]) {
                    case 0:
                        outCount++;
                        break;
                    case 1:
                        if (bases[2]) {
                            score++;
                        }
                        bases[2] = bases[1];
                        bases[1] = bases[0];
                        bases[0] = true;
                        break;
                    case 2:
                        for (int j = 1; j < 3; j++) {
                            if (bases[j]) {
                                score++;
                            }
                        }

                        bases[2] = bases[0];
                        bases[1] = true;
                        bases[0] = false;
                        break;
                    case 3:
                        for (int j = 0; j < 3; j++) {
                            if (bases[j]) {
                                score++;
                            }
                        }

                        bases[2] = true;
                        bases[1] = false;
                        bases[0] = false;
                        break;

                    case 4:
                        score++;
                        for (int j = 0; j < 3; j++) {
                            if (bases[j]) {
                                score++;
                            }
                            bases[j] = false;
                        }
                        break;
                }

                playerIndex++;
                playerIndex %= 9;
            }
        }

        return score;
    }
}
