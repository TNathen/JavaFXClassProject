/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Nathen
 */ 
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField display;
    
    @FXML
    private MenuItem itmStandard, itmScientific, aEasy, sEasy, mEasy, dEasy,
            aMedium, sMedium, mMedium, dMedium, aHard, sHard, mHard, dHard;
    
    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, 
            btnA, btnS, btnD, btnM, btnEQ, btnPM, btnDec, btnC, btnCE,
            btnDel, btnMod, btnTan, btnEx, btnSqrt, btnXY, btnLog, btnCos, btnN,
            btnClose, btnOpen, btnPi, btnSin, btn10X, btnSq;
    
    @FXML 
    AnchorPane AnchorPane;
    
    private String displayCon = "0";
    private String val,opVal, opType = "";
    private String gameOp, difficulty = "easy";
    boolean lastInWasOp = false, isDot = false, gameOn = false;
    public double num1 = 0, num2 = 0, total = 0;
    public int first, second, answer;
    
   
    @FXML
    private void handleNum(ActionEvent event){

        val = ((Button) event.getSource()).getText();
        
        if(lastInWasOp == true){
            num1 = Double.parseDouble(displayCon);            
            displayCon = "0";
            display.setText(displayCon);
            isDot = false;
            lastInWasOp = false;
        }
        
        if("0".equals(val) && displayCon.equals("0")){
            display.setText(displayCon);
        }else if(displayCon.equals("0") && !"0".equals(val)){
            displayCon = val;
            display.setText(displayCon);
        }else{
            displayCon += val;
            display.setText(displayCon);
        }
        
    }
    
    
    @FXML
    private void handleOP(ActionEvent event){
        opVal = ((Button) event.getSource()).getText();
        
        lastInWasOp = true;
        
        if(opType.equals("")){
            opType = opVal;
        }else{
            num2 = Double.parseDouble(displayCon);
            switch (opType) {
                case "+":
                    total = num1 + num2;
                    break;
                case "-":
                    total = num1 - num2;
                    break;
                case "*":
                    total = num1 * num2;
                    break;
                case "/":
                    if(num2 == 0){
                        total = 0;
                    }else{
                        total = num1 / num2;
                    }   break;
                case "Mod":
                    total = num1 % num2;
                    break;
                case "x^y":
                    total = Math.pow(num1, num2);
                    break;    
                default:
                    System.out.println("Error");
                    break;
            }
            displayCon = Double.toString(total);
            display.setText(displayCon);
            opType = opVal;
        }
    }
    
    @FXML
    private void handlePM(ActionEvent event){
        if(displayCon.startsWith("-")){
            int len = displayCon.length();
            String remove = (String) displayCon.subSequence(1, len);
            displayCon = remove;
            display.setText(displayCon);
        }else if(!displayCon.equals("0")){
            displayCon = "-" + displayCon;
            display.setText(displayCon);
        }else{
            
        }
    }
    
    @FXML
    private void handleDot(ActionEvent event){
        if(!displayCon.contains(".")){
            val = ((Button) event.getSource()).getText();displayCon += val;
            display.setText(displayCon);
            isDot = true;
        }else{
        
        }
    }
    
    @FXML
    private void handleCE(ActionEvent event){
        displayCon = "0";
        val = "";
        opVal = "";
        opType = "";
        lastInWasOp = false;
        isDot = false;
        num1 = 0;
        num2 = 0;
        display.setText(displayCon);
    }
    
    @FXML
    private void handleC(ActionEvent event){
        displayCon = "0"; 
        display.setText(displayCon);
    }
    
    @FXML
    private void handleEq(ActionEvent event){
        String use = "";
        
        if(opType.equals("")){

        }else{
            switch (opType) {
                case "+":
                    num2 = Double.parseDouble(displayCon);
                    total = num1 + num2;
                    break;
                case "-":
                    num2 = Double.parseDouble(displayCon);
                    total = num1 - num2;
                    break;
                case "*":
                    num2 = Double.parseDouble(displayCon);
                    total = num1 * num2;
                    break;
                case "/":
                    num2 = Double.parseDouble(displayCon);
                    if(num2 == 0){
                        total = 0;
                    }else{
                        total = num1 / num2;
                    }   break;
                case "Mod":
                    num2 = Double.parseDouble(displayCon);
                    total = num1 % num2;
                    break;
                case "Tan":
                    use = displayCon.substring(4, displayCon.length()-1);
                    total = Math.tan(Double.parseDouble(use));
                    break;
                case "Cos":
                    use = displayCon.substring(4, displayCon.length()-1);
                    total = Math.cos(Double.parseDouble(use));
                    break;
                case "Sin":
                    use = displayCon.substring(4, displayCon.length()-1);
                    total = Math.sin(Double.parseDouble(use));
                    break;
                case "log":
                    use = displayCon.substring(4, displayCon.length()-1);
                    total = Math.log(Double.parseDouble(use));
                    break;                    
                case "Sqrt":
                    use = displayCon.substring(5, displayCon.length()-1);
                    total = Math.sqrt(Double.parseDouble(use));
                    break;                    
                case "x^y":
                    num2 = Double.parseDouble(displayCon);
                    total = Math.pow(num1, num2);
                    break;
                    
                default:
                    System.out.println("Error");
                    break;
            }
            displayCon = Double.toString(total);
            display.setText(displayCon);
            displayCon = "0";
            val = "";
            opVal = "";
            opType = "";
            lastInWasOp = false;
            isDot = false;
            num1 = 0;
            num2 = 0;
        }
    }

    @FXML
    private void handleSwitch(ActionEvent event) throws IOException{
        Stage stage;
        Parent root = null;
        if(event.getSource() == itmStandard){
            stage = (Stage) display.getScene().getWindow();            
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        }else if(event.getSource() == itmScientific){
            stage = (Stage) display.getScene().getWindow(); 
            root = FXMLLoader.load(getClass().getResource("FXMLDocument2.fxml"));
        }else{
            stage = (Stage) display.getScene().getWindow(); 
        }
        gameOn = false;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    private void handleGame(ActionEvent event) throws IOException{
        if(event.getSource() == aEasy){
            difficulty = "easy";
            gameOp = "add";
        }else if(event.getSource() == aMedium){
            difficulty = "medium";
            gameOp = "add";
        }else if(event.getSource() == aHard){
            difficulty = "hard";
            gameOp = "add";
        }else if(event.getSource() == sEasy){
            difficulty = "easy";
            gameOp = "sub";
        }else if(event.getSource() == sMedium){
            difficulty = "medium";
            gameOp = "sub";
        }else if(event.getSource() == sHard){
            difficulty = "hard";
            gameOp = "sub";
        }else if(event.getSource() == mEasy){
            difficulty = "easy";
            gameOp = "mult";
        }else if(event.getSource() == mMedium){
            difficulty = "medium";
            gameOp = "mult";
        }else if(event.getSource() == mHard){
            difficulty = "hard";
            gameOp = "mult";
        }else if(event.getSource() == dEasy){
            difficulty = "easy";
            gameOp = "divide";
        }else if(event.getSource() == dMedium){
            difficulty = "medium";
            gameOp = "divide";
        }else if(event.getSource() == dHard){
            difficulty = "hard";
            gameOp = "divide";
        }else{
            gameOp="add";
        }
        
        Stage stage;
        Parent root;
        stage = (Stage) display.getScene().getWindow();            
        root = FXMLLoader.load(getClass().getResource("FXMLDocumentGame.fxml"));
        gameOn = true;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        startGame();
    }
    
    
    @FXML
    private void startGame(){
        
        switch (difficulty) {
            case "easy":
                System.out.println("Game is easy");
                first = (int) (Math.random() * 10);
                second = (int) (Math.random() * 10);
                break;
            case "medium":
                System.out.println("Game is Medium");
                first = (int) (Math.random() * 100);
                second = (int) (Math.random() * 100);
                break;
            case "hard":
                System.out.println("Game is Hard");
                first = (int) (Math.random() * 1000);
                second = (int) (Math.random() * 1000);
                break;
            default:
                break;
        }
        
        switch (gameOp) {
            case "add":
                displayCon = first + "+" + second;
                answer = first + second;
                System.out.println(displayCon +"=" + answer);
                display.setText(displayCon);
                break;
            case "sub":
                displayCon = first + "-" + second;
                answer = first - second;
                System.out.println(displayCon +"=" + answer);
                display.setText(displayCon);
                break;
            case "mult":
                displayCon = first + "*" + second;
                answer = first * second;
                System.out.println(displayCon +"=" + answer);
                display.setText(displayCon);
                break;
            case "divide":
                
                if(first < second){
                    int temp;
                    temp = first;
                    first = second;
                    second = temp;
                }
                while(first%second != 0){
                    second = second - 1;
                }
                answer = first/second;
                displayCon = first + "/" + second;
                System.out.println(displayCon +"=" + answer);
                display.setText(displayCon);
                break;
            default:
                break;
        }
        
    }
    
    @FXML
    public void handleDel(ActionEvent event){
        displayCon = display.getText();
        displayCon = displayCon.substring(0, displayCon.length()-1);
        display.setText(displayCon);
    }

    @FXML
    public void handleTrig(ActionEvent event){
        opVal =((Button) event.getSource()).getText();
        opType = opVal;
        if(displayCon.equals("0")){
            displayCon = opType + "(";
            display.setText(displayCon);
        }else{
            displayCon = opType + "(";
            display.setText(displayCon);
        }
    }

    @FXML
    public void handleSingleEx(ActionEvent event){
        opVal =((Button) event.getSource()).getText();
        switch (opVal) {
            case "e^x":
                displayCon = Double.toString(Math.pow(Math.E,Double.parseDouble(displayCon)));
                break;
            case "10^x":
                displayCon = Double.toString(Math.pow(10,Double.parseDouble(displayCon)));
                break;
            case "x^2":
                displayCon = Double.toString(Math.pow(Double.parseDouble(displayCon),2));
                break;
            case "n!":
                int n = Integer.parseInt(displayCon);
                int fact = n;
                while(n > 1){
                    n = n-1;
                    fact *= n;
                }
                displayCon = Integer.toString(fact);
                break;                
            default:
                break;
        }
        display.setText(displayCon);
    }
    
    @FXML
    public void handleOpenP(ActionEvent event){
        displayCon = displayCon + "(";
        display.setText(displayCon);
    }
    
    @FXML
    public void handleCloseP(ActionEvent event){
        displayCon = displayCon +")";
        display.setText(displayCon);
    }
    
    @FXML
    public void handlePI(ActionEvent event){
        displayCon = Double.toString(Math.PI);
        display.setText(displayCon);
    }
        
    
    
    @FXML
    public ComboBox comSize;
    
    
    @FXML
    private void handleSettings(ActionEvent event) throws IOException{
        Stage stage = new Stage(); 
 
    BorderPane layout;    
    GridPane grid;
    Scene scene;

    Label lbl1, lbl2, lbl3, lbl4, lbl5;
    Button btnEnter, btnCancel;

            
        //Creates new grid
        grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(10);
         
        
        //Creates all color blocks with Rectangle objects
        Rectangle red = new Rectangle(20, 10, Color.RED);
        Rectangle white = new Rectangle(20, 10, Color.WHITE);
        Rectangle gray = new Rectangle(20, 10, Color.GRAY);
        Rectangle black= new Rectangle(20, 10, Color.BLACK);
        Rectangle yellow = new Rectangle(20, 10, Color.YELLOW);
        Rectangle orange = new Rectangle(20, 10, Color.ORANGE);
        Rectangle green = new Rectangle(20, 10, Color.GREEN);
        Rectangle blue = new Rectangle(20, 10, Color.BLUE);
        Rectangle violet = new Rectangle(20, 10, Color.VIOLET);
        Rectangle pink = new Rectangle(20, 10, Color.PINK);
        Rectangle de = new Rectangle(20, 10, Color.web("#F4F4F4"));
        
        //Call back for Cell Factory of filler and stroke color
        //Displays rectangle with according colors in a specific cell
        Callback cbc = new Callback<ListView<Rectangle>, ListCell<Rectangle>>(){
            @Override
            public ListCell<Rectangle> call(ListView<Rectangle> param) {
                return new ListCell<Rectangle>(){
                    
                    //Default Rectangle
                    private final Rectangle rec;
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        rec = new Rectangle (20, 10);
                    }
                    
                    @Override
                    public void updateItem(Rectangle itm, boolean empty){
                        super.updateItem(itm, empty);
                        
                        if(itm == null || empty){
                            setGraphic(null);
                            
                        //Sets color of rectangle and displays graphics
                        }else{
                            rec.setFill(itm.getFill());
                            setGraphic(rec);
                        }
                    }
                };
            }
        };
        
        //Creates Fill ComboBox and sets Cell Factory
        ComboBox <Rectangle> fontColor = new ComboBox<>();
        fontColor.getItems().addAll(black,red, de, white, gray, yellow, orange, green, blue, violet, pink);
        fontColor.getSelectionModel().selectFirst();
        fontColor.setCellFactory(cbc);
        
        //Creates OutLine ComboBox for the stroke color and sets Cell Factory
        ComboBox <Rectangle> bgColor = new ComboBox <>();
        bgColor.getItems().addAll(de, black, red, white, gray, yellow, orange, green, blue, violet, pink);
        bgColor.getSelectionModel().selectFirst();
        bgColor.setCellFactory(cbc);
        
        ComboBox <Rectangle> btnColor = new ComboBox <>();
        btnColor.getItems().addAll(gray, black, red, white, de, yellow, orange, green, blue, violet, pink);
        btnColor.getSelectionModel().selectFirst();
        btnColor.setCellFactory(cbc);        
        
        ComboBox fontSize = new ComboBox();
        fontSize.getItems().addAll("10", "15", "20", "25", "30");
        fontSize.getSelectionModel().selectFirst();
        ComboBox fontStyle = new ComboBox();
        fontStyle.getItems().addAll("Arial", "Kai", "Onyx", "Serif", "Times New Roman");
        fontStyle.getSelectionModel().selectFirst();
        
        //Creates labels
        lbl1 = new Label("Font Size:");
        lbl2 = new Label("Font Style:");
        lbl3 = new Label("Font Color:");
        lbl4 = new Label("Background Color:");
        lbl5 = new Label ("Button Color:");
        
        btnEnter = new Button("   Ok   ");
        btnEnter.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){

                String font = fontSize.getValue().toString();
                String style = fontStyle.getValue().toString();
                
                String fColor = fontColor.getValue().getFill().toString();
                fColor = fColor.substring(2, fColor.length());
                String bkgColor = bgColor.getValue().getFill().toString();
                bkgColor = bkgColor.substring(2, bkgColor.length());
                String bColor = btnColor.getValue().getFill().toString();
                bColor = bColor.substring(2, bColor.length());
                
                int disSize = Integer.parseInt(fontSize.getValue().toString()) * 2;

                String buttonSettings = "-fx-font: " +font+ " " + style + ";" + " -fx-base: #" + bColor + "; " + "-fx-text-fill: #" +fColor+";";
                String disSettings = "-fx-font: " +disSize+ " " + style + ";" + "-fx-text-fill: #" +fColor+";";
                String bgSettings = "-fx-background-color: #" +bkgColor+";";
                btn1.setStyle(buttonSettings);
                btn2.setStyle(buttonSettings);
                btn3.setStyle(buttonSettings);
                btn4.setStyle(buttonSettings);
                btn5.setStyle(buttonSettings);
                btn6.setStyle(buttonSettings);
                btn7.setStyle(buttonSettings);
                btn8.setStyle(buttonSettings);
                btn9.setStyle(buttonSettings);
                btn0.setStyle(buttonSettings);
                btnA.setStyle(buttonSettings);
                btnS.setStyle(buttonSettings);
                btnM.setStyle(buttonSettings);
                btnD.setStyle(buttonSettings);
                btnEQ.setStyle(buttonSettings);
                btnPM.setStyle(buttonSettings);
                btnDec.setStyle(buttonSettings);
                btnC.setStyle(buttonSettings);
                btnCE.setStyle(buttonSettings);
                btnDel.setStyle(buttonSettings);
                btnMod.setStyle(buttonSettings);
                btnTan.setStyle(buttonSettings);
                btnEx.setStyle(buttonSettings);
                btnSqrt.setStyle(buttonSettings);
                btnXY.setStyle(buttonSettings);
                btnLog.setStyle(buttonSettings);
                btnCos.setStyle(buttonSettings);
                btnN.setStyle(buttonSettings);
                btnClose.setStyle(buttonSettings);
                btnOpen.setStyle(buttonSettings);
                btnPi.setStyle(buttonSettings);
                btnSin.setStyle(buttonSettings);
                btn10X.setStyle(buttonSettings);
                btnSq.setStyle(buttonSettings);
                display.setStyle(disSettings);
                AnchorPane.setStyle(bgSettings);
                stage.close();

            }
        });
        
        btnCancel = new Button("Cancel");
        btnCancel.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
                stage.close();
            }
        });
        
        //Adds labels to the grid
        grid.add(lbl1, 0, 0);
        grid.add(lbl2, 0, 1);
        grid.add(lbl3, 0, 2);
        grid.add(lbl4, 0, 3);
        grid.add(lbl5, 0, 4);
               
        //Adds ComboBoxs and TextField to grid
        grid.add(fontSize, 2, 0);
        grid.add(fontStyle, 2, 1);
        grid.add(fontColor, 2, 2);
        grid.add(bgColor, 2, 3);
        grid.add(btnColor, 2, 4);
        
        grid.add(btnEnter, 1, 5);
        grid.add(btnCancel, 2, 5);
        

        
        //Adds Creates BorderPane and adds MenuBar, Grid and Anchor to the pane
        layout = new BorderPane();
        grid.setPadding(new Insets(50,0,0,100));
        
        
        scene = new Scene(grid, 500, 270);
        stage.setScene(scene);
        stage.setTitle("Preferences");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
          
        

    }
    

    @FXML
    private void handleClose(ActionEvent event){
        Platform.exit();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
    
}
