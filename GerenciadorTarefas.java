import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Collections;
import java.util.Comparator;
import java.math.BigDecimal;

public class GerenciadorTarefas {
   private List<Tarefa> tarefas;
   private String nomeArquivo;

   public GerenciadorTarefas(String nomeUsuario) {
      this.nomeArquivo = "dados" + File.separator + nomeUsuario + ".txt";
      tarefas = new ArrayList<>();
      carregarTarefasDoArquivo();
   }

   public void salvarTarefas() {
      try (FileWriter writer = new FileWriter(nomeArquivo)) {
         for (Tarefa tarefa : tarefas) {
            String linha = tarefa.getTitulo() + ";" + tarefa.getDescricao() + ";" + tarefa.getDataCriacao() + ";"
                  + tarefa.getDataConclusao() + ";" + tarefa.getId() + ";" + tarefa.getCategoria() + "\n";
            writer.write(linha);

            for (Tarefa subtarefa : tarefa.getSubtarefas()) {
               String linhaSubtarefa = "SUBTAREFA:" + subtarefa.getTitulo() + ";" + subtarefa.getDescricao() + ";"
                     + subtarefa.getDataCriacao() + ";" + subtarefa.getDataConclusao() + ";" + subtarefa.getId() + ";"
                     + subtarefa.getCategoria() + ";" + subtarefa.getIdTarefaPai() + "\n";
               writer.write(linhaSubtarefa);
            }
         }
      } catch (IOException e) {
         Utils.imprimirTexto("\nErro ao salvar tarefas no arquivo.");
      }
   }

   public void salvarCategoria(Tarefa tarefa, String novaCategoria) {
      boolean tarefaExiste = false;
      for (Tarefa t : tarefas) {
         if (t.equals(tarefa)) {
            tarefaExiste = true;
            break;
         }
      }

      if (!tarefaExiste) {
         tarefa.setCategoria(novaCategoria);
         tarefas.add(tarefa);
         Utils.imprimirTexto("\nA tarefa foi adicionada com a nova categoria.");
      } else {
         tarefa.setCategoria(novaCategoria);
         Utils.imprimirTexto("\nA categoria da tarefa foi atualizada.");
      }
      salvarTarefas();
   }

   public void adicionarTarefa(Tarefa tarefa) {
      if (!tarefas.contains(tarefa)) {
         tarefas.add(tarefa);
         salvarTarefas();
      } else {
         Utils.imprimirTexto("\nEssa tarefa já existe na lista.");
      }
   }

   public void concluirTarefa(Tarefa tarefa) {
      tarefa.setDataConclusao(LocalDate.now());
      List<Tarefa> subtarefas = tarefa.getSubtarefas();
      if (subtarefas != null) {
         for (Tarefa subtarefa : subtarefas) {
            subtarefa.setDataConclusao(LocalDate.now());
         }
      }
      salvarTarefas();
   }

   public void exibirTarefasPendentes() {
      Utils.imprimirTexto("\nTarefas pendentes:");
      int numTarefas = 0;
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getDataConclusao() == null) {
            numTarefas++;
            Utils.imprimirTexto("[" + numTarefas + "] [Tarefa] " + tarefa.getTitulo() + " -> " + tarefa.getDescricao()
                  + "\nStatus: " + tarefa.getStatus(tarefa) + " (ID: " + tarefa.getId() + ")");
            List<Tarefa> subtarefas = tarefa.getSubtarefas();
            if (subtarefas != null) {
               int numSubtarefas = 0;
               for (Tarefa subtarefa : subtarefas) {
                  numSubtarefas++;
                  Utils.imprimirTexto("   [" + numTarefas + "." + numSubtarefas + "] [Subtarefa] "
                        + subtarefa.getTitulo() + " -> " + subtarefa.getDescricao() + "\n   Status: "
                        + subtarefa.getStatus(subtarefa) + " (ID: " + subtarefa.getId() + ")");
               }
            }
         }
      }
      if (numTarefas == 0) {
         Utils.imprimirTexto("\nNão há tarefas pendentes.");
      }
   }

   public void exibirTarefasConcluidas() {
      Utils.imprimirTexto("\nTarefas concluídas:");
      List<Tarefa> tarefasConcluidas = new ArrayList<>();
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getDataConclusao() != null) {
            tarefasConcluidas.add(tarefa);
         }
         List<Tarefa> subtarefas = tarefa.getSubtarefas();
         if (subtarefas != null) {
            for (Tarefa subtarefa : subtarefas) {
               if (subtarefa.getDataConclusao() != null) {
                  tarefasConcluidas.add(subtarefa);
               }
            }
         }
      }
      if (tarefasConcluidas.isEmpty()) {
         Utils.imprimirTexto("\nNão há tarefas concluídas.");
      } else {
         organizarTarefasPorDataCriacao(tarefasConcluidas);

         int numTarefasConcluidas = 0;
         int numSubtarefasConcluidas = 0;

         for (Tarefa tarefa : tarefasConcluidas) {
            String status = "Concluído em " + tarefa.getDataConclusao().toString();

            if (tarefa.getIdTarefaPai() == null) {
               numSubtarefasConcluidas = 0;
               numTarefasConcluidas++;
               Utils.imprimirTexto("[" + numTarefasConcluidas + "] [Tarefa] " + tarefa.getTitulo() + " -> "
                     + tarefa.getDescricao() + "\nStatus: " + status + " (ID: " + tarefa.getId() + ")");
            } else {
               numSubtarefasConcluidas++;
               int indexTarefaPai = tarefasConcluidas.indexOf(tarefa.getIdTarefaPai());
               Utils.imprimirTexto("   [" + numTarefasConcluidas + "." + numSubtarefasConcluidas + "] "
                     + tarefa.getTitulo() + " -> " + tarefa.getDescricao() + "\n   Status: " + status + " (ID: "
                     + tarefa.getId() + ") (ID Tarefa Pai: " + tarefa.getIdTarefaPai() + ")");
            }
         }
      }
   }

   public Tarefa selecionarTarefa() {
      List<Tarefa> tarefasPendentes = new ArrayList<>();
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getDataConclusao() == null) {
            tarefasPendentes.add(tarefa);
         }
      }
      if (tarefasPendentes.isEmpty()) {
         Utils.imprimirTexto("\nNão há tarefas pendentes.");
         return null;
      }
      exibirTarefasPendentes();
      int indice = Utils.lerInt("\nDigite o número da tarefa que deseja concluir:");
      if (indice < 1 || indice > tarefasPendentes.size()) {
         Utils.imprimirTexto("Índice inválido.");
         return null;
      }
      Tarefa tarefa = tarefasPendentes.get(indice - 1);
      tarefasPendentes.remove(tarefa);
      return tarefa;
   }

   private void exibirTarefaESubtarefas(Tarefa tarefa, int nivel) {
      String identacao = "   ".repeat(nivel);
      String status = (tarefa.getDataConclusao() != null) ? "Concluído em " + tarefa.getDataConclusao().toString()
            : "Pendente";
      Utils.imprimirTexto(identacao + "[Tarefa] " + tarefa.getTitulo() + " -> " + tarefa.getDescricao());
      Utils.imprimirTexto(identacao + "Status: " + status + " (ID: " + tarefa.getId() + ")");
      List<Tarefa> subtarefas = tarefa.getSubtarefas();
      if (subtarefas != null) {
         for (Tarefa subtarefa : subtarefas) {
            exibirTarefaESubtarefas(subtarefa, nivel + 1);
         }
      }
   }

   private void carregarTarefasDoArquivo() {
      File arquivo = new File(nomeArquivo);
      if (arquivo.exists()) {
         try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
               String linha = scanner.nextLine();
               if (linha.startsWith("SUBTAREFA:")) {
                  carregarSubTarefasDoArquivo(linha);
               } else {
                  String[] campos = linha.split(";");
                  String titulo = campos[0];
                  String descricao = campos[1];
                  LocalDate dataCriacao = LocalDate.parse(campos[2]);
                  LocalDate dataConclusao = campos[3].equals("null") ? null : LocalDate.parse(campos[3]);
                  UUID uuid = UUID.fromString(campos[4]);
                  String categoria = campos[5];
                  tarefas.add(new Tarefa(titulo, descricao, dataCriacao, dataConclusao, uuid, categoria));
               }
            }
         } catch (IOException e) {
            Utils.imprimirTexto("\nErro ao carregar tarefas do arquivo.");
         }
      } else {
         Utils.imprimirTexto("\nArquivo de tarefas não encontrado.");
      }
   }

   private void carregarSubTarefasDoArquivo(String linha) {
      linha = linha.substring(10);
      String[] campos = linha.split(";");
      String titulo = campos[0];
      String descricao = campos[1];
      LocalDate dataCriacao = LocalDate.parse(campos[2]);
      LocalDate dataConclusao = null;
      if (!campos[3].equals("null")) {
         dataConclusao = LocalDate.parse(campos[3]);
      }
      UUID uuid = UUID.fromString(campos[4]);
      String categoria = campos[5];
      UUID idTarefaPai = UUID.fromString(campos[6]);
      Tarefa subtarefa = new Tarefa(titulo, descricao, dataCriacao, dataConclusao, uuid, categoria);
      subtarefa.setIdTarefaPai(idTarefaPai);
      adicionarSubtarefa(subtarefa);
   }

   public void organizarTarefasPorDataCriacao(List<Tarefa> lista) {
      Collections.sort(lista, new Comparator<Tarefa>() {
         @Override
         public int compare(Tarefa t1, Tarefa t2) {
            return t1.getDataCriacao().compareTo(t2.getDataCriacao());
         }
      });
   }

   public void filtrarTarefasPorCategoria(String categoria) {
      Utils.imprimirTexto("\nTarefas na categoria '" + categoria + "':");
      boolean encontrouTarefaNaCategoria = false;
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getCategoria() != null && tarefa.getCategoria().equals(categoria)) {
            System.out.println("\n" + tarefa);
            exibirSubtarefas(tarefa);
            encontrouTarefaNaCategoria = true;
         }
      }
      if (!encontrouTarefaNaCategoria) {
         Utils.imprimirTexto("\nNão há tarefas nessa categoria.");
      }
   }

   public void adicionarSubtarefa(Tarefa subtarefa) {
      UUID idTarefaPai = subtarefa.getIdTarefaPai();
      for (Tarefa tarefa : tarefas) {
         if (tarefa.getId().equals(idTarefaPai)) {
            tarefa.getSubtarefas().add(subtarefa);
            salvarTarefas();
            return;
         }
      }
      Utils.imprimirTexto("\nErro ao adicionar subtarefa: tarefa pai não encontrada.");
   }

   public void exibirSubtarefas(Tarefa tarefa) {
      List<Tarefa> subtarefas = tarefa.getSubtarefas();
      if (subtarefas.isEmpty()) {
         Utils.imprimirTexto("A tarefa não possui subtarefas.");
      } else {
         Utils.imprimirTexto("Subtarefas da tarefa '" + tarefa.getTitulo() + "':");
         for (int i = 0; i < subtarefas.size(); i++) {
            Tarefa subtarefa = subtarefas.get(i);
            Utils.imprimirTexto("\n[" + (i + 1) + "] " + subtarefa.getTitulo() + " -> " + subtarefa.getDescricao()
                  + "Status: " + subtarefa.getStatus(subtarefa) + "(ID: " + subtarefa.getId() + ")" + "(ID tarefaPai: "
                  + subtarefa.getIdTarefaPai() + ")");
         }
      }
   }

   public void concluirSubtarefa(Tarefa tarefaPai, Tarefa subtarefa) {
      subtarefa.setDataConclusao(LocalDate.now());
      salvarTarefas();
   }

   public List<String> buscarPalavra(String palavra) {
      List<String> ocorrencias = new ArrayList<>();
      buscarPalavraRecursivo(palavra, tarefas, ocorrencias);
      return ocorrencias;
   }

   private void buscarPalavraRecursivo(String palavra, List<Tarefa> tarefas, List<String> ocorrencias) {
      for (Tarefa tarefa : tarefas) {
         String titulo = tarefa.getTitulo();
         String descricao = tarefa.getDescricao();

         if (titulo.contains(palavra) || descricao.contains(palavra)) {
            ocorrencias.add("Título da tarefa: " + titulo);
            ocorrencias.add("Descrição da tarefa: " + descricao);
         }

         List<Tarefa> subtarefas = tarefa.getSubtarefas();
         if (subtarefas != null) {
            buscarPalavraRecursivo(palavra, subtarefas, ocorrencias);
         }
      }
   }

}