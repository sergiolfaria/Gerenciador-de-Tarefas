
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
        this.nomeArquivo = "dados" + File.separator + nomeUsuario + ".txt";
        tarefas = new ArrayList<>();
        carregarTarefasDoArquivo();
    }

 
        public void adicionarTarefa(Tarefa tarefa) {
    if (!tarefas.contains(tarefa)) {
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso.");
    } else {
        System.out.println("Essa tarefa já existe na lista.");
    }


        
        salvarTarefas();
    }

    public enum StatusTarefa {
        PENDENTE,
        CONCLUIDO
    }

    public void concluirTarefa(Tarefa tarefa) {
        tarefa.setDataConclusao(LocalDate.now());
        salvarTarefas();
    }
  

    public void exibirTarefas() {
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            System.out.println("[" + i + "] " + tarefa.getTitulo() + " (ID: " + tarefa.getId() + ")");
        }
    }

    public void exibirTarefasPendentes() {
        System.out.println("Tarefas pendentes:");
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDataConclusao() == null) {
                System.out.println(tarefa);
            }
        }
    }

    public Tarefa selecionarTarefa() {
        // Mostra todas as tarefas e seus números e IDs
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            System.out.printf("%d. %s (ID: %s)\n", i + 1, tarefa.getTitulo(), tarefa.getId());
        }

        // Pede que o usuário selecione uma tarefa pelo número ou ID
        int escolha = Utils.lerInt("Digite o número ou ID da tarefa desejada:");
        if (escolha >= 1 && escolha <= tarefas.size()) {
            return tarefas.get(escolha - 1);
        } else {
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getId().equals(escolha)) {
                    return tarefa;
                }
            }
            return null;
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

private void salvarTarefas() {
    try (FileWriter writer = new FileWriter(nomeArquivo)) {
        for (Tarefa tarefa : tarefas) {
            String linha = tarefa.toString();
            writer.write(linha + System.lineSeparator());
        }
    } catch (IOException e) {
        System.out.println("Erro ao salvar tarefas no arquivo.");
    }
}
}