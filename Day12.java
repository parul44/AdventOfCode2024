import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day12 {

    static char[][] farm;
    static boolean[][] visited;
    static int[][] dir = {
        {0,1}, {0,-1},{-1,0},{1,0}
    };
    static long area, perimeter;
    static long fenceCost;
    static int len = 140;

    public static void main(String[] args) {
         try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day12.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String reads; int t = 0;
            farm = new char[len][len];
            visited = new boolean[len][len];
            while ((reads = br.readLine()) != null) {
                farm[t++] = reads.toCharArray();
            }
            br.close();
            getCostForRegion();
            System.out.println(fenceCost);
        }
        catch(Exception e) {
            System.err.println("Error while reading file" + e.getMessage());
        }
    }

    private static void getCostForRegion() {
        for (int i = 0; i < farm.length; i++) {
            for (int j = 0; j < farm[i].length; j++) {
                if (!visited[i][j]) {
                    // System.out.println(" area " + area + " peri " + perimeter);
                    area = perimeter = 0L;
                    getAreaAndPerimeter(i, j);
                    fenceCost += (area *perimeter);
                }
            }
        }
    }

    private static void getAreaAndPerimeter(int r, int c) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        area++;
        // perimeter+= 4;

        for (int d = 0; d < 4; d++) {
            int nextRow = r + dir[d][0];
            int nextCol = c + dir[d][1];
            if (isValidPeri(nextRow, nextCol, farm[r][c])) perimeter++;
            if (isValid(nextRow, nextCol, farm[r][c])) {
                getAreaAndPerimeter(nextRow, nextCol);
            }
        }
        // System.out.println(r + ", " + c + " area " + area + " peri " + perimeter);
    }

    private static boolean isValid(int r, int c, char search) {
        if (r <0 || r == len || c < 0 || c == len || visited[r][c] || farm[r][c] != search) return false;
        return true;
    }

    private static boolean isValidPeri(int r, int c, char search) {
        if (r < 0 || r == len || c < 0 || c == len || farm[r][c]!= search) return true;
        return false;
    }
}
