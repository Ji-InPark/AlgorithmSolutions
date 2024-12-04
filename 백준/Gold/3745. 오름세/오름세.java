import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            var nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }

            var lis = new ArrayList<Integer>();
            lis.add(nums[0]);

            for (int i = 1; i < n; i++) {
                if (lis.get(lis.size() - 1) < nums[i]) {
                    lis.add(nums[i]);
                } else {
                    var index = getIndex(lis, nums[i]);

                    lis.set(index, nums[i]);
                }
            }

            bw.write(lis.size() + "\n");
        }
        bw.flush();
    }

    private static int getIndex(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size() - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (list.get(m) < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return r;
    }
}

