import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Arquivo {

    private String nomeArquivo;

    public void criarArquivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo:");
        nomeArquivo = scanner.nextLine();
        Path path = Paths.get("dados", nomeArquivo + ".txt");
        File arquivo = path.toFile();
        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado com sucesso.");
            } else {
                System.out.println("O arquivo já existe.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        }
    }
   public void selecionarArquivo() {
       File diretorio = new File("dados");
       File[] arquivos = diretorio.listFiles();
   
       if (arquivos != null && arquivos.length > 0) {
           System.out.println("Selecione um arquivo:");
           int contador = 1;
           for (File arquivo : arquivos) {
               System.out.println(contador + "- " + arquivo.getName());
               contador++;
           }
   
           Scanner scanner = new Scanner(System.in);
           int opcao = scanner.nextInt();
           if (opcao >= 1 && opcao <= arquivos.length) {
               nomeArquivo = arquivos[opcao - 1].getName();
               System.out.println("Arquivo selecionado: " + nomeArquivo);
           } else {
               System.out.println("Opção inválida.");
           }
       } else {
           System.out.println("Não há arquivos na pasta dados.");
       }
   }
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void verArquivo(String nome) {
        Path path = Paths.get("dados", nome + ".txt");
        File arquivo = path.toFile();
        if (arquivo.exists()) {
            try (Scanner scanner = new Scanner(arquivo)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo.");
            }
        } else {
            System.out.println("Arquivo não encontrado.");
        }
    }

}
