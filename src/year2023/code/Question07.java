package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.Arrays;

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
