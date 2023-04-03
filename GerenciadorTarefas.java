import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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

    public void exibirTarefas() {
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            System.out.println("[" + (i+1) + "] " + tarefa.getTitulo() + " (ID: " + tarefa.getId() + ")");
        }
    }

          public void exibirTarefasPendentes() {
          System.out.println("Tarefas pendentes:");
          for (int i = 0; i < tarefas.size(); i++) {
              Tarefa tarefa = tarefas.get(i);
              if (tarefa.getDataConclusao() == null) {
                  System.out.println("[" + (i+1) + "] " + tarefa.getTitulo() + " (ID: " + tarefa.getId() + ")");
              }
          }
          if (tarefas.stream().noneMatch(tarefa -> tarefa.getDataConclusao() == null)) {
              System.out.println("Não há tarefas pendentes.");
          }
      }    
         public int lerInteiro(String mensagem) {
             Scanner scanner = new Scanner(System.in);
             System.out.print(mensagem);
             while (!scanner.hasNextInt()) {
                 System.out.println("Valor inválido. Digite um número inteiro.");
                 scanner.nextLine();
                 System.out.print(mensagem);
             }
             int numero = scanner.nextInt();
             scanner.nextLine();
             return numero;
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
           int indice = lerInteiro("Digite o número da tarefa que deseja concluir:");
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
