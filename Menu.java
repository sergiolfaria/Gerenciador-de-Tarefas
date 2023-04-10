public class Menu {
   
   public void exibirMenuArquivos() {
      Utils.imprimirTexto("Escolha uma opção:");
      Utils.imprimirTexto("1 - Criar um novo arquivo");
      Utils.imprimirTexto("2 - Selecionar arquivo ");
      Utils.imprimirTexto("0 - prosseguir");
   }
   
   public void exibirMenuTarefas(){
      Utils.imprimirTexto("Escolha uma opção:");
      Utils.imprimirTexto("1 - Criar nova tarefa");
      Utils.imprimirTexto("2 - Concluir tarefa");
      Utils.imprimirTexto("3 - Exibir tarefas pendentes");
      Utils.imprimirTexto("4 - Exibir tarefas concluídas");
      Utils.imprimirTexto("0 - Sair");
   }
}
