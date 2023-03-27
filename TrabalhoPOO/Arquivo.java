import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class Arquivo {
    public void criarArquivo() {
       
        String nome = getNomeArquivo();

        Path currentPath = Paths.get("");
        Path dadosPath = currentPath.resolve("dados");

        File dadosDir = new File(dadosPath.toString());
        if (!dadosDir.exists()) {
            if (dadosDir.mkdirs()) {
                System.out.println("Diretório criado: " + dadosDir.getAbsolutePath());
            } else {
                System.out.println("Não foi possível criar o diretório " + dadosDir.getAbsolutePath());
                return;
            }
        }

        File arquivo = new File(dadosPath.toString() + File.separator + nome + ".txt");

        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado: " + arquivo.getName());
                FileOutputStream fos = new FileOutputStream(arquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
            } else {
                System.out.println("O arquivo já existe.");
                Scanner leitor = new Scanner(arquivo);
                while (leitor.hasNextLine()) {
                    String linha = leitor.nextLine();
                    System.out.println(linha);
                }
                leitor.close();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo.");
            e.printStackTrace();
        }
    }

    public void verArquivo(String nome) {
        Path currentPath = Paths.get("");
        Path dadosPath = currentPath.resolve("dados");

        File arquivo = new File(dadosPath.toString() + File.separator + nome + ".txt");
        if (!arquivo.exists()) {
            System.out.println("O arquivo não existe.");
            return;
        }

        try {
            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                System.out.println(linha);
            }
            leitor.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }
    public String getNomeArquivo() {
      String NomeArquivo = Utils.lerTexto("Digite o nome do arquivo de dados: ");
      return NomeArquivo;
   }
   public void selecionarArquivo() {
    Path currentPath = Paths.get("");
    Path dadosPath = currentPath.resolve("dados");
    File dadosDir = dadosPath.toFile();
    File[] arquivos = dadosDir.listFiles();
    if (arquivos == null || arquivos.length == 0) {
        System.out.println("Não há arquivos na pasta dados.");
        return;
    }
    System.out.println("Selecione um arquivo:");
    for (int i = 0; i < arquivos.length; i++) {
        System.out.println((i + 1) + ") " + arquivos[i].getName());
    }
    Scanner scanner = new Scanner(System.in);
    int escolha = scanner.nextInt();
    if (escolha < 1 || escolha > arquivos.length) {
        System.out.println("Opção inválida.");
        return;
    }
    String nomeArquivo = arquivos[escolha - 1].getName();
    GerenciadorTarefas gerenciador = new GerenciadorTarefas(nomeArquivo);
}


}
