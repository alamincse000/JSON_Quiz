package java_file_mainpulation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class javaQuiz {
    public static void main(String[] args) throws ParseException, IOException {
        System.out.println("1. Add Quiz");
        System.out.println("2. Start Quiz");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        if (number == 1) {
            writeJSONList();
        } else if (number == 2) {
            System.out.println("You will be asked 5 questions, each questions has 1 marks");
            pointJsonList();
        }
    }

    private static void pointJsonList() throws IOException, ParseException, IOException {

        String fileName = "./src/main/resources/quiz.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        Scanner sc = new Scanner(System.in);
        int count = 0;
        for (int i = 1; i <= 5; i++) {
            int random = (int) (Math.random() * 20) + 1;
            JSONObject jsonObj1 = (JSONObject) jsonArray.get(random);
            String Question = (String) jsonObj1.get("Question");
            String A = (String) jsonObj1.get("Option a");
            String B = (String) jsonObj1.get("Option b");
            String C = (String) jsonObj1.get("Option c");
            String D = (String) jsonObj1.get("Option d");
            String CorrectAnswer = (String) jsonObj1.get("answer");
            System.out.println(i + "." + Question);
            System.out.println("a. " + A + "\nb. " + B + "\nc. " + C + "\nd. " + D);
            System.out.println("Give the correct answer  as the following option: ");
            String input = sc.nextLine();
            if (input.equals(CorrectAnswer)) {
                count++;
                System.out.println(" correct!");
            } else {
                System.out.println("Not Correct");
            }
            System.out.println("Your got " + count + " Marks Out of 5 ");
        }
    }

    private static void writeJSONList() throws IOException, org.json.simple.parser.ParseException {
        char ch = 'y';
        String fileName = "./src/main/resources/quiz.json";
        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject studentObj = new JSONObject();

            Scanner input = new Scanner(System.in);
            System.out.println("Please add a question here : ");
            studentObj.put("Question", input.nextLine());

            System.out.println("Option a : ");
            studentObj.put("Option a", input.nextLine());

            System.out.println("Option b : ");
            studentObj.put("Option b", input.nextLine());

            System.out.println("Option c : ");
            studentObj.put("Option c", input.nextLine());

            System.out.println("Option d : ");
            studentObj.put("Option d", input.nextLine());

            System.out.println("Please input the correct answer : ");
            studentObj.put("answer", input.nextLine());

            JSONArray jsonStudentArray = (JSONArray) obj;
            jsonStudentArray.add(studentObj);
            FileWriter file = new FileWriter(fileName);
            file.write(jsonStudentArray.toJSONString());
            file.flush();
            file.close();
            System.out.println("saved.");
            System.out.println("Do you want to add more?[y/n]");
            ch = input.next().charAt(0);
        }
        while (ch != 'n');

    }
}
