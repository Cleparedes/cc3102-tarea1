package LRs;

public class AFD {

    private int s, k;
    private int[] F;
    private int[][] delta;

    public AFD(int k, int[] F, int[][] delta){
        s = 0;
        this.k = k;
        this.F = F;
        this.delta = delta;
    }

    public static int[][] enD(int[][] D, char a, char b){
        for(int i=(int) a; i<=(int) b; i++) {
            D[0][i -10] = 1;
        }
        return D;
    }

    public static int[][] diccionario(int k){
        int[][] D = new int[k+1][113];
        D[0][0] = 1;
        D[0][(int) ' ' - 10] = 1;
        D = enD(D, '0', '9');
        D = enD(D, 'A', 'Z');
        D = enD(D, 'a', 'z');
        return D;
    }

    public static AFND[][] mapa(int k){
        AFND[][] M = new AFND[k][114];
        return M;
    }
}
