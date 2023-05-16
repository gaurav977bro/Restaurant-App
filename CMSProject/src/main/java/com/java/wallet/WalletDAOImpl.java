package com.java.wallet;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.java.cms.SessionHelper;

@ManagedBean(name = "wDAO")
@ViewScoped
public class WalletDAOImpl implements WalletDAO {

	@Override
	public List<Wallet> showWallets() {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Wallet.class);

		List<Wallet> wList = cr.list();
		return wList;
	}

	@Override
	public List<Wallet> showWalletsForCustomer(int id) {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Wallet.class);
		cr.add(Restrictions.eq("custId", id));
		List<Wallet> wList = cr.list();
		return wList;
	}

}
