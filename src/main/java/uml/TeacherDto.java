package uml;

import lombok.*;

import java.util.Date;


public class TeacherDto extends  Person{

    private String teacherTitle;


    //parametresiz constructor
    public TeacherDto() {
    }

    //parametreli constructor
    public TeacherDto(Long id, String name, String surname, String teacherTitle) {
        super(id, name, surname);
        this.teacherTitle = teacherTitle;
    }

    // Override
    @Override
    public String fullName() {
        return super.fullName();
    }

    //Getter and setter
    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }
}
