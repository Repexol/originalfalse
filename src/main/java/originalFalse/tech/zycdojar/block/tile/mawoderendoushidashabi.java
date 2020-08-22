package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.tech.zycdojar.main;

public class mawoderendoushidashabi extends TileEntity {
    public static TileEntityType<mawoderendoushidashabi> type=
            (TileEntityType<mawoderendoushidashabi>) TileEntityType.Builder.create(()->{return new mawoderendoushidashabi();}, main.wuzhongshenyouyishi).build(null).setRegistryName(main.wuzhongshenyouyishi.getRegistryName());

    public mawoderendoushidashabi() {
        super(type);
    }

}
