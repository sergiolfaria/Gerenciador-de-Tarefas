import java.util.*;

public class Utils{
   
   public static int lerInteiro(String perguntaInteiro){
      Scanner teclado = new Scanner(System.in);
      imprimirTexto(perguntaInteiro);    
      int valorInteiro = teclado.nextInt();
      teclado.nextLine();
      return valorInteiro;
   }
   
   public static String lerTexto(String perguntaTexto){
      Scanner teclado = new Scanner(System.in);
     imprimirTexto(perguntaTexto);    
      return teclado.nextLine();
   }
   
   public static void imprimirTexto(String texto){
      System.out.print(texto);
   }

}
