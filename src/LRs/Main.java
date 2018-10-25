package LRs;

import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String er = "*|.ab..aba";   //Expresion regular
        AFND A0 = new AFND(er);   //Nodo inicial
        AFND A1 = new AFND(1, true);   //Nodo final
        A0.setN(2);
        A0.thompson(A0, A1);
        int n = A0.getN();
        AFND[][] M = AFD.mapa(n);
        M = AFND.rellenar(A0, M);
        boolean[][] C = AFND.clausuras(M, n);

        Lista L = new Lista();
        Lista.aLista(A0,L);
        System.out.println(Lista.aString(L.siguiente()));

        /*
        Scanner s = new Scanner(System.in);
        System.out.println("Nombre del archivo de entrada?");
        String archivo = s.nextLine();
        String er = s.nextLine();   //Expresion regular
        s.close();
        AFND A0 = new AFND(er);   //Nodo inicial
        AFND A1 = new AFND(1, true);   //Nodo final
        A0.setN(2);
        A0.thompson(A0, A1);
        int n = A0.getN();
        */

        /*
        try(InputStream is = new FileInputStream(archivo)){
            int letra = is.read();
            while(letra != -1) {
                //Comprobar
            }
        }
        catch(IOException ex) {
            System.out.println("Error leyendo " + archivo);
        }
        */
    }
}