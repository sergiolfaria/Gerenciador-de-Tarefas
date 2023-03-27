import java.time.LocalDate;
import java.util.UUID;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private UUID uuid;

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        dataCriacao = LocalDate.now();
        uuid = UUID.randomUUID();
    }

    public Tarefa(String titulo, String descricao, LocalDate dataCriacao, LocalDate dataConclusao, UUID uuid) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.uuid = uuid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    @Override
    public String toString() {
        String str = "Título: " + titulo + "\n";
        str += "Descrição: " + descricao + "\n";
        str += "Data de criação: " + dataCriacao + "\n";
        if (dataConclusao != null) {
            str += "Data de conclusão: " + dataConclusao + "\n";
        }
        str += "UUID: " + uuid + "\n";
        return str;
    }
}
