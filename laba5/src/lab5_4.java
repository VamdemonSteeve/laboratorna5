import java.util.Random;
import java.util.Scanner;
import java.util.regex.*;
public class lab5_4 {
    public static class Ugadayka{
      public static int number;
      public static int[] range=new int[2];
      static
      {
          Random random=new Random();
          number =random.nextInt(100)+1;
          range[0]=0;
          range[1]=100;
      }
      public static void Game()
      {
          Pattern correct=Pattern.compile("^[\\d]+$");
          Scanner in=new Scanner(System.in);
          String guess="0";
          while(Integer.parseInt(guess)!=number) {
              boolean durendefense=true;
              System.out.println("Let's play! Guess the number, the current diapason is:  [" + range[0] + ";" + range[1] + "]");
              while(durendefense) {
                  guess = in.nextLine();

                  Matcher matcher=correct.matcher(guess);
                  if (!matcher.matches()) {
                      System.out.println("Incorrect input!");
                      continue;
                  }
                  if(guess.length()>3)
                  {
                      System.out.println("Incorrect input!");
                      continue;
                  }
                  if(Integer.parseInt(guess)>range[1]||Integer.parseInt(guess)<range[0]){
                      System.out.println("Ooops! Your number is probably not there");
                      continue;
                  }
                  durendefense=false;
              }
              if(Integer.parseInt(guess)==number)
              {
                  System.out.println("You won!");
                  break;
              }
              if(Integer.parseInt(guess)>number)
                  range[1]=Integer.parseInt(guess);
              else
                  range[0]=Integer.parseInt(guess);

          }
      }
    }
}
