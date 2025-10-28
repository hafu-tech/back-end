package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.List;

public class DAO {
    private JdbcTemplate jdbcTemplate;

    public DAO() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hafutech?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("@Hdyk44339854");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void salvarLista(List<Escola> escolas) {
        int contador = 1;
        for (Escola e : escolas) {
            jdbcTemplate.update(
                """
                INSERT INTO escola
                (ano, id_municipio, id_escola, area, localizacao, rede,
                 inse_qtd_alunos, valor_inse, inse_classificacao2014, inse_classificacao2015)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """,
                    e.getAno(),
                    e.getIdMunicipio(),
                    e.getIdEscola(),
                    e.getArea(),
                    e.getLocalizacao(),
                    e.getRede(),
                    e.getInseQuantidadeAlunos(),
                    e.getValorInse(),
                    e.getInseClassificacao2014(),
                    e.getInseClassificacao2015()
            );
            Log log = new Log("Linha " + contador + " adicionada");
            salvarLogIndividual(log);
            contador++;
        }
    }

    public void salvarLogIndividual(Log log) {
        jdbcTemplate.update("""
            INSERT INTO Log_historico_usuario
            (data_hora, descricao)
            VALUES (?, ?)
        """,
                log.getData(),
                log.getDescricao()
        );
    }
        }

