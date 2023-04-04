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
      this.nomeArquivo = "dados" + File.separator + nomeUsuario;
      tarefas = new ArrayList<>();
      carregarTarefasDoArquivo();
   }
   public void salvarTarefas() {
      try (FileWriter writer = new FileWriter(nomeArquivo)) {
         for (Tarefa tarefa : tarefas) {
            String linha = tarefa.getTitulo() + ";" + tarefa.getDescricao() + ";" + tarefa.getDataCriacao() + ";" + tarefa.getDataConclusao() + ";" + tarefa.getId() + "\n";
            writer.write(linha);
         }
      } catch (IOException e) {
         System.out.println("Erro ao salvar tarefas no arquivo.");
      }
   }

   public void adicionarTarefa(Tarefa tarefa) {
      if (!tarefas.contains(tarefa)) {
         tarefas.add(tarefa);
         salvarTarefas();
      } else {
         System.out.println("Essa tarefa já existe na lista.");
      }
   }

    public void concluirTarefa(Tarefa tarefa) {
        tarefa.setDataConclusao(LocalDate.now());
        salvarTarefas();
    }
   
   public void exibirTarefasPendentes() {
    System.out.println("Tarefas pendentes:");
    List<Tarefa> tarefasPendentes = new ArrayList<>();
    for (Tarefa tarefa : tarefas) {
        if (tarefa.getDataConclusao() == null) {
            tarefasPendentes.add(tarefa);
        }
    }
    if (tarefasPendentes.isEmpty()) {
        System.out.println("Não há tarefas pendentes.");
    } else {
        // Ordenar tarefas pendentes por data de criação
        Collections.sort(tarefasPendentes, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                return t1.getDataCriacao().compareTo(t2.getDataCriacao());
            }
        });
        for (int i = 0; i < tarefasPendentes.size(); i++) {
            Tarefa tarefa = tarefasPendentes.get(i);
            System.out.println("[" + (i+1) + "] " + tarefa.getTitulo() + " -> " + tarefa.getDescricao() + " (ID: " + tarefa.getId() + ")");
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
               System.out.println("Não há tarefas pendentes.");
               return null;
           }
           exibirTarefasPendentes();
           int indice = Utils.lerInt("Digite o número da tarefa que deseja concluir:");
           if (indice < 1 || indice > tarefasPendentes.size()) {
               System.out.println("Índice inválido.");
               return null;
           }
           Tarefa tarefa = tarefasPendentes.get(indice - 1);
           tarefasPendentes.remove(tarefa);
           return tarefa;
       }

    public void exibirTarefasConcluidas() {
        System.out.println("Tarefas concluídas:");
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
                     Tarefa tarefa = new Tarefa(titulo, descricao, dataCriacao, dataConclusao, uuid);
                     tarefas.add(tarefa);
                     }
                     } catch (IOException e) {
                     System.out.println("Erro ao carregar tarefas do arquivo.");
                     }
                     } else {
                     System.out.println("Arquivo de tarefas não encontrado.");
      }
   }
}