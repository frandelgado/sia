package ar.edu.itba.sia.g4.search.rollingcube.graphics;
import java.util.HashMap;

import ar.edu.itba.sia.g4.search.rollingcube.action.FaceColor;
import javafx.scene.image.Image;

public class GraphicsFactory {

    private static HashMap<FaceColor, Image> faceColorImages;
    private static HashMap<FaceColor, String> faceColorPaths;
    private static Image emptySpotImage;

    public static void createLetterImages(){
        if(faceColorImages != null){
            return;
        }
        faceColorImages = new HashMap<FaceColor, Image>();
        faceColorPaths = createFaceColorPaths();
        for (FaceColor faceColor : faceColorPaths.keySet()) {
            faceColorImages.put(faceColor, new Image(faceColorPaths.get(faceColor)));
        }
        emptySpotImage = new Image("file:Assets/" + "EMPTY_SPOT" + ".png");
    }

    public static Image getFaceColorImage(FaceColor faceColor){
        return faceColorImages.get(faceColor);
    }

    public static Image getEmptySpotImage(){
        return emptySpotImage;
    }


    private static HashMap<FaceColor, String> createFaceColorPaths(){
        HashMap<FaceColor, String> result = new HashMap<FaceColor, String>();
        result.put(FaceColor.ALL_BLACK, "file:Assets/" + "ALL_BLACK" + ".png");
        result.put(FaceColor.ALL_WHITE, "file:Assets/" + "ALL_WHITE" + ".png");
        result.put(FaceColor.N_WHITE_S_BLACK, "file:Assets/" + "N_WHITE_S_BLACK" + ".png");
        result.put(FaceColor.N_BLACK_S_WHITE, "file:Assets/" + "N_BLACK_S_WHITE" + ".png");
        result.put(FaceColor.W_BLACK_E_WHITE, "file:Assets/" + "W_BLACK_E_WHITE" + ".png");
        result.put(FaceColor.W_WHITE_E_BLACK, "file:Assets/" + "W_WHITE_E_BLACK" + ".png");
        return result;
    }

}
