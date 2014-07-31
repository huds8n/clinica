package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Refracao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class RefracaoDAO {
	private Session session;



	public RefracaoDAO(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	//METODO QUE LISTA OS AGENDAMENTOS DO DIA
	@SuppressWarnings("unchecked")
	public List<Refracao> listarRefracao(int idCliente) throws HibernateException {

		List<Refracao> refracoes = new ArrayList<Refracao>();
		try {
			Query query = session.createQuery("from Refracao rf where rf.cliente = :sen order by rf.dta desc");
			query.setInteger("sen", idCliente);
			refracoes = query.list();
			Collections.sort(refracoes);


		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();	
		}
		return refracoes;

	}



}