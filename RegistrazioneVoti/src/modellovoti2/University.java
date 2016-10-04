/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modellovoti2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Gianluca
 */
public class University{
    private Archive archive;
    private FactoriesManager factManager;
    public University(){
        archive = new Archive();
        factManager = new FactoriesManager(archive);        
    }

    public FactoriesManager getFactManager() {
        return factManager;
    }
    
    public void add(Faculty f){
        archive.add(f);
    }
    public void remove(Faculty f){
        archive.remove(f);
    }
    public Teacher getTeacherByCF(String cf){
        return archive.getTeacherByCF(cf);
    }
    public Student getStudentByCF(String cf){
       return archive.getStudentByCF(cf);
    }

    public ArrayList<Faculty> getFacolties() {
        return archive.getFacolties();
    }
    ArrayList<Student> getStudents() {
        return archive.getStudents();
    }
    ArrayList<Teacher> getTeachers(){
        return archive.getTeachers();
    }

}
