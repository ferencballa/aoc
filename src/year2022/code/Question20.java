package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question20 {
    public static void main(String[] args) throws IOException {
        Q20Part1.run();
        Q20Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 20);
    }
}

class Q20Part1 {
    public static void main(String[] args) throws IOException {
        /*Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(50) * (r.nextBoolean() ? 1 : -1));
        }*/
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question20.getInput();
        ArrayList<MovingNumber> vals = new ArrayList<>();
        for (String in : input) {
            vals.add(new MovingNumber(Integer.parseInt(in)));
        }
        int listSize = vals.size();
        for (int i = 0; i < listSize; i++) {
            if (!vals.get(i).moved) {
                //System.out.println(vals.get(0).value + ", " + vals.get(1).value + ", " + vals.get(2).value + ", " + vals.get(3).value + ", " + vals.get(4).value + ", " + vals.get(5).value + ", " + vals.get(6).value);
                int val = vals.remove(i).value;
                int pos = (i + val) % (listSize-1);
                while (pos <= 0) {
                    pos += listSize - 1;
                }
                vals.add(pos, new MovingNumber(val, true));
                i--;
            }
        }
        int pos0 = -1;
        for (int i = 0; i < listSize; i++) {
            if (vals.get(i).value == 0) {
                pos0 = i;
            }
        }
        //System.out.println(vals.get(0).value + ", " + vals.get(1).value + ", " + vals.get(2).value + ", " + vals.get(3).value + ", " + vals.get(4).value + ", " + vals.get(5).value + ", " + vals.get(6).value);
        System.out.println(vals.get((pos0 + 1000) % listSize).value + vals.get((pos0 + 2000) % listSize).value + vals.get((pos0 + 3000) % listSize).value);
    }
}

class MovingNumber {
    int value;
    boolean moved;

    public MovingNumber(int v) {
        value = v;
        moved = false;
    }

    public MovingNumber(int v, boolean m) {
        value = v;
        moved = m;
    }
}

class MovingNumberWithPos {
    long value;
    boolean moved;
    long pos;

    public MovingNumberWithPos(long v, long p) {
        value = v;
        moved = false;
        pos = p;
    }

    public MovingNumberWithPos(long v, long p, boolean m) {
        value = v;
        moved = m;
        pos = p;
    }
}

class Q20Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question20.getInput();
        ArrayList<MovingNumberWithPos> vals = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            vals.add(new MovingNumberWithPos(((long)Integer.parseInt(input[i])) * 811589153, i));
        }
        int listSize = vals.size();
        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < listSize; i++) {
                int index = 0;
                while ((int) vals.get(index).pos != i) {
                    index++;
                }
                //System.out.println(vals.get(0).value + ", " + vals.get(1).value + ", " + vals.get(2).value + ", " + vals.get(3).value + ", " + vals.get(4).value + ", " + vals.get(5).value + ", " + vals.get(6).value);
                MovingNumberWithPos val = vals.remove(index);
                long pos = (index + val.value) % (listSize - 1);
                while (pos <= 0) {
                    pos += listSize - 1;
                }
                vals.add((int) pos, new MovingNumberWithPos(val.value, val.pos, true));
            }
        }
        int pos0 = -1;
        for (int i = 0; i < listSize; i++) {
            if (vals.get(i).value == 0) {
                pos0 = i;
            }
        }
        //System.out.println(vals.get(0).value + ", " + vals.get(1).value + ", " + vals.get(2).value + ", " + vals.get(3).value + ", " + vals.get(4).value + ", " + vals.get(5).value + ", " + vals.get(6).value);
        System.out.println(vals.get((pos0 + 1000) % listSize).value + vals.get((pos0 + 2000) % listSize).value + vals.get((pos0 + 3000) % listSize).value);
    }
}
