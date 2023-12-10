import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] arr = {35, 34, 33, 23, 21, 32, 35, 19, 26, 42};

        int numTrucks = 3;
        int minRoutes = 6;
        int maxRoutes = 1000;
        int maxTime = 30; // 30 seconds
        double dispersion = 0.5;

        backtrackingStrategy(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);

        // routesAverage(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
        // printBacktrackingResult(backtrackResult);
        // greedyStrategy1(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
        // greedyStrategy2(numTrucks, minRoutes, maxRoutes, maxTime, dispersion);
        // mergeSortAndPrint(arr, numTrucks);
        // dynamicProgrammingAndPrint(arr, numTrucks);

    }

    public static void backtrackingStrategy(int numTrucks, int minRoutes, int maxRoutes, int maxTime, double dispersion) {
        for (int i = minRoutes; i <= maxRoutes; i ++) {
            List<int[]> rotas = GeradorDeProblemas.geracaoDeRotas(i, 10, dispersion);
    
            long totalTime = 0;
            List<List<Integer>> result = new ArrayList<>();
    
            for (int j = 0; j < 10; j++) {
                long startTime = System.currentTimeMillis();
                Backtracking.main(rotas.get(j), numTrucks);
                long endTime = System.currentTimeMillis();
                totalTime += endTime - startTime;
            }
    
            double averageTime = totalTime / 10.0;
    
            if (averageTime > maxTime) {
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

    public static void printBacktrackingResult(List<List<Integer>> result) {
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static void greedyStrategy1(int numTrucks, int minRoutes, int maxRoutes, int maxTime, double dispersion) {
        for (int i = minRoutes; i <= maxRoutes; i ++) {
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
    
            System.out.println("Resultados guloso1 para " + i + " rotas (Estratégia gulosa 1): " + result);
        }
    }
    
    public static void greedyStrategy2(int numTrucks, int minRoutes, int maxRoutes, int maxTime, double dispersion) {
        for (int i = minRoutes; i <= maxRoutes; i ++) {
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
    
            System.out.println("Resultados guloso2 para " + i + " rotas (Estratégia gulosa 2): " + result);
        }
    }

    public static void mergeSortAndPrint(int[] arr, int numTrucks) {
        int start = 0;
        int end = arr.length - 1;
        int mid1 = start + (end - start) / 3;
        int mid2 = start + 2 * (end - start) / 3;
        MergeSort.mergeSort(arr, start, mid1);
        MergeSort.mergeSort(arr, mid1 + 1, mid2);
        MergeSort.mergeSort(arr, mid2 + 1, end);
        System.out.println("Resultado Divisão e Conquista: " + Arrays.toString(arr));
        System.out.println("Caminhão 1: " + Arrays.toString(Arrays.copyOfRange(arr, start, mid1 + 1)));
        System.out.println("Caminhão 2: " + Arrays.toString(Arrays.copyOfRange(arr, mid1 + 1, mid2 + 1)));
        System.out.println("Caminhão 3: " + Arrays.toString(Arrays.copyOfRange(arr, mid2 + 1, end + 1)));
    }

    public static void dynamicProgrammingAndPrint(int[] routes, int numTrucks) {
        int[] arr = Arrays.copyOf(routes, routes.length);
        int start = 0;
        int end = arr.length - 1;
        int mid1 = start + (end - start) / 3;
        int mid2 = start + 2 * (end - start) / 3;
        DynamicProgramming.dynamicProgramming(arr, numTrucks);
        
        System.out.println("O resultado do Algoritmo de Programação Dinâmica: " + Arrays.toString(arr));
        System.out.println("Caminhão 1: " + Arrays.toString(Arrays.copyOfRange(arr, start, mid1 + 1)));
        System.out.println("Caminhão 2: " + Arrays.toString(Arrays.copyOfRange(arr, mid1 + 1, mid2 + 1)));
        System.out.println("Caminhão 3: " + Arrays.toString(Arrays.copyOfRange(arr, mid2 + 1, end + 1)));
    }

}

