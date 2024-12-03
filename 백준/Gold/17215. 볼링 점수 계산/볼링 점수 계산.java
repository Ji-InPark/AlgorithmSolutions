import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        var frames = new int[10];

        var bonusMap = new HashMap<Integer, ArrayList<Integer>>();

        int index = 0;

        for (int i = 0; i < 9; i++) {
            var c = input.charAt(index);
            int sum = 0;

            if (c == 'S') {
                frames[i] = 10;

                calculateBonusFrame(bonusMap, index, frames, 10);

                for (int j = 1; j <= 2; j++) {
                    int key = index + j;

                    if (!bonusMap.containsKey(key)) {
                        bonusMap.put(key, new ArrayList<>());
                    }

                    bonusMap.get(key).add(i);
                }

                index++;

                continue;
            }

            if (c != '-') {
                var score = c - '0';
                sum += score;

                calculateBonusFrame(bonusMap, index, frames, score);
            }

            index++;

            c = input.charAt(index);

            if (c == 'P') {
                var score = 10 - sum;
                sum = 10;

                calculateBonusFrame(bonusMap, index, frames, score);

                var key = index + 1;
                if (!bonusMap.containsKey(key)) {
                    bonusMap.put(key, new ArrayList<>());
                }

                bonusMap.get(key).add(i);
            } else if (c != '-') {
                var score = c - '0';
                sum += score;

                calculateBonusFrame(bonusMap, index, frames, score);
            }

            frames[i] = sum;

            index++;
        }

        int prev = 0;
        while (index < input.length()) {
            var c = input.charAt(index);
            int score = 0;

            if (c == 'S') {
                score = 10;
            } else if (c == 'P') {
                score = 10 - prev;
            } else if (c != '-') {
                score = c - '0';
            }

            calculateBonusFrame(bonusMap, index, frames, score);

            frames[9] += score;
            prev = score;

            index++;
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += frames[i];
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static void calculateBonusFrame(HashMap<Integer, ArrayList<Integer>> bonusMap, int index,
            int[] frames, int score) {
        var bonusFrames = bonusMap.getOrDefault(index, new ArrayList<>());

        for (var bonusFrame : bonusFrames) {
            frames[bonusFrame] += score;
        }
    }
}

