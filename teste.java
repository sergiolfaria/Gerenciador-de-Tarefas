Path currentPath = Paths.get("");
Path dadosPath = currentPath.resolve("dados");
if (!Files.exists(dadosPath)) {
    try {
        Files.createDirectories(dadosPath);
    } catch (IOException e) {
        System.out.println("Erro ao criar a pasta dados: " + e.getMessage());
        return;
    }
}

File arquivo = new File(dadosPath.toString() + File.separator + nome + ".txt");
try {
    if (arquivo.createNewFile()) {
        System.out.println("Arquivo criado: " + arquivo.getName());
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
    System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
    e.printStackTrace();
}
