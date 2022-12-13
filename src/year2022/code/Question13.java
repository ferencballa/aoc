package year2022.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Question13 {
    public static void main(String[] args) throws IOException {
        Q13Part1.run();
        Q13Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 13);
    }
}

class PairNS {
    NumOrList n;
    String s;

    public PairNS(NumOrList nu, String st) {
        n = nu;
        s = st;
    }
}

class NumOrList {
    boolean isNum;
    int val;
    ArrayList<NumOrList> listVal;

    public NumOrList(int v) {
        isNum = true;
        val = v;
    }

    public NumOrList(ArrayList<NumOrList> listV) {
        isNum = false;
        listVal = listV;
    }

    public static PairNS parse(String s) {
        NumOrList n;
        if (s.startsWith(",")) {
            s = s.substring(1);
        }
        if (s.startsWith("[")) {
            s = s.substring(1);
            ArrayList<NumOrList> list = new ArrayList<>();
            while (!s.startsWith("]")) {
                PairNS pairNS = parse(s);
                NumOrList el = pairNS.n;
                s = pairNS.s;
                list.add(el);
            }
            s = s.substring(1);
            n = new NumOrList(list);
        } else {
            int index = 0;
            while (index < s.length() && s.charAt(index) >= 48 && s.charAt(index) <= 57) {
                index++;
            }
            n = new NumOrList(Integer.parseInt(s.substring(0, index)));
            s = s.substring(index);
        }
        return new PairNS(n, s);
    }
}

class Q13Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static int packetsCorrectOrder(NumOrList p1, NumOrList p2) {
        if (p1.isNum && p2.isNum) {
            return Integer.compare(p2.val, p1.val);
        } else {
            ArrayList<NumOrList> list1;
            ArrayList<NumOrList> list2;
            if (p1.isNum) {
                list1 = new ArrayList<>();
                list1.add(p1);
            } else {
                list1 = p1.listVal;
            }
            if (p2.isNum) {
                list2 = new ArrayList<>();
                list2.add(p2);
            } else {
                list2 = p2.listVal;
            }
            for (int i = 0; i < list1.size(); i++) {
                if (i == list2.size()) {
                    return -1;
                }
                int ret = packetsCorrectOrder(list1.get(i), list2.get(i));
                if (ret != 0) {
                    return ret;
                }
            }
            if (list1.size() < list2.size()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question13.getInput();
        int indexSum = 0;
        for (int i = 0; i < input.length; i+=3) {
            boolean correctOrder = true;
            NumOrList packet1 = NumOrList.parse(input[i]).n;
            NumOrList packet2 = NumOrList.parse(input[i+1]).n;
            correctOrder = packetsCorrectOrder(packet1, packet2) == 1;
            if (correctOrder) {
                indexSum += i/3 + 1;
            }
        }
        System.out.println(indexSum);
    }
}

class NumOrListComparator implements Comparator<NumOrList> {
    public int compare(NumOrList p1, NumOrList p2) {
        return Q13Part2.packetsCorrectOrder(p1, p2);
    }
}

class Q13Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static int packetsCorrectOrder(NumOrList p1, NumOrList p2) {
        if (p1.isNum && p2.isNum) {
            return Integer.compare(p2.val, p1.val);
        } else {
            ArrayList<NumOrList> list1;
            ArrayList<NumOrList> list2;
            if (p1.isNum) {
                list1 = new ArrayList<>();
                list1.add(p1);
            } else {
                list1 = p1.listVal;
            }
            if (p2.isNum) {
                list2 = new ArrayList<>();
                list2.add(p2);
            } else {
                list2 = p2.listVal;
            }
            for (int i = 0; i < list1.size(); i++) {
                if (i == list2.size()) {
                    return -1;
                }
                int ret = packetsCorrectOrder(list1.get(i), list2.get(i));
                if (ret != 0) {
                    return ret;
                }
            }
            if (list1.size() < list2.size()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question13.getInput();
        ArrayList<NumOrList> packets = new ArrayList<>();
        for (int i = 0; i < input.length; i+=3) {
            NumOrList packet1 = NumOrList.parse(input[i]).n;
            NumOrList packet2 = NumOrList.parse(input[i+1]).n;
            packets.add(packet1);
            packets.add(packet2);
        }
        packets.add(NumOrList.parse("[[2]]").n);
        packets.add(NumOrList.parse("[[6]]").n);
        packets.sort(new NumOrListComparator());
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < packets.size(); i++) {
            NumOrList packet = packets.get(i);
            if (!packet.isNum && packet.listVal.size() == 1 && !packet.listVal.get(0).isNum && packet.listVal.get(0).listVal.size() == 1 && packet.listVal.get(0).listVal.get(0).isNum) {
                if (packet.listVal.get(0).listVal.get(0).val == 2) {
                    index1 = packets.size() - i;
                } else if (packet.listVal.get(0).listVal.get(0).val == 6) {
                    index2 = packets.size() - i;
                }
            }
        }
        System.out.println(index1 * index2);
    }
}
