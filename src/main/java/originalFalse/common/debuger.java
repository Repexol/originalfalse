package originalFalse.common;

import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class debuger {
    public debuger(){
    }
    public static Logger log= LogManager.getLogger();
    public void sendMessage(ITextComponent component){
        log.info(component.getString());
    }
    public void info(String message){
        log.info(message);
    }
}
