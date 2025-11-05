package school.sptech;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss");
        List<String> arquivosHoje = new ArrayList<>();
        boolean executando = true;
        DAO dao = new DAO();

        while (executando) {
            System.out.println("\n=== MENU - HAFUTECH ===");
            System.out.println("1 - Inserir arquivo");
            System.out.println("2 - Ver arquivos adicionados hoje");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            if (in.hasNextInt()) {
                int opcao = in.nextInt();
                in.nextLine();

                switch (opcao) {
                    case 1:
                        boolean continuarInserindo = true;

                        while (continuarInserindo) {
                            System.out.print("Digite o nome do arquivo: ");
                            String nomeArquivo = in.nextLine();

                            try {
                                String caminho = nomeArquivo;
                                S3Reader s3 = new S3Reader();
                                InputStream arquivo = s3.getFileFromS3("s3-raw-hafu", caminho);
                                LocalDateTime dataHora = LocalDateTime.now();
                                String dataHoraFormatada = dataHora.format(formatter);


                                LeitorExcel leitorExcel = new LeitorExcel();
                                List<Escola> escolasExtraidas = leitorExcel.extrairEscolas(nomeArquivo, arquivo);

                                arquivo.close();
                                dao.salvarLista(escolasExtraidas);
                                arquivosHoje.add(nomeArquivo);
                                System.out.println(dataHoraFormatada + ", foram adicionados " + escolasExtraidas.size() + " dados no banco!");

                            } catch (Exception e) {
                                System.out.println(ANSI_RED + "Erro ao processar o arquivo: " + e.getMessage() + ANSI_RESET);

                            }

                            while (true) {
                                System.out.print("Quer inserir outro arquivo? (s/n): ");
                                String resposta = in.nextLine();
                                if (resposta.equalsIgnoreCase("n")) {
                                    continuarInserindo = false;
                                    break;
                                } else if (resposta.equalsIgnoreCase("s")) {
                                    break;
                                }
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\nArquivos adicionados hoje:");
                        if (arquivosHoje.isEmpty()) {
                            System.out.println("Nenhum arquivo foi adicionado hoje.");
                        } else {
                            for (String arquivoHoje : arquivosHoje) {
                                System.out.println("- " + arquivoHoje);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Encerrando o programa...");
                        executando = false;
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Opção inválida! Digite apenas números.");
                in.nextLine();
            }
        }
        in.close();
    }
}
