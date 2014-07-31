package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Lente;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class LenteDAO {
	private Session session;



	public LenteDAO(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	//METODO QUE LISTA OS AGENDAMENTOS DO DIA
	@SuppressWarnings("unchecked")
	public List<Lente> listarLentes(int idCliente) throws HibernateException {

		List<Lente> lentes = new ArrayList<Lente>();
		try {
			Query query = session.createQuery("from Lente lt where lt.cliente = :sen order by lt.data desc");
			query.setInteger("sen", idCliente);
			lentes = query.list();
			Collections.sort(lentes);

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();	
		}
		return lentes;

	}



}