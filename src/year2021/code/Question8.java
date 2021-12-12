package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question8 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question8.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int count = 0;
        for (String inp : input) {
            String[] parts = inp.split(" \\| ");
            String[] numbers = parts[1].split(" ");
            for (String num : numbers) {
                int l = num.length();
                if (l == 2 || l == 4 || l == 3 || l == 7) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void part2(String[] input) {
        int count = 0;
        for (String inp : input) {
            boolean[][] possibleLetters = new boolean[7][7];
            String[] parts = inp.split(" \\| ");
            String[] numbersS = parts[0].split(" ");
            int[][] numbers = new int[10][];
            for (int i = 0; i < 10; i++) {
                String[] splitNumS = numbersS[i].split("");
                numbers[i] = new int[splitNumS.length];
                for (int j = 0; j < splitNumS.length; j++) {
                    switch (splitNumS[j]) {
                        case "a":
                            numbers[i][j] = 0;
                            break;
                        case "b":
                            numbers[i][j] = 1;
                            break;
                        case "c":
                            numbers[i][j] = 2;
                            break;
                        case "d":
                            numbers[i][j] = 3;
                            break;
                        case "e":
                            numbers[i][j] = 4;
                            break;
                        case "f":
                            numbers[i][j] = 5;
                            break;
                        case "g":
                            numbers[i][j] = 6;
                            break;
                    }
                }
            }
            for (int[] num : numbers) {
                int l = num.length;
                if (l == 2) { //1
                    possibleLetters[2][num[0]] = true;
                    possibleLetters[2][num[1]] = true;
                    possibleLetters[5][num[0]] = true;
                    possibleLetters[5][num[1]] = true;
                }
            }
            for (int[] num : numbers) {
                int l = num.length;
                if (l == 3) { //7
                    for (int bar : num) {
                        if (!possibleLetters[2][bar] && !possibleLetters[5][bar]) {
                            possibleLetters[0][bar] = true;
                        }
                    }
                }
            }
            for (int[] num : numbers) {
                int l = num.length;
                if (l == 4) { //4
                    for (int bar : num) {
                        if (!possibleLetters[2][bar] && !possibleLetters[5][bar]) {
                            possibleLetters[1][bar] = true;
                            possibleLetters[3][bar] = true;
                        }
                    }
                }
            }
            boolean nineFound = false;
            for (int[] num : numbers) {
                int l = num.length;
                if (l == 6 && !nineFound) { //9
                    int countSides = 0;
                    boolean[] found = new boolean[6];
                    for (int i = 0; i < 6; i++) {
                        int value = num[i];
                        for (int j = 0; j < 7; j++) {
                            if (!found[i]) {
                                if (possibleLetters[j][value]) {
                                    countSides++;
                                    found[i] = true;
                                }
                            }
                        }
                    }
                    if (countSides == 5) {
                        nineFound = true;
                        for (int bar = 0; bar < 6; bar++) {
                            if (!found[bar]) {
                                possibleLetters[6][num[bar]] = true;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < 7; j++) { //general deduction
                boolean hasPossibility = false;
                for (int i = 0; i < 7; i++) {
                    if (possibleLetters[i][j]) {
                        hasPossibility = true;
                        break;
                    }
                }
                if (!hasPossibility) {
                    possibleLetters[4][j] = true;
                }
            }
            boolean twoFound = false;
            for (int[] num : numbers) {
                int l = num.length;
                if (l == 5 && !twoFound) { //2
                    int countCertains = 0;
                    for (int j = 0; j < 5; j++) {
                        int side = num[j];
                        int options = 0;
                        for (int i = 0; i < 7; i++) {
                            if (possibleLetters[i][side]) {
                                options++;
                            }
                        }
                        if (options == 1) {
                            countCertains++;
                        }
                    }
                    if (countCertains == 3) {
                        twoFound = true;
                        int oneOptionOne = -1;
                        int oneOptionTwo = -1;
                        boolean optionOneForOneFound = false;
                        for (int i = 0; i < 7; i++) {
                            if (possibleLetters[2][i]) {
                                if (!optionOneForOneFound) {
                                    oneOptionOne = i;
                                    optionOneForOneFound = true;
                                } else {
                                    oneOptionTwo = i;
                                }
                            }
                        }
                        boolean oneOptionOneForOneInTwo = false;
                        for (int i = 0; i < 5; i++) {
                            if (num[i] == oneOptionOne) {
                                oneOptionOneForOneInTwo = true;
                                break;
                            }
                        }
                        if (oneOptionOneForOneInTwo) {
                            possibleLetters[2][oneOptionTwo] = false;
                            possibleLetters[5][oneOptionOne] = false;
                        } else {
                            possibleLetters[2][oneOptionOne] = false;
                            possibleLetters[5][oneOptionTwo] = false;
                        }
                        //-------------------------------------------------
                        int fourOptionOne = -1;
                        int fourOptionTwo = -1;
                        boolean optionOneForFourFound = false;
                        for (int i = 0; i < 7; i++) {
                            if (possibleLetters[3][i]) {
                                if (!optionOneForFourFound) {
                                    fourOptionOne = i;
                                    optionOneForFourFound = true;
                                } else {
                                    fourOptionTwo = i;
                                }
                            }
                        }
                        boolean oneOptionOneForFourInTwo = false;
                        for (int i = 0; i < 5; i++) {
                            if (num[i] == fourOptionOne) {
                                oneOptionOneForFourInTwo = true;
                                break;
                            }
                        }
                        if (oneOptionOneForFourInTwo) {
                            possibleLetters[3][fourOptionTwo] = false;
                            possibleLetters[1][fourOptionOne] = false;
                        } else {
                            possibleLetters[3][fourOptionOne] = false;
                            possibleLetters[1][fourOptionTwo] = false;
                        }
                    }
                }
            }
            String[] letterPerPos = new String[7];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (possibleLetters[i][j]) {
                        if (j == 0) {
                            letterPerPos[i] = "a";
                        } else if (j == 1) {
                            letterPerPos[i] = "b";
                        } else if (j == 2) {
                            letterPerPos[i] = "c";
                        } else if (j == 3) {
                            letterPerPos[i] = "d";
                        } else if (j == 4) {
                            letterPerPos[i] = "e";
                        } else if (j == 5) {
                            letterPerPos[i] = "f";
                        } else {
                            letterPerPos[i] = "g";
                        }
                    }
                }
            }
            String[] numbersToBeDecoded = parts[1].split(" ");
            String resultNum = "";
            for (String numDec : numbersToBeDecoded) {
                if (numDec.length() == 6
                        && numDec.contains(letterPerPos[0])
                        && numDec.contains(letterPerPos[1])
                        && numDec.contains(letterPerPos[2])
                        && numDec.contains(letterPerPos[4])
                        && numDec.contains(letterPerPos[5])
                        && numDec.contains(letterPerPos[6])) {
                    resultNum = resultNum.concat("0");
                }
                else if (numDec.length() == 2) {
                    resultNum = resultNum.concat("1");
                } else if (numDec.length() == 5
                        && numDec.contains(letterPerPos[0])
                        && numDec.contains(letterPerPos[2])
                        && numDec.contains(letterPerPos[3])
                        && numDec.contains(letterPerPos[4])
                        && numDec.contains(letterPerPos[6])) {
                    resultNum = resultNum.concat("2");
                }
                else if (numDec.length() == 5
                        && numDec.contains(letterPerPos[0])
                        && numDec.contains(letterPerPos[2])
                        && numDec.contains(letterPerPos[3])
                        && numDec.contains(letterPerPos[5])
                        && numDec.contains(letterPerPos[6])) {
                    resultNum = resultNum.concat("3");
                }
                else if (numDec.length() == 4) {
                    resultNum = resultNum.concat("4");
                }
                else if (numDec.length() == 5
                        && numDec.contains(letterPerPos[0])
                        && numDec.contains(letterPerPos[1])
                        && numDec.contains(letterPerPos[3])
                        && numDec.contains(letterPerPos[5])
                        && numDec.contains(letterPerPos[6])) {
                    resultNum = resultNum.concat("5");
                }
                else if (numDec.length() == 6
                        && numDec.contains(letterPerPos[0])
                        && numDec.contains(letterPerPos[1])
                        && numDec.contains(letterPerPos[3])
                        && numDec.contains(letterPerPos[4])
                        && numDec.contains(letterPerPos[5])
                        && numDec.contains(letterPerPos[6])) {
                    resultNum = resultNum.concat("6");
                }
                else if (numDec.length() == 3) {
                    resultNum = resultNum.concat("7");
                }
                else if (numDec.length() == 7) {
                    resultNum = resultNum.concat("8");
                }
                else if (numDec.length() == 6
                        && numDec.contains(letterPerPos[0])
                        && numDec.contains(letterPerPos[1])
                        && numDec.contains(letterPerPos[2])
                        && numDec.contains(letterPerPos[3])
                        && numDec.contains(letterPerPos[5])
                        && numDec.contains(letterPerPos[6])) {
                    resultNum = resultNum.concat("9");
                }
            }
            int resultValue = Integer.parseInt(resultNum);
            count += resultValue;
        }
        System.out.println(count);
    }
}
