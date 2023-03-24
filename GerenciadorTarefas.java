import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

class GerenciadorTarefas{


   private static int proximoId = 1;
   private static List<Tarefa> tarefas = new ArrayList<Tarefa>();
   
   public static Tarefa criarTarefa(String titulo, String descricao, Date dataInicio) {
       Tarefa tarefa = new Tarefa(proximoId, titulo, descricao, dataInicio);
       proximoId++;
       tarefas.add(tarefa);
       return tarefa;
   }
   
   public static void concluirTarefa(int id) {
       for (Tarefa t : tarefas) {
           if (t.getId() == id) {
               t.concluir();
               break;
           }
       }
   }
   
   public static List<Tarefa> listarTarefasPendentes() {
       List<Tarefa> pendentes = new ArrayList<Tarefa>();
       for (Tarefa t : tarefas) {
           if (!t.isConcluida()) {
               pendentes.add(t);
           }
       }
       return pendentes;
   }
   
   public static List<Tarefa> listarTarefasConcluidas() {
       List<Tarefa> concluidas = new ArrayList<Tarefa>();
       for (Tarefa t : tarefas) {
           if (t.isConcluida()) {
               concluidas.add(t);
           }
       }
       return concluidas;
   }
   
}