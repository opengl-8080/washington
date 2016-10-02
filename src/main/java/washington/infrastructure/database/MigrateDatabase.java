package washington.infrastructure.database;

import org.flywaydb.core.Flyway;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MigrateDatabase implements ServletContextListener {

    @Resource(lookup="java:app/washington-ds")
    private DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("migrate database");
        Flyway flyway = new Flyway();

        flyway.setDataSource(this.ds);
        flyway.clean();
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
