package ir.iust.computer.ood.evar.model;


import javax.persistence.*;

@Entity
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone")
    private int phone;

    @Column(name = "adderss")
    private String adderss;

    @Column(name = "postalcode")
    private int postalcode;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "token")
    private String token;


    public User() {
    }

    public User(String username,
                String password,
                String name,
                String lastname,
                int phone,
                String adderss,
                int postalcode,
                String uuid,
                String token) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.adderss = adderss;
        this.postalcode = postalcode;
        this.uuid = uuid;
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }
}
