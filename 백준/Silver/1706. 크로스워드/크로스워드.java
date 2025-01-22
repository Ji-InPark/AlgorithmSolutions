

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int h = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]);

        var arr = new ArrayList<String>();
        for (int i = 0; i < h; i++) {
            arr.add(br.readLine());
        }

        for (int i = 0; i < w; i++) {
            var sb = new StringBuilder();
            for (int j = 0; j < h; j++) {
                sb.append(arr.get(j).charAt(i));
            }
            arr.add(sb.toString());
        }

        var result = "zzzzzzzzzzzzzzzzzzzzzz";
        for (var str : arr) {
            for (var s : str.split("#")) {
                if (s.length() <= 1) {
                    continue;
                }

                if (result.compareTo(s) > 0) {
                    result = s;
                }
            }
        }

        bw.write(result);
        bw.flush();
    }
}
