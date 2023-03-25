import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private List<Tarefa> tarefasPendentes;
    private List<Tarefa> tarefasConcluidas;

    public GerenciadorTarefas() {
        this.tarefasPendentes = new ArrayList<>();
        this.tarefasConcluidas = new ArrayList<>();
    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        this.tarefasPendentes.add(tarefa);
        return tarefa;
    }

    public void concluirTarefa(Tarefa tarefa) {
        tarefa.setConcluida(true);
        tarefa.setDataConclusao(LocalDate.now());
        this.tarefasPendentes.remove(tarefa);
        this.tarefasConcluidas.add(tarefa);
    }

    public List<Tarefa> listarTarefasPendentes() {
        return this.tarefasPendentes;
    }

    public List<Tarefa> listarTarefasConcluidas() {
        return this.tarefasConcluidas;
    }
    
    public Tarefa getTarefa(int id) {
        for (Tarefa tarefa : tarefasPendentes) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }
        return null;
    }
}
