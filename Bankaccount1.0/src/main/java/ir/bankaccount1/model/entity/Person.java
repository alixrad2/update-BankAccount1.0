package ir.bankaccount1.model.entity;

import com.google.gson.Gson;
import lombok.*;

import java.time.LocalDateTime;

//create table person (
// id number primary key, name nvarchar2(20),family nvarchar2(20),nc_number char(10),
//father_name nvarchar2(20),
//cc_number nvarchar2(16),
//create_date timestamp);

//CREATE SEQUENCE PERSON_SEQ START WITH 1 INCREMENT BY 1;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    int id;
    String Name,Family,nc_number,Father_name,cc_number;
    LocalDateTime create_date;

    public Person(int id, String name, String family) {
        this.id = id;
        this.Name = name;
        this.Family = family;
    }

    public Person(String name, String family, String nc_number, String father_name, String cc_number, LocalDateTime create_date) {
        Name = name;
        Family = family;
        this.nc_number = nc_number;
        Father_name = father_name;
        this.cc_number = cc_number;
        this.create_date = create_date;
    }
//  Finds in DA before debug code and append create date
    public Person(int id, String name, String family, String nc_number, String father_name, String cc_number) {
        this.id = id;
        Name = name;
        Family = family;
        this.nc_number = nc_number;
        Father_name = father_name;
        this.cc_number = cc_number;
    }

    public Person(String name, String family, String nc_number, String father_name, String cc_number) {
        Name = name;
        Family = family;
        this.nc_number = nc_number;
        Father_name = father_name;
        this.cc_number = cc_number;
    }

    public Person(int id, String cc_number) {
        this.id = id;
        this.cc_number = cc_number;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
