package de.spring_buch.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.TopLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import de.spring_buch.businessprocess.BestellungBusinessProcess;
import de.spring_buch.businessprocess.IBestellungBusinessProcess;
import de.spring_buch.dao.IBestellungDAO;
import de.spring_buch.dao.IKundeDAO;
import de.spring_buch.dao.IWareDAO;
import de.spring_buch.jpadao.BestellungDAO;
import de.spring_buch.jpadao.KundeDAO;
import de.spring_buch.jpadao.WareDAO;

@Configuration
public class JavaConfiguration {

	@Value("${driver}")
	String driver;

	@Value("${url}")
	String url;

	@Value("${user}")
	private String user;

	@Value("${password}")
	private String password;
	
	@Autowired
	private IWareDAO wareDAO;

	@Bean(destroyMethod = "close")
	protected DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	protected EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceUnitName("SpringBuchJavaConfig");
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getObject();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setDatabase(Database.HSQL);
		return jpaVendorAdapter;
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	@Bean
	public IKundeDAO kundeDAO() {
		return new KundeDAO();
	}

	@Bean
	public IBestellungDAO bestellungDAO() {
		return new BestellungDAO();
	}

	@Bean
	public IBestellungBusinessProcess bestellung() {
		return new BestellungBusinessProcess(bestellungDAO(), kundeDAO(),
				wareDAO);
	}

}
