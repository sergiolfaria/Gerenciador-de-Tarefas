import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Collections;
import java.util.Comparator;

public class GerenciadorTarefas {
   private List<Tarefa> tarefas;
   private String nomeArquivo;

   public GerenciadorTarefas(String nomeUsuario) {
      this.nomeArquivo = "dados" + File.separator + nomeUsuario + ".txt";
      tarefas = new ArrayList<>();
      carregarTarefasDoArquivo();
   }

   public void salvarTarefas() {
      try (FileWriter writer = new FileWriter(nomeArquivo)) {
         for (Tarefa tarefa : tarefas) {
            String linha = tarefa.getTitulo() + ";" + tarefa.getDescricao() + ";" + tarefa.getDataCriacao() + ";"
                  + tarefa.getDataConclusao() + ";" + tarefa.getId() + ";" + tarefa.getCategoria() + "\n"; // adicione a categoria aqui
            writer.write(linha);
         }
      } catch (IOException e) {
         Utils.imprimirTexto("\nErro ao salvar tarefas no arquivo.");
      }
   }
   public void salvarCategoria(Tarefa tarefa, String novaCategoria) {
      // Verifique se a tarefa já existe na lista
      boolean tarefaExiste = false;
      for (Tarefa t : tarefas) {
         if (t.equals(tarefa)) {
            tarefaExiste = true;
            break;
         }
      }
   
      // Se a tarefa não existir, adicione-a à lista
      if (!tarefaExiste) {
         tarefa.setCategoria(novaCategoria);
         tarefas.add(tarefa);
         Utils.imprimirTexto("\nA tarefa foi adicionada com a nova categoria.");
      }
      // Se a tarefa existir, atualize a categoria
      else {
         tarefa.setCategoria(novaCategoria);
         Utils.imprimirTexto("\nA categoria da tarefa foi atualizada.");
      }
      
      // Salve todas as tarefas no arquivo
      salvarTarefas();
   }


   public void adicionarTarefa(Tarefa tarefa) {
      if (!tarefas.contains(tarefa)) {
         tarefas.add(tarefa);
         salvarTarefas();
      } else {
         Utils.imprimirTexto("\nEssa tarefa já existe na lista.");
      }
   }

   public void concluirTarefa(Tarefa tarefa) {
      tarefa.setDataConclusao(LocalDate.now());
      salvarTarefas();
   }

   public void exibirTarefasPendentes() {
    Utils.imprimirTexto("\nTarefas pendentes:");
    List<Tarefa> tarefasPendentes = new ArrayList<>();
    for (Tarefa tarefa : tarefas) {
        if (tarefa.getDataConclusao() == null) {
            tarefasPendentes.add(tarefa);
            tarefasPendentes.addAll(tarefa.getSubtarefas()); // Adicionar as subtarefas pendentes à lista
        }
    }
    if (tarefasPendentes.isEmpty()) {
        Utils.imprimirTexto("\nNão há tarefas pendentes.");
    } else {
        organizarTarefasPorDataCriacao(tarefasPendentes);
        for (int i = 0; i < tarefasPendentes.size(); i++) {
            Tarefa tarefa = tarefasPendentes.get(i);
            String status = (tarefa.getDataConclusao() == null) ? "Pendente"
                    : "Concluído em " + tarefa.getDataConclusao().toString();
            Utils.imprimirTexto("[" + (i + 1) + "] " + tarefa.getTitulo() + " -> " + tarefa.getDescricao()
                    + "\nStatus: " + status + " (ID: " + tarefa.getId() + ")");
        }
    }
}


   public Tarefa selecionarTarefa() {
      List<Tarefa> tarefasPendentes = new ArrayList<>();
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getDataConclusao() == null) {
            tarefasPendentes.add(tarefa);
         }
      }
      if (tarefasPendentes.isEmpty()) {
         Utils.imprimirTexto("\nNão há tarefas pendentes.");
         return null;
      }
      exibirTarefasPendentes();
      int indice = Utils.lerInt("\nDigite o número da tarefa que deseja concluir:");
      if (indice < 1 || indice > tarefasPendentes.size()) {
         Utils.imprimirTexto("Índice inválido.");
         return null;
      }
      Tarefa tarefa = tarefasPendentes.get(indice - 1);
      tarefasPendentes.remove(tarefa);
      return tarefa;
   }

   public void exibirTarefasConcluidas() {
      Utils.imprimirTexto("\nTarefas concluídas:");
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getDataConclusao() != null) {
            System.out.println(tarefa);
         }
      }
   }

   private void carregarTarefasDoArquivo() {
      File arquivo = new File(nomeArquivo);
      if (arquivo.exists()) {
         try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
               String linha = scanner.nextLine();
               String[] campos = linha.split(";");
               String titulo = campos[0];
               String descricao = campos[1];
               LocalDate dataCriacao = LocalDate.parse(campos[2]);
               LocalDate dataConclusao = null;
               if (!campos[3].equals("null")) {
                  dataConclusao = LocalDate.parse(campos[3]);
               }
               UUID uuid = UUID.fromString(campos[4]);
               String categoria = campos[5]; // adicione a categoria aqui
               Tarefa tarefa = new Tarefa(titulo, descricao, dataCriacao, dataConclusao, uuid, categoria);
               tarefas.add(tarefa);
            }
         } catch (IOException e) {
            Utils.imprimirTexto("\nErro ao carregar tarefas do arquivo.");
         }
      } else {
         Utils.imprimirTexto("\nArquivo de tarefas não encontrado.");
      }
   }
   
   public void organizarTarefasPorDataCriacao(List<Tarefa> lista) {
      Collections.sort(lista, new Comparator<Tarefa>() {
         @Override
         public int compare(Tarefa t1, Tarefa t2) {
            return t1.getDataCriacao().compareTo(t2.getDataCriacao());
         }
      });
   }
   public void filtrarTarefasPorCategoria(String categoria) {
      Utils.imprimirTexto("\nTarefas na categoria '" + categoria + "':");
      boolean encontrouTarefaNaCategoria = false;
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getCategoria() != null && tarefa.getCategoria().equals(categoria)) {
            System.out.println(tarefa);
            encontrouTarefaNaCategoria = true;
         }
      }
      if (!encontrouTarefaNaCategoria) {
         Utils.imprimirTexto("\nNão há tarefas nessa categoria.");
      }
   }
   public void adicionarSubtarefa(Tarefa tarefaPai, Tarefa subtarefa) {
    tarefaPai.adicionarSubtarefa(subtarefa);
    salvarTarefas();
   }
   public void exibirSubtarefas(Tarefa tarefa) {
      List<Tarefa> subtarefas = tarefa.getSubtarefas();
      if (subtarefas.isEmpty()) {
        Utils.imprimirTexto("A tarefa não possui subtarefas.");
      } else {
        Utils.imprimirTexto("Subtarefas da tarefa '" + tarefa.getTitulo() + "':");
        for (int i = 0; i < subtarefas.size(); i++) {
            Tarefa subtarefa = subtarefas.get(i);
            Utils.imprimirTexto("[" + (i + 1) + "] " + subtarefa.getTitulo() + " -> " + subtarefa.getDescricao());
        }
     }
   }
   public void concluirSubtarefa(Tarefa tarefaPai, Tarefa subtarefa) {
    subtarefa.setDataConclusao(LocalDate.now());
    salvarTarefas();
   }
   

}