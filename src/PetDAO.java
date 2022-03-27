import java.util.*;
import java.util.logging.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;

public class PetDAO {

	public List<Pet> findNamedPetsBySpecies(Session session, String species) {
		List<Pet> results = new ArrayList<Pet>();
		String sql = "Select p from " + Pet.class.getName() + " p "//
				+ " Where p.species = :species and name != null";
		Query<Pet> query = session.createQuery(sql);
		query.setParameter("species", species);
		results = query.getResultList();
		return results;
	}

	public List<Pet> findNamedPetsBySpecies_old(Session session, String species) {

		session.getSessionFactory().openSession();
		EntityManager em = session.getEntityManagerFactory().createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pet> query = builder.createQuery(Pet.class);
		Root<Pet> root = query.from(Pet.class);
		Predicate predicate = builder.conjunction();

		//if(!StringUtils.isEmpty(species)) {
		if(!species.equals(null) && !species.trim().equals("")) {
			predicate = builder.and(predicate, builder.equal(root.get("species"), species));
		}

		query.where(predicate);

		List<Order> orderList = new ArrayList();
		orderList.add(builder.asc(root.get("id")));
		orderList.add(builder.asc(root.get("name")));
		orderList.add(builder.desc(root.get("species")));
		query.orderBy(orderList);

		TypedQuery<Pet> typedQuery = (TypedQuery<Pet>) em.createQuery(query);
		List<Pet> list = typedQuery.getResultList();

		return list;
	}

	@Entity(name = "Pet")
	@Table
	public static class Pet {
		@Id
		public Integer id;
		@Column
		public String name;
		@Column
		public String species;

		public Pet() {}

		public Pet(Integer id, String name, String species) {
			this.id = id;
			this.name = name;
			this.species = species;
		}
	}

	public static void main(String[] args) {
		LogManager logManager = LogManager.getLogManager();
		Logger logger = logManager.getLogger("");
		logger.setLevel(Level.OFF);

		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:h2:mem:db1");
		prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "create");

		SessionFactory sessionFactory = new Configuration().addProperties(prop)
				.addAnnotatedClass(Pet.class).buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Pet dog = new Pet(0, "Lady", "Dog");
		Pet cat = new Pet(1, "Max", "Cat");
		Pet camel = new Pet(2, null, "Camel");

		session.save(dog);
		session.save(cat);
		session.save(camel);
		session.flush();

		PetDAO petDao = new PetDAO();
		List<Pet> pets = petDao.findNamedPetsBySpecies(session, "Cat");
		for(Pet p : pets) {
			System.out.println(p.id);
			System.out.println(p.name);
			System.out.println(p.species);
		}
	}
}