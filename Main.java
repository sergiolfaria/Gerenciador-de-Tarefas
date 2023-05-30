import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorArquivos arquivo = new GerenciadorArquivos();
        Menu menu = new Menu();
        String nomeArquivo = "";

        // Solicita o nome do arquivo de dados ao usuário
        arquivo.criarArquivo();
        nomeArquivo = arquivo.getNomeArquivo();

        // Cria o gerenciador de tarefas a partir do nome do arquivo fornecido
        GerenciadorTarefas gerenciador = new GerenciadorTarefas(nomeArquivo);

        int opcaoTarefa = 1;
        while (opcaoTarefa != 0) {
            menu.exibirMenuTarefas(); // Apresenta as opções de operação disponíveis
            opcaoTarefa = scanner.nextInt(); // Lê a opção escolhida pelo usuário
            scanner.nextLine(); // Consome a quebra de linha

            if (opcaoTarefa == 1) {
                // Solicita o título e a descrição da tarefa ao usuário
                String titulo = Utils.lerTexto("\nDigite o título da tarefa:");
                String descricao = Utils.lerTexto("Digite a descrição da tarefa:");

                String respostaCategoria = Utils.lerTexto("Deseja adicionar uma categoria à tarefa? (S/N)");
                String categoria = null;

                if (respostaCategoria.equalsIgnoreCase("S")) {
                    categoria = Utils.lerTexto("Digite a categoria da tarefa:");
                }

                // Cria uma nova tarefa com os dados fornecidos e a adiciona ao gerenciador
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

                // Informa ao usuário que a tarefa foi adicionada com sucesso
                Utils.imprimirTexto("\nTarefa adicionada com sucesso!");
            } else if (opcaoTarefa == 2) {
                Tarefa tarefaSelecionada = gerenciador.selecionarTarefa();
                if (tarefaSelecionada != null) {
                    gerenciador.concluirTarefa(tarefaSelecionada);
                    Utils.imprimirTexto("\nTarefa concluída com sucesso.");
                } else {
                    Utils.imprimirTexto("\nNenhuma tarefa selecionada.");
                }
            } else if (opcaoTarefa == 3) {
                // Exibe as tarefas pendentes ao usuário
                gerenciador.exibirTarefasPendentes();
            } else if (opcaoTarefa == 4) {
                // Exibe as tarefas concluídas ao usuário
                gerenciador.exibirTarefasConcluidas();
            } else if (opcaoTarefa == 5) {
                // Solicita a categoria do usuário
                String categoria = Utils.lerTexto("Digite a categoria:");
                // Filtra as tarefas pela categoria fornecida
                gerenciador.filtrarTarefasPorCategoria(categoria);
            }else if (opcaoTarefa == 6) {
                // Solicita a palavra a ser buscada
                String palavra = Utils.lerTexto("Digite a palavra a ser buscada:");
                // Busca a palavra nos arquivos TXT
                List<String> ocorrencias = gerenciador.buscarPalavra(palavra);

                // Exibe as ocorrências encontradas
                if (ocorrencias.isEmpty()) {
                    Utils.imprimirTexto("\nA palavra não foi encontrada nos arquivos TXT.");
                } else {
                    Utils.imprimirTexto("\nOcorrências encontradas:");
                    for (String ocorrencia : ocorrencias) {
                        Utils.imprimirTexto(ocorrencia);
                    }
                }
            }else if (opcaoTarefa == 0) {
                // Encerra o programa
                Utils.imprimirTexto("\nSaindo...");
            }else {
                // Informa ao usuário que a opção é inválida
                Utils.imprimirTexto("\nOpção inválida.");
            }
        }
    }
}
