import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var arr = br.readLine().toCharArray();
        var dp = new boolean[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            checkPalindrome(i, i, arr, dp);
            checkPalindrome(i - 1, i, arr, dp);
        }

        var q = new LinkedList<Integer>();
        var set = new HashSet<Integer>();
        var result = 0;

        q.add(0);
        set.add(0);

        Loop:
        while (!q.isEmpty()) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var num = q.poll();

                if (num == arr.length) {
                    break Loop;
                }

                for (int index = num; index < arr.length; index++) {
                    if (set.contains(index + 1) || !dp[num][index]) {
                        continue;
                    }

                    q.add(index + 1);
                    set.add(index + 1);
                }
            }

            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static void checkPalindrome(int i, int j, char[] arr, boolean[][] dp) {
        if (i < 0 || j == dp.length || arr[i] != arr[j]) {
            return;
        }

        dp[i][j] = true;
        
        checkPalindrome(i - 1, j + 1, arr, dp);
    }


}