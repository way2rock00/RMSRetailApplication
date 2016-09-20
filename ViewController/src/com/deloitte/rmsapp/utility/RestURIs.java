package com.deloitte.rmsapp.utility;

public class RestURIs {
    public RestURIs() {
        super();
    }

        
        public static String convertString2URLFormat(String strInput)
        {
            String strOutput = "";
            for(int i=0;i<strInput.length();i++)
            {
                char c = strInput.charAt(i);
                if(32==(int)c)
                {
                    strOutput=strOutput+"%20";
                }
                else
                {
                    strOutput=strOutput+c;
                }
            }
            return strOutput;
        }
   
    
}
