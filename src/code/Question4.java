package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Question4 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/input/Question4.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[] calledStr = input[0].split(",");
        int[] called = Helper.StringArrayToInt(calledStr);
        ArrayList<int[][]> cards = new ArrayList<>();
        for (int i = 2; i < input.length; i += 6) {
            int[][] card = new int[5][];
            for (int j = 0; j < 5; j++) {
                String[] line = input[i + j].trim().split(" +");
                card[j] = Helper.StringArrayToInt(line);
            }
            cards.add(card);
        }
        boolean bingo = false;
        int bingoValue = 0;
        int bingoIndex = 0;
        while (!bingo) {
            int numCalled = called[bingoIndex];
            for (int[][] card : cards) {
                if (!bingo) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (!bingo) {
                                if (card[i][j] == numCalled) {
                                    card[i][j] = -1;
                                    if ((card[0][j] == -1
                                            && card[1][j] == -1
                                            && card[2][j] == -1
                                            && card[3][j] == -1
                                            && card[4][j] == -1) ||
                                            (card[i][0] == -1
                                                    && card[i][1] == -1
                                                    && card[i][2] == -1
                                                    && card[i][3] == -1
                                                    && card[i][4] == -1)) {
                                        bingo = true;
                                        for (int k = 0; k < 5; k++) {
                                            for (int l = 0; l < 5; l++) {
                                                if (card[k][l] != -1) {
                                                    bingoValue += card[k][l];
                                                }
                                            }
                                        }
                                        bingoValue *= numCalled;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            bingoIndex++;
        }
        System.out.println(bingoValue);
    }

    private static void part2(String[] input) {
        boolean[] bingoedCards = new boolean[100];
        int bingoedCardsCount = 0;
        String[] calledStr = input[0].split(",");
        int[] called = Helper.StringArrayToInt(calledStr);
        ArrayList<int[][]> cards = new ArrayList<>();
        for (int i = 2; i < input.length; i += 6) {
            int[][] card = new int[5][];
            for (int j = 0; j < 5; j++) {
                String[] line = input[i + j].trim().split(" +");
                card[j] = Helper.StringArrayToInt(line);
            }
            cards.add(card);
        }
        int bingoValue = 0;
        int bingoIndex = 0;
        int numCalled = -1;
        int losingCard = -1;
        while (bingoedCardsCount < 100) {
            numCalled = called[bingoIndex];
            for (int bingoCardIndex = 0; bingoCardIndex < 100; bingoCardIndex++) {
                int[][] card = cards.get(bingoCardIndex);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (card[i][j] == numCalled) {
                            card[i][j] = -1;
                            if ((card[0][j] == -1
                                    && card[1][j] == -1
                                    && card[2][j] == -1
                                    && card[3][j] == -1
                                    && card[4][j] == -1) ||
                                    (card[i][0] == -1
                                            && card[i][1] == -1
                                            && card[i][2] == -1
                                            && card[i][3] == -1
                                            && card[i][4] == -1)) {
                                if (!bingoedCards[bingoCardIndex]) {
                                    bingoedCards[bingoCardIndex] = true;
                                    bingoedCardsCount++;
                                    if (bingoedCardsCount == 100) {
                                        losingCard = bingoCardIndex;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            bingoIndex++;
        }
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (cards.get(losingCard)[k][l] != -1) {
                    bingoValue += cards.get(losingCard)[k][l];
                }
            }
        }
        bingoValue *= numCalled;

        System.out.println(bingoValue);
    }
}
