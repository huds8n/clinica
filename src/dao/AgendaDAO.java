package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Agenda;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class AgendaDAO {
	private Session session;



	public AgendaDAO(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	//METODO QUE LISTA OS AGENDAMENTOS DO DIA
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Agenda> listarHoje() throws HibernateException {

		Date dataAtual = new Date();
		dataAtual.setHours(0);
		dataAtual.setMinutes(0);
		dataAtual.setSeconds(0);

		Date hoje = new Date();
		hoje.setHours(23);
		hoje.setMinutes(59);
		hoje.setSeconds(59);

		List<Agenda> agendamentos = new ArrayList<Agenda>();
		try {
			Query query = session.createQuery("from Agenda where "
					+ "dataAgenda between :hoje and :hoje1 and status='ATIVO' ");

			query.setDate("hoje", dataAtual);
			query.setDate("hoje1", hoje);
			agendamentos =  query.list();
			return agendamentos; 
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();	
		}
		return null;
	}

	//METODO QUE LISTA OS AGENDAMENTOS DO DIA PASSADO PELO USUARIO
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Agenda> buscarPorData(Date data) throws HibernateException {

		Date data1 = data;
		data1.setHours(0);
		data1.setMinutes(0);
		data1.setSeconds(0);

		Date data2 = data;
		data2.setHours(23);
		data2.setMinutes(59);
		data2.setSeconds(59);

		List<Agenda> agendamentos = new ArrayList<Agenda>();
		try {
			Query query = session.createQuery("from Agenda where "
					+ "dataAgenda between :hoje and :hoje1 and status='ATIVO' ");

			query.setDate("hoje", data1);
			query.setDate("hoje1", data2);
			agendamentos =  query.list();
			return agendamentos; 
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();	
		}
		return null;
	}


}