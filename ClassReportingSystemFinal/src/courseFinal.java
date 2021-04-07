import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class courseFinal {
	private String crn;
	private String cName;
	private String tFname;
	private String tLname;;
	private String days;
	private String time;
	private int seats;
	private int taken;
	private String status;
	public static int instances = 0;
	//ArrayList to keep student objects
	private ArrayList<StudentFinal> studentList;

	 public courseFinal(){
	        instances++;
	        this.cName = "Not Set";
	        this.days = "Not Set";
	    }
	courseFinal(String crn, String cName, String tFname, String tLname, String days, String time, int seats, int taken, String status){
		this.studentList = new ArrayList<StudentFinal>();
		this.crn = crn;
		this.cName = cName;
		this.tFname = tFname;
		this.tLname = tLname;
		this.days = days;
		this.time = time;
		this.seats = seats;
		this.taken = taken;
		this.status = status;
	}
	
	public void getCourseInfo() {
		System.out.printf("%1$-6s%2$-11s%3$-10s%4$-10s%5$-9s%6$-15s%7$-9s%8$-9s%9$s\n",this.crn,this.cName,this.tFname,this.tLname,this.days,this.time,this.seats,this.taken,getStatus());
	}
	
	public void addStudent() {
		this.taken += 1;
	}
	public int getTaken() {
		return taken;
	}
	public String getCrn1() {
		return this.crn;
	}
	public String getCourseName() {
		return this.cName;
	}
	public String getStatus() {
		if(this.taken < seats) {
			return "OPEN";
		}else {
			return "CLOSED";
		}
	}
	// add student objects to course arrayList
	public boolean addStudent(StudentFinal student){
        if (student==null || this.studentList.contains(student)) {
            return false;
        }
        this.studentList.add(student);
        this.taken += 1;
        return true;
    }
	 public void printStudents(){
		    for(StudentFinal s : studentList)
		    	System.out.printf("%1$-12s%2$-11s%3$-13s%4$-11s%5$s\n",s.Sname,s.Smiddle,s.Slast,s.id,s.email);
		    }
	//student class
	public static class StudentFinal{
		private String crn;
		private String Sname;
		private String Smiddle;
		private String Slast;
		private String id;
		private String email;
		
		StudentFinal(String crn, String Sname, String Smiddle, String Slast, String id, String email){
			this.crn = crn;
			this.Sname = Sname;
			this.Smiddle = Smiddle;
			this.Slast = Slast;
			this.id = id;
			this.email = email;
		}
		public String getName() {
			return this.Sname;
		}
		public String getCrn() {
			return this.crn;
		}
		
		public String getInfo() {
			return this.Sname + " " + this.Smiddle + " " + this.Slast + " " + this.id + " " + this.email;
		}
	}
	//Read file and make function return an ArrayList of students with their grades
	public static ArrayList<StudentFinal> readFile(String filename) throws FileNotFoundException{
		
		ArrayList<StudentFinal> students = new ArrayList<StudentFinal>();
		
		File fileNames = new File(filename);
		
		if(fileNames.canRead()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(fileNames);
			while(scanner.hasNext()) {
				String crn = scanner.next();
				String first = scanner.next();
				String middle = scanner.next();
				String last = scanner.next();
				String id = scanner.next();
				String email = scanner.next();
				
				students.add(new StudentFinal(crn,first,middle,last,id,email));

			}

		}
	
		return students;
	}
	
	//reads a file into courseFinal arrayList
	public static ArrayList<courseFinal> readCourses(String filename) throws FileNotFoundException{
		ArrayList<courseFinal> course = new ArrayList<courseFinal>();
		
		File fileNames = new File(filename);
		if(fileNames.canRead()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(fileNames);

			while(scanner.hasNext()) {
				String crn = scanner.next();
				String courseName = scanner.next();
				String tname = scanner.next();
				String tlast = scanner.next();
				String days = scanner.next();
				String time = scanner.next();
				int seats = Integer.valueOf(scanner.next());
				int taken = Integer.valueOf(scanner.next());
				String status = scanner.next();

				course.add(new courseFinal(crn,courseName,tname,tlast,days,time,seats,taken,status));
				
			}

		}
	
		return course;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);	
		int userChoice;
		boolean quit = false;
		System.out.println("Welcome to Student Class Report System");
		do {
			System.out.println("1. Course report");
			System.out.println("2. Students in course report");
			System.out.println("3. Students grade report");
			System.out.println("4. Get student attendance report");
			System.out.println("Enter your choice. Enter 0 to quit.");
			userChoice = scanner.nextInt();
			scanner.nextLine();
			switch(userChoice) {
			case 1:
				System.out.println("Enter course file path: ");
				String course_file = scanner.nextLine();
				System.out.println("Enter students enrolled file path or enter null to read only the courses available");
				String student_file = scanner.nextLine();
				coursesAvailable(student_file,course_file);
				break;
			case 2:
				System.out.println("Enter courses file path: ");
				String coursesFile = scanner.nextLine();
				
				System.out.println("Enter students enrolled in courses file path: ");
				String studentFile = scanner.nextLine();
				System.out.println("Courses available: ");
				coursesAvailable(studentFile,coursesFile);
				System.out.println();
				System.out.println("Enter course c# : ");
				String crn = scanner.nextLine();
				courseInformation(studentFile,coursesFile,crn);
				break;
			case 3:
				System.out.println("Enter grades file path: ");
				String grades_file = scanner.nextLine();
				System.out.println("Enter a file path to save grade report: ");
				String new_file_path = scanner.nextLine();
				ArrayList<averageClass> students = averageClass.readClientFile(grades_file);
				averageClass.displayFile(grades_file, new_file_path, students);
				averageClass.readAverage(new_file_path);
				System.out.println("New grades file saved at :" + new_file_path);
				break;
			case 0:
				quit = true;
				break;
			default:
				System.out.println("Wrong choice. Try again");
				break;
			}
			System.out.println();
		}while(!quit); 
			System.out.println("See you later!");
		scanner.close();
		
  }
	//reads students and course text file takes 3 parameters and return the students in a specified class
	public static void courseInformation(String file1, String file2, String crn) throws FileNotFoundException {
		ArrayList<StudentFinal> stu = readFile(file1);
		ArrayList<courseFinal> courses1 = readCourses(file2);
		for(int i = 0; i < stu.size();i++) {
			for(int y = 0; y < courses1.size();y++) {
				if(stu.get(i).getCrn().equals(courses1.get(y).getCrn1())) {
					courses1.get(y).addStudent(stu.get(i));
				}
			}
		}
		for(int i = 0; i < courses1.size(); i++) {
			if(courses1.get(i).getCrn1().equals(crn)) {
				System.out.println("Students enrolled in :  " + courses1.get(i).getCourseName());
			}
		}
		
		System.out.println("fNAME     mNAME        lNAME        stuID      stuEMAIL ");
		for(int y = 0; y < courses1.size(); y++) {
			if(courses1.get(y).getCrn1().equals(crn)) {
				courses1.get(y).printStudents();
			}
		}
	}
	//read students and course files. add students to course. course gets updated and displays updated course information
	public static void coursesAvailable(String students, String courses) throws FileNotFoundException {
		ArrayList<courseFinal> courses1 = readCourses(courses);
		ArrayList<StudentFinal> names = readFile(students);
		for(int i = 0; i < names.size();i++) {
			for(int y = 0; y < courses1.size();y++) {
				if(names.get(i).getCrn().equals(courses1.get(y).getCrn1())) {
					courses1.get(y).addStudent(names.get(i));
				}
			}
		}
		System.out.println("c#    cName            Professor    Days      Time        #Seats   #Enrolled  Status");
		for(int i = 0; i < courses1.size(); i++) {
			courses1.get(i).getCourseInfo();
		}
	}

}
