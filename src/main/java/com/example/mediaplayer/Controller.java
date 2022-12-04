package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private String mediaPath = "src/main/resources/com/example/mediaplayer/Lab1.mp4";
    @FXML
    private BorderPane parent;

    @FXML
    private MediaView mediaView;

    private MediaPlayer mpVideo;
    private Media mediaVideo;

    @FXML
    private VBox controls;

    @FXML
    private Label labelCurrentTime;
    @FXML
    private Slider sliderTime;
    @FXML
    private Label labelTotalTime;

    @FXML
    private Button slowerButton;
    @FXML
    private Button rollbackButton;
    @FXML
    private Button playButton;
    @FXML
    private Button skipButton;
    @FXML
    private Button fasterButton;
    @FXML
    private Label volumeLabel;
    @FXML
    private Slider volumeSlider;

    @FXML
    private ButtonBar fileManager;
    @FXML
    private Button openFileButton;
    @FXML
    private Button fullScreenButton;

    private boolean atEndOfVideo = false;
    private boolean isPlaying = true;
    private boolean isMuted = false;

    private ImageView ivPlay;
    private ImageView ivPause;
    private ImageView ivRestart;
    private ImageView ivVolume;
    private ImageView ivFullScreen;
    private ImageView ivMute;
    private ImageView ivExit;

    public Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //openFileExplorer();

        final int VIEW_SIZE = 25;

        mediaVideo = new Media((new File(mediaPath).toURI().toString()));
        mpVideo = new MediaPlayer(mediaVideo);
        mediaView.setMediaPlayer(mpVideo);

        volumeSlider.setValue(0.2);
        mpVideo.setVolume(0.2);

        parent.backgroundProperty().set(Background.fill(Paint.valueOf("#000C66")));
        controls.backgroundProperty().set(Background.fill(Paint.valueOf("white")));
        fileManager.backgroundProperty().set(Background.fill(Paint.valueOf("white")));

        Image imagePlay = new Image(new File("src/main/resources/com/example/mediaplayer/play_pl.png").toURI()
                .toString());
        ivPlay = new ImageView(imagePlay);
        ivPlay.setFitHeight(VIEW_SIZE);
        ivPlay.setFitWidth(VIEW_SIZE);

        Image imagePause = new Image(new File("src/main/resources/com/example/mediaplayer/pause_pl.png").toURI()
                .toString());
        ivPause = new ImageView(imagePause);
        ivPause.setFitHeight(VIEW_SIZE);
        ivPause.setFitWidth(VIEW_SIZE);

        Image imageMute = new Image(new File("src/main/resources/com/example/mediaplayer/mute_pl.png").toURI()
                .toString());
        ivMute = new ImageView(imageMute);
        ivMute.setFitHeight(VIEW_SIZE);
        ivMute.setFitWidth(VIEW_SIZE);

        Image imageVolume = new Image(new File("src/main/resources/com/example/mediaplayer/unmute_pl.png").toURI()
                .toString());
        ivVolume = new ImageView(imageVolume);
        ivVolume.setFitHeight(VIEW_SIZE);
        ivVolume.setFitWidth(VIEW_SIZE);

        Image imageSkip = new Image(new File("src/main/resources/com/example/mediaplayer/skip_pl.png").toURI()
                .toString());
        ImageView ivSkip = new ImageView(imageSkip);
        ivSkip.setFitHeight(VIEW_SIZE);
        ivSkip.setFitWidth(VIEW_SIZE);

        Image imageRollback = new Image(new File("src/main/resources/com/example/mediaplayer/rollbak_pl.png").toURI()
                .toString());
        ImageView ivRollback = new ImageView(imageRollback);
        ivRollback.setFitHeight(VIEW_SIZE);
        ivRollback.setFitWidth(VIEW_SIZE);

        Image imageRestart = new Image(new File("src/main/resources/com/example/mediaplayer/replay_pl.png").toURI()
                .toString());
        ivRestart = new ImageView(imageRestart);
        ivRestart.setFitHeight(VIEW_SIZE);
        ivRestart.setFitWidth(VIEW_SIZE);

        Image imageSlower = new Image(new File("src/main/resources/com/example/mediaplayer/slower_pl.png").toURI()
                .toString());
        ImageView ivSlower = new ImageView(imageSlower);
        ivSlower.setFitHeight(VIEW_SIZE);
        ivSlower.setFitWidth(VIEW_SIZE);

        Image imageFaster = new Image(new File("src/main/resources/com/example/mediaplayer/faster_pl.png").toURI()
                .toString());
        ImageView ivFaster = new ImageView(imageFaster);
        ivFaster.setFitHeight(VIEW_SIZE);
        ivFaster.setFitWidth(VIEW_SIZE);

        Image imageFullScreen = new Image(new File("src/main/resources/com/example/mediaplayer/fullscr_pl.png").toURI()
                .toString());
        ivFullScreen = new ImageView(imageFullScreen);
        ivFullScreen.setFitHeight(VIEW_SIZE);
        ivFullScreen.setFitWidth(VIEW_SIZE);

        Image imageExit = new Image(new File("src/main/resources/com/example/mediaplayer/close_pl.png").toURI()
                .toString());
        ivExit = new ImageView(imageExit);
        ivExit.setFitHeight(VIEW_SIZE);
        ivExit.setFitWidth(VIEW_SIZE);

        playButton.setGraphic(ivPlay);
        fasterButton.setGraphic(ivFaster);
        slowerButton.setGraphic(ivSlower);
        skipButton.setGraphic(ivSkip);
        rollbackButton.setGraphic(ivRollback);
        fullScreenButton.setGraphic(ivFullScreen);
        volumeLabel.setGraphic(ivVolume);

        //controls.setOpacity(0);
        //fileManager.setOpacity(0);

        controls.setOnMouseEntered(mouseEvent -> controls.setOpacity(1));

        controls.setOnMouseExited(mouseEvent -> controls.setOpacity(0));

        fileManager.setOnMouseEntered(mouseEvent -> fileManager.setOpacity(1));

        fileManager.setOnMouseExited(mouseEvent -> fileManager.setOpacity(0));

//        parent.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if (isPlaying){
//                    mpVideo.pause();
//                    isPlaying = false;
//                    playButton.setGraphic(ivPause);
//                } else {
//                    mpVideo.play();
//                    isPlaying = true;
//                    playButton.setGraphic(ivPlay);
//                }
//            }
//        });

        playButton.setOnAction(actionEvent -> {
            Button buttonPlay = (Button) actionEvent.getSource();
            pauseAndPlayEventHandler(buttonPlay);
        });

        openFileButton.setOnMouseClicked(mouseEvent -> {
            openFileExplorer();

            mediaVideo = new Media(mediaPath);
            mpVideo = new MediaPlayer(mediaVideo);
            mediaView.setMediaPlayer(mpVideo);

            volumeSlider.setValue(0.2);
            mpVideo.setVolume(0.2);
        });

        parent.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                pauseAndPlayEventHandler(playButton);
            }
            else if (keyEvent.getCode() == KeyCode.EQUALS){
                if (mpVideo.getRate() < 2)
                    mpVideo.setRate(mpVideo.getRate() + 0.25);
            } else if (keyEvent.getCode() == KeyCode.MINUS) {
                if (mpVideo.getRate() > 0.25)
                    mpVideo.setRate(mpVideo.getRate() - 0.25);
            } else if (keyEvent.getCode() == KeyCode.W) {
                if (isMuted){
                    isMuted = false;
                    mpVideo.setMute(false);
                    volumeLabel.setGraphic(ivVolume);
                }
                if (volumeSlider.getValue() + 0.02 > 1)
                    volumeSlider.setValue(1);
                else volumeSlider.setValue(volumeSlider.getValue()+0.02);
                mpVideo.setVolume(volumeSlider.getValue());
            } else if (keyEvent.getCode() == KeyCode.S) {
                if (volumeSlider.getValue() - 0.02 <= 0){
                    volumeSlider.setValue(0);
                    volumeLabel.setGraphic(ivMute);
                }
                else volumeSlider.setValue(volumeSlider.getValue()-0.02);
                mpVideo.setVolume(volumeSlider.getValue());
            } else if (keyEvent.getCode() == KeyCode.D) {
                if (mpVideo.getCurrentTime().toSeconds() + 10 > mpVideo.getTotalDuration().toSeconds()){
                    mpVideo.seek(mpVideo.getTotalDuration());
                } else {
                    mpVideo.seek(Duration.millis(mpVideo.getCurrentTime().toMillis() + 10000));
                }
            } else if (keyEvent.getCode() == KeyCode.A) {
                if (mpVideo.getCurrentTime().toSeconds() - 10 < 0){
                    mpVideo.seek(Duration.millis(0));
                } else {
                    mpVideo.seek(Duration.millis(mpVideo.getCurrentTime().toMillis() - 10000));
                }
            }

        });

        bindCurrentTimeLabel();

        volumeSlider.valueProperty().addListener(observable -> {
            mpVideo.setVolume(volumeSlider.getValue());
            if (mpVideo.getVolume() != 0.0) {
                volumeLabel.setGraphic(ivVolume);
                isMuted = false;
            } else {
                volumeLabel.setGraphic(ivMute);
                isMuted = true;
            }
        });

        skipButton.setOnMouseClicked(mouseEvent -> {
            if (mpVideo.getCurrentTime().toSeconds() + 10 > mpVideo.getTotalDuration().toSeconds()){
                mpVideo.seek(mpVideo.getTotalDuration());
            } else {
                mpVideo.seek(Duration.millis(mpVideo.getCurrentTime().toMillis() + 10000));
            }
        });

        rollbackButton.setOnMouseClicked(mouseEvent -> {
            if (mpVideo.getCurrentTime().toSeconds() - 10 < 0){
                mpVideo.seek(Duration.millis(0));
            } else {
                mpVideo.seek(Duration.millis(mpVideo.getCurrentTime().toMillis() - 10000));
            }
        });

        fasterButton.setOnMouseClicked(mouseEvent -> {
            if (mpVideo.getRate() < 2)
                mpVideo.setRate(mpVideo.getRate() + 0.25);
        });

        slowerButton.setOnMouseClicked(mouseEvent -> {
            if (mpVideo.getRate() > 0.25)
                mpVideo.setRate(mpVideo.getRate() - 0.25);
        });

        volumeLabel.setOnMouseClicked(mouseEvent -> {
            if (isMuted) {
                volumeLabel.setGraphic(ivVolume);
                isMuted = false;
                mpVideo.setMute(false);
            } else {
                volumeLabel.setGraphic(ivMute);
                isMuted = true;
                mpVideo.setMute(true);
            }
        });

        parent.sceneProperty().addListener((observableValue, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                mediaView.fitHeightProperty().bind(newScene.heightProperty());
            }
        });

        fullScreenButton.setOnMouseClicked(mouseEvent -> {
            Button button = (Button) mouseEvent.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            if (stage.isFullScreen()) {
                stage.setFullScreen(false);
                fullScreenButton.setGraphic(ivFullScreen);
            } else {
                stage.setFullScreen(true);
                fullScreenButton.setGraphic(ivExit);
            }
            stage.addEventHandler(KeyEvent.KEY_PRESSED,
                    keyEvent -> {
                        if (keyEvent.getCode() == KeyCode.ESCAPE) {
                            fullScreenButton.setGraphic(ivFullScreen);
                            stage.setFullScreen(false);
                        }
                    });
        });

        mpVideo.totalDurationProperty().addListener((observableValue, oldDuration, newDuration) -> {
            sliderTime.setMax(newDuration.toSeconds());
            labelTotalTime.setText(getTime(newDuration));
        });

        sliderTime.valueChangingProperty().addListener((observableValue, wasChanging, isChanging) -> {
            if (!isChanging) {
                mpVideo.seek(Duration.seconds(sliderTime.getValue()));
            }
        });

        sliderTime.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            double currentTime = mpVideo.getCurrentTime().toSeconds();
            if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                mpVideo.seek(Duration.seconds(newValue.doubleValue()));
            }

            labelMatchEndVideo(labelCurrentTime.getText(),
                    labelTotalTime.getText());
        });

        mpVideo.currentTimeProperty().addListener((observableValue, oldTime, newTime) -> {
            if (!sliderTime.isValueChanging()) {
                sliderTime.setValue(newTime.toSeconds());
            }
            labelMatchEndVideo(labelCurrentTime.getText(),
                    labelTotalTime.getText());
        });

        mpVideo.setOnEndOfMedia(() -> {
            playButton.setGraphic(ivRestart);
            atEndOfVideo = true;
            if (labelCurrentTime.textProperty()
                    .equals(labelTotalTime.textProperty())) {
                labelCurrentTime.textProperty().unbind();
                labelCurrentTime.setText(getTime(mpVideo.getTotalDuration()));
            }
        });
    }

    private void pauseAndPlayEventHandler(Button playButton) {
        if (atEndOfVideo) {
            sliderTime.setValue(0);
            atEndOfVideo = false;
            isPlaying = false;
        }
        if (isPlaying) {
            playButton.setGraphic(ivPlay);
            mpVideo.pause();
            isPlaying = false;
        } else {
            playButton.setGraphic(ivPause);
            mpVideo.play();
            isPlaying = true;
        }
    }

    public void bindCurrentTimeLabel() {
        labelCurrentTime.textProperty().bind(Bindings.createStringBinding(() -> getTime(mpVideo.getCurrentTime()), mpVideo.currentTimeProperty()));
    }

    public String getTime(Duration time) {

        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        minutes %= 60;
        seconds %= 60;
        hours %= 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d",
                    hours,
                    minutes,
                    seconds);
        } else {
            return String.format("%02d:%02d",
                    minutes,
                    seconds);
        }
    }

    private void labelMatchEndVideo(String currentTime, String totalTime) {
        if (currentTime.equals(totalTime)) {
            atEndOfVideo = true;
            playButton.setGraphic(ivRestart);
        } else {
            atEndOfVideo = false;
            if (isPlaying)
                playButton.setGraphic(ivPause);
            else playButton.setGraphic(ivPlay);
        }
    }

    private void openFileExplorer(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            mediaPath = file.toURI().toString();
        }
    }

}