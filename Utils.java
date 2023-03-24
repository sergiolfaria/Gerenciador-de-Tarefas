import java.util.*;
import java.util.Date;

public class Utils{
   
   public static int lerInt(String perguntaInteiro){
      Scanner teclado = new Scanner(System.in);
      imprimirTexto(perguntaInteiro);    
      int valorInteiro = teclado.nextInt();
      teclado.nextInt();
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
   public static Date lerData(String perguntaData) {
      Scanner teclado = new Scanner(System.in);
      imprimirTexto(perguntaData);    
      String dataString = teclado.nextLine();
      Date data = null;
      try {
         data = new Date(dataString);
      } catch (IllegalArgumentException e) {
         imprimirTexto("Data inválida. Digite uma data no formato dd/mm/yyyy.\n");
         data = lerData(perguntaData);
      }
      return data;
   }

}
