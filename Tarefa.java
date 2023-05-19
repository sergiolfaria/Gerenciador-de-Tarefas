import java.time.LocalDate;
import java.util.UUID;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private UUID uuid;
    private String status;
    private String categoria;

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        dataCriacao = LocalDate.now();
        uuid = UUID.randomUUID();
        status = "Pendente"; // Inicia como pendente
    }

    public Tarefa(String titulo, String descricao, LocalDate dataCriacao, LocalDate dataConclusao, UUID uuid, categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.uuid = uuid;
        this.categoria = categoria;

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

    public String getStatus() {
        return status;
    }

   

    @Override
    public String toString() {
        String str = "Título: " + titulo + "\n";
        str += "Descrição: " + descricao + "\n";
        str += "Data de criação: " + dataCriacao + "\n";
        if (dataConclusao != null) {
            str += "Data de conclusão: " + dataConclusao + "\n";
        }
        str += "Status: " + status + "\n"; // Adiciona o status à representação em string
        str += "UUID: " + uuid + "\n";
        return str;
    }
}
