import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class KYC
{
    public static void main(String args[]) throws DateTimeException, ParseException {
        ArrayList<String> al=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        while(t-- >0) {
            String d1 = sc.next();
            String d2 = sc.next();
            LocalDate date1 = LocalDate.parse(d1);
            LocalDate date2 = LocalDate.parse(d2);

            if (date1.compareTo(date2) > 0) {
                al.add(null);al.add(null);
                continue;
            }

            LocalDate new_date=date1.withYear(date2.getYear());

            LocalDate start_date=new_date.minusDays(30);
            LocalDate end_date=new_date.plusDays(30);

            al.add(start_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

            if(end_date.compareTo(date2)>0)
                al.add(date2.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            else
                al.add(end_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
       for(int i=0;i<al.size();i++){
            if(i%2==0){
                if(al.get(i)==null){
                    System.out.print("No ");
                    continue;
                }
                System.out.print(al.get(i)+" ");
            }
            else{
                if(al.get(i)==null){
                    System.out.println("range");
                    continue;
                }
                System.out.println(al.get(i)+" ");
            }
        }
    }
}