//package domain;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.List;
//
//@Entity
//@Table(name = "User")
//@NamedQueries({
//        @NamedQuery(name = User.FIND_ALL, query = "select b from User b"),
//        @NamedQuery(name = "User.getByUsernameAndPassword", query = "select u from User u where u.email = :email and u.password = :password"),
//        @NamedQuery(name = "User.getByUsernameAndCode", query = "select u from User u where u.email = :email and u.authCode = :authCode")}
//)
//
//public class User implements Serializable{
//
//    public static final String FIND_ALL = "User.FindAll";
//
//    @Id @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @NotNull
//    private String email;
//
//    @NotNull
//    private String password;
//
//    private Long authCode;
//
//    public Long getAuthCode() {
//        return authCode;
//    }
//
//    public void setAuthCode(Long authCode) {
//        this.authCode = authCode;
//    }
//
//
//    public User() {
//    }
//
//    public User(String password, String email) {
//        this.password = password;
//        this.email = email;
//    }
//
//    @Column(name = "id")
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//}
package domain;

import domain.Role;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findOne", query = "select u from User u where u.id = :id"),
        @NamedQuery(name = "User.getAll", query = "select u from User u"),
        @NamedQuery(name = "User.checkcreds", query = "select u from User u where u.UserName = :username and u.PassWd = :password")
}
)
public class User implements Serializable{

    public long getGebruikersiD() {
        return GebruikersiD;
    }

    public void setGebruikersiD(long gebruikersiD) {
        GebruikersiD = gebruikersiD;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWd() {
        return PassWd;
    }

    public void setPassWd(String passWd) {
        PassWd = passWd;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long GebruikersiD;

    @NotNull(message = "Username cannot be null")
    @Column(unique = true)
    private String UserName;

    @NotNull(message = "Password cannot be null")
    private String PassWd;

//    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String Email;

//    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int Age;


    private Role role;


    public String getAuthenticationKey() {
        return AuthenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        AuthenticationKey = authenticationKey;
    }

    private String AuthenticationKey;

//    @OneToMany(mappedBy = "user")
//    private List<Advertentie> advertenties;
//
//    public List<Advertentie> getAdvertenties() {
//        return advertenties;
//    }
//
//    public User setAdvertentie(List<Advertentie> advertenties) {
//        this.advertenties = advertenties;
//        return this;
//    }
    public User() {
    }

    public User(String name, String lastName) {
        this.UserName = name;
        this.PassWd = lastName;
    }

    public User(String name, String lastName, String email, int age, Role role) {
        this.UserName = name;
        this.PassWd = lastName;
        this.Email = email;
        this.Age = age;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "GebruikersiD=" + GebruikersiD +
                ", UserName='" + UserName + '\'' +
                ", PassWd='" + PassWd + '\'' +
                ", Email='" + Email + '\'' +
                ", Age=" + Age +
                ", role=" + role +
                '}';
    }
}
