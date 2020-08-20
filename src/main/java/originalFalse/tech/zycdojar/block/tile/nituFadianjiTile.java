package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.superTool.zycdojar.item.tile.AnvilTile;
import originalFalse.tech.zycdojar.main;

public class nituFadianjiTile extends TileEntity {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new nituFadianjiTile();}, main.nitunengfadianji).build(null).setRegistryName(main.nitunengfadianji.getRegistryName());
    public nituFadianjiTile() {
        super(type);
    }
}
