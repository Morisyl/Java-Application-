/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "passenger", catalog = "flights", schema = "")
@NamedQueries({
    @NamedQuery(name = "Passenger.findAll", query = "SELECT p FROM Passenger p"),
    @NamedQuery(name = "Passenger.findByName", query = "SELECT p FROM Passenger p WHERE p.name = :name"),
    @NamedQuery(name = "Passenger.findByPassport", query = "SELECT p FROM Passenger p WHERE p.passport = :passport"),
    @NamedQuery(name = "Passenger.findByPhonenumber", query = "SELECT p FROM Passenger p WHERE p.phonenumber = :phonenumber")})
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Name")
    private String name;
    @Id
    @Basic(optional = false)
    @Column(name = "Passport")
    private String passport;
    @Column(name = "Phone_number")
    private Integer phonenumber;

    public Passenger() {
    }

    public Passenger(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passport != null ? passport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passenger)) {
            return false;
        }
        Passenger other = (Passenger) object;
        if ((this.passport == null && other.passport != null) || (this.passport != null && !this.passport.equals(other.passport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication10.Passenger[ passport=" + passport + " ]";
    }
    
}
