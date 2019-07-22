

package com.clg.ccupsbackend.regExUtil;
import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.clg.ccupsbackend.model.MatchInfo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
/**
 * RegExUtilService
 */
@Component
public class RegExUtilService {

    public List<MatchInfo> getMatchInformation(String regExPattern, String line){
        List<MatchInfo> result=new ArrayList<MatchInfo>();
        
        
//         final String regex = "(?<recordType>[0-9]{3})(?<cardNumber>[0-9]{16})(?<name>[a-zA-Z0-9\\s\\_\\@\\$\\&\\(\\)\\-\\[\\]\\;\\:\\,\\.\\/\\|\\\\]{30})(?<accountNumber>[0-9]{10})";
// final String string = "1104183590135202100JHONATHAN CHUA PO             0750227686";
List<String> groupNames=getGroupNames(regExPattern);
        final Pattern pattern = Pattern.compile(regExPattern, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            
            //  matchInfo.setFullMatch( matcher.group());
            
                    
                        for (String item : groupNames) {
                            MatchInfo matchInfo= new MatchInfo();
                            matchInfo.setFieldName(item);
                            matchInfo.setValue( matcher.group(item));
                            matchInfo.setStartIndex( Integer.valueOf(matcher.start(item)));
                            matchInfo.setEndIndex(Integer.valueOf(matcher.end(item)));
                            result.add(matchInfo);
                        }

                        

                       
        }
        return result;
    }

    public List<String> getGroupNames(String regExPattern){
        List<String> result= new ArrayList<String>();
        final String regex = "<(.*?)>";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(regExPattern);

        while (matcher.find()) {
            
            for (int i = 1; i <= matcher.groupCount(); i++) {
                // System.out.println("Group " + i + ": " + matcher.group(i));
               
                result.add(matcher.group(i));
            }
            
        }

        return result;


    }
}