/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author ktouf
 */
public class Compiler implements Runnable {
    private String code;
    private String input;
    private String currLang;
    SubmitCodeController curr;
    
    public Compiler(String code, String input, String currLang, SubmitCodeController curr) {
        this.code = code;
        this.input = input;
        this.currLang = currLang;
        this.curr = curr;
    }
    
    @Override
    public void run(){
        curr.flipbtnSubmit();
        String encodedCode = URLEncoder.encode(code);
        String encodedInput = URLEncoder.encode(input);
        String create = getResponse("http://api.paiza.io:80/runners/create?source_code=" + encodedCode + "&language=" + currLang + "&input=" + encodedInput + "&api_key=guest", "POST");
        String ID = parseID(create);
        try {
            Thread.sleep(3000);
        } catch(InterruptedException ex) {
            System.out.println(ex);
        }
        String details = getResponse("http://api.paiza.io:80/runners/get_details?id=" + ID + "&api_key=guest", "GET");
        String output = parseOutput(details);
        String stderr = parseError(details);
        String timeout = parseTimeout(details);
        if(timeout.contains("timeout")) {
            curr.settxtOutput("Time Limit Exceeded");
        }
        else if(stderr.equals("")) {
            curr.settxtOutput(output);
        } else {
            curr.settxtOutput(stderr);
        }
        SubmitCodeController.output = output;
        curr.flipbtnSubmit();
    }
    
    public String getResponse(String apiURL, String requestType) {
        HttpURLConnection testAPI = null;
        StringBuilder testContent = new StringBuilder();
        try {
            URL testURL = new URL(apiURL);
            testAPI = (HttpURLConnection)testURL.openConnection();
            testAPI.setRequestMethod(requestType);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(testAPI.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    testContent.append(line);
                    testContent.append(System.lineSeparator());
                }
            }
        } catch(IOException e) {
            System.out.println("Buffered Reader Error");
        }
        finally {
            testAPI.disconnect();
        }
        return testContent.toString();
    }
    
    public String parseString(String toProcess, String from, String to) {
        String processed = "";
        boolean startCopy = false;
        int startIndex = toProcess.indexOf(from);
        int endIndex = toProcess.indexOf(to);
        for(int i = startIndex + from.length(); i < endIndex; ++i) {
            if(toProcess.charAt(i) == '\"') {
                startCopy = !startCopy;
            }
            else {
                if(startCopy) {
                    processed += toProcess.charAt(i);
                }
            }
        }
        return processed;
    }
    
    public String parseTimeout(String toProcess) {
        String timeout = parseString(toProcess, "\"result\": ", "}");
        return timeout;
    }
    
    public String parseID(String toProcess) {
        String ID = parseString(toProcess, "\"id\": ", "\"status\": ");
        return ID;
    }
    
    public String parseOutput(String toProcess) {
        String processedOutput = parseString(toProcess, "\"stdout\": ", "\"stderr\": ");
        processedOutput  = (processedOutput.replace("\\n", "\n")).replace("\\t", "\t");
        return processedOutput;
    }
    
    public String parseError(String toProcess) {
        String stdError = parseString(toProcess, "\"stderr\": ", "\"exit_code\": ");
        String buildError = parseString(toProcess, "\"build_stderr\": ", "\"build_exit_code\": ");
        buildError = (buildError.replace("\\n", System.lineSeparator())).replace("\\t", "\t");
        if(stdError.equals("")) {
            return buildError;
        } else {
            return stdError;
        }
    }
    
}
