package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Log {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Insira o arquivo:");
            String arquivo = in.next();

            LocalDateTime dataHora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, 'Dia 'dd 'de' MMMM 'de 'yyyy 'às' HH:mm:ss ");
            String dataHoraFormatada = dataHora.format(formatter);
            if (!arquivo.contains(".")){
                System.out.println(ANSI_RED + arquivo + " não é um arquivo " + dataHoraFormatada + ANSI_RESET);
            }
            else if (arquivo.endsWith(".xlsx") || arquivo.endsWith(".xls") || arquivo.endsWith(".csv")) {
                System.out.println(ANSI_GREEN + "O arquivo " + arquivo + " foi adicionado " + dataHoraFormatada + ANSI_RESET);
            } else {
                System.out.println(ANSI_YELLOW + "O arquivo " + arquivo + " enviado " + dataHoraFormatada + "é um arquivo inválido" + ANSI_RESET);
            }

            System.out.println("Quer inserir outro arquivo? (s/n)");
            String resposta = in.next();

            if (resposta.equals("n")) {
                continuar = false;
                System.out.println("Encerrando programa...");
            }
        }
    }
}
