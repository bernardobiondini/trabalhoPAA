import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Backtracking {

    public int position;
    public int value;

    public Backtracking(int position, int value) {
        this.position = position;
        this.value = value;
    }

    public static void main(int[] routes, int trucks) {
        List<List<Backtracking>> backtrackResult = new ArrayList<>();
        int media = 0;
        for(int i = 0; i < routes.length; i++) {
            media += routes[i];
        }
        media /= trucks;
        // System.out.println(media);

        for(int i = 0; i < routes.length; i++) {
            for(int j = i + 1; j < routes.length; j++) {
                Backtracking.backtrack(routes, i, j, 0, media, 5, backtrackResult, new ArrayList<>());
            }
        }

        // List<List<Backtracking>> resultado = encontrarSequenciasNaoRepetidas(backtrackResult, trucks);

        for (List<Backtracking> list : backtrackResult) {
            for (Backtracking list2 : list) {
                System.out.print("(" + list2.value + "," + list2.position + ")");
            }
            System.out.println();
        }
    }

    public static void backtrack(int[] routes, int pos, int prox, int sum, int avg, int diff, List<List<Backtracking>> options, List<Backtracking> sub) {
        if(pos == routes.length) return;

        int addition = sum + routes[pos];
        // System.out.println("Addition: " + addition);

        if(addition < (avg - diff)) {
            sub.add(new Backtracking(pos, routes[pos]));
            backtrack(routes, prox, prox + 1, addition, avg, diff, options, sub);
        } else if(addition > (avg + diff)) {
            return;
        } else {
            // System.out.println("Colocando " + routes[pos]);
            sub.add(new Backtracking(pos, routes[pos]));
            options.add(sub);
            return;
        }
    }

    public static List<List<Backtracking>> encontrarSequenciasNaoRepetidas(List<List<Backtracking>> sequencias, int numTrucks) {
        int cont = 0;
        List<List<Backtracking>> resultado = new ArrayList<>();
        Set<Backtracking> tuplasUsadas = new HashSet<>();

        while(resultado.size() < numTrucks && cont < sequencias.size()) {
            resultado.clear();
            tuplasUsadas.clear();
            for (int i = cont; i < sequencias.size(); i++) {

                boolean sequenciaValida = true;

                for (Backtracking b : sequencias.get(i)) {
                    if (containsTupla(b, tuplasUsadas)) {
                        sequenciaValida = false;
                        break;
                    }
                }

                if (sequenciaValida) {
                    resultado.add(sequencias.get(i));
                    tuplasUsadas.addAll(sequencias.get(i));
                }
            }
            cont++;
        }

        return resultado;
    }

    public static boolean containsTupla(Backtracking b, Set<Backtracking> list) {
        for(Backtracking c : list) {
            if (c.value == b.value && c.position == b.position) {
                return true;
            }
        }

        return false;
    }
}
