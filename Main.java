import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        int[] arr = { 35, 34, 33, 23, 21, 32, 35, 19, 26, 42 };
        int[] arr2 = { 40, 36, 38, 29, 32, 28, 31, 35, 31, 30, 32, 30, 29, 39, 35, 38, 39, 35, 32, 38, 32, 33, 29, 33,
                32, 39, 28 };

        int numTrucks = 3;
        int minRoutes = 982;
        int maxRoutes = 100000;
        int maxTime = 30000; // 30 seconds
        double dispersion = 0.5;

        // List<List<Backtracking>> resultb = new ArrayList<>();

        // long startTime = System.currentTimeMillis();
        // resultb = Backtracking.main(arr2, numTrucks);
        // long endTime = System.currentTimeMillis();
        // System.out.println("Resultado backtracking:");
        // System.out.println(resultb);
        // long currentTime = endTime - startTime;
        // System.out.println("Tempo: " + currentTime);

        // List<List<Integer>> result = new ArrayList<>();

        // startTime = System.currentTimeMillis();
        // result = GreedyStrategy1.distributeRoutes(arr2, numTrucks);
        // endTime = System.currentTimeMillis();
        // System.out.println("Resultado guloso1:");
        // System.out.println(result);
        // currentTime = endTime - startTime;
        // System.out.println("Tempo: " + currentTime);

        // startTime = System.currentTimeMillis();
        // result = GreedyStrategy1.distributeRoutes(arr2, numTrucks);
        // endTime = System.currentTimeMillis();
        // System.out.println("Resultado guloso2:");
        // System.out.println(result);
        // currentTime = endTime - startTime;
        // System.out.println("Tempo: " + currentTime);

        // backtrackingStrategy(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
        // greedyStrategy1(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
        // greedyStrategy2(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
        mergeSortAndPrint(arr2, numTrucks);
        // dynamicProgrammingAndPrint(arr, numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
    }

    public static void backtrackingStrategy(int numTrucks, int minRoutes, int maxRoutes, int maxTime,
            double dispersion) {
        boolean stop = false;
        for (int i = minRoutes; i <= maxRoutes; i++) {
            List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(i, 10, dispersion);

            long totalTime = 0;
            List<List<Backtracking>> result = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                long startTime = System.currentTimeMillis();
                result = Backtracking.main(rotas.get(j), numTrucks);
                long endTime = System.currentTimeMillis();
                // System.out.println("Resultado backtracking:");
                // System.out.println(result);
                long currentTime = endTime - startTime;
                totalTime += currentTime;
                if (currentTime > maxTime) {
                    stop = true;
                    break;
                }
            }

            double averageTime = totalTime / 10.0;

            if (averageTime > maxTime || stop) {
                System.out.println("Tempo de execução máximo atingido no backtracking para " + i + " rotas:");
                break;
            }

            System.out.println("Tempo de execução médio backtracking para " + i + " rotas: " + averageTime);
        }
    }

    public static void routesAverage(int numTrucks, int minRoutes, int maxRoutes, int maxTime, double dispersion) {
        for (int i = minRoutes; i <= maxRoutes; i++) {
            List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(i, 10, dispersion);
            long totalTime = 0;
            for (int j = 0; j < 10; j++) {
                long startTime = System.currentTimeMillis();
                DistribuicaoDeRotas.distribuirRotas(convertIntArrayToList(rotas.get(j)), numTrucks);
                long endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }
            double averageTime = totalTime / 10.0;
            if (averageTime > maxTime) {
                break;
            }
            System.out.println("Tempo médio para " + i + " rotas: " + averageTime + " ms");
        }
    }

    public static List<Integer> convertIntArrayToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }

    public static void greedyStrategy1(int numTrucks, int minRoutes, int maxRoutes, int maxTime, double dispersion) {
        for (int i = minRoutes; i <= minRoutes * 10; i += i) {
            List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(i, 10, dispersion);

            long totalTime = 0;
            List<List<Integer>> result = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                long startTime = System.currentTimeMillis();
                result = GreedyStrategy1.distributeRoutes(rotas.get(j), numTrucks);
                long endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }

            double averageTime = totalTime / 10.0;

            if (averageTime > maxTime) {
                break;
            }

            System.out.println("Resultados guloso1 para " + i + " rotas (Estratégia gulosa 1): " + averageTime);
        }
    }

    public static void greedyStrategy2(int numTrucks, int minRoutes, int maxRoutes, int maxTime, double dispersion) {
        for (int i = minRoutes; i <= minRoutes * 10; i += i) {
            List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(i, 10, dispersion);

            long totalTime = 0;
            List<List<Integer>> result = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                long startTime = System.currentTimeMillis();
                result = GreedyStrategy2.distributeRoutes(rotas.get(j), numTrucks);
                long endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }

            double averageTime = totalTime / 10.0;

            if (averageTime > maxTime) {
                break;
            }

            System.out.println("Resultados guloso2 para " + i + " rotas (Estratégia gulosa 2): " + averageTime);
        }
    }

    public static void mergeSortAndPrint(int[] arr, int numTrucks) {
        int start = 0;
        int end = arr.length - 1;
        int mid1 = start + (end - start) / 3;
        int mid2 = start + 2 * (end - start) / 3;

        long startTime = System.currentTimeMillis();

        MergeSort.mergeSort(arr, start, mid1);
        MergeSort.mergeSort(arr, mid1 + 1, mid2);
        MergeSort.mergeSort(arr, mid2 + 1, end);

        long endTime = System.currentTimeMillis();
        long currentTime = endTime - startTime;

        System.out.println("Resultado Divisão e Conquista: " + currentTime);
        System.out.println("Caminhão 1: " + Arrays.toString(Arrays.copyOfRange(arr, start, mid1 + 1)));
        System.out.println("Caminhão 2: " + Arrays.toString(Arrays.copyOfRange(arr, mid1 + 1, mid2 + 1)));
        System.out.println("Caminhão 3: " + Arrays.toString(Arrays.copyOfRange(arr, mid2 + 1, end + 1)));
    }

    public static void dynamicProgrammingAndPrint(int routes[], int numTrucks, int minRoutes, int maxRoutes,
            int maxTime, double dispersion) {

        List<List<Integer>> rotasCaminhoes = DynamicProgramming.result(numTrucks, routes);

        for (int i = 0; i < numTrucks; i++) {
            System.out.print("Caminhão " + (i + 1) + ": rotas ");
            for (int rota : rotasCaminhoes.get(i)) {
                System.out.print(rota + " ");
            }
            System.out.println("- total " + rotasCaminhoes.get(i).stream().mapToInt(Integer::intValue).sum() + "km");
        }
    }

}
