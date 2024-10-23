import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String pivot = br.readLine();

        StringBuilder sb = new StringBuilder();
        for(char c : input.toCharArray()) {
            sb.append(c);
            if(sb.length() >= pivot.length() && sb.substring(sb.length() - pivot.length()).equals(pivot)) {
                sb.delete(sb.length() - pivot.length(), sb.length());
            }
        }

        bw.write(sb.toString().equals("") ? "FRULA" : sb.toString());
        bw.flush();
    }
}
