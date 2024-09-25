import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var nums = new int[n];
        var indexes = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(inputs[i]);
        }
        var result = new ArrayList<Integer>();
        result.add(nums[0]);
        indexes[0] = 0;

        for (int i = 1; i < n; i++) {
            var num = nums[i];
            var last = result.get(result.size() - 1);

            if (num > last) {
                indexes[i] = result.size();
                result.add(num);
            } else {
                var index = findIndex(result, num);
                result.set(index, num);
                indexes[i] = index;
            }
        }

        bw.write(result.size() + "\n");

        var index = result.size() - 1;
        result.clear();
        for (int i = n - 1; i >= 0; i--) {
            if (indexes[i] == index) {
                result.add(nums[i]);
                index--;
            }
        }

        Collections.reverse(result);
        for (var num : result) {
            bw.write(num + " ");
        }

        bw.flush();
    }

    public static int findIndex(ArrayList<Integer> list, int target) {
        int left = 0, right = list.size() - 1;

        while (left < right) {
            var mid = left + (right - left) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
