import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day6 {
    private static int m, n;
    private static Map<Character, Integer> dir = Map.of('U', 3,
        'L',1,
        'D',2,
        'R',0
    );
    private static int[][] directions = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    private static List<List<Character>> grid;

    private static int currRow, currCol;
    public static void main(String[] args) {
        try{
            grid = new ArrayList<>();
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day6.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            int t = 0, u = 0;
            while ((read = br.readLine()) != null) {
                List<Character> li = new ArrayList<>();
                u = 0;
                for (Character ch: read.toCharArray()) {
                    
                    if (ch == '^') {
                        currRow = t;
                        currCol = u;
                    }
                    u++;
                    li.add(ch);
                }
                t++;
                grid.add(li);
            }
            
            br.close();
            markPatrols(currRow, currCol, 'U');
            System.out.println(patrolAreas());
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private static long patrolAreas() {
        long count = 0;
        // System.out.println(currRow + " , " + currCol);
        for (List<Character> li: grid) {
            for (Character ch: li) {
                // System.out.print(ch);
                if (ch == 'X') count++;
            }
            // System.out.println();
        }
        return count;
    }

    private static void markPatrols(int r, int c, Character d) {
        // System.out.println("call : " + r + " , " + c + " , " + d);
        grid.get(r).set(c, 'X');
        int nextCol , nextRow;
        nextRow = r + directions[dir.get(d)][0];
        nextCol = c + directions[dir.get(d)][1];
        if (nextRow < 0 || nextCol < 0 || nextRow == grid.size() || nextCol == grid.get(0).size()) {
            return;
        }
        if (grid.get(nextRow).get(nextCol) == '#') {
            if (d == 'U') markPatrols(r, c, 'R');
            else if (d == 'R') markPatrols(r, c, 'D');
            else if (d == 'D') markPatrols(r, c, 'L');
            else markPatrols(r, c, 'U');
        }
        else markPatrols(nextRow, nextCol, d);

    }

}
