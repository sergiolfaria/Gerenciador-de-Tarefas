import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

class GerenciadorTarefas{


   private static int proximoId = 1;
   private static List<Tarefa> tarefas = new ArrayList<Tarefa>();
   
   public static Tarefa criarTarefa(String titulo, String descricao) {
       Tarefa tarefa = new Tarefa( titulo, descricao);
       int proximoId = 0;
       proximoId++;
       tarefas.add(tarefa);
       return tarefa;
   }
}   
