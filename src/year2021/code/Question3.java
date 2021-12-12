package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Question3 {
    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question3.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[] bits = new int[12];
        int count = 0;
        for (String line: input) {
            count ++;
            for (int i = 0; i < 12; i++) {
                if (line.charAt(i) == '1') {
                    bits[i]++;
                }
            }
        }
        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < 12; i++) {
            if (bits[i] > count / 2) {
                gamma += Math.pow(2,11-i);
            } else {
                epsilon += Math.pow(2,11-i);
            }
        }
        System.out.println(gamma * epsilon);
    }

    private static void part2(String[] input) {
        ArrayList<String> oxygen = new ArrayList<>();
        ArrayList<String> co2 = new ArrayList<>();
        for(String inp : input) {
            oxygen.add(inp);
            co2.add(inp);
        }
        int oxygenIndex = 0;
        while(oxygen.size() > 1) {
            int count = 0;
            int ones = 0;
            for(String value : oxygen) {
                count++;
                if (value.charAt(oxygenIndex) == '1') {
                    ones++;
                }
            }
            int finalOxygenIndex = oxygenIndex;
            if (ones > count/2 || ones == count/2)
            {
                oxygen = oxygen.stream().filter(ox -> ox.charAt(finalOxygenIndex) == '1').collect(Collectors.toCollection(ArrayList::new));
            } else {
                oxygen = oxygen.stream().filter(ox -> ox.charAt(finalOxygenIndex) == '0').collect(Collectors.toCollection(ArrayList::new));
            }
            oxygenIndex++;
        }
        int oxy = 0;
        for (int i = 0; i < 12; i++) {
            if (oxygen.get(0).charAt(i) == '1') {
                oxy += Math.pow(2, 11-i);
            }
        }
        int co2Index = 0;
        while(co2.size() > 1) {
            int count = 0;
            int ones = 0;
            for(String value : co2) {
                count++;
                if (value.charAt(co2Index) == '1') {
                    ones++;
                }
            }
            if (ones > count/2 || ones == count/2)
            {
                int finalC02Index = co2Index;
                co2 = co2.stream().filter(c -> c.charAt(finalC02Index) == '0').collect(Collectors.toCollection(ArrayList::new));
            } else {
                int finalOxygenIndex = co2Index;
                co2 = co2.stream().filter(c -> c.charAt(finalOxygenIndex) == '1').collect(Collectors.toCollection(ArrayList::new));
            }
            co2Index++;
        }
        int co = 0;
        for (int i = 0; i < 12; i++) {
            if (co2.get(0).charAt(i) == '1') {
                co += Math.pow(2, 11-i);
            }
        }
        System.out.println(oxy * co);
    }
}
