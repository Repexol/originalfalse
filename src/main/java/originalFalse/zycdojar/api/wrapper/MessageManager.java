package originalFalse.zycdojar.api.wrapper;

import originalFalse.zycdojar.event.dataCenter.worldSaveData;

public class MessageManager {
    public static void registerMessage(messageHandle handle){
        worldSaveData.messageHandles.add(handle);
    }
}
