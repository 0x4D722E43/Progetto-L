package progettoelle.registrazionevoti.controllers.student;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.omnifaces.util.Messages;
import progettoelle.registrazionevoti.domain.Course;
import progettoelle.registrazionevoti.domain.Student;
import progettoelle.registrazionevoti.repositories.DataLayerException;
import progettoelle.registrazionevoti.services.ServiceInjection;
import progettoelle.registrazionevoti.services.courses.ConcreteEnrollmentsService;

@ManagedBean
@RequestScoped
public class EnrollOnCourse {
    
    private final ConcreteEnrollmentsService service = ServiceInjection.provideEnrollmentService();
    
    private DataModel<Course> availableCourses;

    @ManagedProperty(value="#{studentManager.student}")
    private Student student;
    
    public EnrollOnCourse() {
    
    }
    
    @PostConstruct
    public void initialize() {
        try {
            List<Course> courses = service.getCoursesOnWhichStudentCanEnroll(student);
            availableCourses = new ListDataModel<>(courses);
        } catch (DataLayerException ex) {
            Messages.addGlobalError("Ooops.. Qualcosa non ha funzionato");
        }
    }
    
    public String enrollOnCourse() {
        try {
            Course selectedCourse = availableCourses.getRowData();
            service.enrollOnCourse(student, selectedCourse);
            Messages.addFlashGlobalInfo("Ti sei iscritto al coso di " + selectedCourse.getName());
            return "enroll-on-course?faces-redirect=true";
        } catch (DataLayerException ex) {
            Messages.addGlobalError("Ooops.. Qualcosa non ha funzionato");
            return null;
        }
    }

    public DataModel<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(DataModel<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
