package dao;

import model.Usuario;

import org.hibernate.Session;

public class UsuarioDAO {
	private Session session;



	public UsuarioDAO(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public Usuario login(String usuario, String senha) {
		Usuario func = new Usuario();

		try {
			func = (Usuario) session
					.createQuery(
							"from Usuario f where f.login = :usuario"
									+ " and f.senha = :senha")
					.setParameter("usuario", usuario)
					.setParameter("senha", senha).uniqueResult();
			return func;
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return func;

	}


}