package originalFalse.data;

import originalFalse.data.values.mapData;

import java.util.Map;

public class gotoExcaption extends Exception{
    public Map data;
    public gotoExcaption(Map data){
        super();
        this.data=data;
    }
}
