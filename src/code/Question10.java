package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Question10 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/input/Question10.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int errorScore = 0;
        for (String line : input) {
            String[] characters = line.split("");
            ArrayList<String> openChars = new ArrayList<>();
            boolean illegalFound = false;
            for (String character : characters) {
                if (!illegalFound) {
                    switch (character) {
                        case "(":
                            openChars.add("(");
                            break;
                        case "[":
                            openChars.add("[");
                            break;
                        case "{":
                            openChars.add("{");
                            break;
                        case "<":
                            openChars.add("<");
                            break;
                        case ")":
                            if (openChars.get(openChars.size()-1).equals("(")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                errorScore += 3;
                                illegalFound = true;
                            }
                            break;
                        case "]":
                            if (openChars.get(openChars.size()-1).equals("[")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                errorScore += 57;
                                illegalFound = true;
                            }
                            break;
                        case "}":
                            if (openChars.get(openChars.size()-1).equals("{")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                errorScore += 1197;
                                illegalFound = true;
                            }
                            break;
                        case ">":
                            if (openChars.get(openChars.size()-1).equals("<")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                errorScore += 25137;
                                illegalFound = true;
                            }
                            break;
                    }
                }
            }
        }
        System.out.println(errorScore);
    }

    private static void part2(String[] input) {
        ArrayList<Long> completeScores = new ArrayList<>();
        for (String line : input) {
            String[] characters = line.split("");
            ArrayList<String> openChars = new ArrayList<>();
            boolean illegalFound = false;
            for (String character : characters) {
                if (!illegalFound) {
                    switch (character) {
                        case "(":
                            openChars.add("(");
                            break;
                        case "[":
                            openChars.add("[");
                            break;
                        case "{":
                            openChars.add("{");
                            break;
                        case "<":
                            openChars.add("<");
                            break;
                        case ")":
                            if (openChars.get(openChars.size()-1).equals("(")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                illegalFound = true;
                            }
                            break;
                        case "]":
                            if (openChars.get(openChars.size()-1).equals("[")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                illegalFound = true;
                            }
                            break;
                        case "}":
                            if (openChars.get(openChars.size()-1).equals("{")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                illegalFound = true;
                            }
                            break;
                        case ">":
                            if (openChars.get(openChars.size()-1).equals("<")) {
                                openChars.remove(openChars.size()-1);
                            } else {
                                illegalFound = true;
                            }
                            break;
                    }
                }
            }
            if (!illegalFound && openChars.size() > 0) {
                Collections.reverse(openChars);
                long score = 0;
                for (String toClose : openChars) {
                    score *= 5;
                    switch (toClose) {
                        case "(":
                            score += 1;
                            break;
                        case "[":
                            score += 2;
                            break;
                        case "{":
                            score += 3;
                            break;
                        case "<":
                            score += 4;
                            break;
                    }
                }
                completeScores.add(score);
            }
        }
        Collections.sort(completeScores);
        System.out.println(completeScores.get((completeScores.size() - 1) / 2));
    }
}
