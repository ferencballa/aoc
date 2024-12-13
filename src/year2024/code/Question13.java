package year2024.code;

import helpers.Helper;

import java.io.IOException;

public class Question13 {
    public static void main(String[] args) throws IOException {
        Q13Part1.run();
        Q13Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2024, 13);
    }
}

class Q13Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question13.getInput();
        int machines = (input.length + 1) / 4;
        int count = 0;
        for (int index = 0; index < machines; index++) {
            String[] lineParts1 = input[index*4].split(" ");
            int ax = Integer.parseInt(lineParts1[2].split("\\+")[1].split(",")[0]);
            int ay = Integer.parseInt(lineParts1[3].split("\\+")[1]);
            String[] lineParts2 = input[index*4 + 1].split(" ");
            int bx = Integer.parseInt(lineParts2[2].split("\\+")[1].split(",")[0]);
            int by = Integer.parseInt(lineParts2[3].split("\\+")[1]);
            String[] lineParts3 = input[index*4 + 2].split(" ");
            int prizeX = Integer.parseInt(lineParts3[1].split("=")[1].split(",")[0]);
            int prizeY = Integer.parseInt(lineParts3[2].split("=")[1]);
            int cheapest = -1;
            for (int ad = 0; ad <= 100; ad++) { //3 per ad
                for (int bd = 0; bd <= 100; bd++) { //1 per bd
                    if (ax * ad + bx * bd == prizeX && ay * ad + by * bd == prizeY) {
                        if (cheapest == -1) {
                            cheapest = ad * 3 + bd;
                        } else {
                            cheapest = Math.min(cheapest, ad * 3 + bd);
                        }
                    }
                }
            }
            if (cheapest != -1) {
                count += cheapest;
                System.out.println(index);
            }
        }
        System.out.println(count);
    }
}

class Q13Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question13.getInput();
        double addition = 10000000000000L;
        int machines = (input.length + 1) / 4;
        long count = 0;
        for (int index = 0; index < machines; index++) {
            String[] lineParts1 = input[index * 4].split(" ");
            double ax = Integer.parseInt(lineParts1[2].split("\\+")[1].split(",")[0]);
            double ay = Integer.parseInt(lineParts1[3].split("\\+")[1]);
            String[] lineParts2 = input[index * 4 + 1].split(" ");
            double bx = Integer.parseInt(lineParts2[2].split("\\+")[1].split(",")[0]);
            double by = Integer.parseInt(lineParts2[3].split("\\+")[1]);
            String[] lineParts3 = input[index * 4 + 2].split(" ");
            double px = Integer.parseInt(lineParts3[1].split("=")[1].split(",")[0]) + addition;
            double py = Integer.parseInt(lineParts3[2].split("=")[1]) + addition;
            /*if (ax % bx == 0 && ay == by * (ax / bx)) {
                System.out.println(index * 4);
            }
            if (bx % ax == 0 && by == ay * (bx / ax)) {
                System.out.println(index * 4);
            }
            luckily no edge cases where the vector of machine 1 is perpendicular to the vector of machine 2. Would be solvable, but since the multiple would have to be integers would be a few more lines of code
            */
            //ax * ad + bx * bd = px
            //ay * ad + by * bd = py
            //ax * ad = px - bx * bd
            //ad = (px - bx * bd) / ax
            //ay * (px - bx * bd) / ax + by * bd = py
            //ay * px / ax - ay * bx * bd / ax + by * bd = py
            //by * bd - ay * bx * bd / ax = py - ay * px / ax
            //bd * by - bd * ay * bx / ax = py - ay * px / ax
            //bd * (by - ay * bx / ax) = py - ay * px / ax
            //bd = (py - ay * px / ax) / (by - ay * bx / ax)

            double bd = (py - (ay * px) / ax) / (by - (ay * bx) / ax);
            double ad = (px - bx * bd) / ax;
            if ((bd % 1 < 0.0001 || bd % 1 > 0.9999) && (ad % 1  < 0.0001 || ad % 1 > 0.9999)) { //5 precision breaks for me, needs 4 or less
                System.out.println(index);
                count += ad * 3 + bd;
            }
        }
        System.out.println(count);
    }
}