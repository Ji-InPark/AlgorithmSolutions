

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

        var n = Integer.parseInt(br.readLine());
        var arr = new String[n + 2];
        arr[0] = arr[n + 1] = ".";
        var set = new HashSet<String>();
        var index = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = br.readLine();
            if (arr[i].equals("?")) {
                index = i;
                continue;
            }

            set.add(arr[i]);
        }

        var prev = arr[index - 1];
        var next = arr[index + 1];
        var regex = prev.charAt(prev.length() - 1) + "(.*?)" + next.charAt(0);

        var m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            var input = br.readLine();

            if (!set.contains(input) && input.matches(regex)) {
                bw.write(input);
                break;
            }
        }
        bw.flush();
    }
}

