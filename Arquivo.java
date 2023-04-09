import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Files;

public class Arquivo {

   private String nomeArquivo;

   public void criarArquivo() {
      Scanner scanner = new Scanner(System.in);
      Utils.imprimirTexto("Digite o nome do arquivo:");
      nomeArquivo = scanner.nextLine();
      Path diretorio = Paths.get("dados");
      
      try {
         Files.createDirectories(diretorio);
      } catch (IOException e) {
         Utils.imprimirTexto("Erro ao criar diretório.");
         return;
      }
      Path arquivoPath = diretorio.resolve(nomeArquivo + ".txt");
      File arquivo = arquivoPath.toFile();
      try {
         if (arquivo.createNewFile()) {
            Utils.imprimirTexto("Arquivo criado com sucesso.");
         } else {
            Utils.imprimirTexto("O arquivo já existe.");
         }
      } catch (IOException e) {
         Utils.imprimirTexto("Erro ao criar arquivo.");
      }
   }
   public void selecionarArquivo() {
      File diretorio = new File("dados");
      File[] arquivos = diretorio.listFiles();
   
      if (arquivos != null && arquivos.length > 0) {
         Utils.imprimirTexto("Selecione um arquivo:");
         int contador = 1;
         for (File arquivo : arquivos) {
            Utils.imprimirTexto(contador + "- " + arquivo.getName());
            contador++;
         }
   
         Scanner scanner = new Scanner(System.in);
         int opcao = scanner.nextInt();
         if (opcao >= 1 && opcao <= arquivos.length) {
            nomeArquivo = arquivos[opcao - 1].getName();
            Utils.imprimirTexto("Arquivo selecionado: " + nomeArquivo);
         } else {
            Utils.imprimirTexto("Opção inválida.");
         }
      } else {
         Utils.imprimirTexto("Não há arquivos na pasta dados.");
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
            Utils.imprimirTexto("Erro ao ler arquivo.");
         }
      } else {
         Utils.imprimirTexto("Arquivo não encontrado.");
      }
   }
}
