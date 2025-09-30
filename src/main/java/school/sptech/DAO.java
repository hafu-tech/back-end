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
        dataSource.setPassword("SPIder3112#");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void salvarLista(List<Escola> escolas) {
        for (Escola e : escolas) {
            jdbcTemplate.update("""
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
        }
    }
}
