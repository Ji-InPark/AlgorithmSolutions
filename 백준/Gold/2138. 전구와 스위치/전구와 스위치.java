import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int n, result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        var startArr = new boolean[n + 2];
        var endArr = new boolean[n + 2];
        var originArr = new boolean[n + 2];

        var startInput = br.readLine();
        var endInput = br.readLine();

        for (int i = 1; i <= n; i++) {
            originArr[i] = startInput.charAt(i - 1) == '1';
            endArr[i] = endInput.charAt(i - 1) == '1';
        }

        getCount(startArr, endArr, originArr, new int[]{});
        getCount(startArr, endArr, originArr, new int[]{1});
        getCount(startArr, endArr, originArr, new int[]{n});
        getCount(startArr, endArr, originArr, new int[]{1, n});

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static void getCount(boolean[] startArr, boolean[] endArr, boolean[] originArr, int[] indexes) {
        System.arraycopy(originArr, 0, startArr, 0, n + 2);
        for (var index : indexes) {
            flip(startArr, index);
        }

        int count = indexes.length;
        for (int i = 2; i < n; i++) {
            if (startArr[i - 1] != endArr[i - 1]) {
                flip(startArr, i);
                count++;
            }
        }

        if (isSame(startArr, endArr)) {
            result = Math.min(result, count);
        }
    }

    private static void flip(boolean[] arr, int index) {
        for (int i = -1; i < 2; i++) {
            arr[index + i] = !arr[index + i];
        }
    }

    private static boolean isSame(boolean[] startArr, boolean[] endArr) {
        for (int i = 1; i < startArr.length - 1; i++) {
            if (startArr[i] != endArr[i]) {
                return false;
            }
        }

        return true;
    }
}
