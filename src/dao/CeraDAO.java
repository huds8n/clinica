package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Ceratometria;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class CeraDAO {
	private Session session;



	public CeraDAO(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	//METODO QUE LISTA OS AGENDAMENTOS DO DIA
	@SuppressWarnings("unchecked")
	public List<Ceratometria> listarCeratometria(int idCliente) throws HibernateException {

		List<Ceratometria> ceratometrias = new ArrayList<Ceratometria>();
		try {
			Query query = session.createQuery("from Ceratometria cr where cr.cliente = :sen order by cr.data desc");
			query.setInteger("sen", idCliente);
			ceratometrias = query.list();
			Collections.sort(ceratometrias);

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();	
		}
		return ceratometrias;

	}



}