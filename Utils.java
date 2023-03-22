import java.util.*;
public class Utils{
   
   public static int lerInteiro(String perguntaInteiro){
      Scanner teclado = new Scanner(System.in);
      imprimir(perguntaInteiro);    
      int valorInteiro = teclado.nextInt();
      teclado.nextLine();
      return valorInteiro;
   }
   
   public static String lerTexto(String perguntaTexto){
      Scanner teclado = new Scanner(System.in);
      imprimir(perguntaTexto);    
      return teclado.nextLine();
   }
   
   public static void imprimir(String texto){
      System.out.print(texto);
   }

}
