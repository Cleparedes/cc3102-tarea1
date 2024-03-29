package LRs;

public class AFND {

    private int id;
    private int n;
    private boolean f;
    private boolean v;
    private char l;
    private AFND s, e;
    private String pila;

    //Constructores
    public AFND(boolean esFinal, char enlace, AFND siguiente, AFND nEpsilon, String ER, int id){
        this.id = id;
        v = false;
        f = esFinal;
        l = enlace;
        s = siguiente;
        e = nEpsilon;
        pila = ER;
    }

    public AFND(int id, boolean esFinal){
        this(esFinal, (char) 949, null, null, "", id);
    }

    public AFND(String ER){
        this(false, (char) 949, null, null, ER, 0);
    }

    public AFND(int id){
        this(id, false);
    }

    //Getters
    public int getId(){
        return id;
    }

    public boolean esFinal(){
        return f;
    }

    public boolean visitado(){
        return v;
    }

    public char enlace(){
        return l;
    }

    public AFND siguiente(){
        return s;
    }

    public AFND epsilon(){
        return e;
    }

    public int getN(){
        return n;
    }

    //Setters
    public void setN(int c) {
        n = c;
    }

    public void visitar(){
        v = true;
    }

    //Metodos
    public void enlazar(AFND nodo, char enlace){
        s = nodo;
        l = enlace;
    }

    public void enlazar(AFND nodo){
        e = nodo;
    }

    public void thompson(AFND A0, AFND A1){
        char c = pila.charAt(0);
        pila = pila.substring(1);
        if(c == '*'){
            A0.enlazar(A1);
            AFND A2 = new AFND(n);
            n++;
            AFND A3 = new AFND(n);
            n++;
            A0.enlazar(A2, (char) 949);
            A3.enlazar(A1);
            A3.enlazar(A2, (char) 949);
            thompson(A2, A3);
        }
        else{
            if(c == '|'){
                AFND A2 = new AFND(n);
                n++;
                AFND A3 = new AFND(n);
                n++;
                AFND A4 = new AFND(n);
                n++;
                AFND A5 = new AFND(n);
                n++;
                A0.enlazar(A2);
                A0.enlazar(A3, (char) 949);
                A4.enlazar(A1);
                A5.enlazar(A1);
                thompson(A2, A4);
                thompson(A3, A5);
            }
            else{
                if(c == '.'){
                    AFND A2 = new AFND(n);
                    n++;
                    thompson(A0, A2);
                    thompson(A2, A1);
                }
                else A0.enlazar(A1, c);
            }
        }
    }

    public static boolean[] clausura(AFND A, boolean[] B){
        if(A == null) return B;
        B[A.id] = true;
        if(A.e != null){
            B[A.e.id] = true;
            B = clausura(A.e, B);
        }
        if(A.l == (char) 949 && A.s != null){
            B[A.s.id] = true;
            B = clausura(A.s,B);
        }
        return B;
    }

    public static AFND[][] rellenar(AFND A, AFND[][] M){
        if(M[A.id][0] == null) M[A.id][0] = A;
        else return M;
        if(A.l != (char) 949){
            M[A.id][(int) A.l -9] = A.s;
        }
        if(A.e != null) M = rellenar(A.e, M);
        if(A.s != null) M = rellenar(A.s, M);
        return M;
    }

    /*
    public static boolean[][] clausuras(AFND[][] M, int n){
        boolean[][] C = new boolean[n][n];
        for(int i=0; i<n; i++){
            C[i] = clausura(M[i][0], C[i]);
        }
        return C;
    }*/
}
