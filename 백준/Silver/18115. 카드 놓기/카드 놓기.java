

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var result = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            var index = n - i;

            if (inputs[index].equals("1")) {
                result.add(0, i);
            } else if (inputs[index].equals("2")) {
                result.add(1, i);
            } else {
                result.add(i);
            }
        }

        for (var num : result) {
            bw.write(num + " ");
        }
        bw.flush();
    }
}

