using System;
using System.Collections.Generic;
using UnityEngine;

// file manager reads file into char array
public class FileReader : MonoBehaviour
{
    // attach file output from level generator
    public TextAsset file;

    private static int _heigth;
    private static int _width;
    // parsed input map
    private char[][] _map;
    
    public void ReadFile()
    {
        // split lines
        List<string> lines = new List<string>();
        lines.AddRange(file.text.Split("\n"[0]));
        // extract metadata
        String[] strlist = lines[0].Split('x');
        _width = int.Parse(strlist[0]);
        _heigth = int.Parse(strlist[1]);
        // create array of chars
        char[][] charMap = new char[_heigth][];
        for (int i = 1; i < _heigth + 1; i++)
        {
            charMap[i - 1] = lines[i].ToCharArray();
        }
        // character map 
        this._map = charMap;
    }

    public char[][] GetMap()
    {
        return this._map;
    }
    public int GetHeigth()
    {
        return _heigth;
    }
    public int GetWidth()
    {
        return _width;
    }

}
