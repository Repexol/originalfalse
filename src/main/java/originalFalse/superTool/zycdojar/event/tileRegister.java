package originalFalse.superTool.zycdojar.event;

import net.minecraft.tileentity.TileEntityType;
import originalFalse.superTool.zycdojar.item.tile.AnvilTile;
import originalFalse.superTool.zycdojar.main;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.block.bsTile;

/**
 * 注册方块实体
 */
public class tileRegister {
    public static TileEntityType<?> Anvil=
            TileEntityType.Builder.create(()->{return new AnvilTile();}, main.anvil).build(null).setRegistryName(main.anvil.getRegistryName());
}