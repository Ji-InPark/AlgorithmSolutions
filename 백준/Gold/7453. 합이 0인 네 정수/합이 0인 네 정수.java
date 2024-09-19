import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>(16000000);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                var sum = arr[i][2] + arr[j][3];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                var sum = (arr[i][0] + arr[j][1]) * -1;

                result += map.getOrDefault(sum, 0);
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
