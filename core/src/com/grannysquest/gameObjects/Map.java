package com.grannysquest.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.Random;

public class Map extends TiledMap {

    private TextureRegion[][] groundTextures;
    private TextureRegion[][] shelfsTextures;
    private TextureRegion[][] collectiblesTextures;

    private MapLayers layers;


    private TiledMapTileLayer ground;
    private TiledMapTileLayer objects;
    private TiledMapTileLayer collectibles;

    private int checkoutX;
    private int checkoutY;

    public Map(int x, int y) {

        groundTextures = TextureRegion.split(new Texture(Gdx.files.internal("media/ground.png")), 64, 64);
        shelfsTextures = TextureRegion.split(new Texture(Gdx.files.internal("media/shelfs.png")), 64, 64);
        collectiblesTextures = TextureRegion.split(new Texture(Gdx.files.internal("media/collectibles.png")), 64, 64);

        ground = new TiledMapTileLayer(x, y, 64, 64);
        objects = new TiledMapTileLayer(x, y, 64, 64);
        collectibles = new TiledMapTileLayer(x, y, 64, 64);

        for (int i = 0; i < ground.getWidth(); i++) {
            for (int j = 0; j < ground.getHeight(); j++) {

                if (i == ground.getWidth() - 1) {
                    if (j == 0) {
                        addWall(2, 2, i, j);
                    } else if (j == ground.getHeight() - 1) {
                        addWall(0, 2, i, j);
                    } else {
                        addWall(1, 2, i, j);
                    }
                } else if (i == 0) {
                    if (j == 0) {
                        addWall(2, 0, i, j);
                    } else if (j == ground.getHeight() - 1) {
                        addWall(0, 0, i, j);
                    } else {
                        addWall(1, 0, i, j);
                    }
                } else {
                    if (j == 0) {
                        addWall(2, 1, i, j);
                    } else if (j == ground.getHeight() - 1) {
                        addWall(0, 1, i, j);
                    } else {

                        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                        cell.setTile(new StaticTiledMapTile(groundTextures[1][1]));
                        ground.setCell(i, j, cell);
                    }
                }


            }
        }


        layers = this.getLayers();

        layers.add(ground);
        layers.add(objects);
        layers.add(collectibles);

        addCheckout(x,y);

        int nbShelfs = 70;

        Random r = new Random();

        while (nbShelfs > 0) {
            int sizeShelf = Math.min(r.nextInt(7) + 3, nbShelfs);
            int orientation = r.nextInt(2);
            int originX = r.nextInt(ground.getHeight() - 1);
            int originY = r.nextInt(ground.getWidth() - 1);

            if (validateShelfPosition(sizeShelf, orientation, originX, originY)) {
                addShelfs(sizeShelf, orientation, originX, originY);
                nbShelfs -= sizeShelf;
            }
        }

        int nbCollectibles = 10;

        while (nbCollectibles > 0) {
            if (addCollectible(r.nextInt(ground.getHeight() - 1) + 1, r.nextInt(r.nextInt(ground.getWidth() - 1) + 1))) {
                nbCollectibles--;
            }
        }


    }

    private void addWall(int x, int y, int xPos, int yPos) {

        TiledMapTile t;
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

        RectangleMapObject myRectangle = new RectangleMapObject(xPos * 64, yPos * 64, 64, 64);

        t = new StaticTiledMapTile(groundTextures[x][y]);
        cell.setTile(t);
        objects.setCell(xPos, yPos, cell);
        objects.getObjects().add(myRectangle);

    }

    private void addShelfs(int number, int orientation, int xPos, int yPos) {

        int tex = (new Random()).nextInt(8);
        int shelfXPos = xPos;
        int shelfYPos = yPos;

        for (int i = 0; i < number; i++) {

            TiledMapTile t;
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

            RectangleMapObject myRectangle = new RectangleMapObject(shelfXPos * 64,
                    shelfYPos * 64,
                    64,
                    64);

            objects.getObjects().add(myRectangle);

            t = new StaticTiledMapTile(shelfsTextures[0][tex]);
            cell.setTile(t);
            objects.setCell(shelfXPos, shelfYPos, cell);

            shelfXPos = orientation == 0 ? shelfXPos + 1 : shelfXPos;
            shelfYPos = orientation == 1 ? shelfYPos + 1 : shelfYPos;

        }

    }

    public boolean addCollectible(int xPos, int yPos) {
        if (objects.getCell(xPos, yPos) != null) {
            return false;
        }

        TiledMapTile t;
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();


        RectangleMapObject myRectangle = new RectangleMapObject(xPos * 64,
                yPos * 64,
                64,
                64);

        collectibles.getObjects().add(myRectangle);

        TextureRegion tex = collectiblesTextures[0][(new Random()).nextInt(collectiblesTextures[0].length)];

        t = new StaticTiledMapTile(tex);
        cell.setTile(t);
        collectibles.setCell(xPos, yPos, cell);

        return true;

    }

    private boolean validateShelfPosition(int number, int orientation, int xPos, int yPos) {

        for (int i = xPos; i < (orientation == 0 ? xPos + number : xPos) + 1; i++) {
            for (int j = yPos; j < (orientation == 1 ? yPos + number : yPos) + 1; j++) {

                for (int deltaX = -2; deltaX < 3; deltaX++) {
                    for (int deltaY = -2; deltaY < 3; deltaY++) {
                        if (i == (ground.getWidth() / 2) + deltaX && j == (ground.getHeight() / 2) + deltaY) {
                            return false;
                        }
                    }

                }
                for (int k = Math.max(0, i - 3); k < Math.min(i + 3, objects.getHeight()); k++) {
                    for (int l = Math.max(0, i - 3); l < Math.min(i + 3, objects.getWidth()); l++) {
                        if (objects.getCell(k, l) != null) {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

    private void addCheckout(int xMax, int yMax) {

        Random r = new Random();

        checkoutX = r.nextInt(xMax-1) + 1;
        checkoutY = r.nextInt(yMax-2) +1;

        RectangleMapObject myRectangle = new RectangleMapObject(getCheckoutX() * 64,
                getCheckoutY() * 64,
                64,
                128);

        objects.getObjects().add(myRectangle);
    }


    public int getCheckoutX() {
        return checkoutX;
    }

    public int getCheckoutY() {
        return checkoutY;
    }
}
