package progettoelle.registrazionevoti.controllers.professor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import progettoelle.registrazionevoti.domain.Professor;
import progettoelle.registrazionevoti.repositories.DataLayerException;
import progettoelle.registrazionevoti.services.ServiceInjection;
import progettoelle.registrazionevoti.services.account.UserAccountService;

@ManagedBean
@SessionScoped
public class ProfessorManager {
    
    private final UserAccountService service = ServiceInjection.provideUserAccountService();
    
    private Professor professor;
   
    public ProfessorManager() {
    
    }
    
    @PostConstruct
    public void initializeSession() {
        String email = Faces.getRemoteUser();
        
        try {
            professor = (Professor)service.getUser(email);
        } catch (DataLayerException ex) {
            
        }
    }
    
    public void updateProfessorInfo() {
        try {
            service.updateUserInfo(professor);
            Messages.create("Modifiche salvate!").add("growl");
        } catch (DataLayerException ex) {
            
        }
    }
    
    public String logout() {
        Faces.invalidateSession();
        return "/index?faces-redirect=true";
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
}
