package pojo;

import java.util.List;

public class GetCourse {

	private Dashboard dashboard;
	private List<Courses> courses;
	private String Instructor;
	private String Services;
	private String Expertise;
	
	
	public String getInstructor() {
		return Instructor;
	}
	public void setInstructor(String instructor) {
		Instructor = instructor;
	}
	public String getServices() {
		return Services;
	}
	public void setServices(String services) {
		Services = services;
	}
	public String getExpertise() {
		return Expertise;
	}
	public void setExpertise(String expertise) {
		Expertise = expertise;
	}
	public pojo.Dashboard getDashboard() {
		return dashboard;
	}
	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	public List<Courses> getCourses() {
		return courses; 

	}
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	
	
}
