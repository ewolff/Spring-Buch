package businessprocess;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

import businessobjects.Bestellung;
import businessobjects.Einkaufswagen;

public class BestellungTransaktionBusinessProcess extends
		BestellungBusinessProcess implements
		IBestellungTransaktionBusinessProcess {

	public BestellungTransaktionBusinessProcess() {
		super();
	}

	public BestellungTransaktionBusinessProcess(IBestellungDAO bestellungDAO,
			IKundeDAO kundeDAO, IWareDAO wareDAO,
			PlatformTransactionManager transactionManager,
			KreditkartenAutorisierer kreditkartenAuthorisierer) {
		super(bestellungDAO, kundeDAO, wareDAO);
		this.transactionManager = transactionManager;
		this.kreditkartenAuthorisierer = kreditkartenAuthorisierer;
	}

	private KreditkartenAutorisierer kreditkartenAuthorisierer;

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public KreditkartenAutorisierer getKreditkartenAuthorisierer() {
		return kreditkartenAuthorisierer;
	}

	public void setKreditkartenAuthorisierer(
			KreditkartenAutorisierer kreditkartenAuthorisierer) {
		this.kreditkartenAuthorisierer = kreditkartenAuthorisierer;
	}

	public void bestellenKreditkarteTransactionManager(
			Einkaufswagen einkaufswagen, int kreditkartenNummer)
			throws BestellungException {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus transactionStatus = transactionManager
				.getTransaction(def);
		Bestellung bestellung = null;
		try {
			bestellung = bestellungErzeugen(einkaufswagen);
		} catch (BestellungException ex) {
			transactionManager.rollback(transactionStatus);
			throw ex;
		}
		getBestellungDAO().save(bestellung);
		if (kreditkartenAuthorisierer.belasten(kreditkartenNummer, bestellung
				.determineBetrag())) {
			transactionManager.commit(transactionStatus);
		} else {
			transactionManager.rollback(transactionStatus);
		}
	}

	public void bestellenKreditkarteTransactionTemplate(
			final Einkaufswagen einkaufswagen, final int kreditkartenNummer)
			throws BestellungException {
		TransactionTemplate transactionTemplate = new TransactionTemplate(
				transactionManager);
		BestellungException exception = transactionTemplate
				.execute(new TransactionCallback<BestellungException>() {

					public BestellungException doInTransaction(
							TransactionStatus transactionStatus) {
						Bestellung bestellung = null;
						try {
							bestellung = bestellungErzeugen(einkaufswagen);
						} catch (BestellungException ex) {
							return ex;
						}
						getBestellungDAO().save(bestellung);
						if (!kreditkartenAuthorisierer.belasten(
								kreditkartenNummer, bestellung
										.determineBetrag())) {
							transactionStatus.setRollbackOnly();
						}
						return null;
					}
				});
		if (exception != null) {
			throw exception;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class, isolation = Isolation.SERIALIZABLE)
	public void bestellenKreditkarteTransactionAnnotation(
			final Einkaufswagen einkaufswagen, final int kreditkartenNummer)
			throws BestellungException {
		Bestellung bestellung = bestellungErzeugen(einkaufswagen);
		getBestellungDAO().save(bestellung);
		if (!kreditkartenAuthorisierer.belasten(kreditkartenNummer, bestellung
				.determineBetrag())) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}

}
