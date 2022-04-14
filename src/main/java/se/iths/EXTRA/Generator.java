package se.iths.EXTRA;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jonathan
 */
public class Generator {
    /*
    static MegaDao dao = new MegaDao();
    //we need to generate
    //STUDENT
    //student name x
    //student pid x

    //COURSE
    //course name
    //course duration
    //SCHOOL
    //city name
    //school name
    //EDUCATION
    //education name
    //education duration
    //education start
    //TEACHER
    //teacher name x
    //teacher pid x
    //teacher salary
     */
    public static List<List<String>> readList(String filename) throws FileNotFoundException, URISyntaxException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(Thread.currentThread().getContextClassLoader().getResource("/lists/"+filename+".csv").toURI()))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        return records;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter("\n");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    /*Student&Teacher
    public static String generatePID() {
        //yymmdd
        String randomMonthString = "";
        String randomDayString = "";

        int randomYear = ThreadLocalRandom.current().nextInt(1935, 2021 + 1);
        int birthYear = randomYear % 100;

        int randomMonth = ThreadLocalRandom.current().nextInt(1, 12 + 1);
        if (randomMonth < 9) {
            randomMonthString = "0" + String.valueOf(randomMonth);

        } else {
            randomMonthString = String.valueOf(randomMonth);
        }
        int randomDay = ThreadLocalRandom.current().nextInt(1, 31 + 1);
        if (randomDay < 9) {
            randomDayString = "0" + String.valueOf(randomDay);
        } else {
            randomDayString = String.valueOf(randomDay);
        }
        String PID = String.valueOf(birthYear) + randomMonthString + randomDay;

        return PID;

    }
*/
    public static String generatePersonName() throws FileNotFoundException, URISyntaxException {
        List<List<String>> firstNames = readList("SweNames");
        List<List<String>> lastNames = readList("SweLastnames");

        int randFirst = ThreadLocalRandom.current().nextInt(1, firstNames.size());
        int randLast = ThreadLocalRandom.current().nextInt(1, lastNames.size());

        String name = firstNames.get(randFirst) + " " + lastNames.get(randLast);
        name = name.replace("[", "");
        name = name.replace("]", "");
        return name;

    }



    //Subjects
    public static String generateSubjectName() throws FileNotFoundException, URISyntaxException {
        List<List<String>> courseNames = readList("SweSubjectsCourses");
        int courseNum = ThreadLocalRandom.current().nextInt(1, courseNames.size());
        List<String> courseName = courseNames.get(courseNum);
        return courseName.get(0);
    }


    public static List<Subject> generateSubjectsNullConnections(int antal) throws FileNotFoundException, URISyntaxException {
        //TODO add check for name so its not used.
        List<Subject> subjectsList = new ArrayList<Subject>();
        for (int i = 0; i < antal; i++) {
            String cname = generateSubjectName();
            Subject subject = new Subject(cname);
            subjectsList.add(subject);
        }

        return subjectsList;
    }

    public static List<Student> generateStudentsNullConnections(int antal) throws FileNotFoundException, URISyntaxException {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < antal; i++) {
            String studName = generatePersonName();
            String[] name = studName.split(" ");
            Student s = new Student(name[0], name[1], studName.replace(" ","")+"@"+name[1]+".com", null);
            studentList.add(s);
        }
        return studentList;
    }
    public static List<Teacher> generateTeachersNullConnections(int antal) throws FileNotFoundException, URISyntaxException {
        List<Teacher> teacherList = new ArrayList<>();
        for (int i = 0; i < antal; i++) {
            String teacherName = generatePersonName();
            String[] name = teacherName.split(" ");
            Teacher t = new Teacher(name[0], name[1], teacherName.replace(" ","")+"@"+name[1]+".com", null);
            teacherList.add(t);
        }
        return teacherList;
    }

    public static void clearScreen() {
        for (int i = 0; i < 0; i++) {
            System.out.println("");

        }
    }

}
