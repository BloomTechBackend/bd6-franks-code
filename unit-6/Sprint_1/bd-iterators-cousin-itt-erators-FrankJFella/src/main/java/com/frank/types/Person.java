package com.frank.types;

import java.util.Objects;
import java.util.Random;
/********************************************************************************************
 * Represent a basic person object with an id
 ********************************************************************************************/
public class Person {
//*******************************************************************************
// Instance Variables
//*******************************************************************************
        private int id;
        private String name;

//*******************************************************************************
// Constructors
//*******************************************************************************
        // default ctor in case Java needs one
        public Person() {}

        // default 2-arg ctor - only available to members of the class
        //                      it is not expected a user would know
        //                      the id of a new person since we are assigning it
        private Person(int id, String name) {
                this.id   = id;
                this.name = name;
        }
        // 1-arg ctor - id is randomly generated
        //
        public  Person(String name) {
                Random randomNumberGenerator = new Random();
                this.id   = randomNumberGenerator.nextInt(Integer.MAX_VALUE);
                this.name = name;
        }
//*******************************************************************************
// getters/setters
//*******************************************************************************
        public int getId() { return id; }
        public void setId(int id) {
                this.id = id;
        }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

//*******************************************************************************
// Method Overrides
//*******************************************************************************
        @Override
        public String toString() {
                return  "id=" + id +
                        ", name=" + name;
        }
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Person)) return false;
                Person person = (Person) o;
                return getId() == person.getId() && getName().equals(person.getName());
        }
        @Override
        public int hashCode() {
                return Objects.hash(getId(), getName());
        }
//*******************************************************************************
// Helper and other methods
//*******************************************************************************
public Person updatePersonName( String newName) {
                this.name = newName;
                return this;

}
}
