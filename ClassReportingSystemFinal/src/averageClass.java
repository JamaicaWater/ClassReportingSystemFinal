import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;




public class averageClass{
		private String first;
		private String last;
		private double test1;
		private double test2;
		private double test3;
		private double test4;
		private double tfinal;
		public averageClass() {
			this.first = "none";
			this.last = "none";
			this.test1 = 0.0;
			this.test2 = 0.0;
			this.test3 = 0.0;
			this.test4 = 0.0;
			this.tfinal = 0.0;
		}
		averageClass(String first, String last, double test1,double test2,double test3,double test4,double tfinal){
			this.first = first;
			this.last = last;
			this.test1 = test1;
			this.test2 = test2;
			this.test3 = test3;
			this.test4 = test4;
			this.tfinal = tfinal;
		}
		public double getTest1() {
			return this.test1;
		}
		public double getTest2() {
			return this.test2;
		}
		public double getTest3() {
			return this.test3;
		}
		public double getTest4() {
			return this.test4;
		}
		public double getFinal() {
			return this.tfinal;
		}
		public double getAverage() {
			return (this.test1 + this.test2 + this.test3 + this.test4 + 2*(this.tfinal))/6;
		}
		public String getLetter() {
			if(getAverage() >= 90.0) {
				return "A";
			}else if((getAverage() < 90.0) && (getAverage() >= 80.0)) {
				return "B";
			}else if((getAverage() < 80.0) && (getAverage() >= 70.0)) {
				return "C";
			}else if((getAverage() < 70.0) && (getAverage() >= 60.0)) {
				return "D";
			}else {
				return "F";
			}
		}
		public String getInfo() {
			return String.format("%1$-10.2f%2$-6s",getAverage(),getLetter());
		}

	public static ArrayList<averageClass> readClientFile(String read_file) {
		ArrayList<averageClass> person = new ArrayList<averageClass>();
		try {
		      File myObj = new File(read_file);
		      Scanner scanner = new Scanner(myObj);
		      while (scanner.hasNextLine()) {
		        String first = scanner.next();
		        String last = scanner.next();
		        double t1 = Double.parseDouble(scanner.next());
		        double t2 = Double.parseDouble(scanner.next());
		        double t3 = Double.parseDouble(scanner.next());
		        double t4 = Double.parseDouble(scanner.next());
		        double ftest = Double.parseDouble(scanner.next());
		 
		        person.add(new averageClass(first,last,t1,t2,t3,t4,ftest));
		        
		      }
		      scanner.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return person;
	}
public static void displayFile(String old_file, String new_file, ArrayList<averageClass> grades) throws FileNotFoundException {
	File n_file = new File(old_file);
	PrintWriter outFile = new PrintWriter(new_file);
	Scanner scanner = new Scanner(n_file);
	int count = 0;
	outFile.println("FiName  LaName  Test1  Test2    Test3   Test4   Finals  Average   Grade\n");
	while(scanner.hasNextLine()) {
		String data = scanner.nextLine();
		String[] info = data.split("\\s+");
		String align = String.format("%-8s%-9s%-7s%-8s%-8s%-8s%-8s", info[0],info[1],info[2],info[3],info[4],info[5],info[6]);
		outFile.println(align + "" + grades.get(count).getInfo());
		count++;
	}
	
	double max = grades.get(0).getTest1();
	for(int i = 0; i < grades.size();i++) {
		if(grades.get(i).getTest1() > max) {
			max = grades.get(i).getTest1();
		}
	}
	outFile.println();
	outFile.println("Test 1 highest grade: " + max);
	outFile.println("Test 2 highest grade: " + highestTest2(grades));
	outFile.println("Test 3 highest grade: " + highestTest3(grades));
	outFile.println("Test 4 highest grade: " + highestTest4(grades));
	outFile.println("Finals highest grade: " + highestFinal(grades));
	scanner.close();
	outFile.close();
}
public static void readAverage(String avFile) throws FileNotFoundException {
	Scanner scanner = new Scanner(new File(avFile));
	while(scanner.hasNextLine()) {
		String data = scanner.nextLine();
		System.out.println(data);
	}
}
public static double highestTest2(ArrayList<averageClass> test2) {
	double max = test2.get(0).getTest2();
	for(int i = 0; i < test2.size();i++) {
		if(test2.get(i).getTest2() > max) {
			max = test2.get(i).getTest2();
		}
	}
	return max;
}
public static double highestTest3(ArrayList<averageClass> test3) {
	double max = test3.get(0).getTest3();
	for(int i = 0; i < test3.size();i++) {
		if(test3.get(i).getTest3() > max) {
			max = test3.get(i).getTest3();
		}
	}
	return max;
}
public static double highestTest4(ArrayList<averageClass> test4) {
	double max = test4.get(0).getTest4();
	for(int i = 0; i < test4.size();i++) {
		if(test4.get(i).getTest4() > max) {
			max = test4.get(i).getTest4();
		}
	}
	return max;
}
public static double highestFinal(ArrayList<averageClass> test5) {
	double max = test5.get(0).getFinal();
	for(int i = 0; i < test5.size();i++) {
		if(test5.get(i).getFinal() > max) {
			max = test5.get(i).getFinal();
		}
	}
	return max;
}

}
