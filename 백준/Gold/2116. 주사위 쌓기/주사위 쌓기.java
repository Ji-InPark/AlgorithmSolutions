import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var oppositeIndex = new int[]{5, 3, 4, 1, 2, 0};
        var sideIndexes = new int[][]{
                {1, 2, 3, 4},
                {0, 2, 4, 5},
                {0, 1, 3, 5},
                {0, 2, 4, 5},
                {0, 1, 3, 5},
                {1, 2, 3, 4},
        };

        var n = Integer.parseInt(br.readLine());
        var dices = new int[n][6];
        var diceIndexes = new int[n][6];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(inputs[j]) - 1;
                diceIndexes[i][dices[i][j]] = j;
            }
        }

        var result = 0;
        for (int i = 0; i < 6; i++) {
            var currentBottom = dices[0][i];
            var sum = 0;

            for (int j = 0; j < n; j++) {
                var currentIndex = diceIndexes[j][currentBottom];
                var max = 0;
                for (var index : sideIndexes[currentIndex]) {
                    max = Math.max(max, dices[j][index]);
                }
                sum += max;
                currentBottom = dices[j][oppositeIndex[currentIndex]];
            }

            result = Math.max(result, sum);
        }

        result += n;
        bw.write(result + "\n");
        bw.flush();
    }
}
