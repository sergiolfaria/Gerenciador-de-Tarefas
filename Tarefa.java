import java.util.Date;

public class Tarefa {
    private String titulo;
    private String descricao;
    /*
    private Date dataCriacao;
    private Date dataConclusao;
    arefa(String titulo, String descricao, Date dataCriacao, Date dataConclusao, String status)
    */
    private String status;

    public Tarefa(String titulo, String descricao, String status) {
        this.titulo = titulo;
        this.descricao = descricao;
         /*
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        */
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo() { 
      String titulo = Utils.lerTexto("digite um titulo");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao() {
        String descricao = Utils.lerTexto("digite um Descricao");
        
    }

   /* public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
   
    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
    
    public void concluirTarefa(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
        this.status = "concluída";
    }
   */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
