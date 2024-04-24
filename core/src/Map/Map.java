package Map;

import com.badlogic.gdx.graphics.Texture;

import java.util.*;

public final class Map
{
    //Map is a Singleton
    private static Map instance;

    //Grid
    private char[][] grid;
    private final char[][][] prefabs;

    //Map data's
    public int width = 15;
    public int height = 10;
    public int tileWidth = 64;
    public int tileHeight = 64;
    private int seed;

    //Texture and Sprites
    private final Texture ground1Image = new Texture("ground1.png");
    private final Texture ground2Image = new Texture("ground2.png");
    private final Texture rockImage = new Texture("rock.png");

    //region <Map Creation>
    public Map(int seed)
    {
        //Initialize the Singleton
        instance = this;

        //Initialize the prefabs array
        prefabs = createPrefabs();

        //Create a random Map
        CreateAMap(seed);

        this.seed = seed;
    }

    public static Map GetInstance()
    {
        return instance;
    }

    public int[] GetFirstPlayerPosition()
    {
        int[] firstPosition = GetInstance().GetRandomPosition(instance.width/2 - 1, 0);

        while(!instance.IsAGroundPosition(firstPosition))
        {
            firstPosition = GetInstance().GetRandomPosition(instance.width/2 - 1, 0);
        }

        return firstPosition;
    }

    public int[] GetSecondPlayerPosition()
    {
        int[] secondPosition = GetInstance().GetRandomPosition(instance.width-1, instance.height-1);

        while(!instance.IsAGroundPosition(secondPosition))
        {
            secondPosition = GetInstance().GetRandomPosition(instance.width-1, instance.height-1);
        }

        return secondPosition;
    }

    private int[] GetRandomPosition(int x, int xOrigin)
    {
        return new int[] {(int) (Math.random() * x) + xOrigin, (int)  (Math.random() * height)};
    }

    private void CreateAMap(int seed)
    {
        //Initialize a basic map with only grounds
        CreateABasicMap();

        //Insert Randomize elements on it
        AddElementsOnMap(seed);

        //If the map is not valid (a player could be blocked) then recreate one
        if(!IsTheMapValid())
        {
            CreateAMap(seed + 1 % Integer.MAX_VALUE);
        }
    }

    private void CreateABasicMap()
    {
        //Initialize a basic map
        grid = new char[width][height];

        for(int i = 0; i < width;i++)
        {
            for(int j = 0; j < height; j++)
            {
                grid[i][j] = 'G';
            }
        }
    }

    private void AddElementsOnMap(int seed)
    {
        //Define the nbElementMax and the seed used
        Random random = new Random(seed);
        int nbElementsMax = random.nextInt(40);

        //While there is still elements to put on the map
        while(nbElementsMax > 0) {
            //Get a random prefab
            char[][] prefab = prefabs[random.nextInt(prefabs.length)];

            //Decrement the number of elements to put
            nbElementsMax -= HowManyElement(prefab);

            //Find the random position to put the new prefab
            int xPosition = random.nextInt(0, this.width + 1);
            int yPosition = random.nextInt(0, this.height + 1);

            //Instantiate the prefab on the map
            for (int i = 0; i < prefab.length; i++) {
                for (int j = 0; j < prefab[i].length; j++) {
                    //Check if the position selected is valid
                    if (IsValidPosition(xPosition + i, yPosition + j)) {
                        //Allow to only define the useful things in the prefabs
                        if (prefab[i][j] == 'R') {
                            grid[xPosition + i][yPosition + j] = prefab[i][j];
                        }
                    }
                }
            }
        }
    }

    private boolean IsTheMapValid()
    {
        //Make a breath-first search to get the ground cases accessible
        Set<List<Integer>> visited = new HashSet<>();
        Stack<int[]> toVisit = new Stack<>();

        int[] start = GetAGroundPosition();
        toVisit.add(start);

        while(!toVisit.empty())
        {
            int[] s = toVisit.pop();

            //Transform s to a list for Set Contains test
            List<Integer> sToList = new ArrayList<>();
            sToList.add(s[0]);
            sToList.add(s[1]);

            if(!visited.contains(sToList))
            {

                visited.add(sToList);

                Set<int[]> neighbors = GetNeighborPosition(s);

                for(int[] t : neighbors)
                {
                    //Transform t to a list for Set Contains test
                    List<Integer> tToList = new ArrayList<>();
                    tToList.add(t[0]);
                    tToList.add(t[1]);

                    if(!visited.contains(tToList))
                    {
                        toVisit.add(t);
                    }
                }
            }
        }

        //Now the set visited contains all the accessible ground cell
        //We now count if the length of this set is equals to the number of ground cells to check if a player could be blocked

        return visited.size() == HowMuchGroundCell();
    }

    private int HowMuchGroundCell()
    {
        int cpt = 0;

        for (char[] chars : grid)
        {
            for (char aChar : chars)
            {
                if (aChar == 'G')
                {
                    cpt++;
                }
            }
        }

        return cpt;
    }

    public Set<int[]> GetNeighborPosition(int[] position)
    {
        Set<int[]> neighbors = new HashSet<>();

        int x = position[0];
        int y = position[1];

        if(IsAGroundPosition(new int[] {x+1, y}))
        {
            neighbors.add(new int[] {x+1, y});
        }

        if(IsAGroundPosition(new int[] {x - 1, y}))
        {
            neighbors.add(new int[] {x-1, y});
        }

        if(IsAGroundPosition(new int[] {x, y+1}))
        {
            neighbors.add(new int[] {x, y+1});
        }

        if(IsAGroundPosition(new int[] {x, y-1}))
        {
            neighbors.add(new int[] {x, y-1});
        }

        return neighbors;
    }

    private boolean IsAGroundPosition(int[] position)
    {
        int x = position[0];
        int y = position[1];

        //Check if it's a valid position
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
        {
            return false;
        }
        else
        {
            return grid[x][y] == 'G';
        }
    }

    private int[] GetAGroundPosition()
    {
        for(int i = 0; i < grid.length;i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j] == 'G')
                {
                    return new int[] {i, j};
                }
            }
        }

        //It is impossible to not have ground on the map
        return new int[] {0, 0};
    }

    private boolean IsValidPosition(int line, int column)
    {
        //Check if the position is valid
        return (0 <= line) &&  (line < width) && (0 <= column) && (column < height);
    }

    private int HowManyElement(char[][] prefab)
    {
        int cpt = 0;

        for (char[] line : prefab) {
            for (char c : line) {
                if (c == 'R')
                {
                    cpt++;
                }
            }
        }

        return cpt;
    }

    private char[][][] createPrefabs()
    {
        char[][] singleRock = new char[1][1];
        singleRock[0][0] = 'R';

        char[][] twoRockVertical = new char[2][1];
        twoRockVertical[0][0] = 'R';
        twoRockVertical[1][0] = 'R';

        char[][] twoRockHorizontal = new char[1][2];
        twoRockHorizontal[0][0] = 'R';
        twoRockHorizontal[0][1] = 'R';

        char[][] LformRockV1 = new char[2][2];
        LformRockV1[0][0] = 'R';
        LformRockV1[0][1] = 'R';
        LformRockV1[1][0] = 'R';

        char[][] LformRockV2 = new char[2][2];
        LformRockV2[0][0] = 'R';
        LformRockV2[0][1] = 'R';
        LformRockV2[1][1] = 'R';

        char[][] LformRockV3 = new char[2][2];
        LformRockV3[1][0] = 'R';
        LformRockV3[0][1] = 'R';
        LformRockV3[1][1] = 'R';

        char[][] LformRockV4 = new char[2][2];
        LformRockV4[0][0] = 'R';
        LformRockV4[1][1] = 'R';
        LformRockV4[1][0] = 'R';

        char[][] TformRock = new char[3][2];
        TformRock[0][0] = 'R';
        TformRock[1][1] = 'R';
        TformRock[2][0] = 'R';

        return new char[][][] {singleRock, twoRockVertical, twoRockHorizontal, LformRockV1, LformRockV2, LformRockV3, LformRockV4,
        TformRock};
    }
    //endregion

    //region <Map Visualisation>
    public Texture GetTexture(int line, int column)
    {
        if(grid[line][column] == 'G' && (line + column) % 2 == 0)
        {
            return ground1Image;
        }
        else if(grid[line][column] == 'G' && (line + column) % 2 == 1)
        {
            return ground2Image;
        }
        else if(grid[line][column] == 'R')
        {
            return rockImage;
        }

        //Normally there should not be other things then G, R
        else
        {
            return ground1Image;
        }
    }

    public void DisplayMap()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
    }

    public int getSeed() {
        return seed;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean IsGroundPosition(int line, int column)
    {
        if(column < height && line < width)
        {
            return grid[line][column] == 'G';
        }
        else
        {
            return false;
        }
    }
    //endregion

    //region <Map Destruction>
    public void Dispose()
    {
        //Dispose all the Textures
        ground1Image.dispose();
        rockImage.dispose();
    }
    //endregion
}
