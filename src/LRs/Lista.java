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
        return aString(L.direcciones) + '\n' + aString(L.siguiente);
    }

    public static String aString(AFND[] A){
        int n = A.length;
        String s = "";
        for(int i=1; i<n; i++){
            if(A[i] == null) s += (char) 237 + ",";
            else s += A[i].getId() + ",";
        }
        return s;
    }
}
