import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main(String[] args) {
        List<Integer> distances = List.of(50, 55, 57, 58, 60);
        int t = 175;
        int k = 3;

        Integer bestSum = chooseBestSum(t, k, distances);
        if (bestSum != null) {
            System.out.println("Максимальная сумма расстояний: " + bestSum);
        } else {
            System.out.println("Нет подходящей комбинации.");
        }
	}
	public static Integer chooseBestSum(int t, int k, List<Integer> distances) {
        return findBestSum(distances, k, 0, 0, t);
    }

    private static Integer findBestSum(List<Integer> distances, int k, int start, int currentSum, int t) {
        if (k == 0) {
            if (currentSum <= t) return currentSum;
            else return null;
        }
        if (start >= distances.size()) return null;

        Integer best = null;
        for (int i = start; i < distances.size(); i++) {
            Integer temp = findBestSum(distances, k - 1, i + 1, currentSum + distances.get(i), t);
            if (temp != null) {
                if (best == null || temp > best) {
                    best = temp;
                }
            }
        }
        return best;
    }
}