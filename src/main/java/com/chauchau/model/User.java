package com.chauchau.model;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class User implements Validator {

  private String firstName;
  private String lastName;
  private int age;
  private String email;
  private String number;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    User user = (User) target;
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    int age = user.getAge();
    String email = user.getEmail();
    ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
    ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
    if (firstName.length()>45 || firstName.length()<5){
      errors.rejectValue("firstName", "firstName.length");
    }
    if (lastName.length()>45 || lastName.length()<5){
      errors.rejectValue("lastName", "lastName.length");
    }
    if (age>18){
      errors.rejectValue("age", "age");
    }
    if (!isValid(email)){
      errors.rejectValue("email", "email");
    }

    String number = user.getNumber();
    ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
    if (number.length()>11 || number.length()<10){
      errors.rejectValue("number", "number.length");
    }
    if (!number.startsWith("0")){
      errors.rejectValue("number", "number.startsWith");
    }
    if (!number.matches("(^$|[0-9]*$)")){
      errors.rejectValue("number", "number.matches");
    }
  }

  private  boolean isValid(String email) {
    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    return email.matches(regex);
  }
}