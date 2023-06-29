package handlers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import java.awt.Color;

public class ColourSchemeManager {

    private static Map<String, Color> darkMode;
    private static ImageIcon darkModeButton;
    private static Map<String, Color> babyGirlMode;
    private static ImageIcon babyGirlModeButton;

    static{

        darkMode = new HashMap<String, Color>() {{
            put("mainBackground", Color.decode("#232323"));
            put("sideBar", Color.decode("#3f3f3f"));
            put("buttonText", Color.decode("#141414"));
            put("textColour", Color.decode("#FFFFFF"));
        }};
    
        darkModeButton = new ImageIcon("src\\button_grey.png");
    
        babyGirlMode = new HashMap<String, Color>() {{
            put("mainBackground", Color.decode("#e11299"));
            put("sideBar", Color.decode("#f5c6ec"));
            put("buttonText", Color.decode("#9a208c"));
            put("textColour", Color.decode("#ffeaea"));
        }};
    
        babyGirlModeButton = new ImageIcon("src\\button_pink.png");

    }


    /**
     * Retrieves the color of a specific component in the specified color scheme mode.
     *
     * @param mode      The color scheme mode ("darkMode" or "babyGirlMode").
     * @param component The specific component or item for which to retrieve the color.
     * @return The color of the specified component in the given color scheme mode,
     *         or null if the mode or component is not found.
     */
    public static Color getColor(String mode, String component){

        Color color = null;
            // Determine the color scheme mode
        if (mode.equals("darkMode")) {
            color = darkMode.get(component);
        } else if (mode.equals("babyGirlMode")) {
            color = babyGirlMode.get(component);
        }
        
        return color;
    }
    


    public static ImageIcon getButtonImage(String mode){

        if(mode.equals("darkMode")){
            return darkModeButton;
        }else if(mode.equals("babyGirlMode")){
            return babyGirlModeButton;
        }
        return null;

    }


}
