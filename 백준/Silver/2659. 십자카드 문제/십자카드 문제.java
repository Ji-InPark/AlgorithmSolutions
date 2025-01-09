import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        var arr = new int[5];
        var start = 9999;
        for (int i = 1; i <= 4; i++) {
            arr[i] = Integer.parseInt(numInputs[i - 1]);
        }

        for (int i = 0; i < 4; i++) {
            int num = shiftAndGetNum(arr);
            start = Math.min(start, num);
        }

        var set = new HashSet<Integer>();

        var runArr = new int[]{0, 1, 1, 1, 1};
        for (int i = 1; ; i++) {
            var isValid = true;
            for (int j = 0; j < 4; j++) {
                if (set.contains(shiftAndGetNum(runArr))) {
                    isValid = false;
                }
            }

            var num = getNum(runArr);

            if (num == start) {
                bw.write(i + "\n");
                break;
            }

            set.add(num);

            updateArr(runArr);

            if (!isValid) {
                i--;
            }
        }

        bw.flush();
    }

    private static void updateArr(int[] arr) {
        arr[4]++;
        for (int i = 4; i >= 1; i--) {
            if (arr[i] == 10) {
                arr[i - 1]++;
                arr[i] = 1;
            }
        }
    }

    private static int getNum(int[] arr) {
        int num = 0;
        for (int i = 1; i <= 4; i++) {
            num *= 10;
            num += arr[i];
        }

        return num;
    }

    private static int shiftAndGetNum(int[] arr) {
        int temp = arr[1];
        for (int i = 1; i < 4; i++) {
            arr[i] = arr[i + 1];
        }
        arr[4] = temp;

        return getNum(arr);
    }
}
