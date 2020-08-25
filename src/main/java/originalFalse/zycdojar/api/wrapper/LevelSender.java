package originalFalse.zycdojar.api.wrapper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import originalFalse.zycdojar.api.levelPack;

/**
 * networking
 * 主要负责服务端向客户端发送玩家状态
 * 请见https://neutrino.v2mcdev.com/networking/intro.html
 */
public class LevelSender {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("originalfalse", "level_"),
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
                levelPack.class,
                (pack, buffer) -> {
                    pack.toBytes(buffer);
                },
                (buffer) -> {
                    return new levelPack(buffer);
                },
                (pack, ctx) -> {
                    pack.handler(ctx);
                }
        );
    }
    public static void sendData(ServerPlayerEntity dataSource){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->{return dataSource;}),new levelPack(dataSource));
    }
}
