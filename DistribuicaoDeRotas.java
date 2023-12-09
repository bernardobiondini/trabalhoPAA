import java.util.ArrayList;
import java.util.List;

public class DistribuicaoDeRotas {
    public static int menorDiferenca = Integer.MAX_VALUE;

    public static List<List<Integer>> distribuirRotas(List<Integer> rotas, int numCaminhoes) {
        List<List<Integer>> caminhoes = new ArrayList<>();
        for (int i = 0; i < numCaminhoes; i++) {
            caminhoes.add(new ArrayList<>());
        }
        distribuirRotasAux(rotas, caminhoes, 0);
        return caminhoes;
    }

    public static void distribuirRotasAux(List<Integer> rotas, List<List<Integer>> caminhoes, int caminhaoAtual) {
        if (rotas.isEmpty()) {
            int maxKm = Integer.MIN_VALUE;
            int minKm = Integer.MAX_VALUE;
            for (List<Integer> caminhao : caminhoes) {
                int km = 0;
                for (int rota : caminhao) {
                    km += rota;
                }
                maxKm = Math.max(maxKm, km);
                minKm = Math.min(minKm, km);
            }
            menorDiferenca = Math.min(menorDiferenca, maxKm - minKm);
            return;
        }
        for (int i = 0; i < caminhoes.size(); i++) {
            List<Integer> caminhao = caminhoes.get(i);
            int rota = rotas.get(0);
            if (caminhao.isEmpty() || caminhao.get(caminhao.size() - 1) != rota) {
                int km = 0;
                for (int r : caminhao) {
                    km += r;
                }
                if (km + rota <= menorDiferenca / 3) {
                    caminhao.add(rota);
                    List<Integer> rotasRestantes = new ArrayList<>(rotas);
                    rotasRestantes.remove(0);
                    distribuirRotasAux(rotasRestantes, caminhoes, i);
                    caminhao.remove(caminhao.size() - 1);
                }
            }
        }
    }
}
