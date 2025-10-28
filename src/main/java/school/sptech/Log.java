package school.sptech;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private String data;
    private String descricao;

    public Log() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataHora = LocalDateTime.now();
        this.data = dataHora.format(formatter);
    }

    public Log(String descricao) {
        this();
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
