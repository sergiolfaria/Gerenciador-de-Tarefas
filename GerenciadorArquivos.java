import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class GerenciadorArquivos {
    private String nomeArquivo;
    private Map<String, String> arquivoSenhaMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public String getNomeArquivo() {
        return nomeArquivo;
    }
    
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
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith("_senha.txt"));

        if (arquivos != null && arquivos.length > 0) {
            Utils.imprimirTexto("\nArquivos disponíveis:");
            for (File arquivo : arquivos) {
                String nomeArquivo = arquivo.getName().replace("_senha.txt", "");
                Utils.imprimirTexto(nomeArquivo);
            }

            boolean arquivoSelecionado = false;
            do {
                Utils.imprimirTexto("\nDigite o nome do arquivo desejado:");
                String nomeArquivo = scanner.nextLine();

                for (File arquivo : arquivos) {
                    if (arquivo.getName().replace("_senha.txt", "").equals(nomeArquivo)) {
                        arquivoSelecionado = true;
                        validarSenha(nomeArquivo);
                        break;
                    }
                }

                if (!arquivoSelecionado) {
                    Utils.imprimirTexto("\nArquivo não encontrado. Tente novamente.");
                }
            } while (!arquivoSelecionado);
        } else {
            Utils.imprimirTexto("\nNenhum arquivo disponível.");
        }
    }
}
