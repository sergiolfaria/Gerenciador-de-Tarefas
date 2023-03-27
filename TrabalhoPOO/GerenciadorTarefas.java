import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GerenciadorTarefas {

    private List<Tarefa> tarefas;
    private String nomeArquivo;
    private Arquivo arquivo;

    public GerenciadorTarefas(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        tarefas = new ArrayList<>();
        arquivo = new Arquivo();
        carregarTarefas();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        salvarTarefas();
    }

    public void concluirTarefa(Tarefa tarefa) {
        tarefa.setDataConclusao(LocalDate.now());
        salvarTarefas();
    }

    public Tarefa buscarTarefaPorTitulo(String titulo) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getTitulo().equals(titulo)) {
                return tarefa;
            }
        }
        return null;
    }

    public void exibirTarefasPendentes() {
        System.out.println("Tarefas pendentes:");
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDataConclusao() == null) {
                System.out.println(tarefa);
            }
        }
    }

    public void exibirTarefasConcluidas() {
        System.out.println("Tarefas concluídas:");
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDataConclusao() != null) {
                System.out.println(tarefa);
            }
        }
    }

    public void criarArquivo() {
        arquivo.criarArquivo();
    }

    public void verArquivo(String nome) {
        arquivo.verArquivo(nome);
    }

  private void carregarTarefas() {
    File arquivo = new File("dados" + File.separator + nomeArquivo);
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

     private void salvarTarefas() {
       try (FileWriter writer = new FileWriter(nomeArquivo)) {
           for (Tarefa tarefa : tarefas) {
               String linha = tarefa.getTitulo() + ";" +
                              tarefa.getDescricao() + ";" +
                              tarefa.getDataCriacao() + ";" +
                              (tarefa.getDataConclusao() != null ? tarefa.getDataConclusao() : "null") + ";" +
                              tarefa.getUuid();
               writer.write(linha + System.lineSeparator());
           }
       } catch (IOException e) {
           System.out.println("Erro ao salvar tarefas no arquivo.");
       }
   }
}