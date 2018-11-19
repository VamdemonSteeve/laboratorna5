import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.lang.Comparable;
public class lab5_3 {
    public static String[] Names={"Aleksiy","Alphar","Alistar","Bob","Brendon","Bard","Cole","Katrin","Caliopy","Serhiy","Soli","Solomiya","Yanush"};
    public static List<worker> workers=new ArrayList<>();
    public static  File file=new File("lab5_3");
    public static void setFile(String string){
        file=new File(string);
    }
    public static void initialize(int size)
    {

            Random random = new Random();
            for (int i = 0; i < size; i++) {
                switch (random.nextInt(2)) {
                    case 0: {
                        if(random.nextBoolean())
                            workers.add(new HourSalaryWorker(Names[random.nextInt(13)], random.nextInt(1000000), random.nextDouble()));
                        else
                            workers.add(new HourSalaryWorker(Names[random.nextInt(13)], random.nextInt(1000000), 50.0));
                        break;
                    }
                    case 1: {
                        if(random.nextBoolean())
                            workers.add(new MonthlySalaryWorker(Names[random.nextInt(13)], random.nextInt(1000000), random.nextDouble()));
                        else
                            workers.add(new MonthlySalaryWorker(Names[random.nextInt(13)], random.nextInt(1000000), 8320));
                        break;
                    }
                }
            }
            Collections.sort(workers);

    }

    public static void Show(int start, int end,boolean write)throws IOException{
        if(start>end)
            throw new NegativeArraySizeException();
        if(start<0 || end <0)
            throw new NegativeArraySizeException();
        if(write){
            new FileWriter("lab5_3").close();
            FileOutputStream writer=new FileOutputStream(file,true);
            for (worker element : workers)
                writer.write(element.toString().getBytes());
        }
        else {
            for (int i = start; i < end; i++)
                System.out.println(workers.get(i));
        }
    }
    public static void readFromFile(int size) throws IOException {
        Pattern NamePattern=Pattern.compile("^Name: [A-Z]([a-z]+)$");
        Pattern TypePattern=Pattern.compile("^Worker's type: class (lab5_3\\$MonthlySalaryWorker|lab5_3\\$HourSalaryWorker)$");
        Pattern IdPattern=Pattern.compile("^id:(\\d)+$");
        Pattern AverageSalaryPattern=Pattern.compile("^Average Salary: [0-9]+\\.[0-9]+$");
        if(!file.exists())
            throw new FileNotFoundException("Such file does not exist!");
        if(file.isDirectory())
            throw new FileNotFoundException("Directories are not permited");
        FileReader r=new FileReader(file);
        BufferedReader reader=new BufferedReader(r);
        for(int i=0;i<size;i++)
        {
            String Name=reader.readLine();
            String type=reader.readLine();
            String id=reader.readLine();
            String AverageSalary=reader.readLine();
            Matcher NameMatcher=NamePattern.matcher(Name);
            Matcher TypeMatcher=TypePattern.matcher(type);
            Matcher IdMatcher=IdPattern.matcher(id);
            Matcher AverageSalaryMatcher=AverageSalaryPattern.matcher(AverageSalary);
            if(Name==null||type==null||id==null||AverageSalary==null)
                throw new NullPointerException("Insufficient amount of data within file!");
            if(!(NameMatcher.matches()&&TypeMatcher.matches()&&IdMatcher.matches()&&AverageSalaryMatcher.matches()))
                throw new RuntimeException("Illegal data format");
            switch (type){
                case "Worker's type: class lab5_3$MonthlySalaryWorker":
                {
                    workers.add(new MonthlySalaryWorker(Name.substring(6),Long.parseLong(id.substring(3)),Double.parseDouble(AverageSalary.substring(15))));
                    System.out.println(workers.get(workers.size()-1));
                    break;
                }
                case "Worker's type: class lab5_3$HourSalaryWorker":
                {
                    workers.add(new HourSalaryWorker(Name.substring(6),Long.parseLong(id.substring(3)),Double.parseDouble(AverageSalary.substring(15))/(20.8*8)));
                    System.out.println(workers.get(workers.size()-1));
                    break;
                }
            }

        }
    }
    public abstract  static class worker implements Comparable<worker>{
        private String Name;
        private long id;
        protected double AverageSalary;
        public String getName() {
            return Name;
        }

        public long getId() {
            return id;
        }

        public void setName(String name) {
            Name = name;
        }

        public void setId(long id) {
            this.id = id;
        }

        public worker(String Name,long id) {
            this.Name=Name;
            this.id=id;
        }
        public worker()
        {
            this.Name="Worker";
            this.id=1;
        }
        public abstract double getAverageSalary();

        @Override
        public int compareTo(worker o) {
            if(this.getAverageSalary()<o.getAverageSalary())
                return 1;
            if(this.getAverageSalary()>o.getAverageSalary())
                return -1;
            else
            {
                return String.CASE_INSENSITIVE_ORDER.compare(this.Name,o.Name);
            }
        }

        @Override
        public String toString()
        {
           return "Name: "+Name+"\nWorker's type: "+getClass()+"\nid:"+id+"\nAverage Salary: "+AverageSalary+"\n";
        }
    }

    public static class HourSalaryWorker extends worker{
        private double hourSalary;
        public double getHourSalary() {
            return hourSalary;
        }

        public void setHourSalary(int hourSalary) {
            this.hourSalary = hourSalary;
        }

        @Override
        public double getAverageSalary() {
            AverageSalary=20.8 * 8 * hourSalary;
            return 20.8 * 8 * hourSalary;
        }

        public HourSalaryWorker(String Name, long id, double hourSalary) {
            super(Name, id);
            this.hourSalary = hourSalary;
            getAverageSalary();
        }
        public HourSalaryWorker()
        {
            super();
            this.hourSalary=0;
            getAverageSalary();
        }

    }

    public static class MonthlySalaryWorker extends worker{
        private double MonthlySalary;

        public double getMonthlySalary() {
            return MonthlySalary;
        }

        public void setMonthlySalary(double monthlySalary) {
            MonthlySalary = monthlySalary;
        }
        @Override
        public double getAverageSalary(){
            AverageSalary=MonthlySalary;
            return MonthlySalary;
        }

        public MonthlySalaryWorker(String Name, long id, double MonthlySalary) {
            super(Name, id);
            this.MonthlySalary=MonthlySalary;
            getAverageSalary();
        }
        public MonthlySalaryWorker(){
            super();
            MonthlySalary=0;
            getAverageSalary();
        }
    }



}
