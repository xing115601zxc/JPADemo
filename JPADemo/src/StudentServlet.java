
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	void updateQuery(HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("update Student SET age=19 where sid=103");
		int r = query.executeUpdate();
		if (r > 0)
			response.getWriter().append("update successfully");
		else
			response.getWriter().append("update failed");
		em.getTransaction().commit();
		em.close();
		emf.close();
		// response.getWriter().append("See Tomcat Console Student Data");
	}

	void insertQuery(HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Student s1 = new Student();
		s1.setSid(1001);
		s1.setSname("Max");
		s1.setAge(27);
		Student s2 = new Student();
		s2.setSid(1102);
		s2.setSname("Yen");
		s2.setAge(21);
		em.persist(s1);
		em.persist(s2);
		em.getTransaction().commit();
		em.close();
		emf.close();
		response.getWriter().append("See Tomcat Console Student Data");
	}

	void deleteQuery(HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Student where sid=1102");
		int r = query.executeUpdate();
		if (r > 0)
			System.out.println("delete successfully");
		else
			System.out.println("delete failed");
		em.getTransaction().commit();
		em.close();
		emf.close();
		response.getWriter().append("See Tomcat Console Student Data");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//deleteQuery(response);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select s from Student s ");
		Query q1 = em.createQuery("Select s from Student s where s.age between 22 and 28");
		Query q2 = em.createQuery("Select s from Student s where s.age IN(20,22,23)");
		Query q3 = em.createQuery("Select s from Student s where s.sname like '%a%'");
		Query q4 = em.createQuery("Select s from Student s order by s.age desc");
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) q4.getResultList();
		System.out.print("sid");
		System.out.print("\t sname");
		System.out.println("\t age");
		for (Student s : list) {
			System.out.print(s.getSid());
			System.out.print("\t" + s.getSname());
			System.out.print("\t" + s.getAge());
			System.out.println();
		}

		em.getTransaction().commit();
		em.close();
		emf.close();
		response.getWriter().append("See Tomcat Console Student Data");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
