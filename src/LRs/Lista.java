package LRs;

public class Lista {

    private boolean[] clausura;
    private AFND[] direcciones;
    private Lista siguiente;

    public Lista(boolean[] C, AFND[] D){
        clausura = C;
        direcciones = D;
        siguiente = null;
    }

    public void append(Lista L){
        if(siguiente == null) siguiente = L;
        else siguiente.append(L);
    }

    public boolean esIgual(boolean[] B0, boolean[] B1){
        int n = B0.length;
        for(int i=0; i<n; i++){
            if (B0[i] != B1[i]) return false;
        }
        return true;
    }

    public void llegada(Lista L, boolean[] B){

    }

    public static Lista aLista(AFND[][] M, int n){
        boolean[] c = new boolean[n];
        Lista L = new Lista(AFND.clausura(M[0][0],c), M[0]);
        for(int i=1; i<n; i++){
            c = new boolean[n];
            Lista nL = new Lista(AFND.clausura(M[i][0],c), M[i]);
            L.append(nL);
        }
        return L;
    }

    public static String aString(Lista L){
        if(L == null) return "";
        return aString(L.direcciones) + '\n' + aString(L.siguiente);
    }

    public static String aString(AFND[] A){
        int n = A.length;
        String s = "";
        for(int i=1; i<n; i++){
            if(A[i] == null) s += (char) 8709 + "|";
            else s += A[i].getId() + "|";
        }
        return s;
    }
}
