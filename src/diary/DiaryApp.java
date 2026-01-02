package diary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DiaryApp extends Application {

    private Label statusLabel = new Label("Ready"); // <-- global status label

    @Override
    public void start(Stage stage) {

        VBox mainLayout = new VBox(40);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setBackground(bg("#f5f8fc"));

        // Add two sections
        mainLayout.getChildren().addAll(
                buildEntriesJournalUI(),
                buildCalendarTimelineUI()
        );

        ScrollPane scroll = new ScrollPane(mainLayout);
        scroll.setFitToWidth(true);

        stage.setScene(new Scene(scroll, 1200, 900));
        stage.setTitle("Personal Diary Manager");
        stage.show();
    }

    /* ================= UI 1 ================= */
    private BorderPane buildEntriesJournalUI() {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(bg("#ffffff", 25));
        root.setEffect(new DropShadow(20, Color.gray(0, 0.2)));

        /* Sidebar */
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(15));
        sidebar.setPrefWidth(130);
        sidebar.setBackground(bg("#b9d2ff", 25));

        Button todayBtn = namedButton("🏠 Today");
        Button calBtn = namedButton("📅 Calendar");
        Button settingsBtn = namedButton("⚙ Settings");

        todayBtn.setOnAction(e -> show("Today button clicked"));
        calBtn.setOnAction(e -> show("Calendar button clicked"));
        settingsBtn.setOnAction(e -> show("Settings button clicked"));

        sidebar.getChildren().addAll(todayBtn, calBtn, settingsBtn);

        /* Entry list */
        VBox entryList = card(280);
        entryList.getChildren().add(new Label("Entries"));

        ListView<String> list = new ListView<>();
        list.getItems().addAll(
                "Morning Reflections",
                "Project Update",
                "Gratitude Notes"
        );

        entryList.getChildren().add(list);

        /* Content */
        VBox content = card(500);

        Label title = new Label("Gratitude Journal");
        title.setFont(Font.font(20));

        TextArea text = new TextArea("Write your thoughts here...");
        text.setWrapText(true);

        HBox actions = new HBox(10);

        Button likeBtn = namedButton("Like ❤️");
        Button shareBtn = namedButton("Share 🔗");
        Button saveBtn = namedButton("Save 💾");

        likeBtn.setOnAction(e -> show("Entry liked"));
        shareBtn.setOnAction(e -> show("Entry shared"));
        saveBtn.setOnAction(e -> show("Entry saved"));

        actions.getChildren().addAll(likeBtn, shareBtn, saveBtn);

        // Add everything to content
        content.getChildren().addAll(title, text, actions);

        // Add status label at the bottom
        statusLabel.setStyle("-fx-text-fill: #2f80ed; -fx-font-weight: bold;");
        content.getChildren().add(statusLabel);

        HBox center = new HBox(20, entryList, content);

        /* New Entry Button */
        Button newEntry = new Button("New Entry +");
        newEntry.setFont(Font.font(16));
        newEntry.setTextFill(Color.WHITE);
        newEntry.setBackground(bg("#2f80ed", 30));
        newEntry.setOnAction(e -> show("New entry created"));

        StackPane stack = new StackPane(root, newEntry);
        StackPane.setAlignment(newEntry, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(newEntry, new Insets(15));

        root.setLeft(sidebar);
        root.setCenter(center);

        return root;
    }

    /* ================= UI 2 ================= */
    private BorderPane buildCalendarTimelineUI() {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(bg("#ffffff", 25));
        root.setEffect(new DropShadow(20, Color.gray(0, 0.2)));

        VBox calendar = card(260);
        calendar.getChildren().add(new Label("October 2024"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 1; i <= 31; i++) {
            Button day = new Button(String.valueOf(i));
            day.setPrefSize(35, 35);

            int selectedDay = i;
            day.setOnAction(e -> show("Selected day: " + selectedDay));

            grid.add(day, (i - 1) % 7, (i - 1) / 7);
        }

        calendar.getChildren().add(grid);

        VBox timeline = new VBox(15);
        timeline.getChildren().addAll(
                new Label("Tuesday, October 26"),
                timelineEntry("8:15 AM", "Morning Reflections"),
                timelineEntry("1:30 PM", "A Productive Afternoon")
        );

        root.setCenter(new HBox(30, calendar, timeline));
        return root;
    }

    /* ================= HELPERS ================= */
    private VBox timelineEntry(String time, String title) {
        VBox box = card(600);
        box.getChildren().addAll(
                new Label(time),
                new Label(title)
        );
        return box;
    }

    private VBox card(double width) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.setPrefWidth(width);
        box.setBackground(bg("#ffffff", 20));
        box.setEffect(new DropShadow(15, Color.gray(0, 0.15)));
        return box;
    }

    private Button namedButton(String text) {
        Button b = new Button(text);
        b.setPrefWidth(120);
        return b;
    }

    // Updated show method — now updates UI instead of alert
    private void show(String msg) {
        statusLabel.setText(msg); // <-- visible feedback
        System.out.println(msg);
    }

    private Background bg(String color) {
        return new Background(new BackgroundFill(
                Color.web(color), CornerRadii.EMPTY, Insets.EMPTY));
    }

    private Background bg(String color, double radius) {
        return new Background(new BackgroundFill(
                Color.web(color), new CornerRadii(radius), Insets.EMPTY));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
