import java.util.List;

public class Test6 {

	public static void main(String[] args) {
		CustomerDao dao = new CustomerDaoImpl();
		Customer c = dao.getCustomerById(6);
		System.out.println(c);
//		Customer c = new Customer("kanna2","kobayashi@gmail.com");
//		dao.update(c);
//		dao.add(c);
//		dao.delete(7);
//		List<Customer> list = dao.query();
//		System.out.println(list);
		
	}

}
