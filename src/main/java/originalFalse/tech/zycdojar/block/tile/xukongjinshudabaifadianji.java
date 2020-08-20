package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.main;

public class xukongjinshudabaifadianji extends TileEntity implements ITickableTileEntity {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new xukongjinshudabaifadianji();}, main.xukongjinshudabaifadianji).build(null).setRegistryName(main.xukongjinshudabaifadianji.getRegistryName());
    public xukongjinshudabaifadianji() {
        super(type);
    }
    private int tickd=0;
    @Override
    public void tick() {
        CompoundNBT nbt=getTileData();
        if(!nbt.getString("player").equals("")){
            if(tickd==50){
                NESystem.grantNE(nbt.getString("player"),2);
                tickd=0;
            }else {
                tickd++;
            }
        }
    }
}
