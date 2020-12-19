package sample;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Board {

    Random rand = new Random();
    double[][] arr;
    double init;
    int n;
    double sw;
    double c;
    private static DecimalFormat df2 = new DecimalFormat("#.####");
    int X;
    int Y;

    public Board(int size) {
        n = size;
        arr = new double[n][n];
        double d = n*n;
        init = 1.0/d;
        sw = 0.8;
        c = 0.2;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                arr[i][j] = init;
            }
        }

        X = rand.nextInt(size);
        Y = rand.nextInt(size);
    }

    void B_print()
    {
        //System.out.println(init+"\n");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                //System.out.println("arr[" + i + "][" + j + "] = " + df2.format(arr[i][j]));
                System.out.print(df2.format(arr[i][j])+"   ");
            }
            System.out.println();
        }
    }

    int side_neighbour(int i, int j)
    {
        int m =0;
        if( (j-1 >= 0) && (j-1 < n))
        {
            m++;
        }
        if( (j+1 >= 0) && (j+1 < n))
        {
            m++;
        }
        if( (i-1 >= 0) && (i-1 < n))
        {
            m++;
        }
        if( (i+1 >= 0) && (i+1 < n))
        {
            m++;
        }

        return m;
    }

    int corner_neighbours(int i , int j)
    {
        int p = 0;
        if( (i-1 >= 0) && (i-1 < n) && (j-1 >= 0) && (j-1 < n))
        {
            p++;
        }
        if( (i+1 >= 0) && (i+1 < n) && (j+1 >= 0) && (j+1 < n))
        {
            p++;
        }
        if( (i-1 >= 0) && (i-1 < n) && (j+1 >= 0) && (j+1 < n))
        {
            p++;
        }
        if( (i+1 >= 0) && (i+1 < n) && (j-1 >= 0) && (j-1 < n))
        {
            p++;
        }
        return p+1;
    }

    void curr_pos(int i, int j)
    {
        ArrayList<point> corner = new ArrayList<>();
        ArrayList<point> side = new ArrayList<>();
        int t = rand.nextInt(10);

        //*****SIDEWAYS****
        if(t>2)
        {
            if( (j-1 >= 0) && (j-1 < n))
            {
                point a = new point(i,j-1);
                side.add(a);
            }
            if( (j+1 >= 0) && (j+1 < n))
            {
                point a = new point(i,j+1);
                side.add(a);
            }
            if( (i-1 >= 0) && (i-1 < n))
            {
                point a = new point(i-1,j);
                side.add(a);
            }
            if( (i+1 >= 0) && (i+1 < n))
            {
                point a = new point(i+1,j);
                side.add(a);
            }

            int t1 = rand.nextInt(side.size());
            point a = side.get(t1);
            X = a.x;
            Y = a.y;
        }


        //*****CORNERS****
        if(t<=2)
        {
            if( (i-1 >= 0) && (i-1 < n) && (j-1 >= 0) && (j-1 < n))
            {
                point a = new point(i-1,j-1);
                corner.add(a);
            }
            if( (i+1 >= 0) && (i+1 < n) && (j+1 >= 0) && (j+1 < n))
            {
                point a = new point(i+1,j+1);
                corner.add(a);
            }
            if( (i-1 >= 0) && (i-1 < n) && (j+1 >= 0) && (j+1 < n))
            {
                point a = new point(i-1,j+1);
                corner.add(a);
            }
            if( (i+1 >= 0) && (i+1 < n) && (j-1 >= 0) && (j-1 < n))
            {
                point a = new point(i+1,j-1);
                corner.add(a);
            }

            point a = new point(i,j);
            corner.add(a);

            int t2 = rand.nextInt(corner.size());
            point d = corner.get(t2);
            X = d.x;
            Y = d.y;

        }

        System.out.println("\nGhost current position: ( "+X+","+Y+")");

    }

    void B_time()
    {
        //double x = 0;
        System.out.println("Ghost current position: ( "+X+","+Y+")");
        double[][] dup = new double[n][n];
        //int temp = rand.nextInt(10);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                double x = 0;
                //*****SIDEWAYS****

                if( (j-1 >= 0) && (j-1 < n))
                {
                    double a = side_neighbour(i,j-1);
                    x += arr[i][j-1] * sw * (1.0/a);
                }
                if( (j+1 >= 0) && (j+1 < n))
                {
                    double a = side_neighbour(i,j+1);
                    x += arr[i][j+1] * sw * (1.0/a);
                }
                if( (i-1 >= 0) && (i-1 < n))
                {
                    double a = side_neighbour(i-1,j);
                    x += arr[i-1][j] * sw * (1.0/a);
                }
                if( (i+1 >= 0) && (i+1 < n))
                {
                    double a = side_neighbour(i+1,j);
                    x += arr[i+1][j] * sw * (1.0/a);
                }

                //*****CORNERS****
                if( (i-1 >= 0) && (i-1 < n) && (j-1 >= 0) && (j-1 < n))
                {
                    double a = corner_neighbours(i-1,j-1);
                    x += arr[i-1][j-1] * c *(1.0/a);
                }
                if( (i+1 >= 0) && (i+1 < n) && (j+1 >= 0) && (j+1 < n))
                {
                    double a = corner_neighbours(i+1,j+1);
                    x += arr[i+1][j+1] * c *(1.0/a);
                }
                if( (i-1 >= 0) && (i-1 < n) && (j+1 >= 0) && (j+1 < n))
                {
                    double a = corner_neighbours(i-1,j+1);
                    x += arr[i-1][j+1] * c * (1.0/a);
                }
                if( (i+1 >= 0) && (i+1 < n) && (j-1 >= 0) && (j-1 < n))
                {
                    double a = corner_neighbours(i+1,j-1);
                    x += arr[i+1][j-1] * c * (1.0/a);
                }

                double a = corner_neighbours(i,j);
                x += arr[i][j] * c * (1.0/a);

                //arr[i][j] = x;
                dup[i][j] = x;
            }
        }



        arr = dup;
        B_print();
        curr_pos(X,Y);

    }

    int update_prob(int p,int q)
    {
        int dist = Math.abs(X-p)+Math.abs(Y-q);
        int d = 0;

        if(dist<=2)
        {
          //COLOUR RED
            // er theke greater than 2.....
            d=1;
            ArrayList<point> temp = new ArrayList<>();
            double sum = 0.0;
            for (int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    int t = Math.abs(p-i)+Math.abs(q-j);
                    if(t>2)
                    {
                        point f = new point(i,j);
                        temp.add(f);
                    }
                    else
                    {
                        sum += arr[i][j];
                    }
                }
            }

            for (point f : temp) {
                arr[f.x][f.y] = 0.0;
            }

            for (int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    if(arr[i][j] != 0.0)
                    {
                        double up = arr[i][j]/sum;
                        arr[i][j] = up;
                    }
                }
            }

        }
        else if(dist>2 && dist<=5)
        {
            //COLOUR YELLOW
            // er theke less than equal 2 & greater than 5....prob 0
            d=2;
            ArrayList<point> temp = new ArrayList<>();
            double sum = 0.0;
            for (int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    int t = Math.abs(p-i)+Math.abs(q-j);
                    if(t<=2 || t>5)
                    {
                        point f = new point(i,j);
                        temp.add(f);
                    }
                    else
                    {
                        sum += arr[i][j];
                    }
                }
            }

            for (point f : temp) {
                arr[f.x][f.y] = 0.0;
            }

            for (int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    if(arr[i][j] != 0.0)
                    {
                        double up = arr[i][j]/sum;
                        arr[i][j] = up;
                    }
                }
            }

        }
        else
        {
            //COLOUR GREEN
            // er theke less than equal 5 dist e jara thakbe.....eder prob 0**********
            // .25 divided by .75
            d = 3;
            ArrayList<point> temp = new ArrayList<>();
            double sum = 0.0;
            for (int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    int t = Math.abs(p-i)+Math.abs(q-j);
                    if(t<=5)
                    {
                        point f = new point(i,j);
                        temp.add(f);
                    }
                    else
                    {
                        sum += arr[i][j];
                    }
                }
            }

            for (point f : temp) {
                arr[f.x][f.y] = 0.0;
            }

            for (int i=0; i<n; i++){
                for(int j=0; j<n; j++) {
                    if(arr[i][j] != 0.0)
                    {
                        double up = arr[i][j]/sum;
                        arr[i][j] = up;
                    }
                }
            }
        }

        // less than .33 red
        // .33 theke .66 orange
        // green
        B_print();
        return d;
    }
}
