package uml;

public class StudentDto extends  Person{

    private String lessonLearning;

    //parametresiz constructor
    public StudentDto() {
    }

    //parametreli constructor
    public StudentDto(Long id, String name, String surname, String lessonLearning) {
        super(id, name, surname);
        this.lessonLearning = lessonLearning;
    }
    // Override
    @Override
    public String fullName() {
        return super.fullName();
    }

    //Getter Setter
    public String getLessonLearning() {
        return lessonLearning;
    }

    public void setLessonLearning(String lessonLearning) {
        this.lessonLearning = lessonLearning;
    }
}
