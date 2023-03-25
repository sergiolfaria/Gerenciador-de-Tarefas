import java.util.Date;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Tarefa {
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    
    private String status;

   public Tarefa(String titulo, String descricao ) {
    this.titulo = titulo;
    this.descricao = descricao;
}

   public String getTitulo() {
       String titulo = Utils.lerTexto("Digite um título:");
       return titulo;
   }
   
   public String getDescricao() {
       String descricao = Utils.lerTexto("Digite uma descrição:");
       return descricao;
   }
   
   public String getData() {
       Date dataAtual = new Date();
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       String dataFormatada = dateFormat.format(dataAtual);
       System.out.println("Data atual: " + dataFormatada);
       return dataFormatada;
   }
   
 
    
}
