import java.util.*;
import java.io.File;
import java.io.IOException;

class ReceberNome { 
   public void criarArquivo(){
      Scanner usuario = new Scanner(System.in);
      System.out.print("Qual seu nome?");   
      String nome = usuario.nextLine();

      File arquivo = new File(nome + ".txt");

      try {
         if (arquivo.createNewFile()) {
            System.out.println("Arquivo criado: " + arquivo.getName());
         } else {
            System.out.println("O arquivo já existe.");
         }
      } catch (IOException e) {
         System.out.println("Ocorreu um erro ao criar o arquivo.");
         e.printStackTrace();
      }    
   }
}