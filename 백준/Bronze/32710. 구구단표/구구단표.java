

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new boolean[101];

        for (int i = 2; i < 10; i++) {
            arr[i] = true;
            for (int j = 1; j <= 9; j++) {
                arr[j] = true;
                arr[i * j] = true;
            }
        }

        bw.write(arr[n] ? "1" : "0");
        bw.flush();
    }

}
