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

    public GerenciadorTarefas(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        tarefas = new ArrayList<>();
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

    private void carregarTarefas() {
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

    private void salvarTarefas() {
        File arquivo = new File(nomeArquivo);
        try (FileWriter writer = new FileWriter(arquivo)) {
            for (Tarefa tarefa : tarefas) {
                writer.write(tarefa.getTitulo() + ";" + tarefa.getDescricao() + ";" + tarefa.getDataCriacao() + ";");
                if (tarefa.getDataConclusao() != null) {
                    writer.write(tarefa.getDataConclusao() + ";");
                } else {
                    writer.write("null;");
                }
                writer.write(tarefa.getUuid() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas no arquivo.");
        }
    }

}
