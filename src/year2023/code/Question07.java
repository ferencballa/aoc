package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Question07 {
    public static void main(String[] args) throws IOException {
        Q07Part1.run();
        Q07Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 7);
    }
}

class Q07Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question07.getInput();
        naiveApproach(input);
        betterApproach(input);
    }

    private static void naiveApproach(String[] input) {
        PokerHand[] pokerHands = new PokerHand[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            pokerHands[i] = new PokerHand(parts[0], Integer.parseInt(parts[1]));
        }
        Arrays.sort(pokerHands);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            count += pokerHands[i].getBid() * (i + 1);
        }
        System.out.println(count);
    }

    private static void betterApproach(String[] input) {
        PokerHandWithHashMap[] pokerHands = new PokerHandWithHashMap[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            pokerHands[i] = new PokerHandWithHashMap(parts[0], Integer.parseInt(parts[1]), false);
        }
        Arrays.sort(pokerHands);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            count += pokerHands[i].getBid() * (i + 1);
        }
        System.out.println(count);
    }
}

class PokerHand implements Comparable<PokerHand> {
    private final int[] values;
    private final int bid;

    /*
    High card = 0
    1 Pair = 1
    2 Pair = 2
    3 of a Kind = 3
    Full House = 4
    4 of a Kind = 5
    5 of a Kind = 6
     */
    private final int handType;

    public PokerHand(String s, int b){
        values = mapStringToPokerValues(s);
        bid = b;
        int[] tempValues = values.clone();
        Arrays.sort(tempValues);
        if (tempValues[0] == tempValues[4]) {
            handType = 6;
        } else if (tempValues[0] == tempValues[3] || tempValues[1] == tempValues[4]) {
            handType = 5;
        } else if ((tempValues[0] == tempValues[2] && tempValues[3] == tempValues[4]) || (tempValues[0] == tempValues[1] && tempValues[2] == tempValues[4])) {
            handType = 4;
        } else if (tempValues[0] == tempValues[2] || tempValues[1] == tempValues[3] || tempValues[2] == tempValues[4]) {
            handType = 3;
        } else if ((tempValues[0] == tempValues[1] && (tempValues[2] == tempValues[3] || tempValues[3] == tempValues[4])) || (tempValues[1] == tempValues[2] && tempValues[3] == tempValues[4])) {
            handType = 2;
        } else if (tempValues[0] == tempValues[1] || tempValues[1] == tempValues[2] || tempValues[2] == tempValues[3] || tempValues[3] == tempValues[4]) {
            handType = 1;
        } else {
            handType = 0;
        }
    }

    private int[] mapStringToPokerValues(String s) {
        int[] vals = new int[5];
        for (int i = 0; i < 5; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                vals[i] = c - 48;
            } else {
                if (c == 'T') {
                    vals[i] = 10;
                } else if (c == 'J') {
                    vals[i] = 11;
                } else if (c == 'Q') {
                    vals[i] = 12;
                } else if (c == 'K') {
                    vals[i] = 13;
                } else {
                    vals[i] = 14;
                }
            }
        }
        return vals;
    }

    public int getBid() {
        return bid;
    }

    @Override public int compareTo(PokerHand p) {
        if (this.handType > p.handType) {
            return 1;
        } else if (this.handType < p.handType) {
            return -1;
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.values[i] > p.values[i]) {
                    return 1;
                } else if (this.values[i] < p.values[i]) {
                    return -1;
                }
            }
            System.out.println("Cards equal, something went wrong");
            return 0;
        }
    }
}

class Q07Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question07.getInput();
        naiveApproach(input);
        betterApproach(input);
    }

    private static void naiveApproach(String[] input) {
        JokerPokerHand[] pokerHands = new JokerPokerHand[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            pokerHands[i] = new JokerPokerHand(parts[0], Integer.parseInt(parts[1]));
        }
        Arrays.sort(pokerHands);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            count += pokerHands[i].getBid() * (i + 1);
        }
        System.out.println(count);
    }

    private static void betterApproach(String[] input) {
        PokerHandWithHashMap[] pokerHands = new PokerHandWithHashMap[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            pokerHands[i] = new PokerHandWithHashMap(parts[0], Integer.parseInt(parts[1]), true);
        }
        Arrays.sort(pokerHands);
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            count += pokerHands[i].getBid() * (i + 1);
        }
        System.out.println(count);
    }
}

class JokerPokerHand implements Comparable<JokerPokerHand> {
    private final int[] values;
    private final int bid;

    /*
    High card = 0
    1 Pair = 1
    2 Pair = 2
    3 of a Kind = 3
    Full House = 4
    4 of a Kind = 5
    5 of a Kind = 6
     */
    private final int handType;

    public JokerPokerHand(String s, int b){
        values = mapStringToJokerPokerValues(s);
        bid = b;
        int[] tempValues = values.clone();
        Arrays.sort(tempValues);
        if (tempValues[0] == tempValues[4] ||
                (tempValues[1] == tempValues[4] && tempValues[0] == 1) ||
                (tempValues[2] == tempValues[4] && tempValues[1] == 1) ||
                (tempValues[3] == tempValues[4] && tempValues[2] == 1) ||
                tempValues[3] == 1) {
            handType = 6;
        } else if (tempValues[0] == tempValues[3] || tempValues[1] == tempValues[4] ||
                ((tempValues[1] == tempValues[3] || tempValues[2] == tempValues[4]) && tempValues[0] == 1) ||
                ((tempValues[2] == tempValues[3] || tempValues[3] == tempValues[4]) && tempValues[1] == 1) ||
                tempValues[2] == 1) {
            handType = 5;
        } else if ((tempValues[0] == tempValues[2] && tempValues[3] == tempValues[4]) || (tempValues[0] == tempValues[1] && tempValues[2] == tempValues[4]) ||
                (tempValues[1] == tempValues[2] && tempValues[3] == tempValues[4] && tempValues[0] == 1)) {
            handType = 4;
        } else if (tempValues[0] == tempValues[2] || tempValues[1] == tempValues[3] || tempValues[2] == tempValues[4] ||
                ((tempValues[1] == tempValues[2] || tempValues[2] == tempValues[3] || tempValues[3] == tempValues[4]) && tempValues[0] == 1) ||
                tempValues[1] == 1) {
            handType = 3;
        } else if ((tempValues[0] == tempValues[1] && (tempValues[2] == tempValues[3] || tempValues[3] == tempValues[4])) || (tempValues[1] == tempValues[2] && tempValues[3] == tempValues[4])) {
            handType = 2;
        } else if (tempValues[0] == tempValues[1] || tempValues[1] == tempValues[2] || tempValues[2] == tempValues[3] || tempValues[3] == tempValues[4] || tempValues[0] == 1) {
            handType = 1;
        } else {
            handType = 0;
        }
    }

    public int getBid() {
        return bid;
    }

    private int[] mapStringToJokerPokerValues(String s) {
        int[] vals = new int[5];
        for (int i = 0; i < 5; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                vals[i] = c - 48;
            } else {
                if (c == 'T') {
                    vals[i] = 10;
                } else if (c == 'J') {
                    vals[i] = 1;
                } else if (c == 'Q') {
                    vals[i] = 12;
                } else if (c == 'K') {
                    vals[i] = 13;
                } else {
                    vals[i] = 14;
                }
            }
        }
        return vals;
    }

    @Override public int compareTo(JokerPokerHand p) {
        if (this.handType > p.handType) {
            return 1;
        } else if (this.handType < p.handType) {
            return -1;
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.values[i] > p.values[i]) {
                    return 1;
                } else if (this.values[i] < p.values[i]) {
                    return -1;
                }
            }
            System.out.println("Cards equal, something went wrong");
            return 0;
        }
    }
}


class PokerHandWithHashMap implements Comparable<PokerHandWithHashMap> {
    private final int[] values;
    private final int bid;

    /*
    High card = 0
    1 Pair = 1
    2 Pair = 2
    3 of a Kind = 3
    Full House = 4
    4 of a Kind = 5
    5 of a Kind = 6
     */
    private int handType;

    public PokerHandWithHashMap(String s, int b, boolean usingJokers) {
        values = mapStringToPokerValues(s, usingJokers);
        bid = b;
        HashMap<Character, Integer> cards = new HashMap<>();
        for(char c : s.toCharArray()) {
            if (cards.containsKey(c)) {
                cards.put(c, cards.get(c) + 1);
            } else {
                cards.put(c, 1);
            }
        }
        if (usingJokers) {
            if (cards.containsKey('J') && cards.get('J') == 5) {
                cards.remove('J');
                cards.put('A', 5);
            } else if (cards.containsKey('J')){
                int highestNumber = 0;
                char highestChar = 0;
                for (Character c : cards.keySet()) {
                    int val = cards.get(c);
                    if (c != 'J' && val > highestNumber) {
                        highestNumber = val;
                        highestChar = c;
                    }
                }
                cards.put(highestChar, highestNumber + cards.get('J'));
                cards.remove('J');
            }
        }
        handType = 0;
        boolean hasTwo = false;
        boolean hasThree = false;
        for (Character c : cards.keySet()) {
            if (cards.get(c) == 5) {
                handType = 6;break;
            } else if (cards.get(c) == 4) {
                handType = 5;break;
            } else if (cards.get(c) == 3) {
                if (hasTwo) {
                    handType = 4;break;
                } else {
                    hasThree = true;
                }
            } else if (cards.get(c) == 2) {
                if (hasThree) {
                    handType = 4;break;
                } else if (hasTwo) {
                    handType = 2;break;
                } else {
                    hasTwo = true;
                }
            }
        }
        if (handType == 0) {
            if (hasThree) {
                handType = 3;
            } else if (hasTwo) {
                handType = 1;
            }
        }
    }

    private int[] mapStringToPokerValues(String s, boolean usingJokers) {
        int[] vals = new int[5];
        for (int i = 0; i < 5; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                vals[i] = c - 48;
            } else {
                if (c == 'T') {
                    vals[i] = 10;
                } else if (c == 'J') {
                    if (usingJokers) {
                        vals[i] = 1;
                    } else {
                        vals[i] = 11;
                    }
                } else if (c == 'Q') {
                    vals[i] = 12;
                } else if (c == 'K') {
                    vals[i] = 13;
                } else {
                    vals[i] = 14;
                }
            }
        }
        return vals;
    }

    public int getBid() {
        return bid;
    }

    @Override public int compareTo(PokerHandWithHashMap p) {
        if (this.handType > p.handType) {
            return 1;
        } else if (this.handType < p.handType) {
            return -1;
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.values[i] > p.values[i]) {
                    return 1;
                } else if (this.values[i] < p.values[i]) {
                    return -1;
                }
            }
            System.out.println("Cards equal, something went wrong");
            return 0;
        }
    }
}