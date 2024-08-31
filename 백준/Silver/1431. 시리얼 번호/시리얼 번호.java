import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() != s2.length()) {
                    return s1.length() - s2.length();
                } else if (s1.matches(".*\\d.*") || s2.matches(".*\\d.*")) {
                    int n1 = 0, n2 = 0;

                    for (var c : s1.toCharArray()) {
                        if ('0' <= c && c <= '9') {
                            n1 += c - '0';
                        }
                    }

                    for (var c : s2.toCharArray()) {
                        if ('0' <= c && c <= '9') {
                            n2 += c - '0';
                        }
                    }

                    if (n1 == n2) {
                        return s1.compareTo(s2);
                    }

                    return n1 - n2;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });

        for (var s : arr) {
            bw.write(s + "\n");
        }
        bw.flush();
    }
}
