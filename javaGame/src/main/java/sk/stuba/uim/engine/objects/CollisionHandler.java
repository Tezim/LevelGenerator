package sk.stuba.uim.engine.objects;

import sk.stuba.uim.engine.core.MainPanel;
import sk.stuba.uim.engine.map.Tile;

public class CollisionHandler {
    private MainPanel panel;

    public CollisionHandler(){}
    public CollisionHandler(MainPanel panel){
        this.panel = panel;
    }
    public void check(Object tile){
        int leftWX = ((tile.x - tile.screenX) + 10);
        int rightWX = ((tile.x - tile.screenX) +  (tile.collisinArea.width));
        int upWY = ((tile.y + (tile.screenY)) -  (tile.collisinArea.height/2));
        int downWY = ((tile.y + (tile.screenY)) +  (tile.collisinArea.height) - 10);

        int leftCol = leftWX / 48;
        int rightCol = rightWX / 48;
        int topRow = upWY / 48 - 5;
        int downRow = downWY / 48 - 5;

        // magic
        switch (tile.diretion){
            case 0:{  // down
                downRow = ((downWY - tile.move_px) / 48 ) - 5;
                Tile tile1 = panel.getTileManager().getTile(panel.getTileManager().tilemap[leftCol][downRow]);
                Tile tile2 = panel.getTileManager().getTile(panel.getTileManager().tilemap[rightCol][downRow]);
                if (tile1 == null || tile2 == null)
                    return;
                if (tile1.collision && tile2.collision)
                    tile.collision = true;
                if (tile.build){
                    if (panel.getTileManager().tilemap[leftCol][downRow].equals("S") || panel.getTileManager().tilemap[rightCol][downRow].equals("S")){
                        panel.getTileManager().tilemap[leftCol][downRow] = "C";
                        System.out.println("Catsle down");
                    }
                    break;
                }
                break;
            }
            case 1:{ // up
                topRow = ((upWY + tile.move_px) / 48) - 5;
                Tile tile1 = panel.getTileManager().getTile(panel.getTileManager().tilemap[leftCol][topRow]);
                Tile tile2 = panel.getTileManager().getTile(panel.getTileManager().tilemap[rightCol][topRow]);
                if (tile1 == null || tile2 == null)
                    return;
                if (tile1.collision && tile2.collision)
                    tile.collision = true;
                if (tile.build){
                    if (panel.getTileManager().tilemap[leftCol][topRow].equals("S") || panel.getTileManager().tilemap[rightCol][topRow].equals("S")){
                        panel.getTileManager().tilemap[leftCol][topRow] = "C";
                        System.out.println("Catsle up");
                    }
                    break;
                }
                break;
            }
            case 2:{ // left
                leftCol = (leftWX + tile.move_px) / 48;
                Tile tile1 = panel.getTileManager().getTile(panel.getTileManager().tilemap[leftCol][topRow]);
                Tile tile2 = panel.getTileManager().getTile(panel.getTileManager().tilemap[leftCol][downRow]);
                if (tile1 == null || tile2 == null)
                    return;
                if (tile1.collision && tile2.collision)
                    tile.collision = true;
                if (tile.build){
                    if (panel.getTileManager().tilemap[leftCol][topRow].equals("S") || panel.getTileManager().tilemap[leftCol][downRow].equals("S")){
                        panel.getTileManager().tilemap[leftCol][topRow] = "C";
                        System.out.println("Catsle left");
                    }
                    break;
                }
                break;
            }
            case 3:{ // right
                rightCol = (rightWX + tile.move_px) / 48;
                Tile tile1 = panel.getTileManager().getTile(panel.getTileManager().tilemap[rightCol][topRow]);
                Tile tile2 = panel.getTileManager().getTile(panel.getTileManager().tilemap[rightCol][downRow]);
                if (tile1 == null || tile2 == null)
                    return;
                if (tile1.collision && tile2.collision)
                    tile.collision = true;
                if (tile.build){
                    if (panel.getTileManager().tilemap[rightCol][topRow].equals("S") || panel.getTileManager().tilemap[rightCol][downRow].equals("S")){
                        panel.getTileManager().tilemap[rightCol][topRow] = "C";
                        System.out.println("Catsle right");
                    }
                    break;
                }
                break;
            }
            default:break;
        }
    }
}
