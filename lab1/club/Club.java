package club;
import java.util.ArrayList;

/**
 * Store details of club memberships.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Club implements ClubInterface
{
    // Define any necessary fields here ...
    private ArrayList<Membership> members;
    /**
     * Constructor for objects of class Club
     */
    public Club()
    {
        // Initialise any fields here ...
         members = new ArrayList<Membership>();
         //System.out.println(members+"martha");
    }

    /**
     * Add a new member to the club's list of members.
     * @param member The member object to be added.
     */
    public void join(Membership member){ 
          // kelly
          for(Membership n:members){// Kelly , Jessie
              if (n.getName().equals(member.getName())){
                  return;
              }
          }
          members.add(member);
        }
        //System.out.println(member);
        
      /*for(int i=0; i<members.size(); i++){
          Membership temp= members.get(i);
          if (member.getName()!=temp.getName()){
              members.add(member);
            }
        
          
          }/*
      }
      
    

    /**
     * @return The number of members (Membership objects) in
     *         the club.
     */
    public int numberOfMembers()
    { 
        if (members.size()>0){
            return members.size();
        }
        return 0;
    }
    
    public ArrayList<Membership> purge(int month, int year){
        ArrayList<Membership> mem = new ArrayList<>();
        for(Membership m : members){
            if(m.getMonth()==month && m.getYear()==year){
                mem.add(m);
                members.remove(m);
                
            }
            return mem;
        }
        
        return null;
    }
    public int joinedInMonth(int month){
        int joined=0;
        for(int i=0; i<members.size(); i++){
            if(members.get(i).getMonth()==month){
                joined+=1;
            }
        if(month>12 || month<0){
            System.out.println("error");
        }
        
        }
        return joined;
    }
    public Membership find(String name){
        for(Membership m : members){
            if(m.getName().equals(name)){
                return m;
            }
        }
       return null;
    }
    
}