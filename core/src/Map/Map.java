package Map;

import com.badlogic.gdx.graphics.Texture;

public class Map
{
    private char[][] grid;

    //Map Datas
    public int width = 15;
    public int height = 10;
    public int tileWidth = 64;
    public int tileHeight = 64;

    //Texture and Sprites
    private Texture groundImage = new Texture("ground.png");
    private Texture rockImage = new Texture("rock.png");
    private Texture holeImage = new Texture("hole.png");

    public Map()
    {
        grid = new char[width][height];

        for(int i = 0; i < width;i++)
        {
            for(int j = 0; j < height; j++)
            {
                grid[i][j] = 'G';
            }
        }
    }

    public Texture GetTexture(int line, int column)
    {
        if(grid[line][column] == 'G')
        {
            return groundImage;
        }
        else if(grid[line][column] == 'R')
        {
            return rockImage;
        }
        else if(grid[line][column] == 'H')
        {
            return holeImage;
        }
        //Normally there should not be other things then G, R, H for now
        else
        {
            return groundImage;
        }
    }

    public char GetTile(int line, int column)
    {
        return grid[line][column];
    }

    public void Dispose()
    {
        //Dispose all the Textures
        groundImage.dispose();
        rockImage.dispose();
        holeImage.dispose();
    }
}
