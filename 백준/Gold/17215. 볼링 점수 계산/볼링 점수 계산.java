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

        for (int currentFrame = 0; currentFrame < 9; currentFrame++) {
            var pins = input.charAt(index);
            int sum = 0;

            if (isStrike(pins)) {
                frames[currentFrame] = 10;

                calculateBonusFrame(bonusMap, index, frames, 10);
                addBonusFrame(bonusMap, index, currentFrame, 2);

                index++;

                continue;
            } else if (isNotZero(pins)) {
                var score = pins - '0';
                sum += score;

                calculateBonusFrame(bonusMap, index, frames, score);
            }

            index++;

            pins = input.charAt(index);

            if (isSpare(pins)) {
                var score = 10 - sum;
                sum = 10;

                calculateBonusFrame(bonusMap, index, frames, score);
                addBonusFrame(bonusMap, index, currentFrame, 1);
            } else if (isNotZero(pins)) {
                var score = pins - '0';
                sum += score;

                calculateBonusFrame(bonusMap, index, frames, score);
            }

            frames[currentFrame] = sum;

            index++;
        }

        int prevScore = 0;
        while (index < input.length()) {
            var pins = input.charAt(index);
            int score = 0;

            if (isStrike(pins)) {
                score = 10;
            } else if (isSpare(pins)) {
                score = 10 - prevScore;
            } else if (isNotZero(pins)) {
                score = pins - '0';
            }

            calculateBonusFrame(bonusMap, index, frames, score);

            frames[9] += score;
            prevScore = score;

            index++;
        }

        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += frames[i];
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static void addBonusFrame(HashMap<Integer, ArrayList<Integer>> bonusMap, int index, int frame, int count) {
        for (int i = 1; i <= count; i++) {
            int key = index + i;

            if (!bonusMap.containsKey(key)) {
                bonusMap.put(key, new ArrayList<>());
            }

            bonusMap.get(key).add(frame);
        }
    }

    private static void calculateBonusFrame(HashMap<Integer, ArrayList<Integer>> bonusMap, int index,
            int[] frames, int score) {
        var bonusFrames = bonusMap.getOrDefault(index, new ArrayList<>());

        for (var bonusFrame : bonusFrames) {
            frames[bonusFrame] += score;
        }
    }

    private static boolean isStrike(char c) {
        return c == 'S';
    }

    private static boolean isSpare(char c) {
        return c == 'P';
    }

    private static boolean isNotZero(char c) {
        return c != '-';
    }
}

