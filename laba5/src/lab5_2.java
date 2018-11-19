import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
public class lab5_2 {
    public static void Show(Collection<Integer> collection)
    {
        for(Integer i: collection)
            System.out.println(i);
    }
    static
    {
     Random random=new Random();
     List<Integer>alpha=new ArrayList<>();
     List<Integer>betha=new ArrayList<>();
     for(int i=0;i<150;i++)
     {
         alpha.add(random.nextInt(200)+1);
         //System.out.println(list.get(i));
     }
         for(int j=200;j>=1;j--)
         {
            for(int i=0;i<150;i++) {
                if(betha.size()==15)
                    break;
                if(alpha.get(i).equals(j))
                    betha.add(j);
            }
         }
        System.out.println(betha.toString());
        File file=new File("lab5_2");
        try {
            FileOutputStream fileoutputstream=new FileOutputStream(file);
            fileoutputstream.write(betha.toString().getBytes());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
