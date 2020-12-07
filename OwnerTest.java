/**
 * @author Jonas Andersson Fagerlund joan1043
 */
public class OwnerTest {

  public static void main(String[] args){

    Assignment assignment = new Assignment();

    // assignment.registerNewOwner();

    System.out.println(assignment.findOwner("Jonas"));
    System.out.println(assignment.findOwner("Jon"));
  }
  
}
