/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.Arrays;
public class OwnerTest {

  public static void main(String[] args){

    int i = 1;
    double d = 0.0;
    String s = "Jonas a";

    d=i;

    System.out.print(Arrays.toString(s.split(" ")));

    Assignment assignment = new Assignment();

    // assignment.registerNewOwner();

    System.out.println(assignment.findOwner("Jonas"));
    System.out.println(assignment.findOwner("Jon"));
  }
  
}
