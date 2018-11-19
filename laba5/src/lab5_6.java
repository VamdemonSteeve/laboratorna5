public class lab5_6 {
    public static String invese(String string){
        char[]inverse=string.toCharArray();
        char t=' ';
        for(int i=0;i<inverse.length/2;i++)
        {
            t=inverse[i];
            inverse[i]=inverse[inverse.length-i-1];
            inverse[inverse.length-i-1]=t;
        }
        return String.copyValueOf(inverse);
    }
}
