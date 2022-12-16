package uml;


abstract public class Person {
    private Long id;
    public String name;
    protected String surname;

    //method
    public String fullName(){
        return name+" "+surname;
    }

    //parametresiz
    public Person() {
    }

    //parametreli
    public Person(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    //Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
