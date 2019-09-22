package com.ubaid.app.model.logger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import com.ubaid.app.controller.Controller;


class Log
{
    private static final int MAX_LOG_ENTRIES = 1_000_000;

    private final BlockingDeque<LogRecord> log = new LinkedBlockingDeque<>(MAX_LOG_ENTRIES);

    public void drainTo(Collection<? super LogRecord> collection)
    {
        log.drainTo(collection);
    }

    public void offer(LogRecord record)
    {
        log.offer(record);
    }
}



class LogRecord
{
    private Date   timestamp;
    private Level  level;
    private String context;
    private String message;

    public LogRecord(Level level, String context, String message)
    {
        this.timestamp = new Date();
        this.level     = level;
        this.context   = context;
        this.message   = message;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public Level getLevel()
    {
        return level;
    }

    public String getContext()
    {
        return context;
    }

    public String getMessage()
    {
        return message;
    }
}

class LogView extends ListView<LogRecord>
{
    private static final int MAX_ENTRIES = 100_000;

    private final static PseudoClass debug = PseudoClass.getPseudoClass("debug");
    private final static PseudoClass info  = PseudoClass.getPseudoClass("info");
    private final static PseudoClass warn  = PseudoClass.getPseudoClass("warn");
    private final static PseudoClass error = PseudoClass.getPseudoClass("error");

    private final static SimpleDateFormat timestampFormatter = new SimpleDateFormat("HH:mm:ss.SSS");

    private final BooleanProperty       showTimestamp = new SimpleBooleanProperty(false);
    private final ObjectProperty<Level> filterLevel   = new SimpleObjectProperty<>(null);
    private final BooleanProperty       tail          = new SimpleBooleanProperty(false);
    private final BooleanProperty       paused        = new SimpleBooleanProperty(false);
    private final DoubleProperty        refreshRate   = new SimpleDoubleProperty(60);

    private final ObservableList<LogRecord> logItems = FXCollections.observableArrayList();

    public BooleanProperty showTimeStampProperty()
    {
        return showTimestamp;
    }

    public ObjectProperty<Level> filterLevelProperty()
    {
        return filterLevel;
    }

    public BooleanProperty tailProperty()
    {
        return tail;
    }

    public BooleanProperty pausedProperty()
    {
        return paused;
    }

    public DoubleProperty refreshRateProperty()
    {
        return refreshRate;
    }

    public LogView(Logger logger)
    {
        getStyleClass().add("log-view");

        Timeline logTransfer = new Timeline
        (
                new KeyFrame
                (
                        Duration.seconds(1),
                        event -> 
                        {
                            logger.getLog().drainTo(logItems);

                            if (logItems.size() > MAX_ENTRIES)
                            {
                                logItems.remove(0, logItems.size() - MAX_ENTRIES);
                            }

                            if (tail.get())
                            {
                                scrollTo(logItems.size());
                            }
                        }
                )
        );
        
        logTransfer.setCycleCount(Timeline.INDEFINITE);
        
        logTransfer.rateProperty().bind(refreshRateProperty());

        this.pausedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue && logTransfer.getStatus() == Animation.Status.RUNNING)
            {
                logTransfer.pause();
            }

            if (!newValue && logTransfer.getStatus() == Animation.Status.PAUSED && getParent() != null)
            {
                logTransfer.play();
            }
        });

        this.parentProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue == null)
            {
                logTransfer.pause();
            }
            else
            {
                if (!paused.get())
                {
                    logTransfer.play();
                }
            }
        });

        filterLevel.addListener((observable, oldValue, newValue) ->
        {
            setItems(
                    new FilteredList<LogRecord>
                    (
                            logItems,
                            logRecord ->
                                logRecord.getLevel().ordinal() >=
                                filterLevel.get().ordinal()
                    )
            );
        });
        
        filterLevel.set(Level.DEBUG);

        setCellFactory(param -> new ListCell<LogRecord>()
        {
            {
                showTimestamp.addListener(observable -> updateItem(this.getItem(), this.isEmpty()));
            }

            @Override
            protected void updateItem(LogRecord item, boolean empty) {
                super.updateItem(item, empty);

                pseudoClassStateChanged(debug, false);
                pseudoClassStateChanged(info, false);
                pseudoClassStateChanged(warn, false);
                pseudoClassStateChanged(error, false);

                if (item == null || empty)
                {
                    setText(null);
                    return;
                }

                String context =
                        (item.getContext() == null)
                                ? ""
                                : item.getContext() + " ";

                if (showTimestamp.get())
                {
                    String timestamp =
                            (item.getTimestamp() == null)
                                    ? ""
                                    : timestampFormatter.format(item.getTimestamp()) + " ";
                    setText(timestamp + context + item.getMessage());
                }
                else
                {
                    setText(context + item.getMessage());
                }

                switch (item.getLevel())
                {
                    case DEBUG:
                        pseudoClassStateChanged(debug, true);
                        break;

                    case INFO:
                        pseudoClassStateChanged(info, true);
                        break;

                    case WARN:
                        pseudoClassStateChanged(warn, true);
                        break;

                    case ERROR:
                        pseudoClassStateChanged(error, true);
                        break;
                }
            }
        });
    }
}


public class LogViewer extends BorderPane
{

	private final Random random = new Random(42);

	
    public LogViewer(Controller controller) throws Exception
    {
        Log    log    = new Log();
        Logger logger = new Logger(log, "System:-");
  
//        logger.info("Log about the activity of uploading files to database");
//        logger.warn("Do not get disconnection");

        for (int x = 0; x < 20; x++)
        {
      
            Thread generatorThread = new Thread(
                    () -> 
                    {
                        for (;;)
                        {
                        	Text text = controller.getQueue().getNext();
                        
                            logger.log
                            (
                                    new LogRecord
                                    (
                                            text.getLevel(),
                                            Thread.currentThread().getName(),
                                            text.toString()
                                    )
                            );

                            try
                            {
                                Thread.sleep(random.nextInt(1_000));
                            }
                            catch (InterruptedException e)
                            {
                                Thread.currentThread().interrupt();
                            }
                        }
                    },
                    "|"
            );
            generatorThread.setDaemon(true);
            generatorThread.start();
        }

        LogView logView = new LogView(logger);
        
        logView.setPrefWidth(400);

        ChoiceBox<Level> filterLevel = new ChoiceBox<>
        (
                FXCollections.observableArrayList
                (
                        Level.values()
                )
        );
        
        filterLevel.getSelectionModel().select(Level.DEBUG);
        logView.filterLevelProperty().bind
        (
                filterLevel.getSelectionModel().selectedItemProperty()
        );

        ToggleButton showTimestamp = new ToggleButton("Show Timestamp");

        logView.showTimeStampProperty().bind(showTimestamp.selectedProperty());

        ToggleButton tail = new ToggleButton("Tail");
        logView.tailProperty().bind(tail.selectedProperty());

        ToggleButton pause = new ToggleButton("Pause");
        logView.pausedProperty().bind(pause.selectedProperty());

        Slider rate = new Slider(0.1, 60, 60);
        
        logView.refreshRateProperty().bind(rate.valueProperty());
        
        Label rateLabel = new Label();
        
        rateLabel.textProperty().bind(Bindings.format("Update: %.2f fps", rate.valueProperty()));
        rateLabel.setStyle("-fx-font-family: monospace;");
        
        VBox rateLayout = new VBox(rate, rateLabel);
        
        rateLayout.setAlignment(Pos.CENTER);

        HBox controls = new HBox
        (
                10,
                filterLevel,
                showTimestamp,
                tail,
                pause,
                rateLayout
        );
        
        controls.setMinHeight(HBox.USE_PREF_SIZE);

        VBox layout = new VBox
        (
                10,
                controls,
                logView
        );
        
        VBox.setVgrow(logView, Priority.ALWAYS);

        //layout has all the things
        
        setCenter(layout);
    }
}