package school.sptech;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> arquivosHoje = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss");

        boolean executando = true;

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
                            System.out.print("Insira o nome do arquivo: ");
                            String arquivo = in.nextLine();

                            LocalDateTime dataHora = LocalDateTime.now();
                            String dataHoraFormatada = dataHora.format(formatter);

                            if (!arquivo.contains(".")) {
                                System.out.println(ANSI_RED + dataHoraFormatada + " - ERRO: " + arquivo + " não é um arquivo. " + ANSI_RESET);
                            } else if (arquivo.endsWith(".xlsx") || arquivo.endsWith(".xls") || arquivo.endsWith(".csv")) {
                                System.out.println(ANSI_GREEN + dataHoraFormatada + " - SUCESSO: O arquivo " + arquivo + " foi adicionado com sucesso." + ANSI_RESET);
                                arquivosHoje.add(arquivo + " , " + dataHoraFormatada);
                            } else {
                                System.out.println(ANSI_YELLOW + dataHoraFormatada + " - AVISO: O arquivo " + arquivo + " enviado é um arquivo inválido. " + ANSI_RESET);
                            }

                            while (true) {
                                System.out.print("Quer inserir outro arquivo? (s/n): ");
                                String resposta = in.nextLine();
                                if (resposta.equalsIgnoreCase("n")) {
                                    continuarInserindo = false;
                                    break;
                                } else if (resposta.equalsIgnoreCase("s")) {
                                    break;
                                } else {
                                    System.out.println("Resposta inválida! Digite apenas 's' ou 'n'.");
                                }
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\nArquivos adicionados hoje:");
                        if (arquivosHoje.isEmpty()) {
                            System.out.println("Nenhum arquivo foi adicionado hoje.");
                        } else {
                            for (int i = 0; i < arquivosHoje.size(); i++) {
                                System.out.println("- " + arquivosHoje.get(i));
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
    }
}
