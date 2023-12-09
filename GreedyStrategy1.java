import java.util.ArrayList;
import java.util.List;

public class GreedyStrategy1 {
    public static List<List<Integer>> distributeRoutes(int[] routes, int numTrucks) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numTrucks; i++) {
            result.add(new ArrayList<>());
        }
        int[] distances = new int[numTrucks];
        for (int route : routes) {
            int minIndex = 0;
            int minDistance = distances[0];
            for (int i = 1; i < numTrucks; i++) {
                if (distances[i] < minDistance) {
                    minIndex = i;
                    minDistance = distances[i];
                }
            }
            result.get(minIndex).add(route);
            distances[minIndex] += route;
        }
        return result;
    }
}
