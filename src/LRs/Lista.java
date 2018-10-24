package LRs;

public class Lista {
    private int out;
    private int in;
    private char enl;
    private Lista sig;

    //Constructores
    public Lista(int out, int in, char enl, Lista sig){
        this.out = out;
        this.in = in;
        this.enl = enl;
        this.sig = sig;
    }

    public Lista(int out, int in, char enl){
        this(out, in, enl, null);
    }

    public Lista(int out, int in){
        this(out, in, (char) 949, null);
    }

    public Lista(){
        this(0,0,(char) 949,null);
    }

    //Getters
    public Lista siguiente(){
        return sig;
    }

    //Metodos
    public static String aString(Lista L){
        if(L == null) return "";
        String s = L.out + " - " + L.enl + " -> " + L.in + "\n" + aString(L.sig);
        return s;
    }

    public void append(Lista L){
        if(sig == null) sig = L;
        else sig.append(L);
    }

    public static void aLista(AFND A, Lista L){
        if(A.esFinal() || A.visitado()) return;
        A.visitar();
        AFND Ae = A.epsilon();
        if(Ae != null){
            Lista L1 = new Lista(A.getId(), Ae.getId());
            L.append(L1);
            aLista(Ae,L);
        }
        AFND As = A.siguiente();
        if(As != null){
            Lista L1 = new Lista(A.getId(), As.getId(), A.enlace());
            L.append(L1);
            aLista(As,L);
        }
    }
}
