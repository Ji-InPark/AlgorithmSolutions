

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var gMap = new HashMap<String, ArrayList<String>>();
        var mMap = new HashMap<String, String>();

        for (int i = 0; i < n; i++) {
            var groupName = br.readLine();
            var count = Integer.parseInt(br.readLine());

            gMap.put(groupName, new ArrayList<>());
            for (int j = 0; j < count; j++) {
                var name = br.readLine();

                gMap.get(groupName).add(name);
                mMap.put(name, groupName);
            }
        }

        for (int i = 0; i < m; i++) {
            var name = br.readLine();
            var type = Integer.parseInt(br.readLine());

            if (type == 1) {
                bw.write(mMap.get(name) + "\n");
            } else {
                var members = gMap.get(name);

                Collections.sort(members);

                for (var member : members) {
                    bw.write(member + "\n");
                }
            }
        }

        bw.flush();

    }

}
