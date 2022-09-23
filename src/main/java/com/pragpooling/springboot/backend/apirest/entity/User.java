package com.pragpooling.springboot.backend.apirest.entity;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "users1")
public class User implements Serializable {
    private static final long serialVersionUID = -3252165509992082073L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id;
    @NotEmpty
    @Size(min = 4, max = 12)
    @Column(nullable = false)
    private  String firstName;
    @NotEmpty
    @Size(min = 4, max = 12)
    @Column(nullable = false)
    private  String lastName;
    @Size( max = 12)
    @Column(nullable = false)
    private  String telephoneNumber;
    @NotEmpty
    @Size(min = 4, max = 20)
    @Column(nullable = false)
    private  String address;
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private  String email;
    @Size(min = 8, max = 15)
    @NotEmpty
    private  String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
