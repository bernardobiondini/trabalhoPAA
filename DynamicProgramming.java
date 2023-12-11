import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que implementa um algoritmo de programação dinâmica para otimizar a distribuição de rotas em caminhões.
 */
public class DynamicProgramming {

    /**
     * Utiliza programação dinâmica para distribuir rotas em caminhões, minimizando a diferença total entre as distâncias percorridas.
     *
     * @param numTrucks Número total de caminhões disponíveis.
     * @param arr       Array contendo as distâncias das rotas.
     * @param capacidade Capacidade máxima de cada caminhão (não utilizado neste algoritmo, mas pode ser útil em casos futuros).
     * @return Um array representando as rotas atribuídas ao último caminhão.
     */
    public static List<List<Integer>> result(int numTrucks, int[] arr) {
        int n = arr.length;

        // Inicialize a matriz para armazenar a soma acumulativa das distâncias
        int[][] dp = new int[n + 1][numTrucks + 1];

        // Inicialize a matriz para rastrear as escolhas feitas
        int[][] choices = new int[n + 1][numTrucks + 1];

        // Preenche a matriz dp com os valores iniciais
        for (int i = 1; i <= n; i++) {
            dp[i][1] = sum(arr, 0, i - 1);
        }

        for (int i = 1; i <= numTrucks; i++) {
            dp[1][i] = arr[0];
        }

        // Preenche dinamicamente as matrizes dp e choices
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= numTrucks; j++) {
                int best = Integer.MAX_VALUE;
                int bestIndex = -1;

                // Encontra a melhor divisão para otimizar a distribuição de rotas
                for (int k = 1; k < i; k++) {
                    int cost = Math.max(dp[k][j - 1], sum(arr, k, i - 1));
                    if (cost < best) {
                        best = cost;
                        bestIndex = k;
                    }
                }

                dp[i][j] = best;
                choices[i][j] = bestIndex;
            }
        }

        // Reconstruir as rotas atribuídas aos caminhões
        List<List<Integer>> rotasCaminhoes = new ArrayList<>();
        int i = n;
        for (int j = numTrucks; j > 0; j--) {
            int startIndex = choices[i][j];
            List<Integer> rotaCaminhao = new ArrayList<>();
            for (int k = startIndex; k < i; k++) {
                rotaCaminhao.add(arr[k]);
            }
            rotasCaminhoes.add(rotaCaminhao);
            i = startIndex;
        }

        return rotasCaminhoes;
    }

    /**
     * Calcula a soma dos elementos em um intervalo específico do array.
     *
     * @param arr   O array de inteiros.
     * @param start O índice de início do intervalo.
     * @param end   O índice de fim do intervalo.
     * @return A soma dos elementos no intervalo especificado.
     */
    public static int sum(int[] arr, int start, int end) {
        int soma = 0;
        for (int i = start; i <= end; i++) {
            soma += arr[i];
        }
        return soma;
    }
}