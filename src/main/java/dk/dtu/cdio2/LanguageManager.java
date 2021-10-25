package dk.dtu.cdio2;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private Locale locale = Locale.getDefault();
    private ResourceBundle messages = getMessages();

    public LanguageManager() {
        if (locale.getLanguage().equals(new Locale("en").getLanguage())) {
            locale = new Locale("en", "US");
        }
        else if (locale.getLanguage().equals(new Locale("da").getLanguage())) {
            locale = new Locale("da", "DK");
        }
        else {
            locale = new Locale("en", "US");
        }
    }

    public void setLocale(String language, String country) {
        locale = new Locale(language, country);
        messages = getMessages();
    }

    public void setLocale(String language) {
        if (language.contains("_")) {
            setLocale(language.split("_")[0], language.split("_")[1]);
        }
        else {
            locale = new Locale(language);
            messages = getMessages();
        }
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        messages = getMessages();
    }

    private ResourceBundle getMessages() {
        return ResourceBundle.getBundle("GameMessages", locale);
    }

    public String getString(String messageKey) {
        String message = "";
        try {
            message = messages.getString(messageKey);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "Could not read message '" + messageKey + "' from locale " + locale.getLanguage();
        }
        return message;
    }
}
