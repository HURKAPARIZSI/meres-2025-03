
package realestate;


public class Seller {
    private int id;
    private String name;
    private String phone;
    
    public Seller(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    
    @Override
    public String toString() {
        return name;
    }
}
