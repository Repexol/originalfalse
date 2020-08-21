package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.tech.zycdojar.main;

public class lightingTile extends TileEntity {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new lightingTile();}, main.nitunengfadianji).build(null).setRegistryName(main.lighting.getRegistryName());
    public lightingTile() {
        super(type);
    }
}
