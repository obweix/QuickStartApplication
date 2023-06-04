/**
  * Copyright 2023 bejson.com 
  */
package com.example.quickstartapplication.network.bean;
import java.util.List;

/**
 * Auto-generated: 2023-05-28 14:44:1
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class NavigateJsonRootBean {

    private List<NavigateData> data;
    private int errorCode;
    private String errorMsg;
    public void setData(List<NavigateData> data) {
         this.data = data;
     }
     public List<NavigateData> getData() {
         return data;
     }

    public void setErrorCode(int errorCode) {
         this.errorCode = errorCode;
     }
     public int getErrorCode() {
         return errorCode;
     }

    public void setErrorMsg(String errorMsg) {
         this.errorMsg = errorMsg;
     }
     public String getErrorMsg() {
         return errorMsg;
     }

}