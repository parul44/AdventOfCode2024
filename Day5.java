import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day5 {

    public static Map<Integer, Set<Integer>> graph;

    public static List<Integer> topoSort;

    public static int[][] orders;

    public static int NODES;

    public static void addEdge(int u, int v) {
        if (!graph.containsKey(u)) {
            graph.put(u, new HashSet<>());
        }
        graph.get(u).add(v);
        if (!graph.containsKey(v)) graph.put(v, new HashSet<>());
    }


    public static void main(String[] args) {
        graph = new HashMap<>();
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day5Edges.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            while ((read = br.readLine()) != null) {
                String[] neighbours = read.split("\\|");
                addEdge(Integer.parseInt(neighbours[0]), Integer.parseInt(neighbours[1]));
            }
            br.close();
            System.out.println(graph.size());
            NODES = graph.size();
            printGraph();

        }
        catch(Exception e) {
            System.err.println(e);
        }

        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day5Order.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            long midSum = 0;
            int t = 1;
            while ((read = br.readLine()) != null) {
                String[] o = read.split(",");
                int[] order = new int[o.length];
                int i = 0;
                for (String s: o) {
                    order[i++] = Integer.parseInt(s);
                    // midSum+= sumOfMidsOfValidOrder(order);
                }
                System.out.println("order #" + t++);
                midSum+= fixOrder(order);
            }
            br.close();
            System.out.println(midSum);
        }
        catch(Exception e) {
            System.err.println(e);
        }

    }

    private static int fixOrder(int[] order) {
        int faultyIdx;
        boolean wasValid = true;
        while ((faultyIdx = isValidOrder(order)) != -1 && faultyIdx > 0 && faultyIdx < order.length) {
            wasValid = false;
            int temp = order[faultyIdx - 1];
            order[faultyIdx - 1] = order[faultyIdx];
            order[faultyIdx] = temp;
        }
        if (!wasValid) System.out.println("fixed order");
        for (int i : order) {
            System.out.print(i +" ,");
        }
        System.out.println();
        return wasValid ? 0 : order[order.length/2];
    }
    private static long sumOfMidsOfValidOrder(int[] order) {
        return isValidOrder(order) == -1 ?  order[order.length/2] : 0; 
    }

    private static int isValidOrder(int[] order) {
        int i = 0;
        if (!graph.containsKey(order[i])) return i;
        Set<Integer> neighbors;
        i = 1;
        neighbors = graph.get(order[0]);
        while (i < order.length) {
            if (!graph.containsKey(order[i])) return i;
            if (neighbors.contains(order[i])) {
                neighbors = graph.get(order[i]);
                i++;
            }
            else return i;
        }
        return -1;
    }

    private static void printGraph() {
        for (Map.Entry<Integer, Set<Integer>> e: graph.entrySet()) {
            System.out.print(e.getKey() + " -> ");
            for (int v : e.getValue()) {
                System.out.print(v + ",");
            }
            System.out.println();
        }
    }
}
