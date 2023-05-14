import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Arquivo {
   private String nomeArquivo;
   private List<String> senhas = new ArrayList<>();
   private Map<String, String> arquivoSenhaMap = new HashMap<>();

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
            adicionarSenha();
         } else {
            Utils.imprimirTexto("O arquivo já existe.");
            validarSenha(nomeArquivo);
         }
      } catch (IOException e) {
         Utils.imprimirTexto("Erro ao criar arquivo.");
      }
   }
   

   public void adicionarSenha() {
      Scanner scanner = new Scanner(System.in);
      Utils.imprimirTexto("Digite a senha para o arquivo:");
      String senha = scanner.nextLine();
      arquivoSenhaMap.put(nomeArquivo, senha);
      senhas.add(senha);
  
      Path path = Paths.get("dados", nomeArquivo + "_senha.txt");
      File arquivoSenha = path.toFile();
  
      if (!arquivoSenha.exists()) {
          try {
              arquivoSenha.createNewFile();
          } catch (IOException e) {
              Utils.imprimirTexto("Erro ao criar arquivo de senha.");
              return;
          }
      }
  
      try (FileWriter writer = new FileWriter(arquivoSenha, false)) {
          writer.write(senha + "\n");
          Utils.imprimirTexto("Senha adicionada com sucesso.");
      } catch (IOException e) {
          Utils.imprimirTexto("Erro ao adicionar senha ao arquivo.");
      }
  }
  

     public boolean validarSenha(String nomeArquivo) {
      Scanner scanner = new Scanner(System.in);
      boolean senhaCorreta = false;
      
      do {
         Utils.imprimirTexto("Digite a senha para acessar o arquivo:");
         String senha = scanner.nextLine();
         
         String senhaSalva = arquivoSenhaMap.get(nomeArquivo);
         if (senha.equals(senhaSalva)) {
            Utils.imprimirTexto("Senha correta.");
            senhaCorreta = true;
         } else {
            Utils.imprimirTexto("Senha incorreta.");
         }
      } while (!senhaCorreta);
      
      return true;
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
   private void carregarSenhasDoArquivo() {
   File arquivo = new File(nomeArquivo + "_senha.txt");
   if (arquivo.exists()) {
      try (Scanner scanner = new Scanner(arquivo)) {
         while (scanner.hasNextLine()) {
            String senha = scanner.nextLine();
            arquivoSenhaMap.put(nomeArquivo, senha);
         }
      } catch (IOException e) {
         Utils.imprimirTexto("Erro ao carregar senhas do arquivo.");
      }
   } else {
      Utils.imprimirTexto("Arquivo de senhas não encontrado.");
   }
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
 

