package Model;

import javafx.scene.paint.Color;

public class Cell
{

    private int state;
    private Color color;

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
}
