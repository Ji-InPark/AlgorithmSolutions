import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = new HashMap<Integer, ArrayList<int[]>>();

        var numInputs = br.readLine().split(" ");
        int v = Integer.parseInt(numInputs[0]), e = Integer.parseInt(numInputs[1]);
        var arr = new int[v + 1][v + 1];

        for (int i = 0; i < v + 1; i++) {
            Arrays.fill(arr[i], 1000000000);
        }

        for (int i = 0; i < e; i++) {
            var inputs = br.readLine().split(" ");
            int start = Integer.parseInt(inputs[0]), end = Integer.parseInt(inputs[1]), distance = Integer.parseInt(
                    inputs[2]);

            arr[start][end] = distance;
        }

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (arr[i][k] + arr[k][j] < arr[i][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        int result = 2000000000;
        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                if (arr[i][j] == 1000000000 || arr[j][i] == 1000000000) {
                    continue;
                }
                
                result = Math.min(arr[i][j] + arr[j][i], result);
            }
        }

        if (result == 2000000000) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
