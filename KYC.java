import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class KYC {
    public static void main(String[] args) throws DateTimeException {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println();
        ArrayList<String> result=new ArrayList<>();  // for printing the output at once not simultaneously with input

        for(int i=0 ; i<n ;i++) {

            String signupDate = sc.next();
            String currentDate = sc.next();

            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate signupLocalDate = LocalDate.parse(signupDate,formatter);
            LocalDate currentLocalDate = LocalDate.parse(currentDate,formatter);

            if(currentLocalDate.getYear() <= signupLocalDate.getYear()){
                result.add("No Range");
                continue;
            }

            LocalDate currentAnniversaryDate = LocalDate.of(currentLocalDate.getYear(),signupLocalDate.getMonth(),signupLocalDate.getDayOfMonth());

            LocalDate startDate = currentAnniversaryDate.minusDays(30);
            LocalDate endDate = currentAnniversaryDate.plusDays(30);

            if(startDate.getMonth().equals(currentLocalDate.getMonth())){
                calculateRangeBasedOnStartDate(startDate,endDate,currentLocalDate,result);
            }

            else if(endDate.getMonth().equals(currentLocalDate.getMonth())){
                calculateRangeBasedOnEndDate(startDate,endDate,currentLocalDate,result);

            }

            else if(currentAnniversaryDate.getMonth().equals(currentLocalDate.getMonth())){
                result.add(Format(startDate)+ " " + Format(currentLocalDate));
            }

            else {
                result.add(Format(startDate) + " " + Format(endDate));
            }
        }

        //printing the ArrayList
        for(String s:result){
            System.out.println(s);
        }
    }

    // Formatting the date to the format "dd-MM-yyyy"

    public static String Format(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // calculating range basing on whether startDate is before currentDate or not

    public static void calculateRangeBasedOnStartDate(LocalDate startDate,LocalDate endDate, LocalDate currentLocalDate, ArrayList<String> result){
        if(startDate.getDayOfMonth() >= currentLocalDate.getDayOfMonth()){
            result.add(Format(startDate)+" "+Format(endDate));
        }
        else {
            result.add(Format(startDate)  + " " + Format(currentLocalDate));
        }
    }

    // calculating range basing on whether endDate is before currentDate or not

    public static void calculateRangeBasedOnEndDate(LocalDate startDate,LocalDate endDate, LocalDate currentLocalDate, ArrayList<String> result){
        if(endDate.getDayOfMonth() < currentLocalDate.getDayOfMonth()){
            result.add(Format(startDate)+ " " +Format(endDate));
        }
        else {
            result.add(Format(startDate)+ " " +Format(currentLocalDate));
        }
    }
}

/*

Input :
5

16-07-1998 27-06-2017
04-02-2016 04-04-2017
04-05-2017 04-04-2017
04-04-2015 04-04-2016
04-04-2015 15-03-2016

Output:

16-06-2017 27-06-2017
05-01-2017 06-03-2017
No range
05-03-2016 04-04-2016
05-03-2016 15-03-2016

*/