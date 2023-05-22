import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GerenciadorArquivos {
   private String nomeArquivo;
   private Map<String, String> arquivoSenhaMap = new HashMap<>();
   private Scanner scanner = new Scanner(System.in);

   public void criarArquivo() {
      Utils.imprimirTexto("\nDigite o nome de Usuario:");
      nomeArquivo = scanner.nextLine();
      Path diretorio = Paths.get("dados");

      try {
         Files.createDirectories(diretorio);
      } catch (IOException e) {
         Utils.imprimirTexto("\nErro ao criar diretório.");
         return;
      }
      Path arquivoPath = diretorio.resolve(nomeArquivo + ".txt");
      File arquivo = arquivoPath.toFile();
      try {
         if (arquivo.createNewFile()) {
            Utils.imprimirTexto("\nArquivo criado com sucesso.");
            adicionarSenha();
         } else {
            Utils.imprimirTexto("\nO arquivo já existe.");
            validarSenha(nomeArquivo);
         }
      } catch (IOException e) {
         Utils.imprimirTexto("\nErro ao criar arquivo.");
      }
   }

   public void adicionarSenha() {
      Utils.imprimirTexto("\nDigite a senha para o arquivo:");
      String senha = scanner.nextLine();
      arquivoSenhaMap.put(nomeArquivo, senha);

      Path path = Paths.get("dados", nomeArquivo + "_senha.txt");
      File arquivoSenha = path.toFile();

      if (!arquivoSenha.exists()) {
         try {
            arquivoSenha.createNewFile();
         } catch (IOException e) {
            Utils.imprimirTexto("\nErro ao criar arquivo de senha.");
            return;
         }
      }

      try (FileWriter writer = new FileWriter(arquivoSenha, false)) {
         writer.write(senha + "\n");
         Utils.imprimirTexto("\nSenha adicionada com sucesso.");
      } catch (IOException e) {
         Utils.imprimirTexto("\nErro ao adicionar senha ao arquivo.");
      }
   }

   public boolean validarSenha(String nomeArquivo) {
      boolean senhaCorreta = false;
      carregarSenhasDoArquivo();

      do {
         Utils.imprimirTexto("\nDigite a senha para acessar o arquivo:");
         String senha = scanner.nextLine();

         String senhaSalva = arquivoSenhaMap.get(nomeArquivo);
         if (senha.equals(senhaSalva)) {
            Utils.imprimirTexto("\nSenha correta.");
            senhaCorreta = true;
         } else {
            Utils.imprimirTexto("\nSenha incorreta.");
         }
      } while (!senhaCorreta);

      return true;
   }

   private void carregarSenhasDoArquivo() {
      File diretorio = new File("dados");
      File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith("_senha.txt"));

      if (arquivos != null) {
         for (File arquivo : arquivos) {
            try (Scanner scanner = new Scanner(arquivo)) {
               while (scanner.hasNextLine()) {
                  String senha = scanner.nextLine();
                  String nomeArquivo = arquivo.getName().replace("_senha.txt", "");
                  arquivoSenhaMap.put(nomeArquivo, senha);
               }
            } catch (IOException e) {
               Utils.imprimirTexto("\nErro ao carregar senhas do arquivo.");
            }
         }
      } else {
         Utils.imprimirTexto("\nNenhum arquivo de senhas encontrado.");
      }
   }

   public void selecionarArquivo() {
      File diretorio = new File("dados");
      File[] arquivos = diretorio.listFiles();

      if (arquivos != null && arquivos.length > 0) {
         Utils.imprimirTexto("\nSelecione um arquivo:");
         int contador = 1;
         for (File arquivo : arquivos) {
            Utils.imprimirTexto(contador + "- " + arquivo.getName());
            contador++;
         }

         int opcao = 0;
         do {
            try {
               opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
               Utils.imprimirTexto("\nEntrada inválida. Por favor, insira um número.");
            }
         } while (opcao < 1 || opcao > arquivos.length);

         nomeArquivo = arquivos[opcao - 1].getName().replace(".txt", "");
         Utils.imprimirTexto("Arquivo selecionado: " + nomeArquivo);
      } else {
         Utils.imprimirTexto("\nNão há arquivos na pasta dados.");
      }
   }

   public String getNomeArquivo() {
      return nomeArquivo;
   }

   public void imprimirConteudoDoArquivo(String nome) {
      Path path = Paths.get("dados", nome + ".txt");
      File arquivo = path.toFile();
      if (arquivo.exists()) {
         try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
               System.out.println(scanner.nextLine());
            }
         } catch (IOException e) {
            Utils.imprimirTexto("\nErro ao ler arquivo.");
         }
      } else {
         Utils.imprimirTexto("\nArquivo não encontrado.");
      }
   }
}
