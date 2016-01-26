package com.example.mateverunica.verunica_fueltrack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by verunica on 1/24/16.
 */
public class  Validation {
    // URL that generated this code:
    // http://txt2re.com/index-java.php3?s=2002-12-10&1

    public boolean validDate (String date){
        String re1="((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";	// YYYYMMDD 1

        Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(date);

        if(m.find()){
            return true;
        }
        else {
            return false;
        }

    }




}

