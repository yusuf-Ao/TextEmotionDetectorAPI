package io.aycodes.RestfulApi_MajorProject1.service;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class TrainModel {

    public List<String> beginAnalysis(String text)  {
        return detectLanguageAndTrain(text);
    }

    private List<String> detectLanguageAndTrain(String text) {
        String s = null;
        List<String> result = new ArrayList<>();
        String lang = null;
        try {
            ProcessBuilder p = new ProcessBuilder("python", System.getProperty("user.dir") +
                    "\\src\\main\\java\\io\\aycodes\\RestfulApi_MajorProject1\\training\\lang_detect.py", text);
            Process process = p.start();
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                lang = s;
            }

            /*while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }*/

            if ("en".equals(lang)) {
                result = getEmotion(text);

            } else {
                result = translateToEnglish(text, lang);
            }

        }
        catch (IOException ignored) {
        }


        return result;
    }

    private List<String> translateToEnglish(String text, String lang) {
        String s = null;
        List<String> result = new ArrayList<>();
        try {
            ProcessBuilder p = new ProcessBuilder("python", System.getProperty("user.dir") +
                    "\\src\\main\\java\\io\\aycodes\\RestfulApi_MajorProject1\\training\\convert_to_english.py", text, lang);
            Process process = p.start();
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                assert false;
                result.add(s);
            }

            /*while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }*/

            result = getEmotion(text);


        }
        catch (IOException ignored) {

        }


        return result;
    }

    private List<String> getEmotion(String text) {

        String s = null;
        List<String> output = new ArrayList<>();
        try {

            ProcessBuilder p = new ProcessBuilder("python", System.getProperty("user.dir") +
                    "\\src\\main\\java\\io\\aycodes\\RestfulApi_MajorProject1\\training\\model.py", text);
            Process process = p.start();
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                output.add(s);
            }

            /*while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }*/

        }
        catch (IOException ignored) {
        }

        return output;
    }

}
