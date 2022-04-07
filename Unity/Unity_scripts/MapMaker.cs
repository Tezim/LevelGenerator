using UnityEngine;
using UnityEngine.Tilemaps;

public class MapMaker : MonoBehaviour
{
    // GameReader game object, provides char array read from input file
    public FileReader reader;
    // Tile objects 
    public Tile tile1; // L 
    public Tile tile2; // # 
    public Tile tile3; // W 
    public Tile tile4; // S 
    public Tile tile5; // X 
    
    // attach Tilemap object, the tiles will spawn there
    public Tilemap tileMap;
    
    public void Start()
    {
        reader.ReadFile();
        tileMap.ClearAllTiles();
        Create();
    }

    private void Create()
    {
        for (int y = 0; y < reader.GetHeigth(); y++)
        {
            for (int x = 0; x < reader.GetWidth(); x++)
            {
                char[][] map = reader.GetMap();
                switch (map[y][x])
                {
                    case 'L':
                    {
                        tileMap.SetTile(new Vector3Int(-x + reader.GetWidth() / 2, -y + reader.GetHeigth() / 2,0),tile1);
                        break;
                    }
                    case '#':
                    {
                        tileMap.SetTile(new Vector3Int(-x + reader.GetWidth() / 2, -y + reader.GetHeigth() / 2,0),tile2);
                        break;
                    }
                    case 'W':
                    {
                        tileMap.SetTile(new Vector3Int(-x + reader.GetWidth() / 2, -y + reader.GetHeigth() / 2,0),tile3);
                        break;
                    }
                    case 'S':
                    {
                        tileMap.SetTile(new Vector3Int(-x + reader.GetWidth() / 2, -y + reader.GetHeigth() / 2,0),tile4);
                        break;
                    }
                    case 'X':
                    {   
                        tileMap.SetTile(new Vector3Int(-x + reader.GetWidth() / 2, -y + reader.GetHeigth() / 2,0),tile5);
                        break;
                    }
                    case '.':
                    {
                        // empty space
                        break;
                    }
                }
                
            }
        }

    }
}
