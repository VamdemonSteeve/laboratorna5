import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class lab5_5 {
    public static void Show(List<String> list)
    {
        for(String i : list)
            System.out.println(i);
    }

    static{

        File file1=new File("lab5_5");
        File file2=new File("lab5_6");
        File file3=new File("lab5_7");
        List<String> textEnglish=new ArrayList<>();
        List<String> textRussian=new ArrayList<>();
        List<String> textUkrainian=new ArrayList<>();
        try {
            FileReader r1=new FileReader(file1);
            FileReader r2=new FileReader(file2);
            FileReader r3=new FileReader(file3);
            BufferedReader reader1=new BufferedReader(r1);
            BufferedReader reader2=new BufferedReader(r2);
            BufferedReader reader3=new BufferedReader(r3);
            do
            {
                textEnglish.add(reader1.readLine());

            }while(textEnglish.get(textEnglish.size()-1)!=null);
            textEnglish.remove(null);
            do
            {
                textRussian.add(reader2.readLine());

            }while(textRussian.get(textRussian.size()-1)!=null);
            textRussian.remove(null);
            do
            {
                textUkrainian.add(reader3.readLine());

            }while(textUkrainian.get(textUkrainian.size()-1)!=null);
            textUkrainian.remove(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(textEnglish,String.CASE_INSENSITIVE_ORDER);
        Collections.sort(textRussian,String.CASE_INSENSITIVE_ORDER);
        Collections.sort(textUkrainian,String.CASE_INSENSITIVE_ORDER);
        System.out.println("                English:   ");
        Show(textEnglish);
        System.out.println("                Russian    ");
        Show(textRussian);
        System.out.println("                Ukrainian  ");
        Show(textUkrainian);
    }
}
