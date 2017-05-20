
public class Player {
    
    private String name;
    private String game;
    private String date;
    
   public Player(String name, String date, String game){
       this.name = name;
       this.date = date;
       this.game = game;
   }
   
   public String getName(){
       return this.name;
   }
   public void setName(String name){
       this.name = name;
   }
   
   public String getGame(){
       return this.game;
   }
   public void setGame(String game){
       this.game = game;
   }
   public String getDate(){
       return this.date;
   }
   public void setDate(String date){
       this.date = date;
   }
   
}
