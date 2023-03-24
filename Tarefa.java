import java.util.Date;

public class Tarefa {
    private String titulo;
    private String descricao;
    /*
    private Date dataCriacao;
    private Date dataConclusao;
    Tarefa(String titulo, String descricao, Date dataCriacao, Date dataConclusao, String status)
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
        String titulo = Utils.lerTexto("digite um titulo");
        return titulo;
    }

    public void setTitulo() { 
      
    }

    public String getDescricao() {
        String descricao = Utils.lerTexto("digite um Descricao");
        return descricao;
    }

    public void setDescricao() {
        
        
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
