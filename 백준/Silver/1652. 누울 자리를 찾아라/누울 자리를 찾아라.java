import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int count = 0, result = 0;

        var arr = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            var input = br.readLine();

            for (int j = 0; j < n; j++) {
                arr[i][j] = input.charAt(j) == 'X';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]) {
                    count = 0;
                    continue;
                }

                if (++count == 2) {
                    result++;
                }
            }
            count = 0;
        }

        bw.write(result + " ");
        result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j][i]) {
                    count = 0;
                    continue;
                }

                if (++count == 2) {
                    result++;
                }
            }
            count = 0;
        }

        bw.write(result + "\n");
        bw.flush();

    }
}
