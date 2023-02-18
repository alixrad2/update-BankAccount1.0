package ir.bankaccount1.model.bl;

import ir.bankaccount1.model.da.PersonDA;
import ir.bankaccount1.model.entity.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonBL {
    public static Person save(Person person) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            if (personDA.findByNC(person.getNc_number()).equals(true)) {
                return null;
            } else {
                return personDA.save(person);
            }
        }
    }

    public static Person edit(Person person){
        try{
            PersonDA personDA= new PersonDA();
            personDA.edit(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;

    }
    public static Person remove(int id){
        try {
            PersonDA personDA= new PersonDA();
           return personDA.remove(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Person> findAll() {
        try  {
            PersonDA personDA = new PersonDA();
                return personDA.findAll();
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
