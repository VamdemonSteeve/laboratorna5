import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
public class lab5_1 {
    public static void getTime(Collection<Double> ...collections) {

        for (Collection<Double>  elements : collections) {
                Iterator<Double> iterator=elements.iterator();
                long t1=System.nanoTime();
                elements.add(10000000.0);
                long t2=System.nanoTime();
                elements.remove(10000000.0);
                long t3=System.nanoTime();
                elements.remove(0.1);
                long t4=System.nanoTime();
                elements.remove(0.2);
                long t5=System.nanoTime();
                System.out.println(elements.getClass()+":\nfind in the end~ "+ Math.abs((t2-t1)/1000)+" microseconds\n"+"remove from the end~ "+Math.abs((t3-t2)/1000)+" microseconds\n"+"remove from the start~ "+Math.abs((t4-t3)/1000)+" microseconds \nremove from the middle~ "+Math.abs((t5-t4)/1000)+" microseconds\n---------\n");
        }
    }
    public static void TimeAddList(List<Double> arraylist,List<Double> linkedList,Set<Double> treeset)
    {
        arraylist.add(799900/2,1.1);
        linkedList.add(799900/2,1.1);
        treeset.add(799900.0/2);
        treeset.add(0.0);
        arraylist.add(0,1.1);
        linkedList.add(0,1.1);
        long t1=System.nanoTime();
        arraylist.get(799900/2);
        long t2=System.nanoTime();
        linkedList.get(799900/2);
        long t3=System.nanoTime();
        arraylist.get(0);
        long t4=System.nanoTime();
        linkedList.get(0);
        long t5=System.nanoTime();
        System.out.println("ArrayList:\nfind in the middle ~ "+Math.abs((t2-t1)/1000)+" microseconds\nfind at the start~ "+Math.abs((t4-t3)/1000)+" microseconds\nLinkedList:\nfind in the middle~ "+Math.abs((t3-t2)/1000)+" microseconds\nfind at the start~ "+Math.abs((t5-t4)/1000)+" microseconds");
    }
    public static void main(String args[]) throws IOException
    {
        Scanner in=new Scanner(System.in);
        System.out.println("TASK1: ");
        List<Double> arraylist = new ArrayList<>();
        List<Double> linkedlist=new LinkedList<>();
        Set<Double> treeset=new TreeSet<>();
        Set<Double> hashset=new HashSet<>();
        Random random=new Random(1);
        long t1=System.nanoTime();
        for(int i=0;i<899999;i++)
        {
          arraylist.add(new Double(i));
        }
        long t2=System.nanoTime();
        for(int i=0;i<899999;i++)
        {
            linkedlist.add(new Double(i));
        }
        long t3=System.nanoTime();
        for(int i=0;i<899999;i++)
        {
            hashset.add(new Double(i));
        }
        long t4=System.nanoTime();
        for(int i=0;i<899999;i++)
        {
            treeset.add(new Double(i));
        }
        long t5=System.nanoTime();
        System.out.println("ArrayList initialization= "+Math.abs((t2-t1)/1000)+" microseconds\nLinkedList initialization= "+Math.abs((t3-t2)/1000)+" microseconds\nHashSet= "+Math.abs((t4-t3)/1000)+" microseconds\nTreeSet initialization= "+Math.abs((t5-t4)/1000)+" microseconds\n");
        arraylist.set(0,11111.1);
        arraylist.set(799900/2,80000.1);
        linkedlist.set(0,11111.1);
        linkedlist.set(799900/2,80000.1);
        hashset.add(0.2);
        //treeset.add(0.1);
        treeset.add(0.2);
        treeset.add(80000.1);
        //for(Number element : treeset)
            //System.out.println(element);
        getTime(arraylist,linkedlist,treeset,hashset);
        TimeAddList(arraylist,linkedlist,treeset);
        System.out.println("TASK2:");
        lab5_2 Task2=new lab5_2();
        System.out.println("TASK3:");
        lab5_3.initialize(10);
        System.out.println("first five workers: ");
        lab5_3.Show(0,5,false);
        System.out.println("last three workers: ");
        lab5_3.Show(7,10,false);
        lab5_3.Show(0,10,true);
        System.out.println("Reading workers from the file: ");
        try {
            lab5_3.readFromFile(7);
        }catch (FileNotFoundException e) {
            System.out.println("------Such file either does not exist or is not txt file");
        }
        catch(IOException e){
            System.out.println("------Unable to open the file");
            }
        catch (NullPointerException e){
            System.out.println("Not enough data to complete the input");
        }
        catch (RuntimeException e){
            System.out.println("Incorrect data format");
        }
        System.out.println("TASK4:");
        lab5_4.Ugadayka.Game();
        System.out.println("TASK5:");
        lab5_5 Task5=new lab5_5();
        System.out.println("TASK6:");
        System.out.println("Input string: ");
        String input=in.nextLine();
        System.out.println(lab5_6.invese(input));
    }
}
/*Выводы: При рассмотрении в работе с большим количеством элементов себя лучше проявляют нелинейные коллекции
типа HashSet,TreeSet первая из них способна удалять, добавлять и получать элемент за О(1), хотя при этом у
нас не гарантируется определенная упорядоченность элементов (в связи с использованием под капотом HashMap)
При необходимости наличия порядка, при больших количествах данных следует использовать TreeSet, поскольку
сложность работы его алгоритмов О(log(n)), При больших объемах допускается использование линейных контейнеров,
при условии добавления элементов в начале и отсутствии частых удалений элементов, при этом себя хорошо демон-
стрирует ArrayList, когда речь идет о добавлении элементов в конец, удалении из начала и середины. Заметно, что
LinkedList не допускает такой скорости, когда речь идет об обработке середины списка, в отличии от конца и начала,
это обусловлено его структурой и наличием так называемого элемента Head, указывающего на начало списка, и имеющий себя
следующим у последнего элемента. Следует отметить, что проблем с доступом к середине можно избежать воспользовавшись
ListIterator, содержащимся в структуре LinkedList.
При малых объемах данных коллекции демонстрируют себя сравнительно одинаково, но при этом актуальность реализаций
интерфейса Set заметно падает, тут большую роль играют линейные контейнеры и словари.
 */
