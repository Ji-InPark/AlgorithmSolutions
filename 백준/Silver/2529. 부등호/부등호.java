import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        var inequalitySigns = new boolean[k];
        var inputs = br.readLine().split(" ");

        for (int i = 0; i < k; i++) {
            inequalitySigns[i] = inputs[i].equals(">");
        }

        var indexes = new int[10];
        for (int i = 9; i >= 0; i--) {
            indexes[9 - i] = i;
        }

        var maxResultArr = new int[k + 1];
        var minResultArr = new int[k + 1];

        solve(maxResultArr, new boolean[10], inequalitySigns, 0, indexes);
        Arrays.sort(indexes);
        solve(minResultArr, new boolean[10], inequalitySigns, 0, indexes);

        for (var num : maxResultArr) {
            bw.write(String.valueOf(num));
        }

        bw.write("\n");

        for (var num : minResultArr) {
            bw.write(String.valueOf(num));
        }

        bw.flush();
    }

    private static boolean solve(int[] resultArr, boolean[] isUsed, boolean[] inequalitySigns, int level,
            int[] indexes) {
        if (level == resultArr.length) {
            return true;
        }

        for (var i : indexes) {
            if (isUsed[i]) {
                continue;
            }

            if (level > 0) {
                if (inequalitySigns[level - 1] && resultArr[level - 1] < i
                        || !inequalitySigns[level - 1] && resultArr[level - 1] > i) {
                    continue;
                }

            }

            isUsed[i] = true;
            resultArr[level] = i;
            var result = solve(resultArr, isUsed, inequalitySigns, level + 1, indexes);
            if (result) {
                return true;
            }
            isUsed[i] = false;
        }

        return false;
    }
}
