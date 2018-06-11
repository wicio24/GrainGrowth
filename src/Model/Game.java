package Model;


import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    private Cell[][] tab1;
    private Cell[][] tab2;
    private int tabSizeHeight;
    private int tabSizeWidth;
    private boolean periodic = false;


    public Game(int tabSizeWidth, int tabSizeHeight) {
        this.tabSizeWidth = tabSizeWidth;
        this.tabSizeHeight = tabSizeHeight;


        tab1 = new Cell[tabSizeHeight][tabSizeWidth];
        tab2 = new Cell[tabSizeHeight][tabSizeWidth];

        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {
                tab1[i][j] = new Cell();
                tab2[i][j] = new Cell();
            }

        }
    }

    public enum NeighborhoodType {
        leftPentagonal, rightPentagonal, upPentagonal, downPentagonal, randomPentagonal, leftHexagonal, rightHexagonal, randomHexagonal
    }

    public Cell[][] getTab1() {
        return tab1;
    }

    public Cell[][] getTab2() {
        return tab2;
    }


    public void mooreNeighborhood() {
        int iP, iM, jP, jM;

        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {
                if (periodic) {
                    if (i == 0) iM = tabSizeHeight - 1;
                    else iM = i - 1;

                    if (j == 0) jM = tabSizeWidth - 1;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = 0;
                    else iP = i + 1;


                    if (j == tabSizeWidth - 1) jP = 0;
                    else jP = j + 1;
                } else {
                    if (i == 0) iM = 0;
                    else iM = i - 1;

                    if (j == 0) jM = 0;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = tabSizeHeight - 1;
                    else iP = i + 1;


                    if (j == tabSizeWidth - 1) jP = tabSizeWidth - 1;
                    else jP = j + 1;
                }
                if (tab1[i][j].getState() == 1) {

                    if (tab1[iM][jM].getState() == 0) {
                        tab2[iM][jM].setState(1);
                        tab2[iM][jM].setColor(tab1[i][j].getColor());
                    }
                    if (tab1[iM][j].getState() == 0) {
                        tab2[iM][j].setState(1);
                        tab2[iM][j].setColor(tab1[i][j].getColor());
                    }
                    if (tab1[iM][jP].getState() == 0) {
                        tab2[iM][jP].setState(1);
                        tab2[iM][jP].setColor(tab1[i][j].getColor());
                    }


                    if (tab1[i][jM].getState() == 0) {
                        tab2[i][jM].setState(1);
                        tab2[i][jM].setColor(tab1[i][j].getColor());
                    }
                    if (tab1[i][jP].getState() == 0) {
                        tab2[i][jP].setState(1);
                        tab2[i][jP].setColor(tab1[i][j].getColor());
                    }


                    if (tab1[iP][jM].getState() == 0) {
                        tab2[iP][jM].setState(1);
                        tab2[iP][jM].setColor(tab1[i][j].getColor());
                    }
                    if (tab1[iP][j].getState() == 0) {
                        tab2[iP][j].setState(1);
                        tab2[iP][j].setColor(tab1[i][j].getColor());
                    }
                    if (tab1[iP][jP].getState() == 0) {
                        tab2[iP][jP].setState(1);
                        tab2[iP][jP].setColor(tab1[i][j].getColor());
                    }

                    tab2[i][j].setState(1);
                    tab2[i][j].setColor(tab1[i][j].getColor());
                }

            }
        }

        writeTables();
    }


    public void vonNeumannNeighborhood() {
        int iP, iM, jP, jM;

        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {


                if (periodic) {
                    if (i == 0) iM = tabSizeHeight - 1;
                    else iM = i - 1;

                    if (j == 0) jM = tabSizeWidth - 1;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = 0;
                    else iP = i + 1;


                    if (j == tabSizeWidth - 1) jP = 0;
                    else jP = j + 1;
                } else {
                    if (i == 0) iM = 0;
                    else iM = i - 1;

                    if (j == 0) jM = 0;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = tabSizeHeight - 1;
                    else iP = i + 1;

                    if (j == tabSizeWidth - 1) jP = tabSizeWidth - 1;
                    else jP = j + 1;
                }

                if (tab1[i][j].getState() == 1) {


                    if (tab1[iM][j].getState() == 0) {
                        tab2[iM][j].setState(1);
                        tab2[iM][j].setColor(tab1[i][j].getColor());
                    }


                    if (tab1[i][jM].getState() == 0) {
                        tab2[i][jM].setState(1);
                        tab2[i][jM].setColor(tab1[i][j].getColor());
                    }
                    if (tab1[i][jP].getState() == 0) {
                        tab2[i][jP].setState(1);
                        tab2[i][jP].setColor(tab1[i][j].getColor());
                    }


                    if (tab1[iP][j].getState() == 0) {
                        tab2[iP][j].setState(1);
                        tab2[iP][j].setColor(tab1[i][j].getColor());
                    }


                    tab2[i][j].setState(1);
                    tab2[i][j].setColor(tab1[i][j].getColor());
                }


            }
        }

        writeTables();
    }

    public void pentagonal(NeighborhoodType type) {
        int iP, iM, jP, jM;


        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {


                if (type == NeighborhoodType.randomPentagonal) {
                    Random r = new Random();
                    int temp = r.nextInt(4);
                    if (temp == 0) type = NeighborhoodType.downPentagonal;
                    if (temp == 1) type = NeighborhoodType.rightPentagonal;
                    if (temp == 2) type = NeighborhoodType.upPentagonal;
                    if (temp == 3) type = NeighborhoodType.leftPentagonal;
                }
                if (periodic) {
                    if (i == 0) iM = tabSizeHeight - 1;
                    else iM = i - 1;

                    if (j == 0) jM = tabSizeWidth - 1;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = 0;
                    else iP = i + 1;

                    if (j == tabSizeWidth - 1) jP = 0;
                    else jP = j + 1;
                } else {
                    if (i == 0) iM = 0;
                    else iM = i - 1;

                    if (j == 0) jM = 0;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = tabSizeHeight - 1;
                    else iP = i + 1;

                    if (j == tabSizeWidth - 1) jP = tabSizeWidth - 1;
                    else jP = j + 1;
                }

                if (tab1[i][j].getState() == 1) {

                    if (type != NeighborhoodType.downPentagonal && type != NeighborhoodType.rightPentagonal) {
                        if (tab1[iM][jM].getState() == 0) {
                            tab2[iM][jM].setState(1);
                            tab2[iM][jM].setColor(tab1[i][j].getColor());
                        }
                    }
                    if (type != NeighborhoodType.downPentagonal) {
                        if (tab1[iM][j].getState() == 0) {
                            tab2[iM][j].setState(1);
                            tab2[iM][j].setColor(tab1[i][j].getColor());
                        }
                    }
                    if (type != NeighborhoodType.downPentagonal && type != NeighborhoodType.leftPentagonal) {
                        if (tab1[iM][jP].getState() == 0) {
                            tab2[iM][jP].setState(1);
                            tab2[iM][jP].setColor(tab1[i][j].getColor());
                        }
                    }


                    if (type != NeighborhoodType.rightPentagonal) {
                        if (tab1[i][jM].getState() == 0) {
                            tab2[i][jM].setState(1);
                            tab2[i][jM].setColor(tab1[i][j].getColor());
                        }
                    }
                    if (type != NeighborhoodType.leftPentagonal) {
                        if (tab1[i][jP].getState() == 0) {
                            tab2[i][jP].setState(1);
                            tab2[i][jP].setColor(tab1[i][j].getColor());
                        }
                    }


                    if (type != NeighborhoodType.upPentagonal && type != NeighborhoodType.rightPentagonal) {
                        if (tab1[iP][jM].getState() == 0) {
                            tab2[iP][jM].setState(1);
                            tab2[iP][jM].setColor(tab1[i][j].getColor());
                        }
                    }
                    if (type != NeighborhoodType.upPentagonal) {
                        if (tab1[iP][j].getState() == 0) {
                            tab2[iP][j].setState(1);
                            tab2[iP][j].setColor(tab1[i][j].getColor());
                        }
                    }
                    if (type != NeighborhoodType.upPentagonal && type != NeighborhoodType.leftPentagonal) {
                        if (tab1[iP][jP].getState() == 0) {
                            tab2[iP][jP].setState(1);
                            tab2[iP][jP].setColor(tab1[i][j].getColor());
                        }
                    }
                    tab2[i][j].setState(1);
                    tab2[i][j].setColor(tab1[i][j].getColor());
                }

            }
        }

        writeTables();
    }


    public void hexagonal(NeighborhoodType type) {
        int iP, iM, jP, jM;


        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {


                if (type == NeighborhoodType.randomHexagonal) {
                    Random r = new Random();
                    int temp = r.nextInt(2);
                    if (temp == 0) type = NeighborhoodType.rightHexagonal;
                    if (temp == 1) type = NeighborhoodType.leftHexagonal;
                }
                if (periodic) {
                    if (i == 0) iM = tabSizeHeight - 1;
                    else iM = i - 1;

                    if (j == 0) jM = tabSizeWidth - 1;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = 0;
                    else iP = i + 1;


                    if (j == tabSizeWidth - 1) jP = 0;
                    else jP = j + 1;
                } else {
                    if (i == 0) iM = 0;
                    else iM = i - 1;

                    if (j == 0) jM = 0;
                    else jM = j - 1;

                    if (i == tabSizeHeight - 1) iP = tabSizeHeight - 1;
                    else iP = i + 1;

                    if (j == tabSizeWidth - 1) jP = tabSizeWidth - 1;
                    else jP = j + 1;
                }

                if (tab1[i][j].getState() == 1) {

                    if (type != NeighborhoodType.rightHexagonal) {
                        if (tab1[iM][jM].getState() == 0) {
                            tab2[iM][jM].setState(1);
                            tab2[iM][jM].setColor(tab1[i][j].getColor());
                        }
                    }

                    if (tab1[iM][j].getState() == 0) {
                        tab2[iM][j].setState(1);
                        tab2[iM][j].setColor(tab1[i][j].getColor());
                    }

                    if (type != NeighborhoodType.leftHexagonal) {
                        if (tab1[iM][jP].getState() == 0) {
                            tab2[iM][jP].setState(1);
                            tab2[iM][jP].setColor(tab1[i][j].getColor());
                        }
                    }


                    if (tab1[i][jM].getState() == 0) {
                        tab2[i][jM].setState(1);
                        tab2[i][jM].setColor(tab1[i][j].getColor());
                    }

                    if (tab1[i][jP].getState() == 0) {
                        tab2[i][jP].setState(1);
                        tab2[i][jP].setColor(tab1[i][j].getColor());
                    }


                    if (type != NeighborhoodType.leftHexagonal) {
                        if (tab1[iP][jM].getState() == 0) {
                            tab2[iP][jM].setState(1);
                            tab2[iP][jM].setColor(tab1[i][j].getColor());
                        }
                    }

                    if (tab1[iP][j].getState() == 0) {
                        tab2[iP][j].setState(1);
                        tab2[iP][j].setColor(tab1[i][j].getColor());
                    }

                    if (type != NeighborhoodType.rightHexagonal) {
                        if (tab1[iP][jP].getState() == 0) {
                            tab2[iP][jP].setState(1);
                            tab2[iP][jP].setColor(tab1[i][j].getColor());
                        }
                    }
                    tab2[i][j].setState(1);
                    tab2[i][j].setColor(tab1[i][j].getColor());
                }

            }
        }

        writeTables();
    }

    public int getEnergy(int i, int j, int temp_id) {
        int iP, iM, jP, jM;
        int energy = 0;


        if (periodic) {
            if (i == 0) iM = tabSizeHeight - 1;
            else iM = i - 1;

            if (j == 0) jM = tabSizeWidth - 1;
            else jM = j - 1;

            if (i == tabSizeHeight - 1) iP = 0;
            else iP = i + 1;


            if (j == tabSizeWidth - 1) jP = 0;
            else jP = j + 1;
        } else {
            if (i == 0) iM = 0;
            else iM = i - 1;

            if (j == 0) jM = 0;
            else jM = j - 1;

            if (i == tabSizeHeight - 1) iP = tabSizeHeight - 1;
            else iP = i + 1;


            if (j == tabSizeWidth - 1) jP = tabSizeWidth - 1;
            else jP = j + 1;
        }


        if (tab1[i][j].getState() == 1) {

            if (tab1[iM][jM].getId() != temp_id) {
                energy++;

            }
            if (tab1[iM][j].getState() != temp_id) {
                energy++;
            }
            if (tab1[iM][jP].getState() != temp_id) {
                energy++;
            }


            if (tab1[i][jM].getState() != temp_id) {
                energy++;
            }
            if (tab1[i][jP].getState() != temp_id) {
                energy++;
            }


            if (tab1[iP][jM].getState() != temp_id) {
                energy++;
            }
            if (tab1[iP][j].getState() != temp_id) {
                energy++;
            }
            if (tab1[iP][jP].getState() != temp_id) {
                energy++;
            }


        }
        return energy;
    }


    public void monteCarlo(int grainAmount) {
        Random generator = new Random();
        int temp_energy, new_energy;
        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {
                temp_energy = getEnergy(i, j, tab1[i][j].getId());

                while (true) {

                    if (temp_energy > 0) {

                        tab2[i][j].setId(generator.nextInt(grainAmount)+1);

                        tab2[i][j].setColor(Cell.getById(tab2[i][j].getId()));



                        new_energy = getEnergy(i, j, tab2[i][j].getId());


                        if( new_energy <= temp_energy)
                        {
                            tab1[i][j].setId(tab2[i][j].getId());
                            tab1[i][j].setColor(tab2[i][j].getColor());
                            break;
                        }
                    }



                }//while
            }//for
        }//for

    }


    public void fillRandomly(int startingPoints) {
        Random generator = new Random();
        for (int i = 0; i < startingPoints; i++) {

            int x = generator.nextInt(tabSizeWidth);
            int y = generator.nextInt(tabSizeHeight);
            tab1[y][x].setColor(Color.rgb(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255)));
            tab1[y][x].setState(1);
            tab1[y][x].setId(startingPoints--);
            Cell.putMyMap(tab1[y][x].getId(), tab1[y][x].getColor());

        }

    }

    public void fillEvenlyPlacement(int startingPoints, int distance) {


        Random generator = new Random();
        int maxOnHeight = (int) Math.ceil(tabSizeHeight / (distance + 1.0));
        int maxOnWidth = (int) Math.ceil(tabSizeWidth / (distance + 1.0));
        int maxGrainNumber = maxOnHeight * maxOnWidth;

        int x = 0, y = 0;
        int counter = 1;

        for (int i = 0; i < startingPoints; i++) {
            if (i == maxGrainNumber) break;
            tab1[x][y].setState(1);
            tab1[x][y].setColor(Color.rgb(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255)));
            tab1[y][x].setId(startingPoints--);
            Cell.putMyMap(tab1[y][x].getId(), tab1[y][x].getColor());
            x += distance + 1;
            counter++;

            if (counter > maxOnHeight) {
                counter = 1;
                x = 0;
                y += distance + 1;
            }
        }


    }

    private class Coordinates {
        public int x, y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private Coordinates getUp(int x, int y, int range) {
        int cx = x, cy = y;

        for (int i = 0; i < range; i++) {
            if (cx == 0)
                if (periodic == true) break;
                else cx = tabSizeHeight - 1;
            else cx--;
        }

        return new Coordinates(cx, cy);
    }

    private Coordinates getDown(int x, int y, int range) {
        int cx = x, cy = y;

        for (int i = 0; i < range; i++) {
            if (cx == tabSizeHeight - 1)
                if (periodic == true) break;
                else cx = 0;
            else cx++;
        }

        return new Coordinates(cx, cy);
    }

    private Coordinates getLeft(int x, int y, int range) {
        int cx = x, cy = y;

        for (int i = 0; i < range; i++) {
            if (cy == 0)
                if (periodic == true) break;
                else cy = tabSizeWidth - 1;
            else cy--;
        }

        return new Coordinates(cx, cy);
    }

    private Coordinates getRight(int x, int y, int range) {
        int cx = x, cy = y;

        for (int i = 0; i < range; i++) {
            if (cy == tabSizeWidth - 1)
                if (periodic == true) break;
                else cy = 0;
            else cy++;
        }

        return new Coordinates(cx, cy);
    }

    private void setRadius(int x, int y, int radius) {
        Coordinates startCell = new Coordinates(x, y);
        startCell = getLeft(startCell.x, startCell.y, radius);
        startCell = getUp(startCell.x, startCell.y, radius);

        Coordinates tmpCell = new Coordinates(startCell.x, startCell.y);
        tab2[tmpCell.x][tmpCell.y].setState(1);


        for (int i = 0; i < 2 * radius + 1; i++) {
            for (int j = 0; j < 2 * radius + 1; j++) {
                tmpCell = getRight(tmpCell.x, tmpCell.y, 1);
                tab2[tmpCell.x][tmpCell.y].setState(1);

            }
            tmpCell = getLeft(tmpCell.x, tmpCell.y, 2 * radius + 1);
            tmpCell = getDown(tmpCell.x, tmpCell.y, 1);
            tab2[tmpCell.x][tmpCell.y].setState(1);
        }
    }

    public void fillRandomlyWithRadius(int startingPoints, int radius) {

        Random generator = new Random();

        int counter = 0, limit = 1000;

        int amount = 0;

        for (int i = 0; i < tabSizeHeight; i++)
            for (int j = 0; j < tabSizeWidth; j++)
                if (tab1[i][j].getState() == 0) amount++;


        for (int i = 0; i < startingPoints; i++) {
            if (amount == 0)
                break;

            int x = generator.nextInt(tabSizeWidth);
            int y = generator.nextInt(tabSizeHeight);

            if (tab2[y][x].getState() == 0) {
                tab1[y][x].setColor(Color.rgb(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255)));
                tab1[y][x].setState(1);
                tab1[y][x].setId(startingPoints--);
                tab2[y][x].setId(startingPoints--);
                Cell.putMyMap(tab1[y][x].getId(), tab1[y][x].getColor());
                tab2[y][x].setState(1);
                setRadius(y, x, radius);
            } else {
                i--;

                if (++counter == limit)
                    break;
            }
        }

        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {

                tab2[i][j].setState(0);
                tab2[i][j].setColor(Color.WHITE);
            }
        }
    }


    public void clearGrid() {
        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {
                tab1[i][j].setState(0);
                tab1[i][j].setColor(Color.WHITE);
                tab2[i][j].setState(0);
                tab2[i][j].setColor(Color.WHITE);
            }
        }
Cell.getMyMap().clear();
    }

    public void setTab1Cell(int x, int y, int state) {
        tab1[y][x].setState(state);
    }

    private void writeTables() {
        for (int i = 0; i < tabSizeHeight; i++) {
            for (int j = 0; j < tabSizeWidth; j++) {

                tab1[i][j].setState(tab2[i][j].getState());
                tab1[i][j].setColor(tab2[i][j].getColor());
                tab2[i][j].setState(0);

            }
        }
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }


}
