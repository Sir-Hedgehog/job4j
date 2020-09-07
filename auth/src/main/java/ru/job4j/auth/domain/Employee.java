package ru.job4j.auth.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 07.09.2020
 */

@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {
    private static final Logger LOG = LoggerFactory.getLogger(Employee.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "taxpayer_number", nullable = false)
    private String taxpayerNumber;

    @Column(name = "hiring_date", nullable = false)
    private Date hiringDate;

    @OneToMany(mappedBy = "employee")
    private List<Person> persons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    /*public String getHiringDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(hiringDate);
    }

    public void setHiringDate(String inputDate) {
        try {
            this.hiringDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);
        } catch (ParseException exception) {
            LOG.error("Exception of date parsing: ", exception);
        }
    }*/

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id
                && Objects.equals(name, employee.name)
                && Objects.equals(surname, employee.surname)
                && Objects.equals(taxpayerNumber, employee.taxpayerNumber)
                && Objects.equals(hiringDate, employee.hiringDate)
                && Objects.equals(persons, employee.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, taxpayerNumber, hiringDate, persons);
    }

    @Override
    public String toString() {
        return "Employee{"
                    + "id=" + id
                    + ", name='" + name + '\''
                    + ", surname='" + surname + '\''
                    + ", taxpayerNumber='" + taxpayerNumber + '\''
                    + ", hiringDate=" + hiringDate
                    + ", persons=" + persons
                + '}';
    }
}
