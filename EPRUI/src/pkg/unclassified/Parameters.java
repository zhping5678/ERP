package pkg.unclassified;

import javafx.scene.paint.Color;

public final class Parameters {
    public static final int FONT_SIZE=13;
    public static final String CSS_FONT_SIZE=String.valueOf(FONT_SIZE);

    public static final int  LEFT_BUTTON_WIDTH=85;
    public static final String CSS_LEFT_BUTTON_WIDTH=String.valueOf(LEFT_BUTTON_WIDTH);

    public static final int LEFT_BUTTON_HEIGHT=30;
    public static final String CSS_LEFT_BUTTON_HEIGHT=String.valueOf(LEFT_BUTTON_HEIGHT);

    public static final int WINDOWS_WIDTH=1200;

    public static final int WINDOWS_HEIGHT=800;

    public static final int WINDOW_BUTTON_WIDTH =42;

    public static final double NOTIFICATION_VIEWER_WIDTH=375;

    public static final double RESIZE_WIDTH = 6;

    public static final int FILE_TITLE_COMPONENT_HEIGHT=22;

    public static final int FILE_TITLE_COMPONENT_FONT_SIZE=18;
    public static final String CSS_FILE_TITLE_COMPONENT_FONT_SIZE=String.valueOf(FILE_TITLE_COMPONENT_FONT_SIZE);

    public static final int CREATION_INFORMATION_COMPONENT_HEIGHT=14;
    public static final String CSS_CREATION_INFORMATION_COMPONENT_HEIGHT=String.valueOf(CREATION_INFORMATION_COMPONENT_HEIGHT);

    public static final int CREATION_INFORMATION_COMPONENT_FONT_SIZE=10;
    public static final String CSS_CREATION_INFORMATION_COMPONENT_FONT_SIZE=String.valueOf(CREATION_INFORMATION_COMPONENT_FONT_SIZE);


    public static final int MIDDLE_PREF_WIDTH=333;
    public static final String CSS_MIDDLE_PREF_WIDTH=String.valueOf(MIDDLE_PREF_WIDTH);

    public static final int MIDDLE_MAX_WIDTH=340;
    public static final String CSS_MIDDLE_MAX_WIDTH=String.valueOf(MIDDLE_MAX_WIDTH);


    public static final Color CREATION_INFORMATION_COMPONENT_TEXT_FILL=Color.grayRgb(146);
    public static final String CSS_CREATION_INFORMATION_COMPONENT_TEXT_FILL=toCSS(CREATION_INFORMATION_COMPONENT_TEXT_FILL);

    public static final Color SEARCH_PROMPT=Color.rgb(150,150,150);
    public static final String CSS_SEARCH_PROMPT=toCSS(SEARCH_PROMPT);


    public static final Color NJU_PURPLE=Color.rgb(99,4,96);
    public static final String CSS_NJU_PURPLE=toCSS(NJU_PURPLE);

    public static final Color LIGHT_PURPLE=Color.rgb(147, 6, 143);//when mouse enters left buttons
    public static final String CSS_LIGHT_PURPLE=toCSS(LIGHT_PURPLE);

    public static final Color DEEP_PURPLE=Color.rgb(70, 3, 68);//when mouse enters left buttons
    public static final String CSS_DEEP_PURPLE=toCSS(DEEP_PURPLE);

    public static final Color UNAVAILABLE_BUTTON_TEXT_FILL=Color.rgb(146,67,211);
    public static final String CSS_UNAVAILABLE_BUTTON_TEXT_FILL=toCSS(UNAVAILABLE_BUTTON_TEXT_FILL);

    public static final Color MIDDLE_PANE_GRAY=Color.grayRgb(238);
    public static final String CSS_MIDDLE_PANE_GRAY=toCSS(MIDDLE_PANE_GRAY);



    public static String toCSS(Color color){
        String s=color.toString();
        return "#"+s.substring(2);
    }




}
