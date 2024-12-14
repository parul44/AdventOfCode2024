import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    public static long checkSum = 0L;
    public static int position = 0;
    public static void main(String[] args) {
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day9.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String reads;
            int t = 0, u = 0;
            long ans = 0L;
            char[] read = br.readLine().toCharArray();

            int leftIdx = 0, rightIdx;
            rightIdx = (read.length %2 == 0) ? read.length - 2 : read.length - 1;

            while (leftIdx < rightIdx) {
                if (leftIdx %2 == 0) {
                    updateCheckSum(leftIdx/2, read[leftIdx] - '0');
                    leftIdx++;
                }
                else {
                    if(read[leftIdx] == read[rightIdx]) {
                        updateCheckSum(rightIdx/2, read[leftIdx] - '0');
                        read[rightIdx] = '.';
                        rightIdx-=2;
                        leftIdx++;
                    }
                    else if (read[leftIdx] > read[rightIdx]) {
                        updateCheckSum(rightIdx/2, read[rightIdx] - '0');
                        read[leftIdx] = (char)('0' + ((read[leftIdx] - '0')- (read[rightIdx] - '0'))) ;
                        read[rightIdx] = '.';
                        rightIdx-=2;
                    }
                    else {
                        updateCheckSum(rightIdx/2, read[leftIdx] - '0');
                        read[rightIdx] = (char)('0' + ((read[rightIdx] - '0')- (read[leftIdx] - '0'))) ;
                        leftIdx++;
                    }
                }
            }
            updateCheckSum(rightIdx/2, read[rightIdx] - '0');
            System.out.println(read.length);
            System.out.println("final value" + checkSum);
        }
        catch(Exception e) {

        }
          
    }

    private static void updateCheckSum(int fileNumber, int count) {
        for (int i = 1; i <= count; i++) {
            // System.out.print(fileNumber);
            checkSum+= position*fileNumber;
            position++;
        }
    }
}
