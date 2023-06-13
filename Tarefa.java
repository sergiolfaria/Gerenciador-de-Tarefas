import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private UUID uuid;
    private String status;
    private String categoria;
    private List<Tarefa> subtarefas;
    private UUID idTarefaPai; // ID da tarefa pai

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        dataCriacao = LocalDate.now();
        uuid = UUID.randomUUID();
        status = "Pendente"; // Inicia como pendente
        categoria = null;
        subtarefas = new ArrayList<>();
    }

    public Tarefa(String titulo, String descricao, LocalDate dataCriacao, LocalDate dataConclusao, UUID uuid, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.uuid = uuid;
        this.categoria = categoria;
        subtarefas = new ArrayList<>();

        status = dataConclusao == null ? "Pendente" : "Concluido"; // Define o status com base na dataConclusao
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public UUID getId() {
        return uuid;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
        status = dataConclusao == null ? "Pendente" : "Concluido"; // Atualiza o status com base na nova dataConclusao
    }

    public String getStatus(Tarefa tarefa) {
      if (tarefa.getDataConclusao() != null) {
         return "Concluída";
      } else {
         return "Pendente";
      }
   }

    public void adicionarSubtarefa(Tarefa subtarefa) {
        subtarefa.setIdTarefaPai(this.uuid); // Define o ID da tarefa pai na subtarefa
        if (!subtarefas.contains(subtarefa)) {
            subtarefas.add(subtarefa);
        }
    }

    public List<Tarefa> getSubtarefas() {
        return subtarefas;
    }

    public UUID getIdTarefaPai() {
        return idTarefaPai;
    }

    public void setIdTarefaPai(UUID idTarefaPai) {
        this.idTarefaPai = idTarefaPai;
    }

    @Override
    public String toString() {
        String str = "Título: " + titulo + "\n";
        str += "Descrição: " + descricao + "\n";
        str += "Data de criação: " + dataCriacao + "\n";
        if (dataConclusao != null) {
            str += "Data de conclusão: " + dataConclusao + "\n";
        }
        str += "Status: " + status + "\n";
        str += "UUID: " + uuid + "\n";
        return str;
    }
}
