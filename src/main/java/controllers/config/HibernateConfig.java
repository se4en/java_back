package controllers.config;

import DAO.PaymentDAO;
import DAO.ProjectDAO;
import DAO.RoleDAO;
import DAO.WorkerDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import services.PaymentService;
import services.ProjectService;
import services.RoleService;
import services.WorkerService;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
//@PropertySource("/WEB-INF/database.properties")
public class HibernateConfig {
    /*@Autowired
    private Environment env;

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("current_session_context_class", env.getRequiredProperty("current_session_context_class"));

        return hibernateProperties;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("postgresql.driver"));
        dataSource.setUrl(env.getRequiredProperty("postgresql.localhost") +
                env.getRequiredProperty("postgresql.database"));
        dataSource.setUsername(env.getRequiredProperty("postgresql.user"));
        dataSource.setPassword(env.getRequiredProperty("postgresql.password"));

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("entity");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(hibernateProperties());

        return factoryBean;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
*/
    @Bean(name = "WorkerDAO")
    public WorkerDAO WorkerDAO() {
        return new WorkerDAO();
    }

    @Bean(name = "PaymentDAO")
    public PaymentDAO PaymentDAO() {
        return new PaymentDAO();
    }

    @Bean(name = "RoleDAO")
    public RoleDAO RoleDAO() {
        return new RoleDAO();
    }

    @Bean(name = "ProjectDAO")
    public ProjectDAO ProjectDAO() {
        return new ProjectDAO();
    }

    // -----

    @Bean(name = "WorkerService")
    public WorkerService WorkerService() {
        return new WorkerService();
    }

    @Bean(name = "PaymentService")
    public PaymentService PaymentService() {
        return new PaymentService();
    }

    @Bean(name = "RoleService")
    public RoleService RoleService() {
        return new RoleService();
    }

    @Bean(name = "ProjectService")
    public ProjectService ProjectService() {
        return new ProjectService();
    }
}