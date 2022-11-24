package com.example.mediaplayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;

public class Controller {
    @FXML
    private BorderPane parent;

    @FXML
    private MediaView mvVideo;
    private MediaPlayer mpVideo;
    private Media mediaVideo;

    @FXML
    private VBox controls;

    @FXML
    private HBox timeControl;
    @FXML
    private Label currentTime;
    @FXML
    private Slider sliderTime;
    @FXML
    private Label totalTime;

    @FXML
    private HBox controlPanel;
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
    private Button openFile;
    @FXML
    private Button fullScreenButton;

    private boolean atEndOfVideo = false;
    private boolean isPlaying = true;
    private boolean isMuted = false;

    private ImageView isPlay;
    private ImageView isPause;
    private ImageView isRestart;
    private ImageView isVolume;
    private ImageView isFullScreen;
    private ImageView isMute;
    private ImageView isExit;

}