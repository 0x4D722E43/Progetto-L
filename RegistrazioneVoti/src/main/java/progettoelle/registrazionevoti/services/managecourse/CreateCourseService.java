package progettoelle.registrazionevoti.services.managecourse;

import java.util.List;
import progettoelle.registrazionevoti.domain.Course;
import progettoelle.registrazionevoti.domain.DegreeCourse;
import progettoelle.registrazionevoti.domain.Professor;
import progettoelle.registrazionevoti.repositories.CourseRepository;
import progettoelle.registrazionevoti.repositories.DataLayerException;
import progettoelle.registrazionevoti.repositories.DegreeCourseRepository;
import progettoelle.registrazionevoti.services.BaseService;
import progettoelle.registrazionevoti.services.ValidationException;

public final class CreateCourseService extends BaseService {
    
    private static final String ALREADY_EXISTENT_COURSE = "Esiste già un corso con questo nome";
    
    private final DegreeCourseRepository degreeCourseRepository;
    private final CourseRepository courseRepository;

    public CreateCourseService(DegreeCourseRepository degreeCourseRepository, CourseRepository courseRepository) {
        this.degreeCourseRepository = degreeCourseRepository;
        this.courseRepository = courseRepository;
    }
    
    public List<DegreeCourse> getPossibleDegreeCourses() throws DataLayerException {
        return degreeCourseRepository.findAllDegreeCourses();
    }

    public void createCourse(String name, int credits, Professor professor, DegreeCourse degreeCourse) throws ValidationException, DataLayerException {
        if (courseRepository.findCourseByName(name) != null) throw new ValidationException(ALREADY_EXISTENT_COURSE);
        
        Course course = new Course(name, credits, professor, degreeCourse);
        courseRepository.createCourse(course);
    }

}
