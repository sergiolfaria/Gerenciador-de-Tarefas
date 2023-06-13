import java.util.Scanner;
import java.util.List;

public class Main {

   private static final int OPCAO_ADICIONAR_TAREFA = 1;
   private static final int OPCAO_CONCLUIR_TAREFA = 2;
   private static final int OPCAO_EXIBIR_TAREFAS_PENDENTES = 3;
   private static final int OPCAO_EXIBIR_TAREFAS_CONCLUIDAS = 4;
   private static final int OPCAO_FILTRAR_TAREFAS_POR_CATEGORIA = 5;
   private static final int OPCAO_BUSCAR_PALAVRA = 6;
   private static final int OPCAO_SAIR = 0;

   
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
        GerenciadorArquivos arquivo = new GerenciadorArquivos();
        Menu menu = new Menu();
        String nomeArquivo = "";

        arquivo.criarArquivo();
        nomeArquivo = arquivo.getNomeArquivo();

        GerenciadorTarefas gerenciador = new GerenciadorTarefas(nomeArquivo);

        int opcaoTarefa = 1;
        String categoria = null;
        while (opcaoTarefa != OPCAO_SAIR) {
            menu.exibirMenuTarefas();
            while (!scanner.hasNextInt()) {
               scanner.next();
               System.out.println("Opção inválida. Por favor, insira novamente:");
            }
            opcaoTarefa = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoTarefa){
            
               case OPCAO_ADICIONAR_TAREFA:
                  
                  String titulo = Utils.lerTexto("\nDigite o título da tarefa:");
                  String descricao = Utils.lerTexto("Digite a descrição da tarefa:");

                  String respostaCategoria = Utils.lerTexto("Deseja adicionar uma categoria à tarefa? (S/N)");

                  if (respostaCategoria.equalsIgnoreCase("S")) {
                     categoria = Utils.lerTexto("Digite a categoria da tarefa:");
                  }

                  Tarefa tarefa = new Tarefa(titulo, descricao);
                  tarefa.setCategoria(categoria);

                  String respostaSubtarefa = Utils.lerTexto("Deseja adicionar uma subtarefa? (S/N)");
                  if (respostaSubtarefa.equalsIgnoreCase("S")) {
                     boolean adicionarSubtarefa = true;
                     while (adicionarSubtarefa) {
                        String tituloSubtarefa = Utils.lerTexto("Digite o título da subtarefa:");
                        String descricaoSubtarefa = Utils.lerTexto("Digite a descrição da subtarefa:");

                        Tarefa subtarefa = new Tarefa(tituloSubtarefa, descricaoSubtarefa);
                        tarefa.adicionarSubtarefa(subtarefa);

                        String respostaContinuar = Utils.lerTexto("Deseja adicionar mais uma subtarefa? (S/N)");
                        if (!respostaContinuar.equalsIgnoreCase("S")) {
                            adicionarSubtarefa = false;
                        }
                     }
                  }
                  gerenciador.adicionarTarefa(tarefa);
                  Utils.imprimirTexto("\nTarefa adicionada com sucesso!");
                  break;
               
               case OPCAO_CONCLUIR_TAREFA:
                  
                  Tarefa tarefaSelecionada = gerenciador.selecionarTarefa();
                  if (tarefaSelecionada != null) {
                     gerenciador.concluirTarefa(tarefaSelecionada);
                     Utils.imprimirTexto("\nTarefa concluída com sucesso.");
                  } else {
                     Utils.imprimirTexto("\nNenhuma tarefa selecionada.");
                  }
                  break; 
               
               case OPCAO_EXIBIR_TAREFAS_PENDENTES:
                
                  gerenciador.exibirTarefasPendentes();
                  break;
               
               case OPCAO_EXIBIR_TAREFAS_CONCLUIDAS:
                  
                  gerenciador.exibirTarefasConcluidas();
                  break;
                  
               case OPCAO_FILTRAR_TAREFAS_POR_CATEGORIA:
                  
                  categoria = Utils.lerTexto("Digite a categoria:");
                  gerenciador.filtrarTarefasPorCategoria(categoria);
                  break;
            
               case OPCAO_BUSCAR_PALAVRA:
                  
                  String palavra = Utils.lerTexto("Digite a palavra a ser buscada:");
                  List<String> ocorrencias = gerenciador.buscarPalavra(palavra);
                  if (ocorrencias.isEmpty()) {
                     Utils.imprimirTexto("\nA palavra não foi encontrada nos arquivos TXT.");
                  } else {
                     Utils.imprimirTexto("\nOcorrências encontradas:");
                     for (String ocorrencia : ocorrencias) {
                        Utils.imprimirTexto(ocorrencia);
                     }
                  }
                  break;
               
               case OPCAO_SAIR:
                  
                  Utils.imprimirTexto("\nSaindo...");
                  break;
            
               default:
                
                  Utils.imprimirTexto("\nOpção inválida.");
                  break;
            }
        }
    }
}
