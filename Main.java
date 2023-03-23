import java.util.*;

public class Main {
   
   public static void main(String[] args) {
      String titulo = "";
      String descricao = "" ;
      String status = "";
      
      Arquivo nome = new Arquivo();
      nome.criarArquivo();
      Tarefa guardaTarefa = new Tarefa( titulo, descricao, status);
      guardaTarefa.setTitulo();

   }
}