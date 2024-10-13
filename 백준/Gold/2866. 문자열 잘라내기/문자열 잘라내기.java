import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int r = Integer.parseInt(inputs[0]), c = Integer.parseInt(inputs[1]);

        var arr = new String[r];
        var sbs = new StringBuilder[c];
        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine();
        }

        for (int i = 0; i < c; i++) {
            sbs[i] = new StringBuilder();
        }

        var set = new HashSet<String>();

        for (int i = r - 1; i >= 0; i--) {
            set.clear();

            for (int j = 0; j < c; j++) {
                sbs[j].append(arr[i].charAt(j));
                set.add(sbs[j].toString());
            }

            if (set.size() == c) {
                bw.write(i + "\n");
                break;
            }
        }

        bw.flush();
    }
}
