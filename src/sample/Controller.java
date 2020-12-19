package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Controller extends VBox {

    int dim;

    Button[][] grid;
    HBox[] h;
    private static DecimalFormat df2 = new DecimalFormat("#.####");
    HashMap<Button, point> map = new HashMap<>();
    Button show;
    Button forward;
    Button Catch;
    Board b;

    //int size;
    boolean showflag ;
    boolean check;

    public Controller(int size) {

        check = false;
        showflag = false;
        dim = size;
        b= new Board(dim);

        grid = new Button[dim][dim];
        h = new HBox[dim + 1];

        for (int i = 0; i < dim; i++) {
            HBox m = new HBox();
            for (int j = 0; j < dim; j++) {

                Button p = new Button();

                p.setPrefHeight(70.0);
                p.setPrefWidth(100.0);
                p.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");
                /*int x = i;
                int y = j;
                p.setOnAction(e -> {
                    b.update_prob(x,y);
                });*/

                grid[i][j] = p;
                m.getChildren().add(grid[i][j]);
                h[i] = m;
                //new
                point g = new point(i,j);
                map.put(p,g);
            }
            this.getChildren().add(h[i]);
        }
        //new start
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Button s = grid[i][j];
                s.setOnAction(e -> {
                    point g = map.get(s);
                    int d = b.update_prob(g.x,g.y);
                    //s.setStyle("-fx-background-color: #800000");
                    if(d==1)
                    {
                        s.setStyle("-fx-background-color: #ff0000");
                    }
                    else if(d==2)
                    {
                        s.setStyle("-fx-background-color: #ffff00");
                    }
                    else
                    {
                        s.setStyle("-fx-background-color: #00ff00");
                    }
                    if(check)
                    {
                        if((b.X == g.x) && (b.Y == g.y))
                        {
                            s.setStyle("-fx-background-color: #000000");
                            System.out.println("\n******Hurray******\n***GHOST CAPTURED***");
                        }
                    }

                });
            }
        }
        //new end
        HBox m = new HBox();
        h[dim] = m;

        Button t1 = new Button();
        t1.setText("Show");

        t1.setPrefHeight(70.0);
        t1.setPrefWidth(100.0);
        t1.setStyle("-fx-background-color: #ff00ff");

        t1.setOnAction(e -> {
            show.setText("Show");
            System.out.println("Show the Ghost!!");

            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    grid[i][j].setText(String.valueOf(df2.format(b.arr[i][j])));
                }
            }
        });
        show = t1;

        Button t2 = new Button();
        t2.setText("Forward");

        t2.setPrefHeight(70.0);
        t2.setPrefWidth(100.0);
        t2.setStyle("-fx-background-color: #ffa500");
        t2.setOnAction(e -> {
            System.out.println("Forward button clicked!! ");
            b.B_time();
            if(showflag){
                for (int i=0; i<dim; i++){
                    for(int j=0; j<dim; j++) {
                        grid[i][j].setText(String.valueOf(df2.format(b.arr[i][j])));
                    }
                }
                showflag = false;
                show.setText("Show");
            }
        });
        forward = t2;

        Button t3 = new Button();
        t3.setText("Catch");
        t3.setPrefHeight(70.0);
        t3.setPrefWidth(100.0);
        t3.setStyle("-fx-background-color: #00ffff");
        t3.setOnAction(e -> {
            System.out.println(" Check for GHOST !!! ");
            check = true;
        });
        Catch = t3;

        h[dim].getChildren().addAll(show,forward,Catch);

        this.getChildren().add(h[dim]);
    }

}
