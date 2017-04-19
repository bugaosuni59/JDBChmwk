import java.util.List;

public interface CustomerDao {
	public void add(Customer c);
	public void update(Customer c);
	public void delete(int id);
	public Customer getCustomerById(int id);
	public List<Customer> query();
	// CRUD 创建，检索，更新，删除
}
