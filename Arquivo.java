import java.util.*;
import java.io.File;
import java.io.IOException;

class ReceberNome { 
   public void criarArquivo(){
      Scanner usuario = new Scanner(System.in);
      System.out.print("Qual seu nome?");   
      String nome = usuario.nextLine();

      File arquivo = new File("C:\\Users\\META-446\\Documents\\EXTRATO DATACORP\\" + nome + ".txt");

      try {
         if (arquivo.createNewFile()) {
            System.out.println("Arquivo criado: " + arquivo.getName());
         } else {
            System.out.println("O arquivo já existe.");
            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
               String linha = leitor.nextLine();
               System.out.println(linha);
         }
         leitor.close();
        }
      } catch (IOException e) {
         System.out.println("Ocorreu um erro ao criar o arquivo.");
         e.printStackTrace();
      }    
   }
}