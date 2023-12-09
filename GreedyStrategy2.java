import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyStrategy2 {
    public static List<List<Integer>> distributeRoutes(int[] routes, int numTrucks) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numTrucks; i++) {
            result.add(new ArrayList<>());
        }
        List<Route> routeList = new ArrayList<>();
        for (int route : routes) {
            routeList.add(new Route(route));
        }
        routeList.sort(Comparator.comparing(Route::getTime));
        for (int i = 0; i < routes.length; i++) {
            int minIndex = 0;
            int minDistance = getDistance(result.get(0));
            for (int j = 1; j < numTrucks; j++) {
                int distance = getDistance(result.get(j));
                if (distance < minDistance) {
                    minIndex = j;
                    minDistance = distance;
                }
            }
            result.get(minIndex).add(routeList.get(i).getRoute());
        }
        return result;
    }

    private static int getDistance(List<Integer> route) {
        int distance = 0;
        for (int r : route) {
            distance += r;
        }
        return distance;
    }

    private static class Route {
        private final int route;
        private final double time;

        public Route(int route) {
            this.route = route;
            this.time = Math.random() * 100;
        }

        public int getRoute() {
            return route;
        }

        public double getTime() {
            return time;
        }
    }
}
