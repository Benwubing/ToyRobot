import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyRobot {

    int x,y;
    String f;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "Robot is currently on:"+this.getX()+","+this.getY()+" facing "+this.getF();
    }

    public void executeCommands(List<String> commands){
        boolean robotPlaced=false;
        for(int i=0;i<commands.size();i++){
            String[] cmd = commands.get(i).split(" ");

            if(i == 0 && !cmd[0].equals("PLACE")){
                System.out.println("Please start input with PLACE command.");
                continue;
            }

            if(cmd[0].equals("PLACE")){
                String[] coordinates = cmd[1].split(",");
                if(coordinates.length<3){
                    System.out.println("Place commands need 3 integer values.");
                    continue;
                }
                try{
                    int projectedX = Integer.parseInt(coordinates[0]);
                    int projectedY = Integer.parseInt(coordinates[1]);

                    if(projectedX < 0 || projectedX > 4 || projectedY < 0 || projectedY > 4){
                        continue; //skip this command if it is off the grid
                    }
                    this.setX(projectedX);
                    this.setY(projectedY);
                    this.setF(coordinates[2]);
                    robotPlaced = true;
                }catch(NumberFormatException e){
                    System.out.println("Place commands need 3 integer values.");
                    continue;
                }
            }else if(cmd[0].equals("MOVE") && robotPlaced){
                int projectedX = this.getX();
                int projectedY = this.getY();
                if(this.getF().equals("NORTH")){
                    projectedY+=1;
                }else if(this.getF().equals("SOUTH")){
                    projectedY-=1;
                }else if(this.getF().equals("WEST")){
                    projectedX -=1;
                }else if(this.getF().equals("EAST")){
                    projectedX+=1;
                }

                if(projectedX < 0 || projectedX > 4 || projectedY < 0 || projectedY > 4){
                    continue; //skip this command if it is off the grid
                }

                this.setX(projectedX);
                this.setY(projectedY);
            }else if(cmd[0].equals("LEFT") && robotPlaced){
                if(this.getF().equals("NORTH")){
                    this.setF("WEST");
                }else if(this.getF().equals("WEST")){
                    this.setF("SOUTH");
                }else if(this.getF().equals("SOUTH")){
                    this.setF("EAST");
                }else if(this.getF().equals("EAST")){
                    this.setF("NORTH");
                }
            }else if(cmd[0].equals("RIGHT") && robotPlaced){
                if(this.getF().equals("NORTH")){
                    this.setF("EAST");
                }else if(this.getF().equals("EAST")){
                    this.setF("SOUTH");
                }else if(this.getF().equals("SOUTH")){
                    this.setF("WEST");
                }else if(this.getF().equals("WEST")){
                    this.setF("NORTH");
                }
            }else if(cmd[0].equals("REPORT") && robotPlaced){
                System.out.println(this.toString());
            }else{
                continue;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a series of commands. End the entire input with a empty input.");
        String lineInput;
        List<String> commands = new ArrayList<>();
        do{
            lineInput = sc.nextLine();

            if(lineInput!=null && lineInput.trim().length()>0){
                commands.add(lineInput);
            }
        }while(lineInput!=null && lineInput.trim().length()>0);

        ToyRobot robot=new ToyRobot();
        robot.executeCommands(commands);
    }






}