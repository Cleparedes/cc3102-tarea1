package LRs;

public class Lista0 {
    private int out;
    private int in;
    private char enl;
    private Lista0 sig;

    //Constructores
    public Lista0(int out, int in, char enl, Lista0 sig){
        this.out = out;
        this.in = in;
        this.enl = enl;
        this.sig = sig;
    }

    public Lista0(int out, int in, char enl){
        this(out, in, enl, null);
    }

    public Lista0(int out, int in){
        this(out, in, (char) 949, null);
    }

    public Lista0(){
        this(0,0,(char) 949,null);
    }

    //Getters
    public Lista0 siguiente(){
        return sig;
    }

    //Metodos
    public static String aString(Lista0 L){
        if(L == null) return "";
        String s = L.out + " - " + L.enl + " -> " + L.in + "\n" + aString(L.sig);
        return s;
    }

    public void append(Lista0 L){
        if(sig == null) sig = L;
        else sig.append(L);
    }

    public static void aLista(AFND A, Lista0 L){
        if(A.esFinal() || A.visitado()) return;
        A.visitar();
        AFND Ae = A.epsilon();
        if(Ae != null){
            Lista0 L1 = new Lista0(A.getId(), Ae.getId());
            L.append(L1);
            aLista(Ae,L);
        }
        AFND As = A.siguiente();
        if(As != null){
            Lista0 L1 = new Lista0(A.getId(), As.getId(), A.enlace());
            L.append(L1);
            aLista(As,L);
        }
    }
}
