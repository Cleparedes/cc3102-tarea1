package LRs;

public class Lista {

    private int id;
    private boolean[] clausura;
    private Lista[] direcciones;
    private Lista siguiente;

    //Getters
    public int getId(){
        return id;
    }

    public Lista siguiente(){
        return siguiente;
    }

    //Metodos
    public Lista(boolean[] C, int id){
        this.id = id;
        clausura = C;
        direcciones = new Lista[113];
        siguiente = null;
    }

    public void append(Lista L){
        if(siguiente == null) siguiente = L;
        else siguiente.append(L);
    }

    public static boolean esIgual(boolean[] B0, boolean[] B1){
        int n = B0.length;
        for(int i=0; i<n; i++){
            if (B0[i] != B1[i]) return false;
        }
        return true;
    }

    public static void llegada(Lista L, boolean[] B, AFND[][] M){
        for(int i=1; i<114; i++){
            Lista Lc = L;
            boolean[] c = L.clausura;
            for(int j=0; j<c.length; j++){
                if(c[j]){
                    if(M[j][i] != null) B[M[j][i].getId()] = true;
                }
            }
            int ct = 0;
            while(Lc != null){
                if(esIgual(Lc.clausura, B)){
                    L.direcciones[i-1] = Lc;
                    break;
                }
                ct = Lc.id;
                Lc = Lc.siguiente;
            }
            if(Lc == null){
                Lista nL = new Lista(B, ct + 1);
                L.append(nL);
                L.direcciones[i-1] = nL;
            }
        }
    }

    public static Lista aLista(AFND[][] M, int n){
        boolean[] c = new boolean[n];
        Lista L = new Lista(AFND.clausura(M[0][0],c), M[0][0].getId());
        for(int i=1; i<n; i++){
            c = new boolean[n];
            Lista nL = new Lista(AFND.clausura(M[i][0],c), M[i][0].getId());
            L.append(nL);
        }
        return L;
    }

    public static String aString(Lista L){
        if(L == null) return "";
        return aString(L.direcciones) + '\n' + aString(L.siguiente);
    }

    public static String aString(Lista[] L){
        int n = L.length;
        String s = "";
        for(int i=1; i<n; i++){
            if(L[i] == null) s += (char) 8709 + "|";
            else s += L[i].getId() + "|";
        }
        return s;
    }
}
