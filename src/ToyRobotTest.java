import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ToyRobotTest{

    @Test
    public void checkNormalFlow(){
        ToyRobot robot =new ToyRobot();
        List<String> commands=new ArrayList<>();
        commands.add("PLACE 0,0,NORTH");
        commands.add("MOVE");
        commands.add("LEFT");
        commands.add("RIGHT");
        commands.add("MOVE");
        commands.add("MOVE");
        commands.add("REPORT");
        robot.executeCommands(commands);
        assertEquals("Robot is currently on:0,3 facing NORTH",robot.toString());
    }

    @Test
    public void checkOverFive(){
        ToyRobot robot =new ToyRobot();
        List<String> commands=new ArrayList<>();
        commands.add("PLACE 3,2,EAST");
        commands.add("MOVE");
        commands.add("MOVE");
        commands.add("REPORT");
        robot.executeCommands(commands);
        assertEquals("Robot is currently on:4,2 facing EAST",robot.toString());
    }

    @Test
    public void checkImpossiblePlacement(){
        ToyRobot robot =new ToyRobot();
        List<String> commands=new ArrayList<>();
        commands.add("PLACE 3,2,EAST");
        commands.add("PLACE 7,7,EAST");
        commands.add("PLACE 5,5,EAST");
        commands.add("PLACE 6,6,EAST");
        commands.add("REPORT");
        robot.executeCommands(commands);
        assertEquals("Robot is currently on:3,2 facing EAST",robot.toString());
    }

    @Test
    public void checkMoveOnlyWhenValidPlacement(){
        ToyRobot robot =new ToyRobot();
        List<String> commands=new ArrayList<>();
        commands.add("PLACE 5,7,EAST");
        commands.add("MOVE");
        commands.add("LEFT");
        commands.add("RIGHT");
        commands.add("MOVE");
        commands.add("PLACE 3,2,EAST");
        commands.add("REPORT");
        robot.executeCommands(commands);
        assertEquals("Robot is currently on:3,2 facing EAST",robot.toString());
    }

    @Test
    public void checkPlacements(){
        ToyRobot robot =new ToyRobot();
        List<String> commands=new ArrayList<>();
        commands.add("PLACE 3,2,EAST");
        commands.add("PLACE 4,2,NORTH");
        commands.add("REPORT");
        robot.executeCommands(commands);
        assertEquals("Robot is currently on:4,2 facing NORTH",robot.toString());
    }

}