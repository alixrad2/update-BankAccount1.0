package ir.bankaccount1.controller;

import ir.bankaccount1.model.bl.PersonBL;
import ir.bankaccount1.model.da.PersonDA;
import ir.bankaccount1.model.entity.Person;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PersonController {
    private static String getMessage(Exception e) {
        if (e instanceof SQLException) {
            return "Error in Saving person  " + e.getMessage();
        } else {
            return "Error Call Admin  " + e.getMessage();
        }
    }


    public static String save(String name, String family, String nc_number, String father_name, String cc_number, LocalDateTime createDate) {
        try {
            if (!name.isEmpty() && !family.isEmpty() && !father_name.isEmpty()) {
                if (nc_number.length() == 10 & cc_number.length() == 16 || cc_number.length() == 10) {
                    Person person = new Person(name, family, nc_number, father_name, cc_number,createDate);
                    return "PERSON SAVED  "+PersonBL.save(person);
                } else {
                    return ("Invalid nc,cc number / edit it and try again");
                }
            } else {
                return "invalid name , family , father name // check it and try again";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getMessage(e);
        }
    }


    public static String edit(int id, String cc_number) {
        try {
            if (id > 0) {
                if (cc_number.length() == 10 || cc_number.length() == 16) {

                    PersonDA personDA = new PersonDA();
                    Person person = new Person(id, cc_number);
                    return "Person edited  " + personDA.edit(person);

                } else {
                    return "cc_number must be 10 or 16 character";
                }
            }else {
                return ("Invalid ID / edit it and try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getMessage(e);
        }
    }


    public static String remove(int id) {
        try {
            if (id > 0) {
                PersonDA personDA = new PersonDA();
                return "Person deleted  "+personDA.remove(id);
            } else {
                return "Invalid ID / edit it and try again";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getMessage(e);
        }
    }

    public static String findById(int id) {
        try {
            if (id > 0) {
                PersonDA personDA = new PersonDA();
                return "PERSON FIND  " +personDA.findById(id);
            } else {
                return "Invalid ID / edit it and try again";
            }
        } catch (Exception e) {
            return getMessage(e);
        }
    }

    public static String findAll() {
        try {
            if (!PersonBL.findAll().isEmpty()) {
                return String.valueOf(PersonBL.findAll());
            } else {
                return "0 USER FOUND";
            }
        } catch (Exception e) {
            return getMessage(e);
        }
    }

    // This method is for Login page
    public static String findByNameAndNC(String name, String nc_number) {
        try {
            if (!name.isEmpty() & !nc_number.isEmpty()) {
                PersonDA personDA = new PersonDA();
                return String.valueOf(personDA.findNameAndNc(name, nc_number));
            } else {
                return "invalid info // check it and try again";
            }
        } catch (Exception e) {
            return getMessage(e);
        }
    }

    //    This method is for BL class
    public static String findByNC(String nc_number) {
        try {
            if (nc_number.length() < 10) {
                return "Length of Nc must be 10 character ";
            } else {
                PersonDA personDA = new PersonDA();
                String msg = String.valueOf(personDA.findByNC(nc_number));
                return msg;
            }
        } catch (Exception e) {
            return getMessage(e);
        }
    }
//      This method is for show Person info in userPage.jsp
    public static String findByNC_info(String nc_number) {
        try {
            if (nc_number.length() < 10) {
                return "Length of Nc must be 10 character ";
            } else {
                PersonDA personDA = new PersonDA();
                String msg = String.valueOf(personDA.findByNC_info(nc_number));
                return msg;
            }
        } catch (Exception e) {
            return getMessage(e);
        }
    }
}
