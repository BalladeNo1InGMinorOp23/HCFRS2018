package ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import net.sf.json.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SampleController {
    private Canvas canvas;
    private boolean allowDrawing;
    private double startX;
    private double startY;
    private double tmpX;
    private double tmpY;
    private JSONObject jsonObject;
    private int count;
    public SampleController(){
        allowDrawing = false;
    }
    @FXML
    void onMousePressed(MouseEvent event) {
        count = 0;
        jsonObject = new JSONObject();
        canvas = (Canvas)event.getTarget();
        allowDrawing = true;
        startX = event.getSceneX() - canvas.getLayoutX();
        startY = event.getSceneY() - canvas.getLayoutY();
        tmpX = startX;
        tmpY = startY;
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        ctx.moveTo(startX, startY);
    }

    @FXML
    void onMouseReleased(MouseEvent event) {
        allowDrawing = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = df.format(System.currentTimeMillis());
        writeFile("src/result/"+date+".json", jsonObject.toString());
    }

    @FXML
    void onMouseDragged(MouseEvent event) {
        canvas = (Canvas)event.getTarget();
        double x = event.getSceneX() - canvas.getLayoutX();
        double y = event.getSceneY() - canvas.getLayoutY();
        count += 1;
        jsonObject.put("x"+String.valueOf(count), x);
        jsonObject.put("y"+String.valueOf(count), y);
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.BLACK);
        if (allowDrawing == true) {
            ctx.setLineWidth(2);
            ctx.strokeLine(x, y, tmpX, tmpY);
            ctx.stroke();
            tmpX = x;
            tmpY = y;
        }
    }

    void writeFile(String filePath, String sets) {
        try {
            FileWriter fw = new FileWriter(filePath);
            PrintWriter out = new PrintWriter(fw);
            out.write(sets);
            out.println();
            fw.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
