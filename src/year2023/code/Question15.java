package year2023.code;

import helpers.Helper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class Question15 {
    public static void main(String[] args) throws IOException {
        Q15Part1.run();
        Q15Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 15);
    }
}

class Q15Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question15.getInput();
        String[] inputStrings = input[0].split(",");
        long count = 0;
        for (String string : inputStrings) {
            long hashVal = 0;
            for (char c : string.toCharArray()) {
                hashVal += c;
                hashVal *= 17;
                hashVal %= 256;
            }
            count += hashVal;
        }
        System.out.println(count);
    }
}

class Q15Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question15.getInput();
        String[] operations = input[0].split(",");
        ArrayList<Pair<String, Integer>>[] boxes = new ArrayList[256];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new ArrayList<Pair<String, Integer>>();
        }
        for (String operation : operations) {
            if (operation.charAt(operation.length() - 1) == '-') {
                String label = operation.substring(0, operation.length() - 1);
                int index = getHash(label);
                ArrayList<Pair<String, Integer>> box = boxes[index];
                for (int i = 0; i < box.size(); i++) {
                    if (box.get(i).getLeft().equals(label)) {
                        box.remove(i);
                        break;
                    }
                }
            } else {
                String label = operation.substring(0, operation.length() - 2);
                int focalLength = Integer.parseInt(operation.substring(operation.length() - 1));
                int index = getHash(label);
                ArrayList<Pair<String, Integer>> box = boxes[index];
                boolean lensAlreadyPresent = false;
                for (int i = 0; i < box.size(); i++) {
                    if (box.get(i).getLeft().equals(label)) {
                        box.remove(i);
                        box.add(i, new ImmutablePair<>(label, focalLength));
                        lensAlreadyPresent = true;
                        break;
                    }
                }
                if (!lensAlreadyPresent) {
                    box.add(new ImmutablePair<>(label, focalLength));
                }
            }
        }
        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            ArrayList<Pair<String, Integer>> box = boxes[i];
            for (int s = 0; s < box.size(); s++) {
                count += (i + 1) * (s + 1) * box.get(s).getRight();
            }
        }
        System.out.println(count);
    }

    private static int getHash(String string) {
        long hashVal = 0;
        for (char c : string.toCharArray()) {
            hashVal += c;
            hashVal *= 17;
            hashVal %= 256;
        }
        return (int) hashVal;
    }
}
