package year2021.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Question10 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 10);
        char[][] inputChars = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            inputChars[i] = input[i].toCharArray();
        }
        System.out.println("Part 1:");
        part1(inputChars);
        System.out.println("Part 2:");
        part2(inputChars);
    }

    private static void part1(char[][] input) {
        int errorScore = 0;
        for (char[] characters : input) {
            ArrayList<Character> openChars = new ArrayList<>();
            errorScore += checkLine(characters, openChars);
        }
        System.out.println(errorScore);
    }

    private static void part2(char[][] input) {
        ArrayList<Long> completeScores = new ArrayList<>();
        Map<Character, Integer> errorValues = Map.of('(', 1, '[', 2, '{', 3, '<', 4);
        for (char[] characters : input) {
            ArrayList<Character> openChars = new ArrayList<>();
            boolean illegalFound = checkLine(characters, openChars) != 0;
            if (!illegalFound && openChars.size() > 0) {
                Collections.reverse(openChars);
                long score = 0;
                for (char toClose : openChars) {
                    score *= 5;
                    score += errorValues.get(toClose);
                }
                completeScores.add(score);
            }
        }
        Collections.sort(completeScores);
        System.out.println(completeScores.get((completeScores.size() - 1) / 2));
    }

    private static int checkLine(char[] characters, ArrayList<Character> openChars) {
        Map<Character, Integer> errorValues = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);
        for (char character : characters) {
            int spaceToOpenChar = 2;
            switch (character) {
                case '(':
                case '[':
                case '{':
                case '<':
                    openChars.add(character);
                    break;
                case ')':
                    spaceToOpenChar = 1;
                case ']':
                case '}':
                case '>':
                    if (openChars.get(openChars.size() - 1).equals((char) (character - spaceToOpenChar))) {
                        openChars.remove(openChars.size() - 1);
                    } else {
                        return errorValues.get(character);
                    }
            }
        }
        return 0;
    }
}
