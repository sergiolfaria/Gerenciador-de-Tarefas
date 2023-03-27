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
        Scanner usuario = new Scanner(System.in);
        System.out.print("Qual seu nome?");
        String nome = usuario.nextLine();

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
                System.out.print("Insira uma descrição para o arquivo:");
                String descricao = usuario.nextLine();
                ArrayList<String> lista = new ArrayList<String>();
                lista.add(descricao);
                FileOutputStream fos = new FileOutputStream(arquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(lista);
                oos.close();
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
}