import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        var result = new ArrayList<String>();

        for (int i = 1; i < input.length() - 1; i++) {
            for (int j = i + 1; j < input.length(); j++) {
                var substr1 = input.substring(0, i);
                var reversedStr1 = new StringBuilder(substr1).reverse().toString();

                var substr2 = input.substring(i, j);
                var reversedStr2 = new StringBuilder(substr2).reverse().toString();

                var substr3 = input.substring(j);
                var reversedStr3 = new StringBuilder(substr3).reverse().toString();

                var resultStr = reversedStr1 + reversedStr2 + reversedStr3;
                result.add(resultStr);
            }
        }

        Collections.sort(result);

        bw.write(result.get(0) + "\n");
        bw.flush();
    }
}
