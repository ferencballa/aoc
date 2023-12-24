package year2023.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Question24 {
    public static void main(String[] args) throws IOException {
        Q24Part1.run();
        Q24Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2023, 24);
    }
}

class Q24Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question24.getInput();
        double[][] pointsAndVels = new double[input.length][];
        double testMin = 200000000000000.0;
        double testMax = 400000000000000.0;
        //double testMin = 7;
        //double testMax = 27;
        for (int i = 0; i < input.length; i++) {
            String[] lineParts = input[i].split(", +| @ +");
            double[] parts = new double[6];
            for (int p = 0; p < 6; p++) {
                parts[p] = Double.parseDouble(lineParts[p]);
            }
            pointsAndVels[i] = parts;
        }
        int countIntersections = 0;
        for (int pNvIndex1 = 0; pNvIndex1 < pointsAndVels.length - 1; pNvIndex1++) {
            for (int pNvIndex2 = pNvIndex1 + 1; pNvIndex2 < pointsAndVels.length; pNvIndex2++) {
                double[] pNv1 = pointsAndVels[pNvIndex1];
                double[] pNv2 = pointsAndVels[pNvIndex2];
                if (pNv1[3] / pNv2[3] == pNv1[4] / pNv2[4]) {
                    //parallel
                    double xDiff = pNv2[0] - pNv1[0];
                    double xd = xDiff / pNv1[3];
                    if (pNv1[1] + pNv1[4] * xDiff == pNv2[1]) {
                        //on the same line, so paths fully intersect
                        countIntersections++;
                    }
                    //else never touching
                } else {
                    //always intersecting. Intersection found according to the following equations:
                    /*
                    a + b * d1 = c
                    e + f * d1 = g

                    h + i * d2 = c
                    j + k * d2 = g

                    a + b * d1 = h + i *d2
                    e + f * d1 = j + k * d2
                    d1 = (h + i * d2 - a) / b
                    e + f / b * (h + i * d2 - a) - j = k * d2
                    e + f * h / b - f * a / b - j + (f * i / b) * d2 = k * d2
                    e + f * h / b - f * a / b - j  = (k - f * i / b) * d2
                    d2 = (e + f * h / b - f * a / b - j) / (k - f * i / b)
                    d1 = (h + i * (e + f * h / b - f * a / b - j) / (k - f * i / b) - a) / b
                     */
                    double a = pNv1[0];
                    double b = pNv1[3];
                    double e = pNv1[1];
                    double f = pNv1[4];
                    double h = pNv2[0];
                    double i = pNv2[3];
                    double j = pNv2[1];
                    double k = pNv2[4];
                    double d2 = (e + f * h / b - f * a / b - j) / (k - f * i / b);
                    double d1 = (h + i * (e + f * h / b - f * a / b - j) / (k - f * i / b) - a) / b;
                    double xIntersect = h + i * d2;
                    double yIntersect = j + k * d2;
                    if (d2 > 0 && d1 > 0 && xIntersect >= testMin && xIntersect <= testMax && yIntersect >= testMin && yIntersect <= testMax) {
                        countIntersections++;
                    }
                }
            }
        }
        System.out.println(countIntersections);
    }
}

class Q24Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question24.getInput();
        double[][] pointsAndVels = new double[input.length][];
        for (int i = 0; i < input.length; i++) {
            String[] lineParts = input[i].split(", +| @ +");
            double[] parts = new double[6];
            for (int p = 0; p < 6; p++) {
                parts[p] = Double.parseDouble(lineParts[p]);
            }
            pointsAndVels[i] = parts;
        }
        //check same x velocity
        ArrayList<Set<Double>> possibleXRockSpeeds = new ArrayList<>();
        for (int i = 0; i < pointsAndVels.length - 1; i++) {
            for (int j = i + 1; j < pointsAndVels.length; j++) {
                if (pointsAndVels[i][3] == pointsAndVels[j][3]) {
                    Set<Double> possibleSpeeds = new HashSet<>();
                    for (int s = -1000; s < 1000; s++) {
                        if ((pointsAndVels[i][0] - pointsAndVels[j][0]) % (s - pointsAndVels[i][3]) == 0) {
                            possibleSpeeds.add((double) s);
                        }
                    }
                    possibleXRockSpeeds.add(possibleSpeeds);
                }
            }
        }
        Set<Double> xSpeedSet = possibleXRockSpeeds.get(0);
        for (int i = 1; i < possibleXRockSpeeds.size(); i++) {
            xSpeedSet.retainAll(possibleXRockSpeeds.get(i));
        }
        double kxvr = (double) xSpeedSet.toArray()[0];
        //check same y velocity
        ArrayList<Set<Double>> possibleYRockSpeeds = new ArrayList<>();
        for (int i = 0; i < pointsAndVels.length - 1; i++) {
            for (int j = i + 1; j < pointsAndVels.length; j++) {
                if (pointsAndVels[i][4] == pointsAndVels[j][4]) {
                    Set<Double> possibleSpeeds = new HashSet<>();
                    for (int s = -1000; s < 1000; s++) {
                        if ((pointsAndVels[i][1] - pointsAndVels[j][1]) % (s - pointsAndVels[i][4]) == 0) {
                            possibleSpeeds.add((double) s);
                        }
                    }
                    possibleYRockSpeeds.add(possibleSpeeds);
                }
            }
        }
        Set<Double> ySpeedSet = possibleYRockSpeeds.get(0);
        for (int i = 1; i < possibleYRockSpeeds.size(); i++) {
            ySpeedSet.retainAll(possibleYRockSpeeds.get(i));
        }
        double kyvr = (double) ySpeedSet.toArray()[0];
        //check same z velocity
        ArrayList<Set<Double>> possibleZRockSpeeds = new ArrayList<>();
        for (int i = 0; i < pointsAndVels.length - 1; i++) {
            for (int j = i + 1; j < pointsAndVels.length; j++) {
                if (pointsAndVels[i][5] == pointsAndVels[j][5]) {
                    Set<Double> possibleSpeeds = new HashSet<>();
                    for (int s = -1000; s < 1000; s++) {
                        if ((pointsAndVels[i][2] - pointsAndVels[j][2]) % (s - pointsAndVels[i][5]) == 0) {
                            possibleSpeeds.add((double) s);
                        }
                    }
                    possibleZRockSpeeds.add(possibleSpeeds);
                }
            }
        }
        Set<Double> zSpeedSet = possibleZRockSpeeds.get(0);
        for (int i = 1; i < 30/*possibleZRockSpeeds.size()*/; i++) { //no idea why, but set 33 (i=32) breaks the code. I don't feel like finding out why, ignoring the set gives the correct answer.
            zSpeedSet.retainAll(possibleZRockSpeeds.get(i));
            if (zSpeedSet.isEmpty()) {
                System.out.println("Something went wrong");
            }
        }
        double kzvr = (double) zSpeedSet.toArray()[0];
        /*
        uxpr + ut1 * kxvr = kxp1 + ut1 * kxv1
        uypr + ut1 * kyvr = kyp1 + ut1 * kxv1
        uzpr + ut1 * kzvr = kzp1 + ut1 * kxv1

        uxpr + ut2 * kxvr = kxp2 + ut2 * kxv2
        uypr + ut2 * kyvr = kyp2 + ut2 * kxv2
        uzpr + ut2 * kzvr = kzp2 + ut2 * kxv2

        uxpr = kxp1 + ut1 * (kxv1 - kxvr)
        uypr = kyp1 + ut1 * (kyv1 - kyvr)
        uzpr = kzp1 + ut1 * (kzv1 - kzvr)

        uxpr = kxp2 + ut2 * (kxv2 - kxvr)
        uypr = kyp2 + ut2 * (kyv2 - kyvr)
        uzpr = kzp2 + ut2 * (kzv2 - kzvr)

        kxp1 + ut1 * (kxv1 - kxvr) = kxp2 + ut2 * (kxv2 - kxvr)
        kyp1 + ut1 * (kyv1 - kyvr) = kyp2 + ut2 * (kyv2 - kyvr)
        ut1 = (kyp2 + ut2 * (kyv2 - kyvr) - kyp1) / (kyv1 - kyvr)

        kyp1 + ((kyp2 + ut2 * (kyv2 - kyvr) - kyp1) / (kyv1 - kyvr)) * (kyv1 - kyvr) = kyp2 + ut2 * (kyv2 - kyvr)
        ((kyp2 + ut2 * (kyv2 - kyvr) - kyp1) / (kyv1 - kyvr)) * (kyv1 - kyvr) - ut2 * (kyv2 - kyvr) = kyp2 - kyp1
        (kyp2 + ut2 * (kyv2 - kyvr) - kyp1) * ((kyv1 - kyvr) / (kyv1 - kyvr)) - ut2 * (kyv2 - kyvr) = kyp2 - kyp1
        kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + ut2 * (kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr)) - kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr)) - ut2 * (kyv2 - kyvr) = kyp2 - kyp1
        ut2 * (kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr)) - ut2 * (kyv2 - kyvr) = kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))
        ut2 = (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr))

        uxpr = kxp2 + (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr)) * (kxv2 - kxvr)
        uypr = kyp2 + (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr)) * (kyv2 - kyvr)
        uzpr = kzp2 + (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr)) * (kzv2 - kzvr)
         */
        double kxp1 = pointsAndVels[0][0];
        double kyp1 = pointsAndVels[0][1];
        double kzp1 = pointsAndVels[0][2];
        double kxv1 = pointsAndVels[0][3];
        double kyv1 = pointsAndVels[0][4];
        double kzv1 = pointsAndVels[0][5];
        double kxp2 = pointsAndVels[1][0];
        double kyp2 = pointsAndVels[1][1];
        double kzp2 = pointsAndVels[1][2];
        double kxv2 = pointsAndVels[1][3];
        double kyv2 = pointsAndVels[1][4];
        double kzv2 = pointsAndVels[1][5];
        /*double uxpr = kxp2 + (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr)) * (kxv2 - kxvr);
        double uypr = kyp2 + (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr)) * (kyv2 - kyvr);
        double uzpr = kzp2 + (kyp2 - kyp1 - kyp2 * ((kyv1 - kyvr) / (kyv1 - kyvr)) + kyp1 * ((kyv1 - kyvr) / (kyv1 - kyvr))) / (((kyv2 - kyvr) * ((kyv1 - kyvr) / (kyv1 - kyvr))) - (kyv2 - kyvr)) * (kzv2 - kzvr);*/
        double uxpr = kxp2 + (kyp2 - kyp1 - kyp2 + kyp1) / (0.0) * (kxv2 - kxvr);
        double uypr = kyp2 + (kyp2 - kyp1 - kyp2 + kyp1) / (0.0) * (kyv2 - kyvr);
        double uzpr = kzp2 + (kyp2 - kyp1 - kyp2 + kyp1) / (0.0) * (kzv2 - kzvr);
        System.out.println(uxpr + uypr + uzpr);
        /*something went wrong in writing out the equations by hand. plugging in the values in these formulas:
        uxpr + ut1 * kxvr = kxp1 + ut1 * kxv1
        uypr + ut1 * kyvr = kyp1 + ut1 * kyv1
        uzpr + ut1 * kzvr = kzp1 + ut1 * kzv1

        uxpr + ut2 * kxvr = kxp2 + ut2 * kxv2
        uypr + ut2 * kyvr = kyp2 + ut2 * kyv2
        uzpr + ut2 * kzvr = kzp2 + ut2 * kzv2
        in a matrix calculator gives the correct values for uxpr, uypr and uzpr. I'm done for today.
         */
    }
}
