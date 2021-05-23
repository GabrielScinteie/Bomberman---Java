package PaooGame.Maps;

import PaooGame.Exceptions.NotEnoughElementsException;
import PaooGame.Exceptions.UnkownTileException;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Map2 implements Map{

    public Map2(RefLinks refLinks){
        /// Retine referinta "shortcut".
        this.refLink = refLinks;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
    }

    protected RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    protected int width;          /*!< Latimea hartii in numar de dale.*/
    protected int height;         /*!< Inaltimea hartii in numar de dale.*/
    protected int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    protected int alreadyRead = 0; /* Variabila ce ne spune daca harta a fost incarcata pentru a se incarca doar o data.*/
    protected int[][] map;        /* O variabila temporala ce o folosesc pentru a incarca harta.*/
    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public int getWidth(){return width;}
    public int getHeight(){return height;}

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {

        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                GetTile(x, y).Draw(g, (int)(x * Tile.TILE_WIDTH - refLink.GetCamera().getRect().x), (int)(y * Tile.TILE_HEIGHT - refLink.GetCamera().getRect().y));
            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.grassTile;
        }
        return t;
    }

    public void SetTile(int x, int y, int code){
        tiles[x][y] = code;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */

    public void LoadWorld()
    {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 60;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 30;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        LoadMap();
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = GetMapTileCode(y, x);

            }
        }
    }

    public int[][] readMap(int noLines, int noCol) throws Exception{

        int[][] map = new int[noLines][noCol];

        String fileName = "Map2";
        String filePath = "";

        BufferedReader reader;
        Connection connection = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:game.db");
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Files where Name = '" + fileName + "'");

            while(rs.next()){
                filePath = rs.getString("Path");
            }

            rs.close();
            stmt.close();
            connection.close();
        }catch ( Exception e ) {
            System.out.println("Eroare la database");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        for(int i = 0; i < noLines;  i++){
            for(int j = 0; j < noCol; j++){
                // Daca nu mai este element nu sunt pe ultima pozitie
                if(scanner.hasNext() == false)
                    if(i != noLines - 1 || j != noCol - 1)
                        throw new NotEnoughElementsException("Not enough elements to be read in the map");
                map[i][j] = scanner.nextInt();
            }
        }

        /*
        for(int i = 0; i < noLines;  i++){
            for(int j = 0; j < noCol; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        */
        for(int i = 0; i < noLines;  i++){
            for(int j = 0; j < noCol; j++){
                if(map[i][j] != 0 && map[i][j] != 1 && map[i][j] != 2)
                    throw new UnkownTileException("Unkown tile code");
            }
        }

        return map;
    }

    public void LoadMap(){
        try{
            if(alreadyRead == 0){
                int noLines = 30;
                int noCol = 60;
                alreadyRead = 1;
                map = readMap(noLines,noCol);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(height + " " + width);
            map = new int[height][width];
            for(int xx = 0; xx < width; xx++)
                for(int yy = 0; yy < height; yy++)
                {
                    if(xx == 0 || xx == 1 || xx == 58 || xx == 59 || yy == 0 || yy == 1 || yy == 28 || yy == 29)
                    {
                        map[yy][xx] = 1;
                    }
                    else
                    {
                        map[yy][xx] = 0;
                    }
                }
            e.printStackTrace();
        }
    }

    public int GetMapTileCode(int x ,int y)
    {
        return map[x][y];
    }
}
