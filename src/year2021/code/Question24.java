package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Question24 {
    /**
     * Code below is initial attempt. Code should work properly, but runs too long. Solution worked out mathematically, see below.
     */
    /*public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 24);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        String[][] inputCommands = new String[input.length][];
        for (int i = 0; i < input.length; i++) {
            inputCommands[i] = input[i].split(" ");
        }
        int[] startinput = new int[14];
        for (int i = 0; i < 14; i++) {
            startinput[i] = 9;
        }
        long highestModelNumber = 0;
        checkNumber:
        while (true) {
            int newModelnumber = 0;
            for (int i = 0; i < 14; i++) {
                newModelnumber += Math.pow(10, i) * startinput[13 - i];
            }
            highestModelNumber = newModelnumber;
            int inputIndex = 0;
            int w = 0;
            int x = 0;
            int y = 0;
            int z = 0;
            for (String[] command : inputCommands) {
                if (command[0].equals("inp")) {
                    if (command[1].equals("w")) {
                        w = startinput[inputIndex];
                    } else if (command[1].equals("x")) {
                        x = startinput[inputIndex];
                    } else if (command[1].equals("y")) {
                        y = startinput[inputIndex];
                    } else {
                        z = startinput[inputIndex];
                    }
                    inputIndex++;
                } else {
                    int firstVal = -1;
                    if (command[1].equals("w")) {
                        firstVal = w;
                    } else if (command[1].equals("x")) {
                        firstVal = x;
                    } else if (command[1].equals("y")) {
                        firstVal = y;
                    } else {
                        firstVal = z;
                    }
                    int secondVal = 0;
                    if (command[2].equals("w")) {
                        secondVal = w;
                    } else if (command[2].equals("x")) {
                        secondVal = x;
                    } else if (command[2].equals("y")) {
                        secondVal = y;
                    } else if (command[2].equals("z")) {
                        secondVal = z;
                    } else {
                        secondVal = Integer.parseInt(command[2]);
                    }
                    int result = 0;
                    if (command[0].equals("add")) {
                        result = firstVal + secondVal;
                    } else if (command[0].equals("mul")) {
                        result = firstVal + secondVal;
                    } else if (command[0].equals("div")) {
                        if ((firstVal < 0 && secondVal > 0) || (firstVal > 0 && secondVal < 0)) {
                            firstVal = Math.abs(firstVal);
                            secondVal = Math.abs(secondVal);
                            result = -1 * Math.floorDiv(firstVal, secondVal);
                        } else {
                            result = Math.floorDiv(firstVal, secondVal);
                        }
                    } else if (command[0].equals("mod")) {
                        result = firstVal % secondVal;
                    } else {
                        if (firstVal == secondVal) {
                            result = 1;
                        } else {
                            result = 0;
                        }
                    }
                    if (command[1].equals("w")) {
                        w = result;
                    } else if (command[1].equals("x")) {
                        x = result;
                    } else if (command[1].equals("y")) {
                        y = result;
                    } else {
                        z = result;
                    }
                }
            }
            if (z == 0) {
                break checkNumber;
            }
            int lowerIndex = 13;
            while (startinput[lowerIndex] == 1) {
                startinput[lowerIndex] = 9;
                lowerIndex--;
            }
            if (lowerIndex == 7) {
                System.out.println("hello");
            }
            startinput[lowerIndex]--;
        }
        System.out.println(highestModelNumber);
    }

    private static void part2(String[] input) {

    }*/

    /**
     * Firstly, the reduced input:
     */
    /**
     * a
     * 1
     * 11
     * 7
     *
     * b
     * 1
     * 14
     * 8
     *
     * c
     * 1
     * 10
     * 16
     *
     * d
     * 1
     * 14
     * 8
     *
     * e
     * 26
     * -8
     * 3
     *
     * f
     * 1
     * 14
     * 12
     *
     * g
     * 26
     * -11
     * 1
     *
     * h
     * 1
     * 10
     * 8
     *
     * i
     * 26
     * -6
     * 8
     *
     * j
     * 26
     * -9
     * 14
     *
     * k
     * 1
     * 12
     * 4
     *
     * l
     * 26
     * -5
     * 14
     *
     * m
     * 26
     * -4
     * 15
     *
     * n
     * 26
     * -9
     * 6
     */

    /**
     * When naming lines in the following text, line 1 is presumed the first line.
     * Each letter is added to the ever growing value in z, in such fashion: (((a + 7) * 26 + b + 8) * 26 + c + 16) * 26 + e + 3 if the value on "line 4" of a piece (starting from input) is 1,
     * using the third value in the reduced input ("3rd line from below" of a piece) as a so called booster.
     * If the value on "line 4" is 26, the last added value with its booster is removed, and checked against the new value minus the second value (let's called it reducer) of the reduced input ("line 6" of a piece).
     * this basically means that you take the booster, add the reducer (since it's negative you are always subtracting something), and check if the removed value + booster - reducer is equal to the new value.
     * If it is not, the new value is added. But since we want z to be 0 in the end, we want it to be equal. So the reduced input results in the following commands:
     */

    /**
     * add(a+7)
     * add(b+8)
     * add(c+16)
     * add(d+8)
     * if (e == remove + -8)
     * add(f+12)
     * if (g == remove + -11)
     * add(h+8)
     * if (i == remove + -6)
     * if (j == remove + -9)
     * add(k+4)
     * if (l == remove + -5)
     * if (m == remove + -4)
     * if (n == remove + -9)
     */

    /**
     * performing this step by step you end up with the following equalities that must hold for z to be 0 in the end:
     */

    /**
     * e==d+8-8
     * g==f+12-11
     * i==h+8-6
     * j==c+16-9
     * l==k+4-5
     * m==b+8-4
     * n==a+7-9
     *
     * E == D
     * G == F+1
     * I == H+2
     * J == C+7
     * L == K-1
     * M == B+4
     * N == A-2
     */

    /**
     * For part 1, the highest value that fullfills this is:
     * abcdefghijklmn
     * 31111121382151
     *
     * For part 2, the lowest value that fullfills this is:
     * abcdefghijklmn
     * 95299897999897
     */

    /**
     * Thanks for reading!
     */
}
