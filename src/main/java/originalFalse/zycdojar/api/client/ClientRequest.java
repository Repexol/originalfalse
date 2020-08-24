package originalFalse.zycdojar.api.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ClientRequest {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("originalfalse", "_request"),
                () -> {
                    return VERSION;
                },
                (version) -> {
                    return version.equals(VERSION);
                },
                (version) -> {
                    return version.equals(VERSION);
                });
        INSTANCE.registerMessage(
                nextID(),
                requestContext.class,
                (pack, buffer) -> {
                    pack.toBytes(buffer);
                },
                (buffer) -> {
                    return new requestContext(buffer);
                },
                (pack, ctx) -> {
                    pack.handler(ctx);
                }
        );
    }
    @OnlyIn(Dist.CLIENT)
    public static void send(requestContext context){
        INSTANCE.sendToServer(context);
    }
}
