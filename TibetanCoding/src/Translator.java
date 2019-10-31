/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author night
 */
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Translator {
    Map<String,String> map=new HashMap<>();
    Map<String,String> variables=new HashMap<>();
    private char variableName='a';
    public Translator(){
        try {
            String path=Translator.class.getResource("").toString() + "Table.txt";
            path = path.replace("file:/", "");
            path = path.replace("/", "//");
            Scanner scanner=new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                String[] strs=line.split(",");
                map.put(strs[0],strs[1]);
            }
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JPanel(), "initialization failed");
        }
    }

    
    
    public String translate(String tText){
        String hText="";
        String[] strs=tText.split(" ");
        boolean flag=false;
        for (String str: strs) {
            
            if (flag) {
                variables.put(str, variableName+"");
                hText += variableName;
                variableName++;
                flag=false;
                continue;
            }
            if (str.equals("འགྱུར་ཚད")) {
                flag=true;
                continue;
            }
            if (map.containsKey(str)){
                hText += map.get(str);
            }
            else if(variables.containsKey(str)){
                hText += variables.get(str);
            }
            else {
                hText += str;
            }
            hText += " ";
            
            
        }
        return hText;
    }

}
