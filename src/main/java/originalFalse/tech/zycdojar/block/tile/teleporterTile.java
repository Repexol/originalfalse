package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.tech.zycdojar.main;

public class teleporterTile extends TileEntity {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new teleporterTile();}, main.teleporter).build(null).setRegistryName(main.teleporter.getRegistryName());
    public teleporterTile() {
        super(type);
    }
}
