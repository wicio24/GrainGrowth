package Model;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Cell
{

    private int state;
    private Color color;
    private int id;

    private static Map<Integer, Color> myMap = new HashMap<Integer, Color>();


    public Cell()
    {
        state = 0;


    }


    public int getState()
    {
        return state;
    }


    public Color getColor()
    {
        return color;
    }
    public void setState(int state)
    {
        this.state = state;
    }


    public void setColor(Color color)
    {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Color getById(int id) {
        return Cell.myMap.get(id);
    }

    public static void setMyMap(Map<Integer, Color> myMap) {
        Cell.myMap = myMap;
    }

    public static Map<Integer, Color> getMyMap() {
        return myMap;
    }

    public static void putMyMap(int id, Color color) {
        Cell.myMap.put(id,color);

    }
}
