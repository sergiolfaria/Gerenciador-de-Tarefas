import java.util.*;
public class Utils{
   public static Int lerInteiro(perguntaInteiro){
      Scanner teclado = new Scanner(System.in);
      System.out.print(perguntaInteiro);    
      return teclado.nextLine();
  
  
   }
   public static String lerTexto(perguntaTexto){
      Scanner teclado = new Scanner(System.in);
      System.out.print(perguntaTexto);    
      return teclado.nextLine();
   }



}
