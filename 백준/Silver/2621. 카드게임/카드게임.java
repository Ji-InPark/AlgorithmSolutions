

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var cards = new Card[5];

        for (int i = 0; i < 5; i++) {
            var inputs = br.readLine().split(" ");

            cards[i] = new Card(inputs[0].charAt(0), Integer.parseInt(inputs[1]));
        }

        Arrays.sort(cards);

        var result = 0;
        if (isStraightFlush(cards)) {
            result = calculateStraightFlush(cards);
        } else if (isFourCard(cards)) {
            result = calculateFourCard(cards);
        } else if (isFullHouse(cards)) {
            result = calculateFullHouse(cards);
        } else if (isFlush(cards)) {
            result = calculateFlush(cards);
        } else if (isStraight(cards)) {
            result = calculateStraight(cards);
        } else if (isTriple(cards)) {
            result = calculateTriple(cards);
        } else if (isTwoPair(cards)) {
            result = calculateTwoPair(cards);
        } else if (isOnePair(cards)) {
            result = calculateOnePair(cards);
        } else if (isHighCard(cards)) {
            result = calculateHighCard(cards);
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isStraightFlush(Card[] cards) {
        for (int i = 1; i < 5; i++) {
            var isSameColor = cards[i - 1].color == cards[i].color;
            var isNextNumber = cards[i - 1].number + 1 == cards[i].number;

            if (!isSameColor || !isNextNumber) {
                return false;
            }
        }

        return true;
    }

    private static int calculateStraightFlush(Card[] cards) {
        return 900 + cards[4].number;
    }

    private static boolean isFourCard(Card[] cards) {
        var counts = new int[10];

        for (var card : cards) {
            if (++counts[card.number] == 4) {
                return true;
            }
        }

        return false;
    }

    private static int calculateFourCard(Card[] cards) {
        var counts = new int[10];
        int number = -1;

        for (var card : cards) {
            if (++counts[card.number] == 4) {
                number = card.number;
                break;
            }
        }
        return 800 + number;
    }

    private static boolean isFullHouse(Card[] cards) {
        var map = new HashMap<Integer, Integer>();

        for (var card : cards) {
            map.put(card.number, map.getOrDefault(card.number, 0) + 1);
        }

        for (var entry : map.entrySet()) {
            var value = entry.getValue();

            if (value < 2 || value > 3) {
                return false;
            }
        }

        return true;
    }

    private static int calculateFullHouse(Card[] cards) {
        var result = 700;
        var map = new HashMap<Integer, Integer>();

        for (var card : cards) {
            map.put(card.number, map.getOrDefault(card.number, 0) + 1);
        }

        for (var entry : map.entrySet()) {
            var value = entry.getValue();

            if (value == 3) {
                result += entry.getKey() * 10;
            } else if (value == 2) {
                result += entry.getKey();
            }
        }

        return result;
    }

    private static boolean isFlush(Card[] cards) {
        var color = cards[0].color;
        for (var card : cards) {
            if (card.color != color) {
                return false;
            }
        }

        return true;
    }

    private static int calculateFlush(Card[] cards) {
        return 600 + cards[4].number;
    }

    private static boolean isStraight(Card[] cards) {
        for (int i = 1; i < 5; i++) {
            var isNextNumber = cards[i - 1].number + 1 == cards[i].number;

            if (!isNextNumber) {
                return false;
            }
        }

        return true;
    }

    private static int calculateStraight(Card[] cards) {
        return 500 + cards[4].number;
    }

    private static boolean isTriple(Card[] cards) {
        var counts = new int[10];

        for (var card : cards) {
            if (++counts[card.number] == 3) {
                return true;
            }
        }

        return false;
    }

    private static int calculateTriple(Card[] cards) {
        var counts = new int[10];
        int number = -1;

        for (var card : cards) {
            if (++counts[card.number] == 3) {
                number = card.number;
                break;
            }
        }
        return 400 + number;
    }

    private static boolean isTwoPair(Card[] cards) {
        var map = new HashMap<Integer, Integer>();

        for (var card : cards) {
            map.put(card.number, map.getOrDefault(card.number, 0) + 1);
        }

        return map.size() == 3;
    }

    private static int calculateTwoPair(Card[] cards) {
        var map = new HashMap<Integer, Integer>();
        var keys = new ArrayList<Integer>();

        for (var card : cards) {
            map.put(card.number, map.getOrDefault(card.number, 0) + 1);
        }

        for (var entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                continue;
            }

            keys.add(entry.getKey());
        }

        Collections.sort(keys);

        int result = 300;
        result += keys.get(0);
        result += keys.get(1) * 10;

        return result;
    }

    private static boolean isOnePair(Card[] cards) {
        var prev = -1;
        for (var card : cards) {
            if (prev == card.number) {
                return true;
            }

            prev = card.number;
        }

        return false;
    }

    private static int calculateOnePair(Card[] cards) {
        int prev = -1, result = 200;
        for (var card : cards) {
            if (prev == card.number) {
                result += card.number;
                break;
            }

            prev = card.number;
        }

        return result;
    }

    private static boolean isHighCard(Card[] cards) {
        return true;
    }

    private static int calculateHighCard(Card[] cards) {
        return 100 + cards[4].number;
    }


    static class Card implements Comparable<Card> {

        char color;
        int number;

        public Card(char color, int number) {
            this.color = color;
            this.number = number;
        }

        @Override
        public int compareTo(Card o) {
            return this.number - o.number;
        }
    }
}
