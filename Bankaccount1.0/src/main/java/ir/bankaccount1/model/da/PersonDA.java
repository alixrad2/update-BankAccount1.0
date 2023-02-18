package ir.bankaccount1.model.da;

import ir.bankaccount1.model.entity.Person;
import ir.bankaccount1.model.tool.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDA implements AutoCloseable {
    Connection connection;
    PreparedStatement preparedStatement;
    public PersonDA() throws SQLException {
        connection = JDBC.connection();
    }

    private int nextVal() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT person_seq.nextval AS NEXT_ID FROM DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int next_id = resultSet.getInt("NEXT_ID");
        return next_id;
    }

    public Person save(Person person) throws SQLException {
        person.setId(nextVal());
        PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO PERSON (ID,NAME,FAMILY,NC_NUMBER,FATHER_NAME,CC_NUMBER,CREATE_DATE) VALUES (?,?,?,?,?,?,?)");
        preparedStatement.setInt(1,person.getId());
        preparedStatement.setString(2,person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getNc_number());
        preparedStatement.setString(5, person.getFather_name());
        preparedStatement.setString(6, person.getCc_number());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(person.getCreate_date()));
        preparedStatement.execute();
        return person;
    }

    public Person edit(Person person) throws SQLException {
        Connection connection= JDBC.connection();
        PreparedStatement preparedStatement= connection.prepareStatement("UPDATE PERSON SET cc_number=? WHERE ID=?");
        preparedStatement.setString(1,person.getCc_number());
        preparedStatement.setInt(2,person.getId());
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
        return person;
    }

    public Person remove(int id) throws SQLException {
        Person person=findById(id);
        System.out.println("DA-REMOVE "+id);

        if (person != null) {
            preparedStatement = connection.prepareStatement("DELETE FROM PERSON WHERE ID=?");
            preparedStatement.setInt(1, id);
            System.out.println("DA-REMOVE "+id);
            preparedStatement.execute();
        }else{
            System.out.println("[DEBUG] THIS PERSON dosn't EXIST");
        }
        return person;
    }

    public Person findById(int id) throws SQLException {
        Person person=null;
        Connection connection= JDBC.connection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet= preparedStatement.executeQuery();
        while (resultSet.next()) {
            person= new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("family"),
                    resultSet.getString("nc_number"),
                    resultSet.getString("father_name"),
                    resultSet.getString("cc_number"),
                    resultSet.getTimestamp("create_date").toLocalDateTime()
            );
        }
        return person;
    }



//    This method is for Login page
    public Boolean findNameAndNc(String name,String nc_number) throws SQLException {
        Person person=null;
        Connection connection= JDBC.connection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE NAME=? AND NC_NUMBER=?");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,nc_number);
        ResultSet resultSet=preparedStatement.executeQuery();

        while (resultSet.next()) {
            person= new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("family"),
                    resultSet.getString("nc_number"),
                    resultSet.getString("father_name"),
                    resultSet.getString("cc_number"),
                    resultSet.getTimestamp("create_date").toLocalDateTime()
            );
        }
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
        if (person!=null){
            return true;
        }else{
            return false;
        }
    }
//    This method is for BL
    public Boolean findByNC(String nc_number) throws SQLException {
        Person person = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE NC_NUMBER=?");
        preparedStatement.setString(1,nc_number);
        ResultSet resultSet= preparedStatement.executeQuery();

        while (resultSet.next()) {
            person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("family"),
                    resultSet.getString("nc_number"),
                    resultSet.getString("father_name"),
                    resultSet.getString("cc_number"),
                    resultSet.getTimestamp("create_date").toLocalDateTime()
            );
        }
        if (person!=null){
            return true;
        }else{
            return false;
        }
    }

//      This method is for show Person info in userPage.jsp
    public Person findByNC_info(String nc_number) throws SQLException {
        Person person = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE NC_NUMBER=?");
        preparedStatement.setString(1,nc_number);
        ResultSet resultSet= preparedStatement.executeQuery();

        while (resultSet.next()) {
            person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("family"),
                    resultSet.getString("nc_number"),
                    resultSet.getString("father_name"),
                    resultSet.getString("cc_number"),
                    resultSet.getTimestamp("create_date").toLocalDateTime()
            );
        }
        return person;
    }


    public List<Person> findAll() throws SQLException {
        Person person=null;
        Connection connection= JDBC.connection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM PERSON");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while(resultSet.next()){
            person= new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("family"),
                    resultSet.getString("nc_number"),
                    resultSet.getString("father_name"),
                    resultSet.getString("cc_number"),
                    resultSet.getTimestamp("create_date").toLocalDateTime()
            );
            personList.add(person);
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
